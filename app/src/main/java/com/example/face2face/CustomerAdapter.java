package com.example.face2face;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
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
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomerAdapter extends ArrayAdapter<Customer> {
    private Context adContext;
    private String photoPath;
    public CustomerAdapter(Context context, List<Customer> customers) {
        super(context, 0, customers);
        adContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        adContext = parent.getContext();
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
                int id_before_picture = customer.getId() * 10 + 1;
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ((Activity) adContext).startActivityForResult(intent, id_before_picture);

            }
        });

        afterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                File photoDir = adContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                try {
                    File photoFile = File.createTempFile("photo" + time, ".jpg", photoDir);
                    photoPath = photoFile.getAbsolutePath();
                    Uri photoUri = FileProvider.getUriForFile(adContext, "com.example.face2face.provider", photoFile);
                    int id_after_picture = customer.getId() * 10 + 2;
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ((Activity) adContext).startActivityForResult(intent, id_after_picture);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return convertView;
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.d("MyAdapter", "onActivityResult");
        Log.i(TAG, String.valueOf(resultCode));
    }


}
