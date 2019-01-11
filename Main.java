import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Main {

    public static void main(String[] args) {
        DataSet dataSet = new DataSet();
        if(!(dataSet.fileExists() && dataSet.dataSourceModified())) {
            dataSet.load();
        } else {
            String query = "Brutus AND Caesar AND NOT Calpurnia";
            BooleanModel booleanModel = new BooleanModel(query, dataSet.getDetails().getPlayData());
            booleanModel.query();
        }
    }

}
