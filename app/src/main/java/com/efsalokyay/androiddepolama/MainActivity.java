package com.efsalokyay.androiddepolama;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText girdi_text;
    private Button yaz_btn;
    private Button oku_btn;
    private Button sil_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        girdi_text = findViewById(R.id.girdi_text);
        yaz_btn = findViewById(R.id.yaz_btn);
        sil_btn = findViewById(R.id.sil_btn);
        oku_btn = findViewById(R.id.oku_btn);

        yaz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hariciYaz();
                dahiliYaz();
            }
        });

        sil_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hariciSil();
                dahiliSil();
            }
        });

        oku_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hariciOku();
                dahiliOku();
            }
        });
    }

    public void hariciYaz() {

        try {
            File dosya_yolu = Environment.getExternalStorageDirectory();

            File dosya = new File(dosya_yolu, "dosyam.txt");

            if (!dosya.exists()) {
                dosya.createNewFile();
            }

            FileWriter writer = new FileWriter(dosya);

            BufferedWriter yazici = new BufferedWriter(writer);
            yazici.write(girdi_text.getText().toString());
            yazici.flush();
            yazici.close();
            writer.close();

            girdi_text.setText("");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hariciOku() {

        try {
            File dosya_yolu = Environment.getExternalStorageDirectory();

            File dosya = new File(dosya_yolu, "dosyam.txt");

            FileReader fr = new FileReader(dosya);

            BufferedReader okuyucu = new BufferedReader(fr);

            StringBuilder sb = new StringBuilder();
            String satir = "";

            while ((satir = okuyucu.readLine()) != null) {

                sb.append(satir + "\n");
            }

            okuyucu.close();

            girdi_text.setText(sb.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hariciSil() {

        File dosya_yolu = Environment.getExternalStorageDirectory();

        File dosya = new File(dosya_yolu, "dosyam.txt");

        dosya.delete();

        girdi_text.setText("");


    }

    public void dahiliYaz() {

        try {

            FileOutputStream fos = openFileOutput("dosyam.txt", MODE_PRIVATE);

            OutputStreamWriter yazici = new OutputStreamWriter(fos);

            yazici.write(girdi_text.getText().toString());
            yazici.close();

            girdi_text.setText("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dahiliOku() {

        try {

            FileInputStream fis = openFileInput("dosyam.txt");

            InputStreamReader isr = new InputStreamReader(fis);

            BufferedReader okuyucu = new BufferedReader(isr);

            StringBuilder sb = new StringBuilder();

            String satir = "";

            while ((satir = okuyucu.readLine()) != null) {

                sb.append(satir + "\n");
            }

            okuyucu.close();

            girdi_text.setText(sb.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dahiliSil() {

        File yol = getFilesDir();

        File file = new File(yol, "dosyam.txt");

        file.delete();

        girdi_text.setText("");
    }
}
