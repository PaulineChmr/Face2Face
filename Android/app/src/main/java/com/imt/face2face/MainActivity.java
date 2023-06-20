package com.imt.face2face;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView transformationListView;
    String[] transformations = new String[]{"Dents de sagesse", "Appendicite", "Pied main"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transformationListView = findViewById(R.id.transformationListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, transformations);
        transformationListView.setAdapter(adapter);
    }

    public void createCustomer(View view) {
        Intent newRecipientIntent = new Intent(this, AddCustomerSheet.class);
        startActivity(newRecipientIntent);
    }

}