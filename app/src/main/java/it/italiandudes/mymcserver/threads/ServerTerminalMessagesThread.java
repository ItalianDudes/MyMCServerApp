package it.italiandudes.mymcserver.threads;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

import it.italiandudes.mymcserver.ConnectivitySingleton;
import it.italiandudes.mymcserver.Constants;
import it.italiandudes.mymcserver.R;
import it.italiandudes.mymcserver.activities.TerminalActivity;

public class ServerTerminalMessagesThread extends Thread{

    private TerminalActivity activity;
    private String message;

    public ServerTerminalMessagesThread(TerminalActivity activity, String message){
        this.activity=activity;
        this.message=message;
    }

    @Override
    public void run() {
        ConnectivitySingleton.getInstance().setPath(Constants.Connectivity.COMMAND);
        ConnectivitySingleton.getInstance().setHTTPHeader(Constants.Protocol.HTTP_Headers.MMCS_TOKEN,ConnectivitySingleton.getInstance().getToken());
        ConnectivitySingleton.getInstance().setHTTPHeader(Constants.Protocol.HTTP_Headers.MMCS_COMMAND,message);

        try {
            ConnectivitySingleton.getInstance().executeQueryHTTP();

            if(ConnectivitySingleton.getInstance().getInteger(Constants.Protocol.HTTP_Headers.RETURN_CODE)==Constants.Protocol.ReturnCodes.HTTP_OK){
                activity.printServerMessageToTerminal(ConnectivitySingleton.getInstance().getString(Constants.Protocol.HTTP_Headers.COMMAND_OUTPUT));
            }else{
                //Print error message
                ConnectivitySingleton.getInstance().resetConnectionAfterFailure();
                Log.d(Constants.Log.TAG,"HTTP code returned: "+ConnectivitySingleton.getInstance().getInteger(Constants.Protocol.HTTP_Headers.RETURN_CODE));
                activity.runOnUiThread(()->{
                    Toast.makeText(activity,activity.getString(R.string.string_error),Toast.LENGTH_LONG).show();
                });
            }
        } catch (IOException | JSONException e) {
            //Print error message
            ConnectivitySingleton.getInstance().resetConnectionAfterFailure();
            Log.d(Constants.Log.TAG,"JSONException or IOException thrown after calling ConnectivitySingleton.getInstance().executeQueryHTTP() or ConnectivitySingleton.getInstance().getInteger() or ConnectivitySingleton.getInstance().getString() inside ServerTerminalMessageThread#run()");
            activity.runOnUiThread(()->{
                Toast.makeText(activity,activity.getString(R.string.string_error),Toast.LENGTH_LONG).show();
            });
        }

        super.run();
    }
}
