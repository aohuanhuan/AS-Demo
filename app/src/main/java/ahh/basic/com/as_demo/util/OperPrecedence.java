package ahh.basic.com.as_demo.util;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/21.
 * 运算符优先级类
 */
public class OperPrecedence
{
    private HashMap<OperKey, String> precedenceMap;

    public OperPrecedence()
    {
        this.precedenceMap = new HashMap<>();
    }

    public void put(String firstKey, String lastKey, String value)
    {
        OperKey operKey = new OperKey(firstKey, lastKey);
        precedenceMap.put(operKey, value);
    }

    public String get(String firstKey, String lastKey)
    {
        OperKey operKey = new OperKey(firstKey, lastKey);

        String value = precedenceMap.get(operKey);
        return value;
    }
}
