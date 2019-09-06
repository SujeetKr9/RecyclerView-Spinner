package com.techcrevices.recyclerviewspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText et = (EditText) findViewById(R.id.et);
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
                DialogHandler.createSingleItemDialog(MainActivity.this, Arrays.asList(numbers),
                        "No. Of Unmarried Brothers", s -> {
                    et.setText(s);
                });

            }
        });

    }
}
