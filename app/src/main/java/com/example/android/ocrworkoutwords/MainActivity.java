package com.example.android.ocrworkoutwords;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    String[] AllPermissions = {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}; // add the permissions to be asked
    public static final int REQUEST_PERMISSION_CODE = 7;
    int CAMERA_ACTIVITY_CODE  = 7;
    ImageView photo;
    Button btn;
    TextView textView;
    private TextRecognizer detectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        photo = findViewById(R.id.photo);
        textView = findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!hasPermissions(MainActivity.this, AllPermissions)){
                    Toast.makeText(MainActivity.this,"Requesting Permissions",Toast.LENGTH_SHORT).show();
                    requestPermissions(AllPermissions,REQUEST_PERMISSION_CODE);

                }
                else
                   runTheCamera();
                    Toast.makeText(MainActivity.this,"You already gave all the permissions",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean hasPermissions(Context context, String[] permissions){
        if (context !=null&& permissions !=null){
            Toast.makeText(MainActivity.this,"Check if permissions are granted",Toast.LENGTH_SHORT).show();
            for (String permission: permissions){
                if (checkSelfPermission(permission)==PackageManager.PERMISSION_DENIED)
                    return false;
            }
        }
        return true;
    }

    private void runTheCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMERA_ACTIVITY_CODE);
        File photo = new File(Environment.getExternalStorageDirectory(),"picture.jpg")
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_ACTIVITY_CODE && resultCode == RESULT_OK) {

            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            photo.setImageBitmap(bitmap);


        }
    }
}