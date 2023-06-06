package it.italiandudes.mymcserver.threads;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import it.italiandudes.mymcserver.ConnectivitySingleton;
import it.italiandudes.mymcserver.Constants;
import it.italiandudes.mymcserver.R;
import it.italiandudes.mymcserver.activities.InfoActivity;
import it.italiandudes.mymcserver.utils.exceptions.ServerInterruptedException;
import it.italiandudes.mymcserver.utils.models.Addon;
import it.italiandudes.mymcserver.utils.models.AddonsDataRow;
import it.italiandudes.mymcserver.utils.models.Player;
import it.italiandudes.mymcserver.utils.models.PlayersDataRow;

public class ServerInfoThread extends Thread{

    private InfoActivity activity;
    private boolean isInterrupted;

    public ServerInfoThread(InfoActivity activity){
        this.activity=activity;
        isInterrupted=false;
    }

    @Override
    public void run() {
        super.run();

        while (!isInterrupted) {
            ConnectivitySingleton.getInstance().setPath(Constants.Connectivity.STATS);
            ConnectivitySingleton.getInstance().setHTTPHeader(Constants.Protocol.HTTP_Headers.MMCS_TOKEN,ConnectivitySingleton.getInstance().getToken());

            try {
                ConnectivitySingleton.getInstance().executeQueryHTTP();

                if(ConnectivitySingleton.getInstance().getInteger(Constants.Protocol.HTTP_Headers.RETURN_CODE)==Constants.Protocol.ReturnCodes.HTTP_OK){

                    double freeMemory = ConnectivitySingleton.getInstance().getDouble(Constants.Protocol.HTTP_Headers.FREE_MEMORY);
                    double systemCPULoad = ConnectivitySingleton.getInstance().getDouble(Constants.Protocol.HTTP_Headers.SYSTEM_CPU_LOAD);
                    double totalMemory = ConnectivitySingleton.getInstance().getDouble(Constants.Protocol.HTTP_Headers.TOTAL_MEMORY);
                    double processCPULoad = ConnectivitySingleton.getInstance().getDouble(Constants.Protocol.HTTP_Headers.PROCESS_CPU_LOAD);
                    double committedVirtualMemory = ConnectivitySingleton.getInstance().getDouble(Constants.Protocol.HTTP_Headers.COMMITTED_VIRTUAL_MEMORY);

                    Log.d(Constants.Log.TAG,"Free Memory: "+freeMemory+"\nSystem CPU Load: "+systemCPULoad+"\nTotal Memory: "+totalMemory+
                                            "\nProcess CPU Load: "+processCPULoad+"\nCommitted Virtual Memory: "+committedVirtualMemory);

                    activity.runOnUiThread(()->{
                        activity.drawCPUGraph(systemCPULoad,processCPULoad);
                        activity.drawRAMGraphs(totalMemory,freeMemory,committedVirtualMemory);
                        activity.clearListViews();
                    });

                    Log.d(Constants.Log.TAG,"CPU and RAM graphs loaded");

                    JSONArray players = ConnectivitySingleton.getInstance().getJSONArray(Constants.Protocol.HTTP_Headers.PLAYER_LIST);
                    boolean exit=false;
                    int i=0;
                    Log.d(Constants.Log.TAG,"Players loaded");
                    while(!exit){
                        int j=0;
                        PlayersDataRow dataRow;
                        Player p1 = new Player(players.getJSONObject(i+j).getString(Constants.Protocol.HTTP_Headers.NAME),players.getJSONObject(i+j).getBoolean(Constants.Protocol.HTTP_Headers.ONLINE));
                        j++;
                        if((i+j)<players.length()){
                            Player p2 = new Player(players.getJSONObject(i+j).getString(Constants.Protocol.HTTP_Headers.NAME),players.getJSONObject(i+j).getBoolean(Constants.Protocol.HTTP_Headers.ONLINE));
                            j++;
                            if((i+j)<players.length()){
                                Player p3 = new Player(players.getJSONObject(i+j).getString(Constants.Protocol.HTTP_Headers.NAME),players.getJSONObject(i+j).getBoolean(Constants.Protocol.HTTP_Headers.ONLINE));
                                j++;
                                if((i+j)<players.length()){
                                    Player p4 = new Player(players.getJSONObject(i+j).getString(Constants.Protocol.HTTP_Headers.NAME),players.getJSONObject(i+j).getBoolean(Constants.Protocol.HTTP_Headers.ONLINE));
                                    j++;
                                    if((i+j)<players.length()){
                                        i+=j;
                                    }else{
                                        exit=true;
                                    }
                                    dataRow = new PlayersDataRow(p1,p2,p3,p4);
                                }else{
                                    exit=true;
                                    dataRow = new PlayersDataRow(p1,p2,p3);
                                }
                            }else{
                                exit=true;
                                dataRow = new PlayersDataRow(p1,p2);
                            }
                        }else{
                            exit=true;
                            dataRow = new PlayersDataRow(p1);
                        }

                        activity.runOnUiThread(()->{
                            activity.updatePlayersList(dataRow);
                        });
                    }

                    Log.d(Constants.Log.TAG,"Players List loaded");

                    JSONArray addons = ConnectivitySingleton.getInstance().getJSONArray(Constants.Protocol.HTTP_Headers.ADDONS_LIST);
                    exit=false;
                    i=0;
                    while(!exit){
                        int j=0;
                        AddonsDataRow dataRow;
                        Addon a1 = new Addon(addons.getJSONObject(i+j).getString(Constants.Protocol.HTTP_Headers.NAME),addons.getJSONObject(i+j).getBoolean(Constants.Protocol.HTTP_Headers.ENABLED));
                        j++;
                        if((i+j)<players.length()){
                            Addon a2 = new Addon(addons.getJSONObject(i+j).getString(Constants.Protocol.HTTP_Headers.NAME),addons.getJSONObject(i+j).getBoolean(Constants.Protocol.HTTP_Headers.ENABLED));
                            j++;
                            if((i+j)<players.length()){
                                Addon a3 = new Addon(addons.getJSONObject(i+j).getString(Constants.Protocol.HTTP_Headers.NAME),addons.getJSONObject(i+j).getBoolean(Constants.Protocol.HTTP_Headers.ENABLED));
                                j++;
                                if((i+j)<players.length()){
                                    Addon a4 = new Addon(addons.getJSONObject(i+j).getString(Constants.Protocol.HTTP_Headers.NAME),addons.getJSONObject(i+j).getBoolean(Constants.Protocol.HTTP_Headers.ENABLED));
                                    j++;
                                    if((i+j)<players.length()){
                                        i+=j;
                                    }else{
                                        exit=true;
                                    }
                                    dataRow = new AddonsDataRow(a1,a2,a3,a4);
                                }else{
                                    exit=true;
                                    dataRow = new AddonsDataRow(a1,a2,a3);
                                }
                            }else{
                                exit=true;
                                dataRow = new AddonsDataRow(a1,a2);
                            }
                        }else{
                            exit=true;
                            dataRow = new AddonsDataRow(a1);
                        }

                        activity.runOnUiThread(()->{
                            activity.updateAddonsList(dataRow);
                        });
                    }

                    Log.d(Constants.Log.TAG,"Addons List loaded");
                    Log.d(Constants.Log.TAG,"All info loaded");


                }else{
                    //Print error message
                    ConnectivitySingleton.getInstance().resetConnectionAfterFailure();
                    Log.d(Constants.Log.TAG,"HTTP code returned: "+ConnectivitySingleton.getInstance().getInteger(Constants.Protocol.HTTP_Headers.RETURN_CODE));
                    activity.runOnUiThread(()->{
                        Toast.makeText(activity,activity.getString(R.string.string_error),Toast.LENGTH_LONG).show();
                    });
                }

            } catch (IOException | JSONException e) {
                ConnectivitySingleton.getInstance().resetConnectionAfterFailure();
                Log.d(Constants.Log.TAG,"JSONException or IOException thrown after calling ConnectivitySingleton.getInstance().executeQueryHTTP() or ConnectivitySingleton.getInstance().getInteger() or ConnectivitySingleton.getInstance().getString() inside ServerInfoThread#run()");
                e.printStackTrace();
                activity.runOnUiThread(()->{
                    Toast.makeText(activity,activity.getString(R.string.string_error),Toast.LENGTH_LONG).show();
                });
            } catch (ServerInterruptedException e) {
                ConnectivitySingleton.getInstance().resetConnectionAfterFailure();
                Log.d(Constants.Log.TAG,"ServerInterruptedException thrown after calling ConnectivitySingleton.getInstance().executeQueryHTTP() inside ServerInfoThread#run()");
                activity.runOnUiThread(()->{
                    Toast.makeText(activity,activity.getString(R.string.string_error_noserver),Toast.LENGTH_LONG).show();
                });
            }finally {
                try {
                    Thread.sleep(Constants.Connectivity.THREAD_SLEEP_INFO);
                } catch (InterruptedException e) {
                    ConnectivitySingleton.getInstance().resetConnectionAfterFailure();
                    Log.d(Constants.Log.TAG,"InterruptedException thrown after calling Thread.sleep() inside ServerInfoThread#run()");
                    activity.runOnUiThread(()->{
                        Toast.makeText(activity,activity.getString(R.string.string_error),Toast.LENGTH_LONG).show();
                    });
                }
            }
        }

        Log.d(Constants.Log.TAG,"ServerInfoThread() interrupted");
    }

    public void setInterrupted(boolean isInterrupted){
        this.isInterrupted=isInterrupted;
    }
}
