package com.fruitbazaar_android2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends Activity {
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private OkHttpClient client;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        client = new OkHttpClient();

        btnLogin.setOnClickListener(v -> login());
    }

    private void login() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            JSONObject json = new JSONObject();
            json.put("username", username);
            json.put("password", password);

            RequestBody body = RequestBody.create(json.toString(), MediaType.get("application/json; charset=utf-8"));
            Request request = new Request.Builder().url("http://10.0.2.2:8080/login").post(body).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(() -> Toast.makeText(LoginActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response.body().string());
                            handleLoginResponse(jsonResponse, username);
                        } catch (JSONException e) {
                            runOnUiThread(() -> Toast.makeText(LoginActivity.this, "解析响应失败", Toast.LENGTH_SHORT).show());
                        }
                    } else {
                        runOnUiThread(() -> Toast.makeText(LoginActivity.this, "登录失败，请检查用户名或密码", Toast.LENGTH_SHORT).show());
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void handleLoginResponse(JSONObject jsonResponse, String username) throws JSONException {
        int code = jsonResponse.getInt("code");
        if (code == 1) {
            JSONObject dataObject = jsonResponse.optJSONObject("data");
            Log.e(TAG, "======================jsonResponse：" + jsonResponse);
            Log.d(TAG, "======================dataObject: " + dataObject);
            if (dataObject != null) {
                String name = dataObject.getString("name");
                saveUsername(name);
                navigateToWelcome();
            } else {
                runOnUiThread(() -> Toast.makeText(LoginActivity.this, "登录失败，返回数据为空", Toast.LENGTH_SHORT).show());
            }
        } else {
            String errorMsg = jsonResponse.getString("msg");
            Log.e(TAG, "服务器返回错误：" + errorMsg);
            runOnUiThread(() -> Toast.makeText(LoginActivity.this, "登录失败，请检查用户名或密码", Toast.LENGTH_SHORT).show());
        }
    }


    private void saveUsername(String name) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.apply();

        Log.d(TAG, "======================editor: " + editor);
    }

    private void navigateToWelcome() {
        runOnUiThread(() -> {
            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
