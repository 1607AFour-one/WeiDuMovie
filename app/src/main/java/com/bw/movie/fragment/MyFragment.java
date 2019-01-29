package com.bw.movie.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.ui.AttentionActivity;
import com.bw.movie.ui.TicketRecordActivity;
import com.bw.movie.view.IView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseFragment implements IView {
    private ImageView the_ticket_record,attention;

    @Override
    protected int setLayoutId() {

        return R.layout.fragment_my;

    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {

        the_ticket_record = view.findViewById(R.id.The_ticket_record);
        attention = view.findViewById(R.id.attention);
        the_ticket_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),TicketRecordActivity.class);
                startActivity(intent);
            }
        });

        attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),AttentionActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void fetchData() {


    }

    @Override
    public void successData(Object data) {

    }

    @Override
    public void errorMsg(Object error) {

    }
}
