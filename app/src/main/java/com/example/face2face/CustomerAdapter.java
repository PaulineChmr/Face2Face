package com.example.face2face;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.List;

public class CustomerAdapter extends ArrayAdapter<Customer> {
    private Context adContext;
    public CustomerAdapter(Context context, List<Customer> customers) {
        super(context, 0, customers);
        adContext = context;
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

        ImageButton beforeButton = (ImageButton) convertView.findViewById(R.id.beforeButton);
        ImageButton afterButton = (ImageButton) convertView.findViewById(R.id.afterButton);

        beforeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(adContext, android.Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) adContext, new String[]{
                            Manifest.permission.CAMERA
                    }, 100);
                }
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ((Activity) adContext).startActivityForResult(intent, 100);

            }
        });

        afterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ((Activity) adContext).startActivityForResult(intent, 200);
            }
        });

        return convertView;
    }

}
