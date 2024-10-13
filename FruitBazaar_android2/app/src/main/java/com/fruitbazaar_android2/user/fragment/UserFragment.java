package com.fruitbazaar_android2.user.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fruitbazaar_android2.base.BaseFragment;
import com.fruitbazaar_android2.R;

public class UserFragment extends BaseFragment {
    private TextView textView;
    private TextView tvUsername;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        tvUsername = view.findViewById(R.id.tvUsername);

        return view;
    }

    @Override
    public View initView() {
        Log.e("UserFragment", "个人中心页面的Fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public void initData() {
        // 确保 tvUsername 不为空才进行操作
        if (tvUsername != null) {

            // 从 SharedPreferences 获取用户名并设置
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
            String name = sharedPreferences.getString("name", "未登录");
            Log.d("UserFragment", "======================sharedPreferences: " + sharedPreferences);
            tvUsername.setText(name);
        }
    }



}
