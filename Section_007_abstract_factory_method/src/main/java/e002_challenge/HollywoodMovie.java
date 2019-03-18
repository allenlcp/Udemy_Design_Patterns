package e002_challenge;

public interface HollywoodMovie {
    String getMovieName();
}

class HollywoodActionMovie implements HollywoodMovie{
    public String getMovieName() {
        return "HollywoodActionMovie::getMovieName() method";
    }
}

class HollywoodComedyMovie implements HollywoodMovie{
    public String getMovieName(){
        return "HollywoodComedyMovie::getMovieName() method";
    }
}