package com.bw.movie.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.view.IView;

public class TicketRecordActivity extends BaseActivity implements IView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_record);
    }

    @Override
    protected int initLayout() {
        return 0;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setClicks() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void setProgress() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void successData(Object data) {

    }

    @Override
    public void errorMsg(Object error) {

    }
}
