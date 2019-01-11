import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataSet {

    private PlayDataSet details;

    public boolean fileExists() {
        File file = new File("ShakespeareDataSet.dat");
        return file.exists();
    }

    public boolean dataSourceModified() {
        File file = new File("Shakespeare_data.csv");
        long x = file.lastModified();
        readDetails();
        return x == details.getLastModified();
    }

    public PlayDataSet getDetails() {
        return details;
    }

    private void readDetails(){
        try{
            File file = new File("ShakespeareDataSet.dat");
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(input);
            details = (PlayDataSet) ois.readObject();
            ois.close();
        } catch(IOException | ClassNotFoundException e){
            System.out.println("Exception: " + e.getMessage());
        }
    }


    public void load() {
        try {
            File file = new File("Shakespeare_data.csv");
            FileReader fr = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fr);
            HashMap<String, PlayData> playMap = new HashMap<>();
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                String actorLine = data[5];
                String actor = data[4];
                String play = data[1];
                if(play.contains("Hamlet")) {
                    System.out.println(line);
                }

                if(playMap.get(play) != null) {
                    playMap.get(play).setText(playMap.get(play).getText() + line);
                } else {
                    playMap.put(play, new PlayData(play, line, actor));
                }

            }

            bufferedReader.close();

            ArrayList<PlayData> playDataArrayList = flatten(playMap);


            PlayDataSet playDataSet = new PlayDataSet(playDataArrayList, file.lastModified());
            createPlayDataSet(playDataSet);
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private ArrayList<PlayData> flatten(HashMap<String,PlayData> playMap) {
        ArrayList<PlayData> playDataArrayList = new ArrayList<>();

        for (PlayData play : playMap.values()) {
            playDataArrayList.add(play);
            System.out.println(play.getPlay());
        }
        return playDataArrayList;

    }

    private void createPlayDataSet(PlayDataSet playDataSet) {
        try {
            File file = new File("ShakespeareDataSet.dat");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(playDataSet);
            oos.close();

        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            System.out.println(e);
        }
    }


}
