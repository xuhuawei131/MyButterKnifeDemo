package com.xuhuawei.mybutterknifedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xuhuawei.aptapi.HuaweiKnife;
import com.xuhuawei.annotation.ViewInjector;

public class MainActivity extends AppCompatActivity {

    @ViewInjector(R.id.text_content)
    public TextView text_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HuaweiKnife.inject(this);

        text_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
