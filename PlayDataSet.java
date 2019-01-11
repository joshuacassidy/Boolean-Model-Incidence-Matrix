import java.io.Serializable;
import java.util.ArrayList;

public class PlayDataSet implements Serializable {

    private ArrayList<PlayData> playData;
    private long lastModified;

    public PlayDataSet(ArrayList<PlayData> playData, long lastModified) {
        this.playData = playData;
        this.lastModified = lastModified;
    }

    public ArrayList<PlayData> getPlayData() {
        return playData;
    }

    public long getLastModified() {
        return lastModified;
    }
}
