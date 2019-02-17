package com.bw.movie.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bw.movie.R;
import com.bw.movie.app.MyApp;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.ui.AttentionActivity;
import com.bw.movie.ui.MainActivity;
import com.bw.movie.ui.MyInfoActivity;
import com.bw.movie.ui.TicketRecordActivity;
import com.bw.movie.utils.SpUtils;
import com.bw.movie.view.IView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends BaseFragment implements IView ,View.OnClickListener {

    private LinearLayout myInfo;
    private LinearLayout myGanzhu;
    private ImageView head_image;
    private TextView nickName;

    @Override
    protected int setLayoutId() {

        return R.layout.fragment_my;

    }

    @Override
    protected void init(View view, Bundle savedInstanceState) {
        myGanzhu = view.findViewById(R.id.my_guanzhu);
        myInfo = view.findViewById(R.id.my_info);
        head_image = view.findViewById(R.id.my_header_image);
        nickName = view.findViewById(R.id.my_nickname_text);
        myGanzhu.setOnClickListener(this);
        myInfo.setOnClickListener(this);
        head_image.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        String name = SpUtils.getPreferneces().getString("nickName","");
        nickName.setText(name+"");

        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(getActivity()).load(SpUtils.getString("headPic")).apply(requestOptions).into(head_image);
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
            case R.id.my_guanzhu:
                startActivity(new Intent(getContext(),AttentionActivity.class));
                break;
            case R.id.my_info:
                startActivity(new Intent(getActivity(),MyInfoActivity.class));
                break;
            case R.id.my_header_image:
               // View view=View.inflate()

                MyApp.getInstance().exitAllActivity();
                startActivity(new Intent(getActivity(),MainActivity.class));
                SpUtils.removeAll();
                break;

        }
    }
}
