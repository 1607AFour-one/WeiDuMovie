package com.bw.movie.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.ui.AttentionActivity;
import com.bw.movie.ui.MyInfoActivity;
import com.bw.movie.ui.TicketRecordActivity;
import com.bw.movie.view.IView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseFragment implements IView ,View.OnClickListener {

    private LinearLayout myInfo;
    private LinearLayout myGanzhu;

    @Override
    protected int setLayoutId() {

        return R.layout.fragment_my;

    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        myGanzhu = view.findViewById(R.id.my_guanzhu);
        myInfo = view.findViewById(R.id.my_info);
        myGanzhu.setOnClickListener(this);
        myInfo.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.The_ticket_record:

                startActivity(new Intent(getContext(),TicketRecordActivity.class));
                break;
            case R.id.attention:
                startActivity(new Intent(getContext(),AttentionActivity.class));
                break;
            case R.id.my_info:
                startActivity(new Intent(getActivity(),MyInfoActivity.class));
                break;
        }
    }
}
