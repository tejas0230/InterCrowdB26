package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.Scene;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose,buttonEqual,button7,button8,button9,buttonDivide,button4,button5,button6,buttonMultiply,button1,button2,button3,buttonAdd,button0,buttonDecimal,buttonMod,buttonSubtract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        assign(buttonC,R.id.button_c);
        assign(buttonBrackOpen,R.id.button_open_bracket);
        assign(buttonBrackClose,R.id.button_close_bracket);
        assign(buttonEqual,R.id.equals);
        assign(buttonDivide,R.id.divide);
        assign(buttonMultiply,R.id.multiply);
        assign(buttonAdd,R.id.add);
        assign(buttonSubtract,R.id.subtract);
        assign(buttonDecimal,R.id.Decimal);
        assign(buttonMod,R.id.Mod);
        assign(button0,R.id.button0);
        assign(button1,R.id.button1);
        assign(button2,R.id.button2);
        assign(button3,R.id.button3);
        assign(button4,R.id.button4);
        assign(button5,R.id.button5);
        assign(button6,R.id.button6);
        assign(button7,R.id.button7);
        assign(button8,R.id.button8);
        assign(button9,R.id.button9);



    }

    void assign(MaterialButton btn, int id)
    {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("C"))
        {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("="))
        {
            solutionTv.setText(resultTv.getText());
            return;
        }
        dataToCalculate += buttonText;
        solutionTv.setText(dataToCalculate);
        String finalRes = getResult(dataToCalculate);
        if(!finalRes.equals("Err"))
        {
            resultTv.setText(finalRes);
        }
    }

    String getResult(String data)
    {
        try {
            Context context =Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0"))
            {
                finalResult=finalResult.replace(".0","");
            }
            return  finalResult;
        }catch (Exception e)
        {
            return "Err";
        }
    }
}