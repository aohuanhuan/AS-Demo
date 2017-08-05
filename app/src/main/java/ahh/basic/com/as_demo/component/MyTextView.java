package ahh.basic.com.as_demo.component;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/7/18.
 */
public class MyTextView extends TextView
{
    public MyTextView(Context context)
    {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean isFocused()
    {
        return true;
    }
}
