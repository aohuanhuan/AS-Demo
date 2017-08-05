package ahh.basic.com.as_demo.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ahh.basic.com.as_demo.R;

/**
 * Created by Administrator on 2017/7/31.
 */
public class MyGridViewFragment extends Fragment implements GridView.OnItemClickListener, GridView.OnItemLongClickListener
{
    private Context context;

    private int[] pics = {R.mipmap.bluetooth, R.mipmap.camera, R.mipmap.dog, R.mipmap.map,
            R.mipmap.photo, R.mipmap.ps, R.mipmap.talk, R.mipmap.calc, R.mipmap.calendar,
            R.mipmap.car, R.mipmap.clock, R.mipmap.reader};
    private String[] picTexts = new String[]{"bluetooth", "camera", "dog", "map", "photo", "ps", "talk", "calc",
            "calendar", "car", "clock", "reader"};

    public void setContext(Context context)
    {
        this.context = context;
    }

    public Context getContext()
    {
        return context;
    }

    public interface MyFragmentListener
    {
        void sendMsg(String mseesge);
    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);

        MyFragmentListener myFragmentListener = (MyFragmentListener) activity;
        myFragmentListener.sendMsg("【第二回合Fragment send】: Hello Activity, Thank you very much for letter me!");
        Toast.makeText(activity, "【第二回合Fragment send】: Hello Activity, Thank you very much for letter me!", Toast.LENGTH_SHORT).show();
        Log.i("message", "【第二回合Fragment send】: Hello Activity, Thank you very much for letter me!");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.gridview, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.gridView);

        List<Map<String, Object>> dataList = new ArrayList<>();

        for (int i = 0; i < pics.length; i++)
        {
            Map<String, Object> map = new HashMap<>();

            map.put("pic", pics[i]);
            map.put("text", picTexts[i]);
            dataList.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this.context, dataList, R.layout.griditem,
                new String[]{"pic", "text"}, new int[]{R.id.gridPic, R.id.gridText});
        gridView.setAdapter(simpleAdapter);
        gridView.setOnItemClickListener(this);
        gridView.setOnItemLongClickListener(this);

        //获取Activity中发送过来的数据
        Bundle arguments = getArguments();
        String message = (String) arguments.get("message");

        Toast.makeText(context, "【第一回合Framgent get】: Hello Activity, I'm Fragment, i get your message:( " + message + " ).",
                Toast.LENGTH_SHORT).show();
        Log.i("message", "【第一回合Framgent get】: Hello Activity, I'm Fragment, i get your message:( " + message + " ).");

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Toast.makeText(this.context, "当前是短点击事件，且您点击的是: " + picTexts[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
    {
        Toast.makeText(this.context, "当前是长点击事件，且您点击的是: " + picTexts[position], Toast.LENGTH_SHORT).show();
        return true;
    }

}