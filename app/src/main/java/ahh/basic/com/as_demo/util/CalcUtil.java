package ahh.basic.com.as_demo.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/7/21.
 */
public class CalcUtil
{
    /**
     * @param operPrecedenceStr
     * @return
     */
    private static OperPrecedence getOperPrecedence(String operPrecedenceStr)
    {
        OperPrecedence operPrecedence = new OperPrecedence();

        String[] rows = operPrecedenceStr.split(";");
        int rowsLength = rows.length;
        String[][] data = new String[rowsLength][rowsLength];
        for (int i = 0; i < rowsLength; i++)
        {
            data[i] = rows[i].split("\t");
        }

        for (int i = 1; i < rowsLength; i++)
        {
            for (int j = 1; j < rowsLength; j++)
            {
                operPrecedence.put(data[i][0], data[0][j], data[i][j]);
            }
        }
        return operPrecedence;
    }

    /**
     * 将四则运算表达式分解为操作数和运算符，并添加到队列中
     *
     * @param middlefix
     * @return
     */
    private static ArrayDeque<DataType> toQueue(String middlefix)
    {
        ArrayDeque<DataType> queue = new ArrayDeque<>();

        String regex = "[＋|－|×|÷]";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(middlefix);

        //从索引0位置开始匹配
        int searchIndex = 0;
        String searchData;
        DataType dataType;

        //matcher.start()代表发现正则匹配项的索引，matcher.end()代表匹配项结束的后一个索引位置
        while (matcher.find())
        {
            //操作数类型截取
            searchData = middlefix.substring(searchIndex, matcher.start());
            dataType = new DataType(true, searchData);
            queue.addLast(dataType);

            //将下次搜索的索引置为上一次搜索的结束位置
            searchIndex = matcher.end();

            //运算符类型截取
            searchData = middlefix.substring(matcher.start(), matcher.end());
            dataType = new DataType(false, searchData);
            queue.addLast(dataType);
        }

        //最后的操作数入队列
        searchData = middlefix.substring(searchIndex, middlefix.length());
        dataType = new DataType(true, searchData);
        queue.addLast(dataType);

        return queue;
    }

    public static String toPostfixAndCalc(String middlefix, String operPrecedenceStr)
    {
        OperPrecedence operPrecedence = getOperPrecedence(operPrecedenceStr);

        ArrayDeque<DataType> dataTypeArrayDeque = toQueue(middlefix);
        DataType dt = new DataType(false, "#");
        dataTypeArrayDeque.addLast(dt);

        Stack<String> operSigStack = new Stack<>();
        operSigStack.push("#");
        Stack<String> operResultStack = new Stack<>();

        // 令x1为当前栈顶运算符的变量，x2为当前扫描读到的运算符的变量，当顺序从中缀表达式中读入的单词为运算符时就赋予x2；
        // 然后比较x1与x2的优先级，若优先级x1>x2，将x1从S中出栈，并加入L中，接着比较新的栈顶运算符x1与x2的优先级；
        // 若优先级x1<x2，将x2入栈S，接着读下一个单词；若优先级x1=x2且x1为”(”而x2为”)”,将x1出栈，接着读下一个单词；
        // 若优先级x1=x2且x1为”#”而x2为”#”,算法结束。
        while (!dataTypeArrayDeque.isEmpty())
        {
            DataType dataType = dataTypeArrayDeque.peek();
            String data = dataType.getData();
            if (dataType.isNumber())
            {
                dataTypeArrayDeque.pop();
                operResultStack.push(data);
            } else
            {
                //x1
                String peek = operSigStack.peek();
                //x1>x2
                String status = operPrecedence.get(peek, data);
                if (">".equals(status))
                {
                    String operSig = operSigStack.pop();
                    String secondOperNumStr = operResultStack.pop();
                    String firstOperNumStr = operResultStack.pop();
                    BigDecimal firstOperNum = new BigDecimal(firstOperNumStr);
                    BigDecimal secondOperNum = new BigDecimal(secondOperNumStr);

                    String result = "";

                    switch (operSig)
                    {
                        case "＋":
                            result = firstOperNum.add(secondOperNum).toString();
                            break;
                        case "－":
                            result = firstOperNum.subtract(secondOperNum).toString();
                            break;
                        case "×":
                            result = firstOperNum.multiply(secondOperNum).toString();
                            break;
                        case "÷":
                            result = firstOperNum.divide(secondOperNum).toString();
                            break;
                        default:
                            break;
                    }

                    if (!"".equals(result))
                    {
                        operResultStack.push(result);
                    }
                } else if ("<".equals(status))
                {
                    dataTypeArrayDeque.pop();
                    operSigStack.push(data);
                } else if ("=".equals(status))
                {
                    //算法结束
                    break;
                } else
                {
                    //空
                }
            }
        }
        String peek = operResultStack.peek();
        return new BigDecimal(peek).toString();
    }
}
