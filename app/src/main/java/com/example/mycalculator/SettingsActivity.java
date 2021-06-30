package com.example.mycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    private static final int APP_THEME_RED = 0;
    private static final int APP_THEME_BLUE = 1;
    private static final int APP_THEME_GREEN = 2;
    private static final int APP_THEME_YELLOW = 3;
    public static int AppTheme = 0;
    private ImageView imageView;
    private Button okButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppThemeRed);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().hide();

        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.fon);
        okButton = findViewById(R.id.button_ok);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:
                        Toast.makeText(getApplicationContext(), "Ничего не выбрано",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_red:
                        AppTheme = APP_THEME_RED;
                        break;
                    case R.id.radio_blue:
                        AppTheme = APP_THEME_BLUE;
//                        Toast.makeText(getApplicationContext(), String.valueOf(AppTheme),
//                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio_green:
                        AppTheme = APP_THEME_GREEN;
                        break;
                    case R.id.radio_yellow:
                        AppTheme = APP_THEME_YELLOW;
                        break;

                    default:
                        break;
                }
            }
        });

        okButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(String.valueOf(AppTheme), AppTheme);
            this.startActivity(intent);
        });
    }


}
