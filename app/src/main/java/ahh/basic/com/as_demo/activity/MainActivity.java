package ahh.basic.com.as_demo.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ahh.basic.com.as_demo.R;
import ahh.basic.com.as_demo.constant.Const;

public class MainActivity extends AppCompatActivity
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
        setContentView(R.layout.activity_main);

        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        MultiAutoCompleteTextView multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView);

        String[] arrString = new String[]{"aohuanhuan", "ahh", "wushanghong", "wsh", "huawei", "hw", "shanghai", "beijing"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1, arrString);

        autoCompleteTextView.setAdapter(arrayAdapter);
        multiAutoCompleteTextView.setAdapter(arrayAdapter);
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        CheckBox checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        CheckBox checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        CheckBox checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        CheckBox checkBox5 = (CheckBox) findViewById(R.id.checkBox5);

        TextView textViewCheckBox = (TextView) findViewById(R.id.textView3);

        CheckBox[] checkBoxes = new CheckBox[]{checkBox1, checkBox2, checkBox3, checkBox4, checkBox5};
        MyCheckBoxOnCheckedListener myCheckBoxOnCheckedListener = new MyCheckBoxOnCheckedListener(textViewCheckBox);
        for (CheckBox checkBox : checkBoxes)
        {
            checkBox.setOnCheckedChangeListener(myCheckBoxOnCheckedListener);
        }

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                imageView.setBackgroundResource(isChecked ? R.mipmap.on : R.mipmap.off);
            }
        });

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        final TextView textViewRadioButton = (TextView) findViewById(R.id.textView4);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton radioButton = null;
                switch (checkedId)
                {
                    case R.id.radio1:
                        radioButton = (RadioButton) findViewById(R.id.radio1);
                        break;
                    case R.id.radio2:
                        radioButton = (RadioButton) findViewById(R.id.radio2);
                        break;
                    case R.id.radio3:
                        radioButton = (RadioButton) findViewById(R.id.radio3);
                        break;
                    default:
                        break;
                }

                if (null != radioButton)
                {
                    textViewRadioButton.setText(getResources().getString(R.string.department) + radioButton.getText().toString());
                    Toast.makeText(MainActivity.this, "您刚刚选择了: " + radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        final TextView textViewProgress = (TextView) findViewById(R.id.textView5);
        //handler用于更视图
        Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                if (Const.MESSAGE_CODE == msg.what)
                {
                    String lastText = textViewProgress.getText().toString();
                    lastText = lastText.substring(0, lastText.length() - 1);

                    int progress = Integer.parseInt(lastText);
                    progress++;
                    textViewProgress.setText(progress + "%");
                    //当进度到达100，就不再更新进度视图了
                    if (progress < 100)
                    {
                        sendEmptyMessageDelayed(Const.MESSAGE_CODE, 1000);
                    }
                }
            }
        };
        handler.sendEmptyMessageDelayed(Const.MESSAGE_CODE, 1000);

        Button goToCalcPage = (Button) findViewById(R.id.goToCalcPage);
        goToCalcPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, CalcAvtivity.class);
                startActivity(intent);
            }
        });

        Button goToListViewPage = (Button) findViewById(R.id.goToListViewPage);
        goToListViewPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, MyListViewActivity.class);
                startActivity(intent);
            }
        });

        Button goToGridViewPage = (Button) findViewById(R.id.goToGridViewPage);
        goToGridViewPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, MyGridViewActivity.class);
                startActivity(intent);
            }
        });

        final TextView spinnerText = (TextView) findViewById(R.id.spinnerText);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < pics.length; i++)
        {
            Map<String, Object> map = new HashMap<>();
            map.put("pic", pics[i]);
            map.put("picText", picTexts[i]);

            dataList.add(map);
        }
        final SimpleAdapter simpleAdapter = new SimpleAdapter(this, dataList, R.layout.listitem, new String[]{"pic", "picText"},
                new int[]{R.id.pic, R.id.textPic});
        simpleAdapter.setDropDownViewResource(R.layout.listitem);
        spinner.setAdapter(simpleAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Map<String, Object> map = (HashMap<String, Object>) simpleAdapter.getItem(position);

                String picText = (String) map.get("picText");
                spinnerText.setText(getResources().getString(R.string.spinnerText) + picText);
                //Toast.makeText(MainActivity.this, getResources().getString(R.string.spinnerText) + picText, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        Button goToProgressDialog = (Button) findViewById(R.id.goToProgressDialog);

        final ProgressDialog progressDialog = getProgressDialog();

        //handlerProgressDialog用于更视图
        Handler handlerProgressDialog = new Handler()
        {
            @Override
            public void handleMessage(Message msg)
            {
                if (Const.MESSAGE_PROGRESS_DIALOG == msg.what)
                {
                    //当进度到达100，就不再更新进度视图了
                    int currProgress = progressDialog.getProgress();
                    if (currProgress < 100)
                    {
                        sendEmptyMessageDelayed(Const.MESSAGE_PROGRESS_DIALOG, 1000);
                        progressDialog.setProgress(currProgress + 1);
                    }
                }
            }
        };
        handlerProgressDialog.sendEmptyMessageDelayed(Const.MESSAGE_PROGRESS_DIALOG, 1000);

        goToProgressDialog.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                progressDialog.show();
            }
        });

        //WebView
        Button goToWebViewPage = (Button) findViewById(R.id.goToWebViewPage);
        goToWebViewPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, MyWebViewActivity.class);
                startActivity(intent);
            }
        });

        Button goToFragment = (Button) findViewById(R.id.goToFragment);
        goToFragment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, MyFragmentActivity.class);
                startActivity(intent);
            }
        });

        Button goToViewPager = (Button) findViewById(R.id.goToViewPager);
        goToViewPager.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, MyViewPagerActivity.class);
                startActivity(intent);
            }
        });


        Button goToViewFlipperPage = (Button) findViewById(R.id.goToViewFlipperPage);
        goToViewFlipperPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, MyViewFlipperActivity.class);
                startActivity(intent);
            }
        });

        Button goToScrollViewPage = (Button) findViewById(R.id.goToScrollViewPage);
        goToScrollViewPage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, MySrcollViewActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 创建一个ProgressDialog
     *
     * @return
     */
    private ProgressDialog getProgressDialog()
    {
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        progressDialog.setIcon(R.mipmap.yahoo);
        progressDialog.setTitle("下载App");
        progressDialog.setMessage("当前的下载进度");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        progressDialog.setIndeterminate(false);
        progressDialog.incrementProgressBy(0);
        progressDialog.setMax(100);

        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(MainActivity.this, "当前下载进度为:" + progressDialog.getProgress() / (float) progressDialog.getMax() * 100 + "%",
                        Toast.LENGTH_SHORT).show();
            }
        });
        progressDialog.setCancelable(true);
        return progressDialog;
    }

    /**
     * CheckBox的监听类
     */
    class MyCheckBoxOnCheckedListener implements CompoundButton.OnCheckedChangeListener
    {
        private HashMap<String, Boolean> map = new HashMap<>();
        private TextView textView;

        public MyCheckBoxOnCheckedListener(TextView textView)
        {
            this.textView = textView;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            String text = buttonView.getText().toString();
            map.put(text, isChecked);

            if (null != textView)
            {
                String init = getResources().getString(R.string.interesting);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(init);

                for (Map.Entry<String, Boolean> entry : map.entrySet())
                {
                    if (entry.getValue())
                    {
                        stringBuilder.append(entry.getKey()).append(", ");
                    }
                }
                String res = stringBuilder.toString();
                if (map.containsValue(true))
                {
                    res = res.substring(0, res.length() - 2);
                }
                textView.setText(res);
            }

            if (isChecked)
            {
                Toast.makeText(MainActivity.this, "您刚刚选择了: " + text, Toast.LENGTH_LONG).show();
            }
        }
    }
}