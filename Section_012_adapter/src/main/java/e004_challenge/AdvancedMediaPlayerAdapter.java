package e004_challenge;

public class AdvancedMediaPlayerAdapter implements MediaPlayer {

    AdvancedMediaPlayer advancedMediaPlayer;

    public AdvancedMediaPlayerAdapter(AdvancedMediaPlayer advancedMediaPlayer) {
        this.advancedMediaPlayer = advancedMediaPlayer;
    }

    @Override
    public void play(String audioType, String filename) {
        advancedMediaPlayer.loadFileName(filename);
        advancedMediaPlayer.listen();
    }
}
