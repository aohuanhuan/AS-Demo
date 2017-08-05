package ahh.basic.com.as_demo.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Toast;

import ahh.basic.com.as_demo.R;
import ahh.basic.com.as_demo.fragment.MyCalcFragment;
import ahh.basic.com.as_demo.fragment.MyGridViewFragment;
import ahh.basic.com.as_demo.fragment.MyListViewFragment;
import ahh.basic.com.as_demo.fragment.MyWebViewFragment;

/**
 * Created by Administrator on 2017/7/31.
 */
public class MyFragmentActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, MyGridViewFragment.MyFragmentListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MyListViewFragment myFragment = new MyListViewFragment();
        myFragment.setContext(MyFragmentActivity.this);

        fragmentTransaction.replace(R.id.addFragment, myFragment);
        fragmentTransaction.commit();

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.fragmentRadioGroup);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId)
    {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (checkedId)
        {
            case R.id.fragmentRadioButton1:
                MyListViewFragment myFragment = new MyListViewFragment();
                myFragment.setContext(MyFragmentActivity.this);

                fragmentTransaction.replace(R.id.addFragment, myFragment);
                break;
            case R.id.fragmentRadioButton2:
                MyGridViewFragment myGridViewFragment = new MyGridViewFragment();

                //Activity往Fragement中发送数据
                Bundle bundle = new Bundle();
                bundle.putString("message", "Hello Fragment, I'm Activity!");
                Toast.makeText(MyFragmentActivity.this, "【第一回合Activity send】: Hello Fragment, I'm Activity!", Toast.LENGTH_SHORT).show();
                Log.i("message", "【第一回合Activity send】: Hello Fragment, I'm Activity!");
                myGridViewFragment.setArguments(bundle);

                myGridViewFragment.setContext(MyFragmentActivity.this);

                fragmentTransaction.replace(R.id.addFragment, myGridViewFragment);
                break;
            case R.id.fragmentRadioButton3:
                MyCalcFragment myCalcFragment = new MyCalcFragment();
                fragmentTransaction.replace(R.id.addFragment, myCalcFragment);

                break;
            case R.id.fragmentRadioButton4:
                MyWebViewFragment myWebViewFragment = new MyWebViewFragment();
                myWebViewFragment.setContext(MyFragmentActivity.this);
                fragmentTransaction.replace(R.id.addFragment, myWebViewFragment);

                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    public void sendMsg(String mseesge)
    {
        Log.i("message", "【第二回合Activity get】: Hello Fragment, you are welcome! where where." + mseesge);
        Toast.makeText(this, "【第二回合Activity get】: Hello Fragment, you are welcome! where where.", Toast.LENGTH_SHORT).show();
    }
}
