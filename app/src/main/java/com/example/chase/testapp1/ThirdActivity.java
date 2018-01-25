package com.example.chase.testapp1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

@SuppressLint("SetTextI18n")
public class ThirdActivity extends AppCompatActivity {
    //private EditText et;
    private TextView et;
    StringBuilder postfix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        et = findViewById(R.id.display);
        //display = findViewById(R.id.display);


        //Menu buttons

        Button nextButton = (Button) findViewById(R.id.next_page);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                goToNextPage();
            }
        });


        Button testSplashButton = (Button) findViewById(R.id.test_splash_button);
        testSplashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                testSplash();
            }
        });




        //Calculator buttons
        Button zeroButton = (Button) findViewById(R.id.zero_button);
        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                et.setText((et.getText().toString()) + getString(R.string.zero_button));
            }
        });

        Button oneButton = (Button) findViewById(R.id.one_button);
        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.one_button);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.one_button));
                }
            }
        });


        Button twoButton = (Button) findViewById(R.id.two_button);
        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.two_button);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.two_button));
                }
            }
        });

        Button threeButton = (Button) findViewById(R.id.three_button);
        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.three_button);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.three_button));
                }
            }
        });


        Button fourButton = (Button) findViewById(R.id.four_button);
        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.four_button);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.four_button));
                }
            }
        });

        Button fiveButton = (Button) findViewById(R.id.five_button);
        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.five_button);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.five_button));
                }
            }
        });

        Button sixButton = (Button) findViewById(R.id.six_button);
        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.six_button);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.six_button));
                }
            }
        });


        Button sevenButton = (Button) findViewById(R.id.seven_button);
        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.seven_button);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.seven_button));
                }
            }
        });

        Button eightButton = (Button) findViewById(R.id.eight_button);
        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.eight_button);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.eight_button));
                }
            }
        });

        Button nineButton = (Button) findViewById(R.id.nine_button);
        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (et.getText().toString().equalsIgnoreCase("0")) {
                    et.setText(R.string.nine_button);
                } else {
                    et.setText((et.getText().toString()) + getString(R.string.nine_button));
                }
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

        Button backspaceButton = (Button) findViewById(R.id.backspace_button);
        backspaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String currentText = et.getText().toString();
                int index = currentText.length()-1;
                if (index >= 0) {
                    currentText = currentText.substring(0, index);
                    et.setText(currentText);
                } else {
                    et.setText("0");
                }

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
                et.setText("0");
            }
        });

        Button equalsButton = (Button) findViewById(R.id.equals_button);
        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ArrayList<String> input = getInput(et.getText().toString());
                for (int i = 0; i < input.size(); i++)
                {
                    Log.d("tag", input.get(i));
                }
                Log.d("Point1", "finished getting input");

                //infix needs to be converted to postfix

                input = convert(input);

                for (int i = 0; i < input.size(); i++)
                {
                    Log.d("tag", input.get(i));
                }

                Log.d("new postfix", "after conversion");

                int answer = calculateInput(input);

                Log.d("Point2", Integer.toString(answer));

                //Log.d("myTag", "Answer is this : " + answer);
                et.setText(String.valueOf(answer));

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

    private int calculateInput(ArrayList<String> input) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        //Convert strings input into an array of integers
        String currentToken = "";
        int rightOperand;
        int leftOperand;

        for (int i = 0; i < input.size(); i++) {
            Log.d("calculateInput", "currentToken: " + currentToken);
            currentToken = input.get(i);
            //if the current token is not an operator it is a digit
            //so push it onto the stack
            if (!isOperator(currentToken)) {
                Log.d("isNotOperator", "Pushing onto stack " + currentToken);
                arrayStack.push(Integer.parseInt(currentToken));
                //else it is an operator
            } else {
                rightOperand = arrayStack.pop();
                Log.d("tag", "Right operand is : " + rightOperand);
                leftOperand = arrayStack.pop();
                Log.d("tag", "Left operand is : " + leftOperand);
                arrayStack.push(evaluate(rightOperand, leftOperand, input.get(i)));
            }
        }

        return arrayStack.pop();
    }

    private ArrayList<String> getInput(String input)
    {
        ArrayList<String> strarr = new ArrayList<>();
        int stringArrayIndex = 0;
        //StringBuilder sb = new StringBuilder();
        String temp = "";
        for (int i = 0; i < input.length(); i++)
        {
            //if its an operand
            if (!isOperator(input.substring(i, i + 1)))
            {
                //add it to the string
                Log.d("tag", "Adding operand: " + input.substring(i, i + 1));
                temp += input.substring(i, i + 1);
                //sb.append(input.substring(i, i + 1));
            }
            else
            {
                //then its an operator
                //so add the previous string to the array
                Log.d("tag", "Adding total operand: " + temp);
                strarr.add(temp);
                //strarr[stringArrayIndex] = temp;
                temp = "";
                stringArrayIndex++;

                Log.d("tag", "Adding operator: " + input.substring(i, i + 1));
                //and add the operator to the array
                //strarr[stringArrayIndex] = input.substring(i, i + 1);
                strarr.add(input.substring(i, i + 1));
                stringArrayIndex++;
            }
            //strarr[stringArrayIndex] = temp;
        }

        Log.d("tag1", "Adding final total operand: " + temp);
        strarr.add(temp);

        /*
        for (int i = 0; i < strarr.length; i++)
        {
            Log.d("tag", strarr[i] + " ");
        }
        */


        //Log.d("myTag", )
        return strarr;
    }

    private ArrayList<String> convert(ArrayList<String> strarr)
    {
        Stack<Character> operatorStack = new Stack<>();
        postfix = new StringBuilder();
        String temp = "";

        for (int i = 0; i < strarr.size(); i++)
        {
            temp = strarr.get(i);
            if (!isOperator(temp))
            {
                postfix.append(temp);
            }
            else if (isOperator(temp))
            {
                processOperator(operatorStack, temp);
            }
            else
            {
                Log.d("convert", "Conversion method error");
            }

        }
        return null;
    }

    private void processOperator(Stack<Character> operatorStack, String currentOperator)
    {
        char operator = currentOperator.charAt(0);
        char topOp = 'a';
        if (operatorStack.isEmpty())
        {
            operatorStack.push(operator);
        }
        else
        {
            topOp = operatorStack.peek();
            if (precedence(operator) > precedence(topOp)) {
                operatorStack.push(operator);
            } else {
                while(!operatorStack.isEmpty() && (precedence(operator) <= precedence(topOp))) {
                    postfix.append(operatorStack.pop());
                    if (!operatorStack.isEmpty()) {
                        topOp = operatorStack.peek();
                    }
                }
                operatorStack.push(operator);
            }
        }
    }

    private int precedence(char op)
    {
        //if the operator is multiply or divide
        if (op == '*' || op == '/') {
            return 1;
        } else { //then its either plus or minus
            return 0;
        }
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
        Log.d("evaluate", "in evaluation");
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
