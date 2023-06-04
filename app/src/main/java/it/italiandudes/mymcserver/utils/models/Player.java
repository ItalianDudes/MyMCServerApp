package it.italiandudes.mymcserver.utils.models;

public class Player {

    private String name;
    private boolean isOnline;

    public Player(String name, boolean isOnline){
        this.name=name;
        this.isOnline=isOnline;
    }

    public String getName(){
        return name;
    }

    public boolean isOnline() {
        return isOnline;
    }
}
