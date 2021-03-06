package com.example.caculator;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.math.BigInteger;
import java.util.Stack;

import static com.example.caculator.IndexInToDuffix.Houzhui;

/**
 * 计算器首页
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
    private Button btn_point;
    private Button btn_plus;
    private Button btn_sub;
    private Button btn_multi;
    private Button btn_div;
    private Button btn_left;
    private Button btn_right;
    private Button btn_area;
    private Button btn_length;

    private Button btn_ac;  //清空
    private Button btn_del;//删除
    private Button btn_equals;  //等号
    //显示输入的结果
    private TextView et_showview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 选择函数
         */
        findViewById(R.id.button_sce).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                getMenuInflater().inflate(R.menu.science, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String str = et_showview.getText().toString();
                        double value = Double.valueOf(str);
                        double result = 0;
                        switch (menuItem.getItemId()) {
                            case R.id.sin:
                                result = Math.sin(Math.toRadians(value));
                                String resultsin = String.valueOf(result);
                                et_showview.setText(resultsin);
                                return true;
                            case R.id.cos:
                                result = Math.cos(Math.toRadians(value));
                                String resultcos = String.valueOf(result);
                                et_showview.setText(resultcos);
                                return true;
                            case R.id.tan:
                                result = Math.tan(Math.toRadians(value));
                                String resulttan = String.valueOf(result);
                                et_showview.setText(resulttan);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });

        /**
         * 选择进制转换
         */
        findViewById(R.id.button_con).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                getMenuInflater().inflate(R.menu.conversion, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String str = et_showview.getText().toString();
                        int i = Integer.parseInt(str);
                        switch (menuItem.getItemId()) {
                            case R.id.to2:
                                //转换成二进制输出
                                et_showview.setText(Integer.toBinaryString(i));
                                return true;
                            case R.id.to8:
                                //转换成八进制输出
                                et_showview.setText(Integer.toOctalString(i));
                                return true;
                            case R.id.to16:
                                //转换成16进制输出
                                et_showview.setText(Integer.toHexString(i));
                                return true;
                            case R.id.ret:
                                //重置
                                BigInteger j = new BigInteger(str, 2);
                                et_showview.setText(j.toString());
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                /*
                  显示弹窗
                 */
                popupMenu.show();
            }
        });

        /**
         * 求平均数
         */
        findViewById(R.id.btn_area).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                getMenuInflater().inflate(R.menu.area, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String str = et_showview.getText().toString();
                        double value = Double.valueOf(str);
                        double result = 0;
                        switch (menuItem.getItemId()) {
                            case R.id.mm3:
                                result = value * 1000;
                                String resultmm = String.valueOf(result);
                                et_showview.setText(resultmm);

                                return true;
                            case R.id.cm3:
                                result = value;
                                String resultcm = String.valueOf(result);
                                et_showview.setText(resultcm);

                                return true;
                            case R.id.dm3:
                                result = value / 1000;
                                String resultdm = String.valueOf(result);
                                et_showview.setText(resultdm);

                                return true;
                            case R.id.m3:

                                result = value / 1000000;
                                String resultm = String.valueOf(result);
                                et_showview.setText(resultm);

                                return true;
                            default:
                                return false;
                        }
                    }
                });

                popupMenu.show();
            }
        });

        findViewById(R.id.btn_length).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                getMenuInflater().inflate(R.menu.length, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String str = et_showview.getText().toString();
                        double value = Double.valueOf(str);
                        double result = 0;
                        switch (menuItem.getItemId()) {
                            case R.id.mm:
                                //毫米
                                result = value * 10;
                                String resultmm = String.valueOf(result);
                                et_showview.setText(resultmm);

                                return true;
                            case R.id.cm:
                                //厘米
                                result = value;
                                String resultcm = String.valueOf(result);
                                et_showview.setText(resultcm);

                                return true;
                            case R.id.dm:
                                //分米
                                result = value / 10;
                                String resultdm = String.valueOf(result);
                                et_showview.setText(resultdm);
                                return true;
                            case R.id.m:
                                //米
                                result = value / 100;
                                String resultm = String.valueOf(result);
                                et_showview.setText(resultm);
                                return true;
                            default:
                                return false;
                        }
                    }
                });

                /*
                  弹窗显示
                 */
                popupMenu.show();
            }
        });

        initView();
    }

    /**
     * 初始化视图
     */
    public void initView() {
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
        btn_plus = (Button) findViewById(R.id.btn_jia);
        btn_sub = (Button) findViewById(R.id.btn_jian);
        btn_multi = (Button) findViewById(R.id.btn_cheng);
        btn_div = (Button) findViewById(R.id.btn_chu);
        btn_point = (Button) findViewById(R.id.btn_dian);

        btn_left = (Button) findViewById(R.id.btn_zuokuo);
        btn_right = (Button) findViewById(R.id.btn_youkuo);
        btn_equals = (Button) findViewById(R.id.btn_dengyu);

        btn_ac = (Button) findViewById(R.id.btn_clean);
        btn_del = (Button) findViewById(R.id.btn_delete);

        btn_area = (Button) findViewById(R.id.btn_area);
        btn_area = (Button) findViewById(R.id.btn_length);
        et_showview = (TextView) findViewById(R.id.msg);

        //设置控件的点击事件
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
        btn_equals.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_multi.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_ac.setOnClickListener(this);
        btn_point.setOnClickListener(this);
    }

    /**
     * 点击事件响应
     *
     * @param view 每个控件
     */
    @Override
    public void onClick(View view) {
        String str = et_showview.getText().toString();
        switch (view.getId()) {
            case R.id.btn_0:
                str += "0";
                et_showview.setText(str);
                break;
            case R.id.btn_1:
                str += "1";
                et_showview.setText(str);
                break;
            case R.id.btn_2:
                str += "2";
                et_showview.setText(str);
                break;
            case R.id.btn_3:
                str += "3";
                et_showview.setText(str);
                break;
            case R.id.btn_4:
                str += "4";
                et_showview.setText(str);
                break;
            case R.id.btn_5:
                str += "5";
                et_showview.setText(str);
                break;
            case R.id.btn_6:
                str += "6";
                et_showview.setText(str);
                break;
            case R.id.btn_7:
                str += "7";
                et_showview.setText(str);
                break;
            case R.id.btn_8:
                str += "8";
                et_showview.setText(str);
                break;
            case R.id.btn_9:
                str += "9";
                et_showview.setText(str);
                break;

            //点击除法
            case R.id.btn_chu:
                if (str.length() != 0) {
                    //获取指定索引位置 charAt
                    /**
                     * 点击除法之前，先判断上一次输入的字符，是否有 加减乘除符号，如果没有的话，可以输入除法。
                     */
                    if (str.charAt(str.length() - 1) != '+') {
                        if (str.charAt(str.length() - 1) != '-') {
                            if (str.charAt(str.length() - 1) != '*') {
                                if (str.charAt(str.length() - 1) != '/') {
                                    str += "/";
                                    et_showview.setText(str);
                                }
                            }
                        }
                    }
                }
                break;

            //点击乘法
            case R.id.btn_cheng:
                if (str.length() != 0) {
                    str += "*";
                    et_showview.setText(str);
                }
                break;
            //点击 点
            case R.id.btn_dian:
                if (str.length() != 0) {
                    str += ".";
                    et_showview.setText(str);
                }
                break;
            //点击加法
            case R.id.btn_jia:
                if (str.length() != 0) {
                    str += "+";
                    et_showview.setText(str);
                }
                break;
            //点击减法
            case R.id.btn_jian:
                if (str.length() != 0) {
                    str += "-";
                    et_showview.setText(str);
                }
                break;
            //如果运算符和小数点不出现在首尾位置，可以添加进字符串里
            //小数点前一位必须是数字
            //点击左括号
            case R.id.btn_zuokuo:
                str += "(";
                et_showview.setText(str);
                break;
            //点击右括号
            case R.id.btn_youkuo:
                str += ")";
                et_showview.setText(str);
                break;
            //点击等于
            case R.id.btn_dengyu:
                Stack houzhui = Houzhui(str);
                String calc = IndexInToDuffix.calc(houzhui);
                et_showview.setText(calc);
                break;
                //点击del  删除键
            case R.id.btn_delete:
                //进行截取字符串，从0开始，字符串总长结束
                str = str.substring(0, str.length() - 1);
                et_showview.setText(str);
                break;
                //点击c  清除键
            case R.id.btn_clean:
                str = "";
                et_showview.setText(str);
                break;
        }
    }
}



