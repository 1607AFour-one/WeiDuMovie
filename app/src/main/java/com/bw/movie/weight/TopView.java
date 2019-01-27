package com.bw.movie.weight;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;

public class TopView extends RelativeLayout {
    private ImageView Edit_Image;
    private EditText search_edittext;
    private TextView Edit_Text;
    private RelativeLayout Ll;
    private Context context1;
    private boolean isShow = true;
    public TopView(Context context) {
        super(context);
    }

    public TopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        context1 = context;
        LayoutInflater.from(context).inflate(R.layout.seek_view, this);

        search_edittext = (EditText) findViewById(R.id.search_edittext);
        Edit_Image = (ImageView) findViewById(R.id.Edit_Image);
        Ll = (RelativeLayout) findViewById(R.id.Ll);
        Edit_Text = (TextView) findViewById(R.id.Edit_Text);

        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        final int width = dm.widthPixels;
        int height = dm.heightPixels;


        Edit_Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShow){
                    float translationX = Ll.getTranslationY();
                    ObjectAnimator translation = ObjectAnimator.ofFloat(Ll, "translationX", 0, -width*3/4,-width*2/4);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.play(translation);
                    animatorSet.setDuration(1000);
                    animatorSet.start();
                    isShow = false;
                }else {
                    ObjectAnimator translation = ObjectAnimator.ofFloat(Ll, "translationX",-width*2/4 , 0,0);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.play(translation);
                    animatorSet.setDuration(1000);
                    animatorSet.start();
                    isShow=true;
                }

            }
        });


        Edit_Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float translationX = Ll.getTranslationY();
                ObjectAnimator translation = ObjectAnimator.ofFloat(Ll, "translationX",-width*2/4 , 0,0);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(translation);
                animatorSet.setDuration(1000);
                animatorSet.start();
                //Toast.makeText(context1,search_edittext.getText().toString() , Toast.LENGTH_SHORT).show();
                if(topViewListener!=null){
                    topViewListener.getEdStr(search_edittext.getText().toString());
                }
                isShow=true;
            }

        });







    }
    TopViewListener topViewListener;
    public interface TopViewListener{
        void getEdStr(String str);
    }
    public void getTopText(TopViewListener topViewListener){
        this.topViewListener=topViewListener;
    }



}
