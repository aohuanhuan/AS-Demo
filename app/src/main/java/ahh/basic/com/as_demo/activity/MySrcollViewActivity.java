package ahh.basic.com.as_demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import ahh.basic.com.as_demo.R;

/**
 * Created by Administrator on 2017/8/3.
 */
public class MySrcollViewActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.scrollview);

        final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.setVerticalScrollBarEnabled(false);
        final TextView textView = (TextView) findViewById(R.id.scrollViewText);
        textView.setText(getResources().getString(R.string.content));

        scrollView.setOnTouchListener(new ScrollView.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_MOVE:

                        //判断滑到底部
                        if (scrollView.getScrollY() <= 0)
                        {
                            Toast.makeText(MySrcollViewActivity.this, "当前滑动到屏幕顶部", Toast.LENGTH_SHORT).show();
                        }

                        //滑动到屏幕底部
                        if (scrollView.getChildAt(0).getMeasuredHeight() <= scrollView.getHeight() + scrollView.getScrollY())
                        {
                            Toast.makeText(MySrcollViewActivity.this, "当前滑动到屏幕底部", Toast.LENGTH_SHORT).show();
                            textView.append(getResources().getString(R.string.content));
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}
