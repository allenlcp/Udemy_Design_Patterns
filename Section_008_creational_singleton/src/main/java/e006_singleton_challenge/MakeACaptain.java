package e006_singleton_challenge;

public class MakeACaptain {

    private MakeACaptain() {}

    // Bill Pugh solution
    private static class SingletonHelper{
        private static final MakeACaptain _captain = new MakeACaptain();
    }

    public static MakeACaptain getInstance(){
        return SingletonHelper._captain;
    }
}
