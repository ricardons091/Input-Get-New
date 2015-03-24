package com.example.arturdn.notes2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;


public class nota extends ActionBarActivity {

    public final static String TITOL = "com.mycompany.PracticaFormulari.titol";
    public final static String NOTA = "com.mycompany.PracticaFormulari.nota";
    public final static String ID = "com.mycompany.PracticaFormulari.id";

    public int idNote = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        show3notes(idNote);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nota, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void sendMessage(View view) {
        Intent intent = new Intent(this, addnote.class);

        EditText editText = (EditText) findViewById(R.id.titol);
        String message = editText.getText().toString();
        intent.putExtra(TITOL, message);

        editText = (EditText) findViewById(R.id.nota);
        message = editText.getText().toString();
        intent.putExtra(NOTA, message);

        startActivity(intent);
    }

    public void sendMessage1(View view) {
        Intent intent = new Intent(this, addnote2.class);

        TextView tv = (TextView) findViewById(R.id.titol1);
        String message = tv.getText().toString();
        intent.putExtra(TITOL, message);

        tv = (TextView) findViewById(R.id.nota1);
        message = tv.getText().toString();
        intent.putExtra(NOTA, message);

        intent.putExtra(ID, idNote);

        startActivity(intent);
    }

    private void show3notes(int id){
        idNote = id;
        Infonota minota = new Infonota();
        DBProxy basenotes = new DBProxy(getApplicationContext());
        TextView tv;

        basenotes.notesperid(idNote, minota);
        tv = (TextView) findViewById(R.id.titol1);
        tv.setText(minota.title);
        tv = (TextView) findViewById(R.id.nota1);
        tv.setText(minota.body);

        basenotes.notesperid(idNote+1, minota);
        tv = (TextView) findViewById(R.id.titol2);
        tv.setText(minota.title);
        tv = (TextView) findViewById(R.id.nota2);
        tv.setText(minota.body);

        basenotes.notesperid(idNote+2, minota);
        tv = (TextView) findViewById(R.id.titol3);
        tv.setText(minota.title);
        tv = (TextView) findViewById(R.id.nota3);
        tv.setText(minota.body);
    }

    public void borrar(View view) {
        getApplicationContext().deleteDatabase(DBProxy.DB_NAME);
        show3notes(idNote);
    }

    public void Down(View view) {
        show3notes(idNote-1);
    }

    public void Up(View view) {
        show3notes(idNote+1);
    }

 }
