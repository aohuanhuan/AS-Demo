package ahh.basic.com.as_demo.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

import ahh.basic.com.as_demo.R;
import ahh.basic.com.as_demo.constant.Const;
import ahh.basic.com.as_demo.util.CalcUtil;
import ahh.basic.com.as_demo.util.OperPrecedence;

/**
 * Created by Administrator on 2017/7/20.
 */
public class CalcAvtivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText editText;

    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;

    private Button btn_add;
    private Button btn_sub;
    private Button btn_multiply;
    private Button btn_devide;

    private Button btn_point;
    private Button btn_clear;
    private Button btn_del;

    private Button btn_devide100;

    private Button btn_equal;

    private boolean isEqualBtnClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc);

        //用于显示计算结果的EditText
        editText = (EditText) findViewById(R.id.calcResult);
        //数字 0-9
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);

        //四则运算的加减乘除
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_devide = (Button) findViewById(R.id.btn_devide);

        //小数点、清除EditText中的所有内容、退格
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_del = (Button) findViewById(R.id.btn_del);

        //%号
        btn_devide100 = (Button) findViewById(R.id.btn_devide100);
        //等于号
        btn_equal = (Button) findViewById(R.id.btn_equal);

        //设置按钮的监听事件
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);

        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_devide.setOnClickListener(this);

        btn_point.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);

        btn_devide100.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if (null == editText)
        {
            return;
        }
        String calcResult = editText.getText().toString();
        String btnText = ((Button) v).getText().toString();
        switch (v.getId())
        {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
                if (isEqualBtnClicked)
                {
                    calcResult = "";
                    isEqualBtnClicked = false;
                }
                calcResult += btnText;
                editText.setText(calcResult);
                break;
            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_multiply:
            case R.id.btn_devide:
                if (chkOper(calcResult))
                {
                    calcResult += btnText;
                    editText.setText(calcResult);
                }
                break;
            case R.id.btn_point:
                if (chkPoint(calcResult))
                {
                    calcResult += btnText;
                    editText.setText(calcResult);
                }
                break;
            case R.id.btn_clear:
                calcResult = "";
                editText.setText(calcResult);
                break;
            case R.id.btn_del:
                //回退一个
                if (calcResult.length() > 0)
                {
                    calcResult = calcResult.substring(0, calcResult.length() - 1);
                    editText.setText(calcResult);
                }
                break;
            case R.id.btn_devide100:
                calcResult = getPreNumber(calcResult);
                editText.setText(calcResult);
                break;
            case R.id.btn_equal:
                isEqualBtnClicked = true;
                //先格式化calcResult，比如1*2*.或者1*2*
                calcResult = getMiddlefix(calcResult);
                if (Const.ERROR.equals(calcResult))
                {
                    calcResult = getResources().getString(R.string.errorInfo);
                } else if ("".equals(calcResult))
                {
                    //空
                } else
                {
                    calcResult = CalcUtil.toPostfixAndCalc(calcResult, readOperFile());
                }
                editText.setText(calcResult);
                break;
            default:
                break;
        }
    }

    /**
     * 检查是否可以再加上四则运算符号
     *
     * @param calcResult
     * @return
     */
    private boolean chkOper(String calcResult)
    {
        if (null == calcResult)
        {
            return false;
        }

        if (!"".equals(calcResult) && !(calcResult.endsWith("＋") || calcResult.endsWith("－") || calcResult.endsWith("×")
                || calcResult.endsWith("÷") || calcResult.endsWith(".")))
        {
            return true;
        }
        return false;
    }

    /**
     * 检查是否可以再加上点号
     *
     * @param calcResult
     * @return
     */
    private boolean chkPoint(String calcResult)
    {
        if (null == calcResult)
        {
            return false;
        }

        if (calcResult.endsWith("＋") || calcResult.endsWith("－") || calcResult.endsWith("×") || calcResult.endsWith("÷"))
        {
            return true;
        }
        String[] datas = calcResult.split("＋|－|×|÷");
        if (!datas[datas.length - 1].contains("."))
        {
            return true;
        }

        return false;
    }

    /**
     * 在%按下之前，得到%前面紧邻的数字，并转化为数字/100结果展示
     *
     * @param calcResult
     */
    private String getPreNumber(String calcResult)
    {
        if (null == calcResult || "".equals(calcResult) || calcResult.endsWith("＋") || calcResult.endsWith("－") || calcResult.endsWith("×") ||
                calcResult.endsWith("÷") || calcResult.endsWith("."))
        {
            return calcResult;
        }

        String[] datas = calcResult.split("＋|－|×|÷");
        String preNumberStr = datas[datas.length - 1];

        String text = calcResult.substring(0, calcResult.length() - preNumberStr.length());

        BigDecimal toDevide = new BigDecimal(preNumberStr);
        BigDecimal forDevide = new BigDecimal(100);
        BigDecimal divide = toDevide.divide(forDevide);

        calcResult = text + divide.toString();

        return calcResult;
    }

    /**
     * 读取四则预算符的优先级文件
     *
     * @return
     */
    public String readOperFile()
    {
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            InputStream inputStream = getResources().openRawResource(R.raw.operator_precedence);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while (null != (line = bufferedReader.readLine()))
            {
                stringBuilder.append(line);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 将输入的表达式转换为中缀表达式的正确形式
     *
     * @param calcResult
     */
    private String getMiddlefix(String calcResult)
    {
        if (null == calcResult || "".equals(calcResult))
        {
            return "";
        }

        if (calcResult.endsWith("＋") || calcResult.endsWith("－") || calcResult.endsWith("×")
                || calcResult.endsWith("÷"))
        {
            return calcResult.substring(0, calcResult.length() - 1);
        }

        if (calcResult.endsWith("＋.") || calcResult.endsWith("－.") || calcResult.endsWith("×.")
                || calcResult.endsWith("÷."))
        {
            return Const.ERROR;
        }

        return calcResult;
    }
}