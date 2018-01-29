package guo.vertifyanim;

import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    View bgView;
    private RelativeLayout rl;
    TextView tv1,tv2;
    int width2 = 0;
    int width1 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_verify);
        rl = (RelativeLayout) this.findViewById(R.id.rl);

        tv1 = (TextView) this.findViewById(R.id.tv1);
        tv2 = (TextView) this.findViewById(R.id.tv2);

        final Animation transToRight = AnimationUtils.loadAnimation(this, R.anim.toright);
        final Animation transToLeft = AnimationUtils.loadAnimation(this, R.anim.toleft);

        bgView =  this.findViewById(R.id.view);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                bgView.startAnimation(transToLeft);
                toLeft();
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                bgView.startAnimation(transToRight);
                toRight();
            }
        });
    }


    private int dp2px(int dp){
        int density = (int) getResources().getDisplayMetrics().density;
        return  density*dp;
    }

    private void toRight(){
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(bgView.getLayoutParams());
        lp.width = width2;
        bgView.setLayoutParams(lp);

        int v = rl.getWidth()-width2;
        ObjectAnimator anitRight = ObjectAnimator.ofFloat(bgView, "translationX", 0,v);
        anitRight.setDuration(1000);
        anitRight.start();
    }

    private void toLeft(){
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(bgView.getLayoutParams());
        lp.width = width1;
        bgView.setLayoutParams(lp);

        int v = rl.getWidth()-width1;
        ObjectAnimator anitRight = ObjectAnimator.ofFloat(bgView, "translationX", v,0);
        anitRight.setDuration(1000);
        anitRight.start();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        setBGWidth();
    }

    /**
     * 设置黄色背景的宽度
     */
    private void setBGWidth(){
        RelativeLayout.MarginLayoutParams marginLP = (RelativeLayout.MarginLayoutParams) tv1.getLayoutParams();
        /**
         * 获取到tv控件左间距
         */
        int leftMargin = marginLP.leftMargin;

        FrameLayout.LayoutParams bgLP = new FrameLayout.LayoutParams(bgView.getLayoutParams());
        /**
         * 黄色背景宽度  =  左间距 + tv宽度 + 右间距
         */
        width1 = leftMargin + tv1.getWidth() + leftMargin;
        bgLP.width = width1;
        /**
         * 设置宽度参数，三个字的宽度
         */
        width2 = leftMargin + tv2.getWidth() + leftMargin;
        bgView.setLayoutParams(bgLP);
    }


}
