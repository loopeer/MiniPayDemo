package com.loopeer.android.apps.masterplus4android.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.loopeer.android.apps.masterplus4android.R;
import com.loopeer.android.apps.masterplus4android.ui.base.BaseActivity;

public class DetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("订单详情");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
