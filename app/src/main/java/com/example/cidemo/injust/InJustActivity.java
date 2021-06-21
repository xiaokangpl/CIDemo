package com.example.cidemo.injust;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cidemo.R;

/**
 * @author wenxiaokang
 * @className InJustActivity
 * @description TODO
 * @date 6/21/21 2:22 PM
 */
public class InJustActivity extends AppCompatActivity {

    @BindView(R.id.tv_injust)
    TextView tvInjust;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_injust);
        ViewBind.bind(this);
        tvInjust.setText("已经被注解到了");
    }

    @OnClick({R.id.bt_injust})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_injust:
                Toast.makeText(InJustActivity.this, "已经被注解到了", Toast.LENGTH_SHORT).show();
        }
    }
}
