import java.io.Serializable;

public class PlayData implements Serializable {

    private String play, text, actor;

    public PlayData(String play, String line, String actor) {
        this.play = play;
        this.text = line;
        this.actor = actor;
    }

    public String getActor() {
        return actor;
    }

    public String getPlay() {
        return play;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
