package com.fruitbazaar_android2.cart.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fruitbazaar_android2.R;
import com.fruitbazaar_android2.adapter.FruitAdapter;
import com.fruitbazaar_android2.base.BaseFragment;
import com.fruitbazaar_android2.model.Fruit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CartFragment extends BaseFragment {
    private TextView textView;
    private ListView listView;
    private List<Fruit> cartList = new ArrayList<>();
    private FruitAdapter adapter;
    private CheckBox checkboxSelectAll;
    private TextView tvTotalPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }


    @Override
    public View initView() {
        Log.e("CartFragment", "购物车的Fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listViewCart);
        checkboxSelectAll = view.findViewById(R.id.checkboxSelectAll);
        tvTotalPrice = view.findViewById(R.id.tvTotalPrice);


        adapter = new FruitAdapter(getContext(), R.layout.cart_item, cartList, true);
        listView.setAdapter(adapter);

        checkboxSelectAll.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // 打印复选框视图
            Log.d("Checkbox", "ButtonView: " + buttonView);
            // 打印选中状态
            Log.d("Checkbox", "isChecked: " + isChecked);
            for (Fruit fruit : cartList) {
                Log.d("Checkbox", "Fruit at index " + ": " + fruit + ", Selected: " + fruit.getSelected());
                fruit.setSelected(isChecked);
            }
            adapter.notifyDataSetChanged();
            calculateTotalPrice();
        });

        Button btnPlaceOrder = view.findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(v -> {
            // 执行下单操作，发起网络请求
            placeOrder();
        });

    }

    // 添加水果到购物车的方法
    public void addFruit(Fruit fruit) {
        boolean found = false;
        for (Fruit f : cartList) {
            if (f.getId() == fruit.getId()) {
                f.setQuantity(f.getQuantity() + 1); // 如果已存在，则数量加一
                found = true;
                break;
            }
        }
        if (!found) {
            fruit.setQuantity(1); // 否则设置数量为1，并添加到购物车列表
            cartList.add(fruit);
        }
        adapter.notifyDataSetChanged(); // 更新适配器数据
    }
    public void refreshListView() {
        adapter.notifyDataSetChanged(); // 通知数据有变化
    }

    // 计算购物车总价的方法
    private void calculateTotalPrice() {
        double totalPrice = 0.0;
        Log.e("CartFragment", "Cart List:");
        for (Fruit fruit : cartList) {
            Log.e("CartFragment", "Fruit: " + fruit.getName() + ", Selected: " + fruit.getSelected() + ", Quantity: " + fruit.getQuantity());
            if (fruit.getSelected()) { // 如果水果项被选中，则计算价格
                totalPrice += fruit.getNewPrice() * fruit.getQuantity();
            }
        }
        tvTotalPrice.setText("总价: " + totalPrice + "元"); // 更新总价显示
    }

    private void placeOrder() {
        OkHttpClient client = new OkHttpClient();

        // 构建购物车数据 JSON
        JSONArray jsonArray = new JSONArray();
        for (Fruit fruit : cartList) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("fruitId", fruit.getId());
                jsonObject.put("userId", 1);
                jsonObject.put("num", fruit.getQuantity());
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // 构建请求体
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"), jsonArray.toString());

        // 创建请求
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8080/order/add")
                .post(requestBody)
                .build();

        // 发送请求并处理响应
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    String responseBody = response.body().string();
//                    Log.d("CartFragment", "Response: " + responseBody);
//                } else {
//                    Log.e("CartFragment", "Error: " + response.code() + " " + response.message());
//                }

                String responseBody = response.body().string();
                Log.d("CartFragment", "Response: " + responseBody);

                // 订单成功下单的处理逻辑
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 在UI线程中更新UI或者显示成功信息
                        Toast.makeText(getContext(), "订单下单成功！", Toast.LENGTH_SHORT).show();

                        // 清空购物车或者其他操作
                        cartList.clear();
                        adapter.notifyDataSetChanged();
                        tvTotalPrice.setText("总价: 0元");
                    }
                });
            }
        });
    }



}

