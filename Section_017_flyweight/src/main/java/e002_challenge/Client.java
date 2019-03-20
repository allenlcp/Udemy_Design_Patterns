package e002_challenge;

import java.util.Random;

public class Client {

    private static String[] playerType = {"T", "CT"};
    private static String[] weapon = {"AK", "Maverick", "Gut", "Flane", "Stone", "Desert Eagle"};

    private static Player generatePlayer(){
        Random r = new Random();
        int index = r.nextInt(playerType.length);
        return PlayerFactory.getPlayer(playerType[index]);
    }

    private static String generateWeapon(){
        Random r = new Random();
        int index = r.nextInt(weapon.length);
        return weapon[index];
    }

    public static void main(String[] args) {

        for (int i=0; i<10; i++){
            Player p = generatePlayer();
            p.assignWeapon(generateWeapon());
            p.mission();
        }

        System.out.println(PlayerFactory.getNumberOfDistinctPlayer());

    }
}
