package it.italiandudes.mymcserver.utils.models;

public class Addon {

    private String name;
    private boolean isActive;

    public Addon(String name, boolean isActive){
        this.name=name;
        this.isActive=isActive;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }
}
