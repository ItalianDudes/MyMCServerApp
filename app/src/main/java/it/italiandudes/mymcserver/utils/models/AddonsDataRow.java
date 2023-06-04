package it.italiandudes.mymcserver.utils.models;

public class AddonsDataRow {

    private Addon addon1;
    private Addon addon2;
    private Addon addon3;
    private Addon addon4;

    private int length;

    public AddonsDataRow(Addon addon1){
        this(addon1,null,null,null);
    }

    public AddonsDataRow(Addon addon1, Addon addon2){
        this(addon1,addon2,null,null);
    }

    public AddonsDataRow(Addon addon1, Addon addon2, Addon addon3){
        this(addon1,addon2,addon3,null);
    }

    public AddonsDataRow(Addon addon1, Addon addon2, Addon addon3, Addon addon4){
        this.addon1=addon1;
        this.addon2=addon2;
        this.addon3=addon3;
        this.addon4=addon4;

        if(addon2==null){
            length=1;
        }else if(addon3==null){
            length=2;
        }else if(addon4==null){
            length=3;
        }else{
            length=4;
        }
    }

    public Addon getAddon1() {
        return addon1;
    }

    public Addon getAddon2() {
        return addon2;
    }

    public Addon getAddon3() {
        return addon3;
    }

    public Addon getAddon4() {
        return addon4;
    }

    public int getLength(){
        return length;
    }
}
