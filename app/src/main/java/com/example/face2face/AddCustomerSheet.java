package com.example.face2face;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;

public class AddCustomerSheet extends AppCompatActivity{

    private EditText prenom, nom;
    private Button deleteButton;
    private Customer selectedCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        initWidgets();
        checkForEditCustomer();
    }

    private void initWidgets(){
        prenom = findViewById(R.id.prenom);
        nom = findViewById(R.id.nom);
        deleteButton = findViewById(R.id.delete);
    }

    private void checkForEditCustomer() {
        Intent previousIntent = getIntent();
        int passedCustomerID = previousIntent.getIntExtra(Customer.CUSTOMER_EDIT_EXTRA, -1);
        selectedCustomer = Customer.getCustomerForID(passedCustomerID);

        if (selectedCustomer != null){
            prenom.setText(selectedCustomer.getFirst_name());
            nom.setText(selectedCustomer.getLast_name());
        }
        else{
            deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    public void saveCustomer(View view){
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String pre = String.valueOf(prenom.getText());
        String no = String.valueOf(nom.getText());

        if(selectedCustomer == null) {
            int id = Customer.customerArrayList.size();
            Customer newCustomer = new Customer(id, pre, no);
            Customer.customerArrayList.add(newCustomer);
            sqLiteManager.addCustomerToDatabase(newCustomer);
        }
        else{
            selectedCustomer.setFirst_name(pre);
            selectedCustomer.setLast_name(no);
            sqLiteManager.updateCustomerInDB(selectedCustomer);
        }
        Intent customersListIntent = new Intent(this, MainActivity.class);
        startActivity(customersListIntent);
        //finish();
    }

    public void deleteCustomer(View view){
        selectedCustomer.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateCustomerInDB(selectedCustomer);
        Intent customersListIntent = new Intent(this, MainActivity.class);
        startActivity(customersListIntent);
        //finish();
    }
}
