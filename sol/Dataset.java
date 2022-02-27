package sol;

import src.IDataset;
import src.Row;

import java.util.List;

/**
 * A class that implements the IDataset interface,
 * representing a training data set.
 */
public class Dataset implements IDataset {

    List<String> attributeNames;
    List<Row> rowList;

    public  Dataset(List<String> attributeNames, List<Row> rowList){
        this.attributeNames = attributeNames;
        this.rowList = rowList;

    }
    // TODO: Implement methods declared in IDataset interface!
    @Override
    public List<String> getAttributeList(){

    }

    @Override
    public List<Row> getDataObjects(){

    }

    @Override
    public int size(){
        return this.rowList.size();
    }
}
