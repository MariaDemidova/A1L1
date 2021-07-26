package com.example.a1l1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CalculLogic calculator;
    private TextView text;
    public static final String PARAM = "PARAM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) setTheme(R.style.dark);
        else setTheme(R.style.CalcTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] numbers = new int[]{
                R.id.button0,
                R.id.button1,
                R.id.button2,
                R.id.button3,
                R.id.button4,
                R.id.button5,
                R.id.button6,
                R.id.button7,
                R.id.button8,
                R.id.button9
        };

        int[] actions = new int[]{
                R.id.button_division,
                R.id.button_minus,
                R.id.button_plus,
                R.id.button_multiplication,
                R.id.button_equals
        };

        text = findViewById(R.id.text);
        calculator = new CalculLogic();

        findViewById(R.id.settings_button).setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class );
            startActivity(intent);
        });

        View.OnClickListener numberButtonClickListener = v -> {
            calculator.buttonPressed(v.getId());
            text.setText(calculator.getText());
        };

        View.OnClickListener actionClickListener = v -> {
            calculator.actionPressed(v.getId());
            text.setText(calculator.getText());
        };

        for (int i = 0; i < numbers.length; i++) {
            findViewById(numbers[i]).setOnClickListener(numberButtonClickListener);
        }

        for (int i = 0; i < actions.length; i++) {
            findViewById(actions[i]).setOnClickListener(actionClickListener);
        }
        findViewById(R.id.button_clear).setOnClickListener(view -> {
            calculator.reset();
            text.setText(calculator.getText());
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(PARAM, text.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        text.setText(savedInstanceState.getString(PARAM));
    }
    private void restartApp() {
        recreate();
    }
}