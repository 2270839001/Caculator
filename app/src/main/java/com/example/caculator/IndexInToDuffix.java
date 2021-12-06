package com.example.caculator;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.math.BigDecimal;

public class IndexInToDuffix {
    /**
     * 对输入的结果进行处理
     * @param s 输入结果
     * @return 栈
     */
    public static Stack Houzhui(String s) {
        //创建2个临时的栈
        Stack<String> stacka = new Stack<String>();   //存放操作数
        Stack<String> stackb = new Stack<String>();   //存放操作符
        //需要创建一个字符串存放数，不然2位数及以上会被拆开，不方便后续处理
        String temp = new String();
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        hashMap.put("(", 0);// 设定优先级 +-优先级相同 */优先级相同
        hashMap.put("+", 1);
        hashMap.put("-", 1);
        hashMap.put("*", 2);
        hashMap.put("/", 2);

        Log.d("数据","数据处理："+hashMap.size());
        for (Map.Entry<String,Integer> entry: hashMap.entrySet()){
            System.out.println("数据："+entry.getKey()+",值："+entry.getValue());
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);  //获取第i个位置的字符
            String m = String.valueOf(c);
            //判断字符是否为数字或者 c等于.
            if (Character.isDigit(c) || c == '.') {
                if (i == s.length() - 1) {
                    temp += m;
                    stacka.pushtemp();
                } else {
                    temp += m;
                }
            } else {
                if (temp != "") {
                    stacka.push(temp);
                    temp = "";
                }
                if (c == '(') {
                    stackb.push(m);
                } else if (c == ')') {
                    while (!stackb.isEmpty() && !stackb.peek().equals("(")) {
                        String r = stackb.pop();
                        stacka.push(r);
                    }
                    //遇到右括号，输出运算符堆栈中的运算符到操作数堆栈，直到遇到左括号为止
                    if (stackb.peek().equals("(")) {
                        stackb.pop();
                    }
                } else {//加减乘除的情况
                    //首先是加减，他们的优先级低于乘除，只有优先级大于等于栈顶才弹出
                    switch (c) {
                        case '+':
                        case '-':
                            if ((!stackb.isEmpty()) && hashMap.get(stackb.peek()) > 1) {
                                String t = stackb.pop();
                                stacka.push(t);
                                stackb.push(m);
                            } else {
                                stackb.push(m);
                            }
                            break;
                        case '*':
                        case '/':
                            stackb.push(m);
                            break;
                    }
                }
            }
        }
        while (!stackb.isEmpty()) {
            String q = stackb.pop();
            stacka.push(q);
        }
        return stacka;
    }

    /**
     * 计算
     * @param stacka
     * @return
     */
    public static String calc(Stack<String> stacka) {
        ArrayList<String> arr = new ArrayList<String>();
        while (!stacka.isEmpty()) {
            String t = stacka.pop();
            arr.add(t);
        }
        ArrayList<String> arr1 = new ArrayList<String>();
        System.out.println(arr);

        for (int i = arr.size() - 1; i >= 0; i--) {
            int j = arr1.size();
            //为什么要用 BigDecimal ，因为不损失精度，计算多少，就是多少
            switch (arr.get(i)) {
                case "+":
                    BigDecimal a = new BigDecimal(arr1.remove(j - 2)).add(
                            new BigDecimal(arr1.remove(j - 2)));
                    arr1.add(String.valueOf(a));
                    break;
                case "-":
                    BigDecimal b = new BigDecimal(arr1.remove(j - 2)).subtract(
                            new BigDecimal(arr1.remove(j - 2)));
                    arr1.add(String.valueOf(b));
                    break;
                case "*":
                    BigDecimal c = new BigDecimal(arr1.remove(j - 2)).multiply(
                            new BigDecimal(arr1.remove(j - 2)));
                    arr1.add(String.valueOf(c));
                    break;
                case "/":
                    BigDecimal d = new BigDecimal(arr1.remove(j - 2)).divide(
                            new BigDecimal(arr1.remove(j - 2)));
                    arr1.add(String.valueOf(d));
                    break;

                default:
                    arr1.add(arr.get(i));
                    break;
            }
        }
        Log.d("计算结果","结果："+arr1.toString());
        //获取数组的第一个角标位置
        return arr1.get(0);
    }
}
