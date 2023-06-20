package com.imt.face2face;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddCustomerSheet extends AppCompatActivity {
    private EditText prenom;
    private EditText nom;

    String userFName;

    String userLName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        final EditText editPrenomView = (EditText) findViewById(R.id.prenom);
        editPrenomView.setText(userFName);

        final EditText editNomView = (EditText) findViewById(R.id.nom);
        editNomView.setText(userLName);

        prenom = findViewById(R.id.prenom);
        nom = findViewById(R.id.nom);
    }

}
