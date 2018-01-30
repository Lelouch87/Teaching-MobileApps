package com.example.chase.testapp1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.StringTokenizer;



@SuppressLint("SetTextI18n")
public class ThirdActivity extends AppCompatActivity {
    private TextView et;
    Vibrator vibr;
    final int VIBRATION_TIME = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        et = findViewById(R.id.display);
        vibr = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Menu buttons

        Button nextButton = findViewById(R.id.next_page);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToNextPage();
                vibr.vibrate(VIBRATION_TIME);
            }
        });


        Button testSplashButton = findViewById(R.id.test_splash_button);
        testSplashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                testSplash();
                vibr.vibrate(VIBRATION_TIME);
            }
        });




        //Calculator buttons
        Button zeroButton = findViewById(R.id.zero_button);
        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText((et.getText().toString()) + getString(R.string.zero_button));
                vibr.vibrate(VIBRATION_TIME);
            }
        });

        Button oneButton = findViewById(R.id.one_button);
        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.one_button);
                    vibr.vibrate(VIBRATION_TIME);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.one_button));
                    vibr.vibrate(VIBRATION_TIME);
                }
            }
        });


        Button twoButton = findViewById(R.id.two_button);
        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.two_button);
                    vibr.vibrate(VIBRATION_TIME);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.two_button));
                    vibr.vibrate(VIBRATION_TIME);
                }
            }
        });

        Button threeButton = findViewById(R.id.three_button);
        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.three_button);
                    vibr.vibrate(VIBRATION_TIME);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.three_button));
                    vibr.vibrate(VIBRATION_TIME);
                }
            }
        });


        Button fourButton = findViewById(R.id.four_button);
        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.four_button);
                    vibr.vibrate(VIBRATION_TIME);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.four_button));
                    vibr.vibrate(VIBRATION_TIME);
                }
            }
        });

        Button fiveButton = findViewById(R.id.five_button);
        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.five_button);
                    vibr.vibrate(VIBRATION_TIME);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.five_button));
                    vibr.vibrate(VIBRATION_TIME);
                }
            }
        });

        Button sixButton = findViewById(R.id.six_button);
        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.six_button);
                    vibr.vibrate(VIBRATION_TIME);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.six_button));
                    vibr.vibrate(VIBRATION_TIME);
                }
            }
        });


        Button sevenButton = findViewById(R.id.seven_button);
        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.seven_button);
                    vibr.vibrate(VIBRATION_TIME);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.seven_button));
                    vibr.vibrate(VIBRATION_TIME);
                }
            }
        });

        Button eightButton = findViewById(R.id.eight_button);
        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.eight_button);
                    vibr.vibrate(VIBRATION_TIME);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.eight_button));
                    vibr.vibrate(VIBRATION_TIME);
                }
            }
        });

        Button nineButton = findViewById(R.id.nine_button);
        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.nine_button);
                    vibr.vibrate(VIBRATION_TIME);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.nine_button));
                    vibr.vibrate(VIBRATION_TIME);
                }
            }
        });





        //OPERATOR BUTTONS

        Button plusButton = findViewById(R.id.plus_button);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText((et.getText().toString()) + getString(R.string.plus_button));
                vibr.vibrate(VIBRATION_TIME);
            }
        });

        Button backspaceButton = findViewById(R.id.backspace_button);
        backspaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String currentText = et.getText().toString();
                int index = currentText.length()-1;

                if (index > 0) {
                    currentText = currentText.substring(0, index);
                    et.setText(currentText);
                    vibr.vibrate(VIBRATION_TIME);
                } else {
                    et.setText("0");
                    vibr.vibrate(VIBRATION_TIME);
                }

            }
        });

        Button timesButton = findViewById(R.id.times_button);
        timesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText((et.getText().toString()) + getString(R.string.times_button));
                vibr.vibrate(VIBRATION_TIME);
            }
        });

        Button leftParenth = findViewById(R.id.left_parenth);
        leftParenth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.left_parenth);
                    vibr.vibrate(VIBRATION_TIME);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.left_parenth));
                    vibr.vibrate(VIBRATION_TIME);
                }
            }
        });

        Button rightParenth = findViewById(R.id.right_parenth);
        rightParenth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.right_parenth);
                    vibr.vibrate(VIBRATION_TIME);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.right_parenth));
                    vibr.vibrate(VIBRATION_TIME);
                }
            }
        });

        Button divideButton = findViewById(R.id.divide_button);
        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText((et.getText().toString()) + getString(R.string.divide_button));
                vibr.vibrate(VIBRATION_TIME);
            }
        });

        Button subtractButton = findViewById(R.id.subtract_button);
        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText((et.getText().toString()) + getString(R.string.subtract_button));
                vibr.vibrate(VIBRATION_TIME);
            }
        });

        Button clearButton = findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText("0");
                vibr.vibrate(VIBRATION_TIME);
            }
        });

        Button equalsButton = findViewById(R.id.equals_button);
        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String text = et.getText().toString();

                Log.d("Input from Text View: ", text);

                String answer = evaluateInfix(text);

                Log.d("myTag", "Origin answer is this : " + answer);

                et.setText(answer);
                vibr.vibrate(VIBRATION_TIME);
            }
        });




    }

    private void goToNextPage()
    {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    private void testSplash()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public static String evaluateInfix(String exps){

        // remove if any spaces from the expression
        exps = exps.replaceAll("\\s+", "");
        // we assume that the expression is in valid format
        ArrayStack<String> stack = new ArrayStack<>(exps.length());
        // break the expression into tokens
        StringTokenizer tokens = new StringTokenizer(exps, "{}()*/+-", true);
        while(tokens.hasMoreTokens()){
            String tkn = tokens.nextToken();
            // read each token and take action
            if(tkn.equals("(")
                    || tkn.equals("{")
                    || tkn.matches("[0-9]+")
                    || tkn.equals("*")
                    || tkn.equals("/")
                    || tkn.equals("+")
                    || tkn.equals("-")){
                // push token to the stack
                stack.push(tkn);
            } else if(tkn.equals("}") || tkn.equals(")")){
                try {
                    int op2 = Integer.parseInt(stack.pop());
                    String oprnd = stack.pop();
                    int op1 = Integer.parseInt(stack.pop());
                    // Below pop removes either } or ) from stack
                    if(!stack.empty()){
                        stack.pop();
                    }
                    int result = 0;
                    switch (oprnd) {
                        case "*":
                            result = op1 * op2;
                            break;
                        case "/":
                            result = op1 / op2;
                            break;
                        case "+":
                            result = op1 + op2;
                            break;
                        case "-":
                            result = op1 - op2;
                            break;
                    }
                    // push the result to the stack
                    stack.push(result+"");
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
        String finalResult = "";
        try {
            finalResult = stack.pop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalResult;
    }


}
