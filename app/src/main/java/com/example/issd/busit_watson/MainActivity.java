package com.example.issd.busit_watson;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an instance of Camera
        mCamera = getCameraInstance();

//        // Create our Preview view and set it as the content of our activity.
//        mPreview = new CameraPreview(this, mCamera);
//        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
//        preview.addView(mPreview);


        //get interface objects
        final Button btnCamera = findViewById(R.id.btnCamera);
        final Button btnNext = findViewById(R.id.btnNext);
        final ListView medList = findViewById(R.id.listMed);


        //testdata

        final String items[] = {"Paracetamol", "Morphine", "Inbrufen", "Avandary", "Kemstro", "Theophylline"};

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        medList.setAdapter(itemsAdapter);

        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {





            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {









            }
        });



    }

    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            //test
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
}