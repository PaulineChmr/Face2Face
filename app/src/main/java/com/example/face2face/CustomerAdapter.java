package com.example.face2face;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomerAdapter extends ArrayAdapter<Customer> {
    public CustomerAdapter(Context context, List<Customer> customers) {
        super(context, 0, customers);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Customer customer = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customer_cell, parent, false);
        }
        TextView prenom = convertView.findViewById(R.id.cellPrenom);
        TextView nom = convertView.findViewById(R.id.cellNom);

        prenom.setText(customer.getFirst_name());
        nom.setText(customer.getLast_name());

        Button addTransformationBtn = (Button) convertView.findViewById(R.id.addTransformation);

        addTransformationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(v.getContext(), CameraActivity.class);
                cameraIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(cameraIntent);
            }
        });

        return convertView;
    }

}
