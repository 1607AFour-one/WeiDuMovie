package com.bw.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.RecordData;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class RecordAdapter extends XRecyclerView.Adapter<RecordAdapter.ViewHolder> {

    private List<RecordData.ResultBean>rList;
    private Context mContext;

    public RecordAdapter(List<RecordData.ResultBean> rList, Context mContext) {
        this.rList = rList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mContext,R.layout.record_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if(rList.get(i).getStatus()==1){
            viewHolder.Record_Name.setText(rList.get(i).getMovieName());
            viewHolder.Record_Dan.setText(rList.get(i).getOrderId()+"");
            viewHolder.Record_Qian.setText(rList.get(i).getPrice()+"");
            SimpleDateFormat format=new SimpleDateFormat("MM-dd hh:mm");
            Date date=new Date(rList.get(i).getCreateTime());
            viewHolder.Record_Time.setText(format.format(date));
            //viewHolder.Record_Kuan.setText(rList.get(i).getStatus()+"");
            viewHolder.Record_Yuan.setText(rList.get(i).getCinemaName());
            viewHolder.Record_Shu.setText(rList.get(i).getAmount()+"张");
            viewHolder.Record_Ting.setText(rList.get(i).getScreeningHall());

        }

    }

    @Override
    public int getItemCount() {
        return rList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Record_Time,Record_Name,Record_Shu,Record_Kuan,Record_Dan,Record_Yuan,Record_Ting,Record_Xiangtime,Record_Qian;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Record_Time=itemView.findViewById(R.id.Record_Time);
            Record_Name=itemView.findViewById(R.id.Record_Name);
            Record_Kuan=itemView.findViewById(R.id.Record_Kuan);
            Record_Dan=itemView.findViewById(R.id.Record_Dan);
            Record_Yuan=itemView.findViewById(R.id.Record_Yuan);
            Record_Ting=itemView.findViewById(R.id.Record_Ting);
            Record_Xiangtime=itemView.findViewById(R.id.Record_Xiangtime);
            Record_Qian=itemView.findViewById(R.id.Record_Qian);
            Record_Shu=itemView.findViewById(R.id.Record_Shu);
        }
    }
}
