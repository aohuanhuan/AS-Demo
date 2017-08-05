package ahh.basic.com.as_demo.util;

/**
 * Created by Administrator on 2017/7/21.
 */
public class OperKey
{
    private String firstKey;

    private String lastKey;

    public OperKey()
    {
    }

    public OperKey(String firstKey, String lastKey)
    {
        this.firstKey = firstKey;
        this.lastKey = lastKey;
    }

    public String getFirstKey()
    {
        return firstKey;
    }

    public void setFirstKey(String firstKey)
    {
        this.firstKey = firstKey;
    }

    public String getLastKey()
    {
        return lastKey;
    }

    public void setLastKey(String lastKey)
    {
        this.lastKey = lastKey;
    }

    @Override
    public boolean equals(Object o)
    {
        OperKey operKey = (OperKey) o;
        return this.firstKey.equals(operKey.getFirstKey()) && this.lastKey.equals(operKey.getLastKey());
    }

    @Override
    public int hashCode()
    {
        return (this.firstKey + this.lastKey).hashCode();
    }
}
