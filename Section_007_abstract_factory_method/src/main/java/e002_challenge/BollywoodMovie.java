package e002_challenge;

public interface BollywoodMovie {
    String getMovieName();
}

class BollywoodActionMovie implements BollywoodMovie{
    public String getMovieName() {
        return "BollywoodActionMovie::getMovieName() method";
    }
}

class BollywoodComedyMovie implements BollywoodMovie{
    public String getMovieName(){
        return "BollywoodComedyMovie::getMovieName() method";
    }
}