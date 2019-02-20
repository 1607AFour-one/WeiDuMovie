package com.bw.movie.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.RecordAdapter;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.RecordData;
import com.bw.movie.presenter.PresenterImpl;
import com.bw.movie.utils.Contacts;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TicketRecordActivity extends BaseActivity implements IView {

    private ImageView ticket_fanHui;
    private RecyclerView ticket_Recy;
    private List<RecordData.ResultBean>rList=new ArrayList<>();
    private int index=1;
    private int count=10;
    private RecordAdapter recordAdapter;
    private PresenterImpl presenter;
    private HashMap<String, Object> headmap;
    private HashMap<String, Object> map;


    @Override
    protected int initLayout() {
        return R.layout.activity_ticket_record;
    }

    @Override
    protected void initView() {
        ticket_Recy = findViewById(R.id.Ticket_Recy);
        ticket_Recy.setLayoutManager(new LinearLayoutManager(this));
        ticket_fanHui = findViewById(R.id.Ticket_FanHui);
        ticket_fanHui.setOnClickListener(this);
    }
    @Override
    protected void setClicks() {

    }
    @Override
    protected void initListener() {

    }

    @Override
    protected void setProgress() {

        presenter = new PresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        int userId = SpUtils.getInt("userId");
        String sessionId=SpUtils.getString("sessionId");
        headmap.put("userId",userId);
        headmap.put("sessionId",sessionId);
        map.put("page",index+"");
        map.put("count",count+"");
        map.put("status",1+"");
        recordAdapter = new RecordAdapter(rList,getApplicationContext());
        ticket_Recy.setAdapter(recordAdapter);
        presenter.requestGEt(Contacts.RECORD_URL, map, headmap,RecordData.class);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.Ticket_FanHui:
                finish();
                break;
        }
    }

    @Override
    public void successData(Object data) {

        if(data instanceof RecordData){
            RecordData recordData= (RecordData) data;
            rList.addAll(recordData.getResult());
            recordAdapter.notifyDataSetChanged();
//            Toast.makeText(this,rList.toString()+"",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void errorMsg(Object error) {

    }
}
