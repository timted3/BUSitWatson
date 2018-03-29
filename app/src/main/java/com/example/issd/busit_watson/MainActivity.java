package com.example.issd.busit_watson;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;
    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.imageView);

        // Create an instance of Camera
        //mCamera = getCameraInstance();

//        // Create our Preview view and set it as the content of our activity.
//        mPreview = new CameraPreview(this, mCamera);
//        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
//        preview.addView(mPreview);


        //get interface objects
        final Button btnCamera = findViewById(R.id.btnCamera);
        final Button btnNext = findViewById(R.id.btnNext);



        //testdata

        final String items[] = {"Paracetamol", "Morphine", "Inbrufen", "Avandary", "Kemstro", "Theophylline"};

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);


        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            dispatchTakePictureIntent();
            }
        });


    }
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    private boolean checkCameraHardware(Context context) {
        return(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA));
    }



    /**
     * A safe way to get an instance of the Camera object.
     */
    public static Camera getCameraInstance() {
        Camera c = null;
        try {

            c = Camera.open(0); // attempt to get a Camera instance



        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
            String TAG = "Camera";
            Log.d(TAG, e.getMessage());
        }
        return c; // returns null if camera is unavailable
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
            VisualRecognition service =
                    new VisualRecognition("{2016-05-20}", "{cec33a82f6f86557667f9c29f0ef6b80593f17ef}" );

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();
            ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);


            // InputStream imamgesStream = new FileInputStream(imageBitmap)
            ClassifyOptions classifyOptions = new  ClassifyOptions.Builder()
                    .imagesFile(bs)
                    //    .threshold(0.6f)
                .build();
           /* ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
                    .imagesFile(bs).parameters("{"classifier_ids": ["fruits_1462128776",
                    + "SatelliteModel_6242312846"],"threshold": 0.6}")).build(); */
           ClassifiedImages result = service.classify(classifyOptions).execute();

           //System.out.println(result);
            
          /*  VisualRecognition.method(parameters).enqueue(new ServiceCallback<ReturnType>() {
                @Override public void onResponse(ReturnType response) {
          
                }
                @Override public void onFailure(Exception e) {
                }
            });*/

        }
    }
}
