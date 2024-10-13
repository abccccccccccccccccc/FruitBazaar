package com.fruitbazaar_android2.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//定义 BaseFragment 类，并声明它是一个抽象类
public abstract class BaseFragment extends Fragment {
    //定义一个受保护的成员变量 mContext，用于存储上下文（Context），用于访问应用程序的资源和启动其他组件。
    protected Context mContext;

    /**
     * 当该类被系统创建的时候；被回调
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }


    //具体的视图初始化需要在继承 BaseFragment 的子类中实现
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 抽象类，由孩子实现，实现不同的效果
     * @return
     */
    public abstract View initView() ;

    /**
     * 当Activity被创建了的时候回调这个方法
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当子类需要联网请求数据的时候，可以重写该方法，在该方法中联网请求
     */
    public void initData() {

    }
}
