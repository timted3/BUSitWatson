package com.example.issd.busit_watson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MedListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_list);

        //testdata

        final String medicine[] = {"Paracetamol", "Morphine", "Inbrufen", "Avandary", "Kemstro", "Theophylline", "Accupil", "Silener", "Sudafed", "Xanax"};

        ArrayAdapter<String> medicineAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, medicine);

        final ListView listMeds = findViewById(R.id.listMeds);

        listMeds.setAdapter(medicineAdapter);




    }
}
