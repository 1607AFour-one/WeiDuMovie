package com.bw.movie.weight;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;

public class ErrorView extends LinearLayout {
    private final TextView error_text;
    private final Context context;
    public ErrorView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.error_item, this);
        error_text = findViewById(R.id.Error_Text);
        error_text.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"检查网络",Toast.LENGTH_SHORT).show();
                Intent intent =  new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                context.startActivity(intent);
            }
        });

    }

   /* private final TextView error_text;

    public ErrorView(final Context context) {
        super(context);
//        LayoutInflater.from(context).inflate(R.layout.error_item, this);
       View view=View.inflate(context,R.layout.error_item, this);
        error_text = view.findViewById(R.id.Error_Text);
        error_text.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"检查网络",Toast.LENGTH_SHORT).show();
                Intent intent =  new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                context.startActivity(intent);
            }
        });


    }*/

}
