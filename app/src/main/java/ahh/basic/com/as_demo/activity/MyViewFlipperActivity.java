package ahh.basic.com.as_demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import ahh.basic.com.as_demo.R;

/**
 * Created by Administrator on 2017/8/2.
 */
public class MyViewFlipperActivity extends AppCompatActivity
{
    private int[] resIds = new int[]{R.mipmap.bluetooth, R.mipmap.camera, R.mipmap.dog, R.mipmap.map,
            R.mipmap.photo, R.mipmap.ps, R.mipmap.talk, R.mipmap.calc, R.mipmap.calendar,
            R.mipmap.car, R.mipmap.clock, R.mipmap.reader, R.mipmap.video, R.mipmap.yahoo};

    private float startX;

    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.viewflipper);

        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        for (int i = 0; i < resIds.length; i++)
        {
            ImageView imgView = getImgView(resIds[i]);
            viewFlipper.addView(imgView);
        }

        viewFlipper.setInAnimation(this, R.anim.left_in);
        viewFlipper.setOutAnimation(this, R.anim.left_out);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.startFlipping();

    }

    public ImageView getImgView(int resId)
    {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(resId);

        return imageView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                //向右滑动
                if (event.getX() - startX > 500)
                {
                    viewFlipper.setInAnimation(this, R.anim.left_in);
                    viewFlipper.setOutAnimation(this, R.anim.left_out);
                    viewFlipper.showPrevious();
                }
                //向左滑动
                if (startX - event.getX() > 500)
                {
                    viewFlipper.setInAnimation(this, R.anim.right_in);
                    viewFlipper.setOutAnimation(this, R.anim.right_out);
                    viewFlipper.showNext();
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }


        return super.onTouchEvent(event);
    }
}
