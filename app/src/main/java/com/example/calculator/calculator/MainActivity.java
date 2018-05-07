package com.example.calculator.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String operator = "";
    String buffer_1 = "";
    String buffer_2 = "";
    int numLength = 11;
    double result = 0.0;

    EditText display;
    TextView[] nums = new TextView[10];
    TextView plus,minus,multiply,divide,equal;
    TextView ce,c,cancle,dot,right_bracket,left_bracket;
    TextView sqr,nth_sqr,sin,cos,tan;
    TextView root,nth_10,log,exp,mod,toggle,pi,factorial,sign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int i, tvid;
        NumberHandler nh = new NumberHandler();
        OperatorHandler oh = new OperatorHandler();
        ExtraHandler eh = new ExtraHandler();


        for (i = 0; i < 10; i++) {
            tvid = getResources().getIdentifier(
                    "num"+i,"id","com.example.calculator.calculator");
            nums[i] = (TextView) findViewById(tvid);
            nums[i].setOnClickListener(nh);
        }
        dot = (TextView) findViewById(R.id.dot);
        dot.setOnClickListener(nh);

        display = (EditText) findViewById(R.id.display);

        plus = (TextView) findViewById(R.id.plus);
        minus = (TextView) findViewById(R.id.minus);
        multiply = (TextView) findViewById(R.id.multiply);
        divide = (TextView) findViewById(R.id.divide);
        equal = (TextView) findViewById(R.id.equal);
        nth_sqr = (TextView) findViewById(R.id.nth_sqr);
        mod = (TextView) findViewById(R.id.Mod);

        plus.setOnClickListener(oh);
        minus.setOnClickListener(oh);
        multiply.setOnClickListener(oh);
        divide.setOnClickListener(oh);
        equal.setOnClickListener(oh);
        nth_sqr.setOnClickListener(oh);
        mod.setOnClickListener(oh);

        ce = (TextView) findViewById(R.id.CE);
        c = (TextView) findViewById(R.id.C);
        cancle = (TextView) findViewById(R.id.cancle);
        right_bracket = (TextView) findViewById(R.id.right_bracket);
        left_bracket = (TextView) findViewById(R.id.left_bracket);
        sqr = (TextView) findViewById(R.id.sqr);
        sin = (TextView) findViewById(R.id.sin);
        cos = (TextView) findViewById(R.id.cos);
        tan = (TextView) findViewById(R.id.tan);
        root = (TextView) findViewById(R.id.root);
        nth_10 = (TextView) findViewById(R.id.nth_10);
        log = (TextView) findViewById(R.id.log);
        exp = (TextView) findViewById(R.id.Exp);
        toggle = (TextView) findViewById(R.id.toggle);
        pi = (TextView) findViewById(R.id.PI);
        factorial = (TextView) findViewById(R.id.factorial);
        sign = (TextView) findViewById(R.id.sign);

        ce.setOnClickListener(eh);
        c.setOnClickListener(eh);
        cancle.setOnClickListener(eh);
        right_bracket.setOnClickListener(eh);
        left_bracket.setOnClickListener(eh);
        sqr.setOnClickListener(eh);
        sin.setOnClickListener(eh);
        cos.setOnClickListener(eh);
        tan.setOnClickListener(eh);
        root.setOnClickListener(eh);
        nth_10.setOnClickListener(eh);
        log.setOnClickListener(eh);
        exp.setOnClickListener(eh);
        toggle.setOnClickListener(eh);
        pi.setOnClickListener(eh);
        factorial.setOnClickListener(eh);
        sign.setOnClickListener(eh);
    }

    class NumberHandler implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            TextView tv = (TextView) view;
            if (operator.equals("")){
                if (tv.getText().toString().equals(".")){
                    if (buffer_1.isEmpty()) {
                        buffer_1 = "0";
                    }else if (buffer_1.contains(".")){
                        return;
                    }
                }
                buffer_1 += (tv.getText().toString());
                if (buffer_1.length() <= numLength){
                    display.setText(buffer_1);
                }else{
                    //Toast.makeText(MainActivity.this,"11자리까지 가능합니다.",Toast.LENGTH_SHORT).show();
                }
            }else{
                if (tv.getText().toString().equals(".")){
                    if (buffer_2.isEmpty()) {
                        buffer_2 = "0";
                    }else if (buffer_2.contains(".")){
                        return;
                    }
                }
                buffer_2 += (tv.getText().toString());
                if (buffer_2.length() <= numLength){
                    display.setText(buffer_2);
                }else{
                    //Toast.makeText(MainActivity.this,"11자리까지 가능합니다.",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    class OperatorHandler implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            TextView tv = (TextView) view;

            if (buffer_1.isEmpty()){
                return;
            }

            if (buffer_2.isEmpty()){
                if (!tv.getText().equals("=")) {
                    operator = tv.getText().toString();
                    if (tv.getText().equals("xⁿ")){
                        operator = "^";
                    }
                    display.setText(operator);
                }
            }else{
                if (!tv.getText().toString().equals("=")) {
                    switch (operator) {
                        case "+":
                            buffer_1 = Double.toString(Double.parseDouble(buffer_1) + Double.parseDouble(buffer_2));
                            buffer_2 = "";
                            operator = tv.getText().toString();
                            break;
                        case "-":
                            buffer_1 = Double.toString(Double.parseDouble(buffer_1) - Double.parseDouble(buffer_2));
                            buffer_2 = "";
                            operator = tv.getText().toString();
                            break;
                        case "X":
                            buffer_1 = Double.toString(Double.parseDouble(buffer_1) * Double.parseDouble(buffer_2));
                            buffer_2 = "";
                            operator = tv.getText().toString();
                            break;
                        case "÷":
                            buffer_1 = Double.toString(Double.parseDouble(buffer_1) / Double.parseDouble(buffer_2));
                            buffer_2 = "";
                            operator = tv.getText().toString();
                            break;
                        case "^":
                            buffer_1 = Double.toString(Math.pow(Double.parseDouble(buffer_1),Double.parseDouble(buffer_2)));
                            buffer_2 = "";
                            operator = tv.getText().toString();
                            if (tv.getText().equals("xⁿ")){
                                operator = "^";
                            }
                            break;
                        case "Mod":
                            buffer_1 = Double.toString(Double.parseDouble(buffer_1) % Double.parseDouble(buffer_2));
                            buffer_2 = "";
                            operator = tv.getText().toString();
                            break;
                    }
                    display.setText(buffer_1);
                    if (buffer_1.contains(".0")){
                        display.setText(buffer_1.replace(".0",""));
                    }
                }else{
                    switch (operator) {
                        case "+":
                            result = Double.parseDouble(buffer_1) + Double.parseDouble(buffer_2);
                            buffer_1 = "";
                            buffer_2 = "";
                            operator = "";
                            break;
                        case "-":
                            result = Double.parseDouble(buffer_1) - Double.parseDouble(buffer_2);
                            buffer_1 = "";
                            buffer_2 = "";
                            operator = "";
                            break;
                        case "X":
                            result = Double.parseDouble(buffer_1) * Double.parseDouble(buffer_2);
                            buffer_1 = "";
                            buffer_2 = "";
                            operator = "";
                            break;
                        case "÷":
                            result = Double.parseDouble(buffer_1) / Double.parseDouble(buffer_2);
                            buffer_1 = "";
                            buffer_2 = "";
                            operator = "";
                            break;
                        case "^":
                            result = Math.pow(Double.parseDouble(buffer_1),Double.parseDouble(buffer_2));
                            buffer_1 = "";
                            buffer_2 = "";
                            operator = "";
                            break;
                        case "Mod":
                            result = Double.parseDouble(buffer_1) % Double.parseDouble(buffer_2);
                            buffer_1 = "";
                            buffer_2 = "";
                            operator = tv.getText().toString();
                            break;
                    }
                    display.setText(Double.toString(result));
                    if (Double.toString(result).contains(".0")){
                        display.setText(Double.toString(result).replace(".0",""));
                    }

                }
            }
        }
    }

    class ExtraHandler implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            TextView tv = (TextView) view;
            switch (tv.getText().toString()) {
                case "C":
                    buffer_1 = "";
                    buffer_2 = "";
                    operator = "";
                    result = 0.0;
                    display.setText("");
                    break;
                case "CE":
                    buffer_1 = "";
                    buffer_2 = "";
                    operator = "";
                    result = 0.0;
                    display.setText("");
                    break;
                case "◀":
                    if (!buffer_2.isEmpty()){
                        buffer_2 = buffer_2.substring(0,buffer_2.length()-1);
                        if (buffer_2.isEmpty()){
                            display.setText(operator);
                        }
                        display.setText(buffer_2);
                    }else if (!operator.isEmpty()){
                        operator="";
                        display.setText(buffer_1);
                    }else if (!buffer_1.isEmpty()){
                        buffer_1 = buffer_1.substring(0,buffer_1.length()-1);
                        if (buffer_1.isEmpty()){
                            display.setText("");
                        }
                        display.setText(buffer_1);
                    }
                    break;
                case "x²":
                    if (!buffer_2.isEmpty()){
                        buffer_2 = Double.toString(Math.pow(Double.parseDouble(buffer_2),2.0));
                        display.setText(buffer_2);
                    }else if(operator.isEmpty()){
                        buffer_1 = Double.toString(Math.pow(Double.parseDouble(buffer_1),2.0));
                        display.setText(buffer_1);
                    }
                    break;
                case "sin":
                    if (!buffer_2.isEmpty()){
                        buffer_2 = Double.toString(Math.sin(Math.toRadians(Double.parseDouble(buffer_2))));
                        display.setText(buffer_2);
                    }else if(operator.isEmpty()){
                        buffer_1 = Double.toString(Math.sin(Math.toRadians(Double.parseDouble(buffer_1))));
                        display.setText(buffer_1);
                    }
                    break;
                case "cos":
                    if (!buffer_2.isEmpty()){
                        buffer_2 = Double.toString(Math.cos(Math.toRadians(Double.parseDouble(buffer_2))));
                        display.setText(buffer_2);
                    }else if(operator.isEmpty()){
                        buffer_1 = Double.toString(Math.cos(Math.toRadians(Double.parseDouble(buffer_1))));
                        display.setText(buffer_1);
                    }
                    break;
                case "tan":
                    if (!buffer_2.isEmpty()){
                        buffer_2 = Double.toString(Math.tan(Math.toRadians(Double.parseDouble(buffer_2))));
                        display.setText(buffer_2);
                    }else if(operator.isEmpty()){
                        buffer_1 = Double.toString(Math.tan(Math.toRadians(Double.parseDouble(buffer_1))));
                        display.setText(buffer_1);
                    }
                    break;
                case "root":
                    if (!buffer_2.isEmpty()){
                        buffer_2 = Double.toString(Math.sqrt(Double.parseDouble(buffer_2)));
                        display.setText(buffer_2);
                    }else if(operator.isEmpty()){
                        buffer_1 = Double.toString(Math.sqrt(Double.parseDouble(buffer_1)));
                        display.setText(buffer_1);
                    }
                    break;
                case "10ⁿ":
                    if (!buffer_2.isEmpty()){
                        buffer_2 = Double.toString(Math.pow(10,Double.parseDouble(buffer_2)));
                        display.setText(buffer_2);
                    }else if(operator.isEmpty()){
                        buffer_1 = Double.toString(Math.pow(10,Double.parseDouble(buffer_1)));
                        display.setText(buffer_1);
                    }
                    break;
                case "log":
                    if (!buffer_2.isEmpty()){
                        buffer_2 = Double.toString(Math.log(Double.parseDouble(buffer_2)));
                        display.setText(buffer_2);
                    }else if(operator.isEmpty()){
                        buffer_1 = Double.toString(Math.log(Double.parseDouble(buffer_1)));
                        display.setText(buffer_1);
                    }
                    break;
                case "Exp":

                    break;
                case "n!":
                    int num = 1;
                    int fac = 1;
                    if (!buffer_2.isEmpty()){
                        num = Integer.parseInt(buffer_2);
                    }else if(operator.isEmpty()){
                        num = Integer.parseInt(buffer_1);
                    }
                    for (int i = 1; i <= num; i++){
                        fac *= i;
                    }
                    display.setText(Integer.toString(fac));
                    if (!buffer_2.isEmpty()){
                        buffer_2 = Double.toString(fac);
                    }else if(operator.isEmpty()){
                        buffer_1 = Double.toString(fac);
                    }
                    break;
                case "±":
                    double sign_buff;
                    if (!buffer_2.isEmpty()){
                        sign_buff = Double.parseDouble(buffer_2);
                        buffer_2 = Double.toString(-1*sign_buff);
                        display.setText(buffer_2);
                    }else if(operator.isEmpty()){
                        sign_buff = Double.parseDouble(buffer_1);
                        buffer_1 = Double.toString(-1*sign_buff);
                        display.setText(buffer_1);
                    }
                    break;
                case "π":
                    if (!buffer_2.isEmpty()){
                        buffer_2 = Double.toString(Math.PI);
                        display.setText(buffer_2);
                    }else if(operator.isEmpty()){
                        buffer_1 = Double.toString(Math.PI);
                        display.setText(buffer_1);
                    }
                    break;
                case "1":break;
            }
        }
    }
}
