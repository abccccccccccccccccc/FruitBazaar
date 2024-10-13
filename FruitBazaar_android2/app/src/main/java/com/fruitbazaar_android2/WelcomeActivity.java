package com.fruitbazaar_android2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.fruitbazaar_android2.R;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView welcomeText = findViewById(R.id.tv_welcome);

        // 设置淡入动画
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setDuration(2000); // 动画持续时间
        welcomeText.startAnimation(fadeIn);

        // 延迟跳转到主页面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 3000); // 停留时间
    }
}
