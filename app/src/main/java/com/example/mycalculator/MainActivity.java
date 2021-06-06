package com.example.mycalculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "@@@ MainActivity";
    private static final String TEXT_KEY = "TextViewKey";
    private static final String NameSharedPreference = "LOGIN";  // Имя настроек
    private TextView textView;
    private static final String AppTheme = "APP_THEME";   // Имя параметра в настройках
    private static final int AppThemeBlue = 0;
    private static final int AppThemeRed = 1;
    private Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.AppThemeBlue));//
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        textView = findViewById(R.id.textView);
        switch1 = findViewById(R.id._switch);


        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((Switch) v).isChecked();
                if (checked) {
                    setAppTheme(AppThemeBlue);
                    recreate();
                } else {
                    setAppTheme(AppThemeRed);
                    recreate();
                }
            }
        });

        if (savedInstanceState != null && savedInstanceState.containsKey(TEXT_KEY)) {
            textView.setText(savedInstanceState.getString(TEXT_KEY));
        }

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

    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    // Чтение настроек, параметр «тема»
    private int getCodeStyle(int codeStyle) {
// Работаем через специальный класс сохранения и чтения настроек
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference, MODE_PRIVATE);
//Прочитать тему, если настройка не найдена - взять по умолчанию
        return sharedPref.getInt(AppTheme, codeStyle);
    }

    // Сохранение настроек
    private void setAppTheme(int codeStyle) {
        SharedPreferences sharedPref = getSharedPreferences(NameSharedPreference,
                MODE_PRIVATE);
// Настройки сохраняются посредством специального класса editor.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(AppTheme, codeStyle);
        editor.apply();
    }

    private int codeStyleToStyleId(int codeStyle) {
        switch (codeStyle) {
            case AppThemeBlue:
                return R.style.AppThemeBlue;
            case AppThemeRed:
                return R.style.AppThemeRed;
            default:
                return R.style.AppTheme;
        }
    }

    private void updateTextView(String a) {

        if (textView.getText().equals("0")) {
            textView.setText(a);
        } else {
            String s = textView.getText() + a;
            textView.setText(s);
        }
    }

    private void clearTextInTextView() {
        textView.setText("0");
    }

    private void deleteLastTextInTextView() {
        String s = (String) textView.getText();
        if (s.length() == 1) {
            clearTextInTextView();
        } else {
            s = s.substring(0, s.length() - 1);
            textView.setText(s);
        }
    }

    private void motion(String a) {
        String s = (String) textView.getText();
        if (s.contains("+") || s.contains("-") || s.contains(".") || s.contains("/") || s.contains("x")) {
            textView.setText(s);
        } else {
            s = textView.getText() + a;
            textView.setText(s);
        }
    }


    private void point(String a) {
        String s = (String) textView.getText();
        if (s.contains("+") || s.contains("-") || s.contains(".")) {
            textView.setText(s);
        } else {
            s = textView.getText() + a;
            textView.setText(s);
        }
    }

    private void myEquals() {
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
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(TEXT_KEY, (String) textView.getText());
        super.onSaveInstanceState(outState);
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