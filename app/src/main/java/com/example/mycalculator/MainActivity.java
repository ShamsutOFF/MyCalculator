package com.example.mycalculator;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "@@@ MainActivity";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        findViewById(R.id.button_0).setOnClickListener(v -> updateTextView("0"));
        findViewById(R.id.button_1).setOnClickListener(v -> updateTextView("1"));
        findViewById(R.id.button_2).setOnClickListener(v -> updateTextView("2"));
        findViewById(R.id.button_3).setOnClickListener(v -> updateTextView("3"));
        findViewById(R.id.button_4).setOnClickListener(v -> updateTextView("4"));
        findViewById(R.id.button_5).setOnClickListener(v -> updateTextView("5"));
        findViewById(R.id.button_6).setOnClickListener(v -> updateTextView("6"));
        findViewById(R.id.button_7).setOnClickListener(v -> updateTextView("7"));
        findViewById(R.id.button_8).setOnClickListener(v -> updateTextView("8"));
        findViewById(R.id.button_9).setOnClickListener(v -> updateTextView("9"));

        findViewById(R.id.button_ac).setOnClickListener(v -> clearTextInTextView());
        findViewById(R.id.button_deleteLast).setOnClickListener(v -> deleteLastTextInTextView());

//        findViewById(R.id.button_point).setOnClickListener(v -> motion("."));
        findViewById(R.id.button_plus).setOnClickListener(v -> motion("+"));
        findViewById(R.id.button_minus).setOnClickListener(v -> motion("-"));
        findViewById(R.id.button_multiply).setOnClickListener(v -> motion("x"));
        findViewById(R.id.button_divide).setOnClickListener(v -> motion("/"));

        findViewById(R.id.button_equals).setOnClickListener(v -> myEquals());
    }

    private void updateTextView(String a) {
        textView = findViewById(R.id.textView);
        if (textView.getText().equals("0")) {
            textView.setText(a);
        } else {
            String s = textView.getText() + a;
            textView.setText(s);
        }
    }

    private void clearTextInTextView() {
        textView = findViewById(R.id.textView);
        textView.setText("0");
    }

    private void deleteLastTextInTextView() {
        textView = findViewById(R.id.textView);
        String s = (String) textView.getText();
        if (s.length() == 1) {
            clearTextInTextView();
        } else {
            s = s.substring(0, s.length() - 1);
            textView.setText(s);
        }
    }

    private void motion(String a) {
        textView = findViewById(R.id.textView);
        String s = (String) textView.getText();
        if (s.contains("+") || s.contains("-") || s.contains(".") || s.contains("/") || s.contains("x")) {
            textView.setText(s);
        } else {
            s = textView.getText() + a;
            textView.setText(s);
        }
    }

    private void minus(String a) {
        textView = findViewById(R.id.textView);
        String s = (String) textView.getText();
        if (s.contains("+") || s.contains("-") || s.contains(".")) {
            textView.setText(s);
        } else {
            s = textView.getText() + a;
            textView.setText(s);
        }
    }

    private void point(String a) {
        textView = findViewById(R.id.textView);
        String s = (String) textView.getText();
        if (s.contains("+") || s.contains("-") || s.contains(".")) {
            textView.setText(s);
        } else {
            s = textView.getText() + a;
            textView.setText(s);
        }
    }

    private void myEquals() {
        textView = findViewById(R.id.textView);
        String s = (String) textView.getText();
        String[] numbers = new String[0];
        if (s.contains("+")) {
            numbers = s.split("\\+");
            int one = Integer.parseInt(numbers[0].trim());
            int two = Integer.parseInt(numbers[1].trim());
            Integer rezult = one + two;
            textView.setText(rezult.toString());
        }
        if (s.contains("-")) {
            numbers = s.split("-");
            int one = Integer.parseInt(numbers[0].trim());
            int two = Integer.parseInt(numbers[1].trim());
            Integer rezult = one - two;
            textView.setText(rezult.toString());
        }
        if (s.contains("x")) {
            numbers = s.split("x");
            int one = Integer.parseInt(numbers[0].trim());
            int two = Integer.parseInt(numbers[1].trim());
            Integer rezult = one * two;
            textView.setText(rezult.toString());
        }
        if (s.contains("/")) {
            numbers = s.split("/");
            int one = Integer.parseInt(numbers[0].trim());
            int two = Integer.parseInt(numbers[1].trim());
            Integer rezult = one / two;
            textView.setText(rezult.toString());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}