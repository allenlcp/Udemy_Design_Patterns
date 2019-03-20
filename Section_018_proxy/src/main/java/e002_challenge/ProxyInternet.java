package e002_challenge;

import java.util.ArrayList;
import java.util.List;

public class ProxyInternet implements Internet {
    private Internet internet = new RealInternet();

    private static List<String> bannedSites;

    static {
        bannedSites  = new ArrayList<>();
        bannedSites.add("whatever.com");
        bannedSites.add("yup.com");
        bannedSites.add("hello.com");
        bannedSites.add("first.com");
    }

    @Override
    public void connectTo(String serverhost) throws Exception{

        if(bannedSites.contains(serverhost.toLowerCase())){
            throw new Exception("Access Denied");
        } else {
            internet.connectTo(serverhost);
        }
    }

}
