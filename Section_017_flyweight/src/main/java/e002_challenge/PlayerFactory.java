package e002_challenge;

import java.util.HashMap;

public class PlayerFactory {
    private static HashMap<String, Player> playerList = new HashMap<>();

    public static int getNumberOfDistinctPlayer(){
        return playerList.size();
    }

    public static Player getPlayer(String playerType){
        Player myPlayer = null;

        if(playerList.containsKey(playerType)){
            myPlayer = playerList.get(playerType);
        } else {
            switch (playerType){
                case "T":
                    System.out.println("T created");
                    myPlayer = new T();
                    break;
                case "CT":
                    System.out.println("CT created");
                    myPlayer = new CT();
                    break;
            }
            playerList.put(playerType, myPlayer);
        }
        return myPlayer;
    }
}
