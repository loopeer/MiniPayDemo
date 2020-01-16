package com.loopeer.android.apps.masterplus4android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.loopeer.android.apps.masterplus4android.R;
import com.loopeer.android.apps.masterplus4android.ui.base.BaseActivity;

//TODO demo test activity
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("");
    }

    public void onPayClick(View view) {
        startActivity(new Intent(this, PayActivity.class));
    }

}
