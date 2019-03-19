package e004_challenge;

public interface AdvancedMediaPlayer {
    void loadFileName(String filename);
    void listen();

}

class VlcPlayer implements AdvancedMediaPlayer{
    String myFile;

    @Override
    public void loadFileName(String filename) {
        myFile = filename;
    }

    @Override
    public void listen() {
        System.out.println("Playing vlc file. Name: " + myFile);
    }
}

class Mp4Player implements AdvancedMediaPlayer{
    String myFile;

    @Override
    public void loadFileName(String filename) {
        myFile = filename;
    }

    @Override
    public void listen() {
        System.out.println("Playing mp4 file. Name: " + myFile);
    }
}