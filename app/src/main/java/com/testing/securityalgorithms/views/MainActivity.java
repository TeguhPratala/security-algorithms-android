package com.testing.securityalgorithms.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.testing.securityalgorithms.R;
import com.testing.securityalgorithms.algorithms.Algorithms;
import com.testing.securityalgorithms.algorithms.RC4;
import com.testing.securityalgorithms.algorithms.Vigenere26Char;
import com.testing.securityalgorithms.algorithms.Vigenere256Char;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText plainText, keyText, cipherText;
    private Spinner spinner;
    private Algorithms algorithms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plainText = findViewById(R.id.et_text);
        keyText = findViewById(R.id.et_key);
        cipherText = findViewById(R.id.et_ciper_text);
        spinner = findViewById(R.id.spinner);

        initSpinner();
    }

    private void initSpinner() {
        ArrayAdapter<Algorithms> adapter =
                new ArrayAdapter<>(this, R.layout.spinner_snippet
                        , R.id.spinner_snippet_tv, getListItem());

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    public void encryptClicked(View view){
        try{
            algorithms.initKey(keyText.getText().toString());
            String text = plainText.getText().toString();
            String result = algorithms.encrypt(text);
            this.cipherText.setText(result);

        }catch (Exception ex){

        }
    }

    public void decryptClicked(View view){
        try{
            algorithms.initKey(keyText.getText().toString());
            String text = plainText.getText().toString();
            String result = algorithms.decrypt(text);
            this.cipherText.setText(result);

        }catch (Exception ex){

        }
    }

    public void swapClicked(View view){
        String tempPlain = cipherText.getText().toString();
        cipherText.setText("");
        plainText.setText(tempPlain);
    }

    public void clearClicked(View view){

        cipherText.setText("");
        plainText.setText("");
        keyText.setText("");
    }

    public void sourceCodeClicked(View v){
        Uri uri = Uri.parse("https://github.com/TeguhPratala/security-algorithms-android.git");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public List<Algorithms> getListItem(){
        ArrayList<Algorithms> listItem = new ArrayList<>();
        listItem.add(new RC4(this));
        listItem.add(new Vigenere26Char(this));
        listItem.add(new Vigenere256Char(this));
        return listItem;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        algorithms = (Algorithms) adapterView.getItemAtPosition(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
