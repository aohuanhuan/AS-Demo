package ahh.basic.com.as_demo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ahh.basic.com.as_demo.R;

/**
 * Created by Administrator on 2017/7/31.
 */
public class MyCalcFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.calc, container, false);

        return view;
    }
}
