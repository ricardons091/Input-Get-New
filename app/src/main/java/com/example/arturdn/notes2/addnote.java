package com.example.arturdn.notes2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class addnote extends ActionBarActivity {

    private String variabletitol;
    private String variablenota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        //ImageView iv = (ImageView) new ImageView(this);
        //iv.setImageResource();

        variabletitol = intent.getStringExtra(nota.TITOL);
        TextView titol = (TextView) new TextView(this);
        titol.setTextSize(30);
        titol.setText(variabletitol);

        variablenota = intent.getStringExtra(nota.NOTA);
        TextView nota = (TextView) new TextView(this);
        nota.setTextSize(30);
        nota.setText(variablenota);

        LinearLayout lv = new LinearLayout(this);
        lv.setOrientation(LinearLayout.VERTICAL);
        lv.addView(titol);
        lv.addView(nota);
        setContentView(lv);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_addnote, menu);
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

    @Override
    public void onBackPressed() {
       insertar();
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp () {
        insertar();
        return super.onSupportNavigateUp();
    }

    private void insertar() {
        DBProxy basenotes = new DBProxy(getApplicationContext());
        basenotes.insertar(variabletitol, variablenota);
    }
}
