package it.italiandudes.mymcserver.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import it.italiandudes.mymcserver.R;
import it.italiandudes.mymcserver.threads.ServerTerminalMessagesThread;

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
        new Thread(()->{
            terminalContent.add(">\t"+message);

            runOnUiThread(()->{
                ((ArrayAdapter<String>)terminalView.getAdapter()).notifyDataSetChanged();
            });
        }).start();
    }

    public void printLocalMessageToTerminal(View view){
        new Thread(()->{
            String message = terminalEdTxt.getText().toString();

            if(message.isEmpty() || message.isBlank()){
                runOnUiThread(()->{
                    Toast.makeText(this,getString(R.string.string_terminal_error),Toast.LENGTH_LONG).show();
                });
            }else{
                terminalContent.add(">\t"+message);
                runOnUiThread(()->{
                    ((ArrayAdapter<String>)terminalView.getAdapter()).notifyDataSetChanged();
                });
                new Thread(new ServerTerminalMessagesThread(this,message)).start();
            }
        }).start();
    }
}