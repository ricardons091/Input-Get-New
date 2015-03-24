package com.example.arturdn.notes2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class addnote2 extends ActionBarActivity {

    private String variabletitol;
    private String variablenota;
    private int idNote;

    private EditText titol;
    private EditText body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        //ImageView iv = (ImageView) new ImageView(this);
        //iv.setImageResource();

        variabletitol = intent.getStringExtra(nota.TITOL);
        titol = (EditText) new EditText(this);
        titol.setTextSize(30);
        titol.setText(variabletitol);

        variablenota = intent.getStringExtra(nota.NOTA);
        body = (EditText) new EditText(this);
        body.setTextSize(30);
        body.setText(variablenota);

        idNote = intent.getIntExtra(nota.ID, 1);
        LinearLayout lv = new LinearLayout(this);
        lv.setOrientation(LinearLayout.VERTICAL);
        lv.addView(titol);
        lv.addView(body);
        setContentView(lv);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_addnote2, menu);
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

    public boolean onSupportNavigateUp () {
        actualitzar();
        return super.onSupportNavigateUp();
    }

    private void actualitzar() {
        variabletitol = titol.getText().toString();
        variablenota = body.getText().toString();

        DBProxy basenotes = new DBProxy(getApplicationContext());
        basenotes.actualitzar(idNote, variabletitol, variablenota);
    }

}
