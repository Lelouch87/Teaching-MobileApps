package com.example.chase.testapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.AbstractQueue;
import java.util.Deque;
import java.util.Queue;

public class ThirdActivity extends AppCompatActivity {
    //private EditText et;
    private TextView et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        et = findViewById(R.id.display);
        //display = findViewById(R.id.display);


        //Menu buttons
        /*
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToNextPage();
            }
        });


        Button button1 = (Button) findViewById(R.id.test_splash_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                testSplash();
            }
        });
        */



        //Calculator buttons
        Button zeroButton = (Button) findViewById(R.id.zero_button);
        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText((et.getText().toString()) + getString(R.string.zero_button));                //addToTextView();
            }
        });

        Button oneButton = (Button) findViewById(R.id.one_button);
        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText((et.getText().toString()) + getString(R.string.one_button));                //addToTextView();
            }
        });



        //Button number two works, but awkwardly
        Button twoButton = (Button) findViewById(R.id.two_button);
        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String temp = et.getText().toString();

                String temp2 = Integer.toString(R.string.two_button);

                String string = getString(R.string.two_button);

                String temp4 = "2";




                String temp3 = temp + temp2;

                //et.setText(temp + string);

                et.setText((et.getText().toString()) + getString(R.string.two_button));
            }
        });

        Button threeButton = (Button) findViewById(R.id.three_button);
        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText((et.getText().toString()) + getString(R.string.three_button));
            }
        });


        Button fourButton = (Button) findViewById(R.id.four_button);
        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText((et.getText().toString()) + getString(R.string.four_button));
            }
        });

        Button fiveButton = (Button) findViewById(R.id.five_button);
        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText((et.getText().toString()) + getString(R.string.five_button));
            }
        });

        Button sixButton = (Button) findViewById(R.id.six_button);
        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText((et.getText().toString()) + getString(R.string.six_button));
            }
        });


        Button sevenButton = (Button) findViewById(R.id.seven_button);
        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText((et.getText().toString()) + getString(R.string.seven_button));
            }
        });

        Button eightButton = (Button) findViewById(R.id.eight_button);
        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText((et.getText().toString()) + getString(R.string.eight_button));
            }
        });

        Button nineButton = (Button) findViewById(R.id.nine_button);
        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText((et.getText().toString()) + getString(R.string.nine_button));
            }
        });





        //OPERATOR BUTTONS

        Button plusButton = (Button) findViewById(R.id.plus_button);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Editable temp = et.getText();
                et.setText((et.getText().toString()) + getString(R.string.plus_button));
            }
        });

        Button timesButton = (Button) findViewById(R.id.times_button);
        timesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Editable temp = et.getText();
                et.setText((et.getText().toString()) + getString(R.string.times_button));
            }
        });

        Button divideButton = (Button) findViewById(R.id.divide_button);
        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Editable temp = et.getText();
                et.setText((et.getText().toString()) + getString(R.string.divide_button));
            }
        });

        Button subtractButton = (Button) findViewById(R.id.subtract_button);
        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Editable temp = et.getText();
                et.setText((et.getText().toString()) + getString(R.string.subtract_button));
            }
        });

        /*
        Button decimalButton = (Button) findViewById(R.id.decimal_button);
        decimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText(".");
            }
        });*/

        Button clearButton = (Button) findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText("");
            }
        });

        Button equalsButton = (Button) findViewById(R.id.equals_button);
        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int answer = calculateInput(et.getText().toString());
                et.setText(answer);
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

    private int calculateInput(String input) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();

        String currentToken = "";
        int rightOperand;
        int leftOperand;

        for (int i = 0; i < input.length(); i++) {
            currentToken = input.substring(i, i + 1);
            //if the current token is not an operator it is a digit
            //so push it onto the stack
            if (!isOperator(currentToken)) {
                arrayStack.push(Integer.parseInt(currentToken));
                //else it is an operator
            } else {
                rightOperand = arrayStack.pop();
                leftOperand = arrayStack.pop();
                arrayStack.push(evaluate(rightOperand, leftOperand, input.substring(i, i + 1)));
            }
        }

        return arrayStack.pop();
    }

    private boolean isOperator(String character)
    {
        return (character.equalsIgnoreCase("+") ||
                character.equalsIgnoreCase("-") ||
                character.equalsIgnoreCase("*") ||
                character.equalsIgnoreCase("/"));
    }

    private int evaluate(int rightOperand, int leftOperand, String operator)
    {
        if (operator.equalsIgnoreCase("+")) {
            return leftOperand + rightOperand;
        } else if (operator.equalsIgnoreCase("-")) {
            return leftOperand - rightOperand;
        } else if (operator.equalsIgnoreCase("*")) {
            return leftOperand * rightOperand;
        } else {
            return leftOperand / rightOperand;
        }
    }




}
