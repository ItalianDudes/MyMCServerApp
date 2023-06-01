package it.italiandudes.mymcserver.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import it.italiandudes.mymcserver.R;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


    }

    public void toTerminal(View view){
        Intent switchTerminal = new Intent(this, TerminalActivity.class);
        startActivity(switchTerminal);
    }
}