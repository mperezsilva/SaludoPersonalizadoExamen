package com.example.mperezsilva.saludopersonalizado;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
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
    public void cambio(View v){
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
    }
}
