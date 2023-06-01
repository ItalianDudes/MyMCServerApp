package it.italiandudes.mymcserver.threads;

import android.app.Activity;

public class ServerTerminalMessagesThread extends Thread{

    private Activity activity;

    public ServerTerminalMessagesThread(Activity activity){
        this.activity=activity;
    }

    @Override
    public void run() {
        super.run();
    }
}
