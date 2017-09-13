package com.example.jianhao.saveread;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=(EditText)findViewById(R.id.et);
        String inputText = load();
        if(!TextUtils.isEmpty(inputText)){
            et.setText(inputText);
            et.setSelection(inputText.length());
            Toast.makeText(this,"success",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        String inputText = et.getText().toString();
        save(inputText);
    }
    public void save(String inputText){
        FileOutputStream out= null;
        BufferedWriter writer = null;
        try{
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(writer != null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public String load(){
        FileInputStream in =null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try{
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine())!= null){
                content.append(line);
            }
        }catch (IOException e ){
            e.printStackTrace();
        }finally {
            if(reader != null){
                try{
                    reader.close();
                }catch (IOException e ){
                    e.printStackTrace();
                }
            }
        }return content.toString();
    }
}
