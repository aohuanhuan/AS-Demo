package ahh.basic.com.as_demo.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
public class MyListViewFragment extends Fragment implements ListView.OnItemClickListener, ListView.OnItemLongClickListener
{
    private Context context;

    private int[] pics = {R.mipmap.bluetooth, R.mipmap.camera, R.mipmap.dog, R.mipmap.map,
            R.mipmap.photo, R.mipmap.ps, R.mipmap.talk, R.mipmap.calc, R.mipmap.calendar,
            R.mipmap.car, R.mipmap.clock, R.mipmap.reader, R.mipmap.video, R.mipmap.yahoo};
    private String[] picTexts = new String[]{"bluetooth", "camera", "dog", "map", "photo", "ps", "talk", "calc",
            "calendar", "car", "clock", "reader", "video", "yahoo"};

    public void setContext(Context context)
    {
        this.context = context;
    }

    public Context getContext()
    {
        return context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.listview, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listView);

        List<Map<String, Object>> dataList = new ArrayList<>();

        for (int i = 0; i < pics.length; i++)
        {
            Map<String, Object> map = new HashMap<>();

            map.put("pic", pics[i]);
            map.put("text", picTexts[i]);
            dataList.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this.context, dataList, R.layout.listitem,
                new String[]{"pic", "text"}, new int[]{R.id.pic, R.id.textPic});
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Toast.makeText(this.context, "当前选择的是: " + picTexts[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
    {
        Toast.makeText(this.context, "当前是长时间的点击事件，并且您选择的是: " + picTexts[position], Toast.LENGTH_SHORT).show();
        return true;
    }

}