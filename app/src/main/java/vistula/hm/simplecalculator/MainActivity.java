package vistula.hm.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView solutionTV,resultTV;
    String operator;
    MaterialButton buttonC,buttonBracketOpen,buttonBracketClose;
    MaterialButton buttonDivide,buttonAdd,buttonSubtract,buttonEquals,buttonMultiply;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTV=findViewById(R.id.result_textView);
        solutionTV=findViewById(R.id.solution_textView);
        assignID(buttonC,R.id.button_C);
        assignID(buttonBracketClose,R.id.button_close_bracket);
        assignID(buttonBracketOpen,R.id.button_open_bracket);
        assignID(buttonDivide,R.id.button_divide);
        assignID(buttonAdd,R.id.button_add);
        assignID(buttonSubtract,R.id.button_minus);
        assignID(buttonEquals,R.id.button_equals);
        assignID(buttonMultiply,R.id.button_multiply);
        assignID(button0,R.id.button_0);
        assignID(button1,R.id.button_1);
        assignID(button2,R.id.button_2);
        assignID(button3,R.id.button_3);
        assignID(button4,R.id.button_4);
        assignID(button5,R.id.button_5);
        assignID(button6,R.id.button_6);
        assignID(button7,R.id.button_7);
        assignID(button8,R.id.button_8);
        assignID(button9,R.id.button_9);
        assignID(buttonAC,R.id.button_all_clear);
        assignID(buttonDot,R.id.button_dot);
        assignID(button8,R.id.button_8);

    }
    private void assignID(MaterialButton button,int buttonId){
        button=findViewById(buttonId);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTV.getText().toString();


        if (buttonText.equals("AC")){
            solutionTV.setText("");
            resultTV.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTV.setText(resultTV.getText().toString());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate+=buttonText;
        }
        solutionTV.setText(dataToCalculate);
        String overallResult = getResult(dataToCalculate);
        if (!overallResult.equals("Error")){
            resultTV.setText(overallResult);
        }
    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult =context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            return finalResult;

        }catch (Exception e){
            return "Error";
        }
    }
}