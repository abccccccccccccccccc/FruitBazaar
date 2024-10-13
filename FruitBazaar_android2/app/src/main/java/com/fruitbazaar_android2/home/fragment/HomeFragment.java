package com.fruitbazaar_android2.home.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.fruitbazaar_android2.MainActivity;
import com.fruitbazaar_android2.R;
import com.fruitbazaar_android2.adapter.FruitAdapter;
import com.fruitbazaar_android2.base.BaseFragment;
import com.fruitbazaar_android2.cart.fragment.CartFragment;
import com.fruitbazaar_android2.model.Fruit;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class HomeFragment extends BaseFragment {
    private TextView textView;
    private ListView listView;
    private List<Fruit> fruitList = new ArrayList<>();
    private CartFragment cartFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listView);
        fetchFruitData();

        FragmentActivity activity = getActivity();
        if (activity instanceof MainActivity) {
            cartFragment = ((MainActivity) activity).getCartFragment();
        }
    }

    @Override
    public View initView() {
        Log.e("HomeFragment", "主页面的Fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("HomeFragment", "主页面的Fragment的数据被初始化了");
        fetchFruitData();
    }

    private void fetchFruitData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/fruit/list")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        parseJson(jsonObject); // 解析服务器返回的JSON数据
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "网络请求失败: " + response.message());
                }

            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("HomeFragment", "网络请求失败: " + e.getMessage());
            }
        });
    }

    private void parseJson(JSONObject jsonObject) {
        try {
            int code = jsonObject.getInt("code");
            if (code == 1) {
                JSONArray dataArray = jsonObject.getJSONArray("data");
                fruitList.clear();
                // 遍历数组，处理每个水果条目
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject fruitObject = dataArray.getJSONObject(i);
                    Fruit fruit = new Fruit();
                    fruit.setId(fruitObject.getInt("id"));
                    fruit.setName(fruitObject.getString("name"));
                    fruit.setOldPrice(fruitObject.getDouble("oldPrice"));
                    fruit.setNewPrice(fruitObject.getDouble("newPrice"));
                    fruit.setOrigin(fruitObject.getString("origin"));
                    fruit.setDesctext(fruitObject.getString("desctext"));
                    fruit.setImg(fruitObject.getString("img"));
                    fruit.setSelected(false);

                    fruitList.add(fruit); // 添加到列表
                }
                updateListView(); // 更新列表视图
            } else {
                String errorMsg = jsonObject.getString("msg");
                Log.e(TAG, "服务器返回错误：" + errorMsg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    private void updateListView() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                FruitAdapter adapter = new FruitAdapter(mContext, R.layout.fruit_item, fruitList,false);
                listView.setAdapter(adapter);
            }
        });
    }
}
