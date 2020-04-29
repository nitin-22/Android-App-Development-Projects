package com.example.homework3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void method(View v){
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        TextView mTextView = (TextView) findViewById(R.id.textView26);
        String s = mTextView.getText().toString().replaceAll("\\.","");
        Double f = new Double(0.00);

        if (!buttonText.equals("CLEAR")){
            s = s + buttonText;   //appending is done
             f = Double.parseDouble(s);             //float is primitive, therefore cannot be use for toString. Float is wrapper class.
            f = f / 100;
            mTextView.setText(f.toString());
        }
        else {
            mTextView.setText("0.00");
        }
        calculation(f);
    }


    public void calculation (Double f){
        ArrayList<Double> list = new ArrayList<Double>(Arrays.asList(20.00, 10.00, 5.00, 1.00, 0.25, 0.10, 0.05, 0.01));
        if (f==0.00){
            reset();
        } else {
            reset();
            for (int i = 0; i<list.size(); i++){

                f = Math.round(f * 100.0)/100.0;
                double num = f / list.get(i);

                int n = (int) Math.floor(num);
                if (n>=1){
                    switch(list.get(i).toString()){
                        case "20.0":
                            ((TextView) findViewById(R.id.textView18)).setText(String.valueOf(n));
                            break;
                        case "10.0":
                            ((TextView) findViewById(R.id.textView19)).setText(String.valueOf(n));
                            break;
                        case "5.0":
                            ((TextView) findViewById(R.id.textView20)).setText(String.valueOf(n));
                            break;
                        case "1.0":
                            ((TextView) findViewById(R.id.textView21)).setText(String.valueOf(n));
                            break;
                        case "0.25":
                            ((TextView) findViewById(R.id.textView22)).setText(String.valueOf(n));
                            break;
                        case "0.1":
                            ((TextView) findViewById(R.id.textView23)).setText(String.valueOf(n));
                            break;
                        case "0.05":
                            ((TextView) findViewById(R.id.textView24)).setText(String.valueOf(n));
                            break;
                        case "0.01":
                            ((TextView) findViewById(R.id.textView25)).setText(String.valueOf(n));
                            break;
                    }
                    f %= list.get(i);
                }
            }
        }

    }
    public void reset(){
        ((TextView) findViewById(R.id.textView18)).setText("0");
        ((TextView) findViewById(R.id.textView19)).setText("0");
        ((TextView) findViewById(R.id.textView20)).setText("0");
        ((TextView) findViewById(R.id.textView21)).setText("0");
        ((TextView) findViewById(R.id.textView22)).setText("0");
        ((TextView) findViewById(R.id.textView23)).setText("0");
        ((TextView) findViewById(R.id.textView24)).setText("0");
        ((TextView) findViewById(R.id.textView25)).setText("0");
    }

}
