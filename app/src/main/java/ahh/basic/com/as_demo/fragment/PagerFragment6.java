package ahh.basic.com.as_demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ahh.basic.com.as_demo.R;

/**
 * Created by Administrator on 2017/8/2.
 */
public class PagerFragment6 extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.pager6, container, false);
    }
}