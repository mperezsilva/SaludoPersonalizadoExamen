package com.example.mperezsilva.saludopersonalizado;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;


public class MyActivity extends Activity {

    String li;
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Button button = (Button) findViewById(R.id.b_saludo);
        sp = (Spinner) findViewById(R.id.spHol);
        ArrayList<String> al = new ArrayList<String>();
        al.add(getResources().getString(R.string.hola));
        al.add(getResources().getString(R.string.adios));
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, al);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adaptador);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text = (EditText) findViewById(R.id.entrada);
                if ("".equals(text.getText().toString().trim())) {
                    //showAlert();
                    showToast();
                    return;
                }
                String salutation = null;
                String sali = null;
                String enteredName = text.getText().toString();
                RadioGroup radio = (RadioGroup) findViewById(R.id.grupoRb);
                //RadioGroup radioH = (RadioGroup) findViewById(R.id.radioBha);
                if (R.id.Sr == radio.getCheckedRadioButtonId()) {
                    salutation = getResources().getString(R.string.sr).toLowerCase();
                } else {
                    salutation = getResources().getString(R.string.sra).toLowerCase();
                }
                /*if (R.id.bHola == radioH.getCheckedRadioButtonId()) {
                    sali = getResources().getString(R.string.hola);
                } else {
                    sali = getResources().getString(R.string.adios);
                }*/
                sali = listarSpinner();
                salutation = sali + " " + salutation + " " + enteredName;
                CheckBox timeCheckBox = (CheckBox) findViewById(R.id.checkBox);
                if (timeCheckBox.isChecked()) {
                    DatePicker date = (DatePicker) findViewById(R.id.datePicker);
                    String dateToShow = date.getDayOfMonth() + "/" + (date.getMonth() + 1) + "/" + date.getYear();
                    TimePicker time = (TimePicker) findViewById(R.id.timePicker);
                    dateToShow += " " + time.getCurrentHour() + ":" + time.getCurrentMinute();
                    salutation += " " + dateToShow;
                }
                /*Intent intent = new Intent(MyActivity.this,Salutation.class);
                intent.putExtra("salutation", salutation);
                startActivity(intent );*/
                TextView sal = (TextView) findViewById(R.id.saludo);
                sal.setText(salutation);

            }
        });
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int visibility = isChecked ? View.VISIBLE : View.GONE;
                View view = findViewById(R.id.timePicker);
                view.setVisibility(visibility);
                view = findViewById(R.id.datePicker);
                view.setVisibility(visibility);
            }
        });
    }

    public String listarSpinner() {
        /*Spinner sp= (Spinner) findViewById(R.id.spHol);
        ArrayList<String> al=new ArrayList<String>();
        al.add(getResources().getString(R.string.hola));
        al.add(getResources().getString(R.string.adios));
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, al);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adaptador);*/
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Toast.makeText(arg0.getContext(), "Seleccionado: " + arg0.getItemAtPosition(arg2).toString(), Toast.LENGTH_SHORT).show();
                li = arg0.getItemAtPosition(arg2).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        return li;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void showAlert() {
        CharSequence text = getResources().getString(R.string.noNameMsg);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(text);
        alert.setPositiveButton(android.R.string.ok, null);
        alert.show();
    }

    protected void showToast() {
        Context context = getApplicationContext();
        CharSequence text = getResources().getString(R.string.noNameMsg);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    /*public void cambio(View v){
        String salu=null;
        EditText entra= (EditText) findViewById(R.id.entrada);
        Button bot= (Button) findViewById(R.id.b_saludo);
        TextView sal=(TextView) findViewById(R.id.saludo);
        RadioGroup rb= (RadioGroup) findViewById(R.id.grupoRb);
        if (R.id.Sr == rb.getCheckedRadioButtonId()){
            salu = getResources().getString(R.string.sr).toLowerCase();
        }
        else{
            salu = getResources().getString(R.string.sra).toLowerCase();
        }
        sal.setText(bot.getResources().getString(R.string.hola)+" "+salu+" "+entra.getText());
    }*/
}
