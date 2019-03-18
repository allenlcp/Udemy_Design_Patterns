package e002_challenge;

public interface MovieFactoryInterface {
    HollywoodMovie getHollywoodMovie(String type);
    BollywoodMovie getBollywoodMovie(String type);
}

class HollywoodMovieFactory implements MovieFactoryInterface{
    public HollywoodMovie getHollywoodMovie(String type) {
        if(type.equalsIgnoreCase("action")){
            return new HollywoodActionMovie();
        } else if (type.equalsIgnoreCase("comedy")){
            return new HollywoodComedyMovie();
        }
        return null;
    }

    public BollywoodMovie getBollywoodMovie(String type) {
        return null;
    }
}

class BollywoodMovieFactory implements MovieFactoryInterface{
    public BollywoodMovie getBollywoodMovie(String type) {
        if(type.equalsIgnoreCase("action")){
            return new BollywoodActionMovie();
        } else if (type.equalsIgnoreCase("comedy")){
            return new BollywoodComedyMovie();
        }
        return null;
    }

    public HollywoodMovie getHollywoodMovie(String type) {
        return null;
    }
}
