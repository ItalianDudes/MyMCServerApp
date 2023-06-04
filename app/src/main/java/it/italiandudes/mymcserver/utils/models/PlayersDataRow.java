package it.italiandudes.mymcserver.utils.models;

public class PlayersDataRow {

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;

    private int length;

    public PlayersDataRow(Player player1){
        this(player1,null,null,null);
    }

    public PlayersDataRow(Player player1, Player player2){
        this(player1,player2,null,null);
    }

    public PlayersDataRow(Player player1, Player player2, Player player3){
        this(player1,player2,player3,null);
    }

    public PlayersDataRow(Player player1, Player player2, Player player3, Player player4){
        this.player1=player1;
        this.player2=player2;
        this.player3=player3;
        this.player4=player4;

        if(player2==null){
            length=1;
        }else if(player3==null){
            length=2;
        }else if(player4==null){
            length=3;
        }else{
            length=4;
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getPlayer3() {
        return player3;
    }

    public Player getPlayer4() {
        return player4;
    }

    public int getLength(){
        return length;
    }
}
