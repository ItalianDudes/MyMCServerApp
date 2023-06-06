package it.italiandudes.mymcserver;

import android.util.Log;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import it.italiandudes.mymcserver.utils.HTTPHeader;
import it.italiandudes.mymcserver.utils.exceptions.ServerInterruptedException;

public class ConnectivitySingleton {

    private static ConnectivitySingleton instance;

    private String url;
    private String urlPath;
    private JSONObject risposta;

    private ArrayList<HTTPHeader> headerList;
    private String token;

    private ConnectivitySingleton(){
        headerList = new ArrayList<>();
    }

    synchronized public static ConnectivitySingleton getInstance(){
        if(instance==null){
            instance=new ConnectivitySingleton();
        }

        return instance;
    }

    /**
     * setURL only sets the URL string.
     * */
    public void setURL(String ip, int port){
        url = Constants.Connectivity.HTTP+ip+":"+port;
        Log.d(Constants.Log.TAG,"Indirizzo URL: "+url);
    }

    /**
     * setPath lets you specify the path to the resource needed.
     * */
    public void setPath(String path){
        urlPath = url+"/"+path;
        Log.d(Constants.Log.TAG,"Indirizzo URL completo: "+urlPath);
    }

    /**
     * addGETQuery sets the GET query, meaning the querystring. This step isn't necessary.
     *
     * @param query represents the GET querystring.
     * */
    public void addGETQuery(String query){
        urlPath+=query;
    }

    /**
     * setHTTPHeader lets you set the HTTP Headers, allowing the implementation of custom headers. This step isn't necessary.
     *
     * @param headerName represents the HTTP header name that is going to be added.
     * @param headerValue represents the value of the specified header name.
     * */
    public void setHTTPHeader(String headerName, String headerValue){
        headerList.add(new HTTPHeader(headerName,headerValue));
    }

    /**
     * executeQueryHTTP executes the HTTP query created up to that moment, storing the possible answer in a {@link JSONObject}
     * After executing the query, the URL, the HTTP query and the HTTP headers' list are emptied, ready to receive the next HTTP
     * request.
     *
     * @throws IOException this exception can be thrown because of connection problems while trying to establish the comunication
     * or read the answer.
     * @throws JSONException this exception can be thrown because of instantiation problems regarding the {@link JSONObject}.
     * */
    public void executeQueryHTTP() throws IOException, JSONException, ServerInterruptedException {
        URL url = new URL(urlPath);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();

        http.setRequestMethod("GET");
        Log.d(Constants.Log.TAG,"HeaderList: "+((headerList==null)?"null":headerList));
        if(headerList!=null){
            for(HTTPHeader header : headerList){
                Log.d(Constants.Log.TAG,header.toString());
                http.setRequestProperty(header.getKey(),header.getValue());
            }
        }

        Log.d(Constants.Log.TAG,"Bella");
        InputStream in = http.getInputStream();
        Log.d(Constants.Log.TAG,"Bella 2");
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        StringBuilder risposta_builder = new StringBuilder("");

        while((line=br.readLine())!=null){
            Log.d(Constants.Log.TAG,"Line: "+line);
            risposta_builder.append(line);
        }

        Log.d(Constants.Log.TAG,"JSONObject risposta: \n"+risposta_builder.toString());
        if(risposta_builder.toString().isBlank()){
            br.close();
            http.disconnect();
            Log.d(Constants.Log.TAG,"The Server has been interrupted");
            throw new ServerInterruptedException();
        }

        risposta = new JSONObject(risposta_builder.toString());

        http.disconnect();
        headerList.removeAll(headerList);
        br.close();
    }

    /**
     * getString references to the {@link JSONObject} obtained after an {@link ConnectivitySingleton#executeQueryHTTP()} and, therefore,
     * it can throw an exception if no {@link ConnectivitySingleton#executeQueryHTTP()} has been executed before, or if that method itself failed.
     *
     * @param key identifies the name paired to a value.
     * @throws JSONException this exception is thrown if there is no value paired to the given key.
     * @return this method returns the {@link String} value paired to the given key.
     * */
    public String getString(String key) throws JSONException {
        return risposta.getString(key);
    }

    /**
     * getInteger references to the {@link JSONObject} obtained after an {@link ConnectivitySingleton#executeQueryHTTP()} and, therefore,
     * it can throw an exception if no {@link ConnectivitySingleton#executeQueryHTTP()} has been executed before, or if that method itself failed.
     *
     * @param key identifies the name paired to a value.
     * @throws JSONException this exception is thrown if there is no value paired to the given key.
     * @return this method returns the int value paired to the given key.
     * */
    public int getInteger(String key) throws JSONException {
        return risposta.getInt(key);
    }

    /**
     * getDouble references to the {@link JSONObject} obtained after an {@link ConnectivitySingleton#executeQueryHTTP()} and, therefore,
     * it can throw an exception if no {@link ConnectivitySingleton#executeQueryHTTP()} has been executed before, or if that method itself failed.
     *
     * @param key identifies the name paired to a value.
     * @throws JSONException this exception is thrown if there is no value paired to the given key.
     * @return this method returns the double value paired to the given key.
     * */
    public double getDouble(String key) throws JSONException {
        return risposta.getDouble(key);
    }

    /**
     * getBoolean references to the {@link JSONObject} obtained after an {@link ConnectivitySingleton#executeQueryHTTP()} and, therefore,
     * it can throw an exception if no {@link ConnectivitySingleton#executeQueryHTTP()} has been executed before, or if that method itself failed.
     *
     * @param key identifies the name paired to a value.
     * @throws JSONException this exception is thrown if there is no value paired to the given key.
     * @return this method returns the boolean value paired to the given key.
     * */
    public boolean getBoolean(String key) throws JSONException {
        return risposta.getBoolean(key);
    }

    /**
     * getJSONArray references to the {@link JSONObject} obtained after an {@link ConnectivitySingleton#executeQueryHTTP()} and, therefore,
     * it can throw an exception if no {@link ConnectivitySingleton#executeQueryHTTP()} has been executed before, or if that method itself failed.
     *
     * @param key identifies the name paired to a value.
     * @throws JSONException this exception is thrown if there is no value paired to the given key.
     * @return this method returns the JSONArray value paired to the given key.
     * */
    public JSONArray getJSONArray(String key) throws JSONException {
        return risposta.getJSONArray(key);
    }

    /**
     * setToken lets you set the token that will be always used for future communications
     *
     * @param token the token value
     * */
    public void setToken(String token){
        this.token=token;
    }

    /**
     * getToken lets you retrieve the stored token.
     *
     * @return this method returns the token value.
     * */
    public String getToken(){
        return token;
    }

    public void resetConnectionAfterFailure(){
        headerList = new ArrayList<>();
        url = "";
        urlPath = "";
    }
}
