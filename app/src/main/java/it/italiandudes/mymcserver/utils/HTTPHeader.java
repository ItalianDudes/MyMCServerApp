package it.italiandudes.mymcserver.utils;

public class HTTPHeader {
    private String key;
    private String value;

    public HTTPHeader(String key, String value){
        this.key=key;
        this.value=value;
    }

    public String getKey(){
        return key;
    }

    public String getValue(){
        return value;
    }
}
