import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class BooleanModel {

    private String query;
    private ArrayList<PlayData>  details;

    public BooleanModel(String query, ArrayList<PlayData> details) {
        this.query = query;
        this.details = details;
    }


    public void query() {
        String[] words = query.split(" ");
        ArrayList<Integer[]> matrix = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            Integer[] plays = new Integer[details.size()];

            switch (words[i]) {
                case "AND":
                    break;
                case "OR":
                    break;
                case "NOT":
                    for (int j = 0; j < details.size(); j++) {
                        String play = details.get(j).getText().toLowerCase();
                        String word = words[i+1].toLowerCase();
                        if(play.contains(word)) {
                            plays[j]=0;
                        } else {
                            plays[j]=1;
                        }
                    }

                    matrix.add(plays);

                    i++;
                    break;

                default:
                    for (int j = 0; j < details.size(); j++) {
                        String play = details.get(j).getText().toLowerCase();
                        String word = words[i].toLowerCase();
                        if(play.contains(word)) {
                            plays[j]=1;
                        } else {
                            plays[j]=0;
                        }
                    }
                    matrix.add(plays);
                    break;
            }
        }

        Stack<String> operationStack = new Stack<>();
        Stack<Integer[]> valueStack = new Stack<>();

        for (String e: words){
            if(e.equals("AND") || e.equals("OR")){
                operationStack.push(e);
            }
        }

        for (Integer[] e: matrix){
                valueStack.push(e);
        }

        while (!operationStack.isEmpty()) {
            String operation = operationStack.pop();
                   if(operation.equals("AND")) {
                       Integer[] x = valueStack.pop();
                       Integer[] y = valueStack.pop();
                       for (int i = 0; i < x.length; i++) {
                           x[i] = x[i] & y[i];
                       }
                       valueStack.push(x);
                  } else if(operation.equals("OR")) {
                       Integer[] x = valueStack.pop();
                       Integer[] y = valueStack.pop();
                       for (int i = 0; i < x.length; i++) {
                           x[i] = x[i] | y[i];
                       }
                       valueStack.push(x);
                  }
        }

        Integer[] results = valueStack.pop();
        System.out.println(query + " results: ");
        for (int i = 0; i < results.length; i++) {
            if(results[i] == 1) {
                System.out.println(details.get(i).getPlay());
            }
        }
        System.out.println(Arrays.toString(results));

    }

}
