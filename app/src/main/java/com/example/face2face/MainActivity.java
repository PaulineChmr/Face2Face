package com.example.face2face;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView customerListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customerListView = findViewById(R.id.customerListView);
        loadFromDBToMemory();
        setCustomerAdapter();
        setOnClickListener();
    }

    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateCustomerListArray();
    }


    private void setCustomerAdapter(){
        CustomerAdapter customerAdapter = new CustomerAdapter(getApplicationContext(), Customer.nonDeleteCustomers());
        customerListView.setAdapter(customerAdapter);
    }

    public void createCustomer(View view) {
        Intent newCustomerIntent = new Intent(view.getContext(), AddCustomerSheet.class);
        startActivity(newCustomerIntent);
    }

    public void createTransfo(View view){
        Intent cameraIntent = new Intent(view.getContext(), CameraActivity.class);
        view.getContext().startActivity(cameraIntent);
    }

    private void setOnClickListener() {
        customerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Customer selectedCustomer = (Customer) customerListView.getItemAtPosition(position);
                Intent editCustomerIntent = new Intent(getApplicationContext(), AddCustomerSheet.class);
                editCustomerIntent.putExtra(Customer.CUSTOMER_EDIT_EXTRA, selectedCustomer.getId());
                startActivity(editCustomerIntent);
            }
        });
    }

}