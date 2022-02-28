package sol;

import src.Row;
import src.IDataset;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that implements the IDataset interface,
 * representing a training data set.
 */
public class Dataset implements IDataset {
    List<String> attributeNames;
    List<Row> rowList;

    public Dataset(List<String> attributeNames, List<Row> rowList){
        this.attributeNames = attributeNames;
        this.rowList = rowList;
    }

    /**
     *
     * @param attribute
     * @return
     */
    public List<String> getSpecificAttribute(String attribute){
        List<String> specificValues = new ArrayList<>();
        for (Row row: this.rowList) { //for each row in the list of rows we have
            String attributeValue = row.getAttributeValue(attribute); //store the attribute value in a variable
            if(!specificValues.contains(attributeValue)) {
                specificValues.add(attributeValue); //if distinct value does not contain the rows attribute values adds it to list
            }
        }
        return specificValues; //returns the specific attributes
    }

    /**
     *
     * @param oldList
     * @param removeString
     * @return filters through a given list and returns the new list without the old attribute in
     * the new list
     */
    public List<String> filterAttribute(List<String> oldList, String removeString){
        List<String> newList = new ArrayList<>();
        newList.remove(removeString);
        return newList;
    }

    /**
     *
     * @param splitAttribute
     * @return splits a dataset depending on the splitAttribute that is passed into the function using
     * filter and getsSpecificAttribute method above
     */
    public List<Dataset> splitDataset(String splitAttribute){
        List<Dataset> subset = new ArrayList<>();
        List<String> attList = this.getSpecificAttribute(splitAttribute); //the list returned from getSpecificAttribute
        List<String> newAttribute = this.filterAttribute(this.attributeNames, splitAttribute); //the attributes left after removing splitAttribute

        for (String attribute: attList){ //for each attribute returned from getSpecificAttribute
            List<Row> currValue = new ArrayList<>(); //create a new row
            for(Row row: this.rowList){
                if(row.getAttributeValue(splitAttribute).equals(attribute)){
                    currValue.add(row);
                }
            }
            subset.add(new Dataset(newAttribute, currValue));
        }
        return subset;
    }

    //boolean to check if whether every datum in the dataset is the same

    @Override
    public List<String> getAttributeList(){
        return this.attributeNames;
    }

    @Override
    public List<Row> getDataObjects(){
        return this.rowList;
    }

    @Override
    public int size(){
        return this.rowList.size();
    }


}
