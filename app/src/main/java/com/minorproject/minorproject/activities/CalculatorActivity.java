package com.minorproject.minorproject.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.minorproject.minorproject.R;

public class CalculatorActivity extends Activity {
    TextView tv;
    String operand1,operand2;
    boolean newValue=true;
    String operator="";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        tv=findViewById(R.id.txtNumber);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CalculatorActivity.this,NavActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    public  void  clearClick(View v)
    {
        tv.setText("0");
        operator=""; operand2=""; operand1="";
    }
    public void  backClick(View v)
    {
        String data=tv.getText().toString();
        if (data.equals("0"))
            return;
        data=data.substring(0,data.length()-1);
        if (data.length()==0)
            data="0";
        tv.setText(data);
    }
    public void digitClick(View v){
        String n=(String)v.getTag();
        String data=tv.getText().toString();
        if(data.equals("0")|| newValue){
            tv.setText(n);
            newValue=false;
        }
        else
            tv.setText(data+n);
    }
    public  void operatorClick(View v){
        if (operator.length()!=0)
            equalClick(null);
        operator=(String)v.getTag();
        operand1=tv.getText().toString();
        newValue=true;
    }
    public void equalClick(View v)
    {
        if (operator.length()==0)
            return;
        operand2=tv.getText().toString();
        double op1=Double.parseDouble(operand1);
        double op2=Double.parseDouble(operand2);
        double result=0;
        switch (operator)
        {
            case "+": result=op1+op2; break;
            case "-": result=op1-op2; break;
            case "*": result=op1*op2; break;
            case "/": result=op1/op2; break;
            case "x^y": result=Math.pow(op1,op2); break;
            case "%": result=op1%op2; break;
            case "y\\u221Ax": result=Math.pow(op1,(1/op2)); break;
        }
        tv.setText(Double.toString(result));
        operator="";
        operand1=""; operand2="";
    }
    public  void dotClick(View v)
    {
        String data=tv.getText().toString();
        if (data.contains("."))
            return;
        data+=".";
        tv.setText(data);
    }
    private double factorial(double n)
    {
        double x=1;
        for (int i=1;i<=n;i++) x=x*i;
        return x;
    }
    public void unaryClick(View v)
    {
        operand1=tv.getText().toString();
        double value=Double.parseDouble(operand1);
        switch (v.getTag().toString())
        {
            case "ceil": value=Math.ceil(value);
                tv.setText(Double.toString(value));
                break;
            case "floor": value=Math.floor(value);
                tv.setText(Double.toString(value));
                break;
            case "e":  double e=Math.E;
                String data=tv.getText().toString();
                data=data+e;
                tv.setText(data);
            case "sin-1": if (value<-1 || value>1)
                Toast.makeText(this,"Domain Error",Toast.LENGTH_SHORT).show();
            else
            {
                value=Math.asin(value);
                value=Math.toDegrees(value);
                tv.setText(Double.toString(value));
            }
                break;
            case "cos-1":    if (value<-1 || value>1)
                Toast.makeText(this,"Domain Error",Toast.LENGTH_SHORT).show();
            else
            {
                value=Math.acos(value);
                value=Math.toDegrees(value);
                tv.setText(Double.toString(value));
            }
                break;
            case "tan-1":

                value=Math.atan(value);
                value=Math.round(Math.toDegrees(value));
                tv.setText(Double.toString(value));
                break;
            case "sin":
                value=Math.toRadians(value);
                value=Math.sin(value);
                tv.setText(Double.toString(value));
                break;
            case "cos":
                value=Math.toRadians(value);
                value=Math.cos(value);
                tv.setText(Double.toString(value));
                break;
            case "tan":
                value=Math.toRadians(value);
                value=Math.tan(value);
                tv.setText(Double.toString(value));
                break;
            case "log":
                if (value==0)
                    Toast.makeText(this,"Invalid Input",Toast.LENGTH_LONG).show();
                else {
                    value=Math.log10(value);
                    tv.setText(Double.toString(value));
                }
                break;
            case "ln":
                if (value==0)
                    Toast.makeText(this,"Invalid Input",Toast.LENGTH_LONG).show();
                else
                {
                    value=Math.log(value);
                    tv.setText(Double.toString(value));
                }
                break;
            case "e^x":
                value=Math.exp(value);
                tv.setText(Double.toString(value)); break;
            case "10^x":
                value=Math.pow(10,value);
                tv.setText(Double.toString(value));
                break;
            case "x^3":
                value=Math.pow(value,3);
                tv.setText(Double.toString(value));
                break;
            case "x^2":
                value=Math.pow(value,2);
                tv.setText(Double.toString(value));
                break;
            case "sqrt":
                value=Math.sqrt(value);
                tv.setText(Double.toString(value));
                break;
            case "pi":
                value=Math.PI;
                tv.setText(Double.toString(value));
                break;
            case "n!":
                value=factorial(value);
                tv.setText(Double.toString(value));
                break;
            case "1/x":
                if (value==0)
                    Toast.makeText(this,"Cannot Divide by Zero",Toast.LENGTH_LONG).show();
                else
                {
                    value=1/value;
                    tv.setText(Double.toString(value));
                }
                break;
            case "deg":
                value=Math.toDegrees(value);
                tv.setText(Double.toString(value));
        }
        newValue=true; operand1=""; operand2=""; operator="";
    }
    public void plusMinusClick(View v)
    {
        if (tv.getText().equals("0")) return;
        String data=tv.getText().toString();
        if (data.contains("-"))  data=data.replace("-","");
        else  data="-"+data; tv.setText(data);
    }

}
