package com.example.face2face;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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
        //setOnClickListener();
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

    /*private void setOnClickListener() {
        customerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Customer selectedCustomer = (Customer) customerListView.getItemAtPosition(position);
                Intent editCustomerIntent = new Intent(getApplicationContext(), AddCustomerSheet.class);
                editCustomerIntent.putExtra(Customer.CUSTOMER_EDIT_EXTRA, selectedCustomer.getId());
                startActivity(editCustomerIntent);
            }
        });
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CustomerAdapter.onActivityResult(requestCode, resultCode, data);
        /*super.onActivityResult(requestCode, resultCode, data);*/
        Log.i(TAG, String.valueOf(resultCode));
        if (resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            MediaStore.Images.Media.insertImage(getContentResolver(), image, String.valueOf(requestCode), "description");
        }
        else{
            Log.i(TAG, "pas sauvegardé");
        }
    }

}