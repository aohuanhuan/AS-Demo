package ahh.basic.com.as_demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ahh.basic.com.as_demo.R;

/**
 * Created by Administrator on 2017/7/28.
 */
public class MyGridViewActivity extends AppCompatActivity implements GridView.OnItemClickListener, GridView.OnItemLongClickListener
{
    private int[] pics = {R.mipmap.bluetooth, R.mipmap.camera, R.mipmap.dog, R.mipmap.map,
            R.mipmap.photo, R.mipmap.ps, R.mipmap.talk, R.mipmap.calc, R.mipmap.calendar,
            R.mipmap.car, R.mipmap.clock, R.mipmap.reader};
    private String[] picTexts = new String[]{"bluetooth", "camera", "dog", "map", "photo", "ps", "talk", "calc",
            "calendar", "car", "clock", "reader"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);

        GridView gridView = (GridView) findViewById(R.id.gridView);

        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < pics.length; i++)
        {
            Map<String, Object> map = new HashMap<>();

            map.put("gridPic", pics[i]);
            map.put("gridText", picTexts[i]);
            dataList.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, dataList, R.layout.griditem, new String[]{
                "gridPic", "gridText"}, new int[]{R.id.gridPic, R.id.gridText});
        gridView.setAdapter(simpleAdapter);

        gridView.setOnItemClickListener(this);
        gridView.setOnItemLongClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Toast.makeText(this, "当前是短点击事件，且您点击的是: " + picTexts[position], Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
    {
        Toast.makeText(this, "当前是长点击事件，且您点击的是: " + picTexts[position], Toast.LENGTH_SHORT).show();
        return true;
    }
}