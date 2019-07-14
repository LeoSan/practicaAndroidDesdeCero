package com.example.applistview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv1;
    private ListView lv1;

    private String arrNombres [] = {"Samuel", "Valentina", "Jose", "Maria" };
    private String arrEdad [] = {"18", "25", "20", "18"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.inpResp);
        lv1 = (ListView)findViewById(R.id.inpList);
        // Detalle importante
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, R.layout.list_item_leo, arrNombres);

        lv1.setAdapter(adapter);
        //Recibe clases anonima tema avanzado de android
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int position, long id) {
                tv1.setText("La edad de "+lv1.getItemAtPosition(position) + "es " + arrEdad[position]+" años");

            }
        });
    }



}
