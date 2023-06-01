package it.italiandudes.mymcserver.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import it.italiandudes.mymcserver.R;

public class TerminalActivity extends Activity {

    private EditText terminalEdTxt;
    private ListView terminalView;
    private ArrayList<String> terminalContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminal);

        terminalEdTxt = findViewById(R.id.terminalEdTxt);
        terminalView = findViewById(R.id.terminalView);
        terminalContent = new ArrayList<>();
        terminalView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,terminalContent));
    }

    public void toInfo(View view){
        Intent switchInfo = new Intent(this,InfoActivity.class);
        startActivity(switchInfo);
    }

    public void printServerMessageToTerminal(String message){
        terminalContent.add(message);

        ((ArrayAdapter<String>)terminalView.getAdapter()).notifyDataSetChanged();
    }

    public void printLocalMessageToTerminal(View view){

    }
}