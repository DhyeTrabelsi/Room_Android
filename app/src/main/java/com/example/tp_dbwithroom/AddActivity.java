package com.example.tp_dbwithroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private EditText nom;
    private EditText prenom;
    private EditText num;
    private EditText adresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        num = findViewById(R.id.num);
        adresses = findViewById(R.id.adresses);
        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(nom.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = nom.getText().toString();
                    replyIntent.putExtra("nom", nom.getText().toString());
                    replyIntent.putExtra("prenom", prenom.getText().toString());
                    replyIntent.putExtra("num", num.getText().toString());
                    replyIntent.putExtra("adresses",adresses.getText().toString());
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }

    }
