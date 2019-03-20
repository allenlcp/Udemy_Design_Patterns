package e002_challenge;

public interface ColorInterface {
    void fillWithColor(int border);
}

class RedColor implements ColorInterface{
    @Override
    public void fillWithColor(int border) {
        System.out.println("Red color with " + border + " inch border");
    }
}

class GreenColor implements ColorInterface{
    @Override
    public void fillWithColor(int border) {
        System.out.println("Green color with " + border + " inch border");
    }
}