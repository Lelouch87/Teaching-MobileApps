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
import java.util.Stack;


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
                String input = infixToPostfix(et.getText().toString());

                for (int i = 0; i < input.length(); i++)
                {
                    Log.d("origin test", input.substring(i, i + 1));
                }

                int answer = evaluatePostfix(input);
                Log.d("myTag", "Origin answer is this : " + answer);

                et.setText(Integer.toString(answer));
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

    private static String infixToPostfix(String exp)
    {
        // initializing empty String for result
        StringBuilder sb = new StringBuilder();

        // initializing empty stack
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i<exp.length(); ++i)
        {
            char c = exp.charAt(i);
            // If the scanned character is an operand, add it to output.
            if (Character.isLetterOrDigit(c)) {
                //result += c;
                sb.append(c);
            }// If the scanned character is an '(', push it to the stack.
            else if (c == '(') {
                stack.push(c);

                //  If the scanned character is an ')', pop and output from the stack
                // until an '(' is encountered.
            } else if (c == ')')
            {
                while (!stack.isEmpty() && stack.peek() != '(')
                    sb.append(stack.pop());
                    //result += stack.pop();

                if (!stack.isEmpty() && stack.peek() != '(')
                    return "Invalid Expression"; // invalid expression
                else
                    stack.pop();
            }
            else // an operator is encountered
            {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek()))
                    sb.append(stack.pop());
                //result += stack.pop();
                stack.push(c);
            }

        }

        // pop all the operators from the stack
        while (!stack.isEmpty())
            sb.append(stack.pop());
            //result += stack.pop();

        //return result;
        return sb.toString();
    }

    static int evaluatePostfix(String exp)
    {
        //create a stack
        Stack<Integer> stack=new Stack<>();

        // Scan all characters one by one
        for(int i=0;i<exp.length();i++)
        {
            char c=exp.charAt(i);

            // If the scanned character is an operand (number here),
            // push it to the stack.
            if(Character.isDigit(c))
                stack.push(c - '0');

                //  If the scanned character is an operator, pop two
                // elements from stack apply the operator
            else
            {
                int val1 = stack.pop();
                int val2 = stack.pop();

                switch(c)
                {
                    case '+':
                        stack.push(val2+val1);
                        break;

                    case '-':
                        stack.push(val2- val1);
                        break;

                    case '/':
                        stack.push(val2/val1);
                        break;

                    case '*':
                        stack.push(val2*val1);
                        break;
                }
            }
        }
        return stack.pop();
    }


    /*
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
    */

    /*
    private boolean isOperator(String character)
    {
        return (character.equalsIgnoreCase("+") ||
                character.equalsIgnoreCase("-") ||
                character.equalsIgnoreCase("*") ||
                character.equalsIgnoreCase("/"));
    }


    private static int evaluate(int rightOperand, int leftOperand, String operator)
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
    */

    static int precedence(char ch)
    {
        switch (ch)
        {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }


}
