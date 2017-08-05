package ahh.basic.com.as_demo.util;

/**
 * Created by Administrator on 2017/7/22.
 */
public class DataType
{
    private boolean isNumber;

    private String data;

    public DataType(boolean isNumber, String data)
    {
        this.isNumber = isNumber;
        this.data = data;
    }

    public DataType()
    {
    }

    public boolean isNumber()
    {
        return isNumber;
    }

    public void setNumber(boolean number)
    {
        isNumber = number;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "DataType{" +
                "isNumber=" + isNumber +
                ", data='" + data + '\'' +
                '}';
    }
}
