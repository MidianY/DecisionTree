package sol;

import src.Row;
import src.IDataset;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

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

    /**
     *
     * @param attribute
     * @return a list of distinct attributes
     */
    public List<String> getDistinctValues(String attribute){
        List<String> specificValues = new ArrayList<>();
        for (Row row: this.rowList) { //for each row in the list of rows we have
            String attributeValue = row.getAttributeValue(attribute); //store the attribute value in a variable
            if(!specificValues.contains(attributeValue)) {
                specificValues.add(attributeValue); //if distinct value does not contain the rows attribute values adds it to list
            }
        }

        return specificValues; //returns the specific attributes
    }

    //gets the most common attribute
    public String getSharedValue(String att){
        if(this.isEmpty()){
            throw new RuntimeException("Dataset is Empty");
        }
        else{
            return this.rowList.get(0).getAttributeValue(att);
                    //this.attributeNames.get(0).;
        }
    }

    /**
     *
     * @param oldList
     * @param removeString
     * @return filters through a given list and returns the new list without the old attribute in
     * the new list
     */
    public List<String> removeAttribute(List<String> oldList, String removeString){
//        List<String> newList = new ArrayList<>(oldList);
        List<String> newList = new ArrayList<>();
        newList.addAll(oldList);
        newList.remove(removeString);
        return newList;
    }

    /**
     *
     * @param splitAttribute
     * @return splits a dataset depending on the splitAttribute that is passed into the function using
     * filter and getsSpecificAttribute method above
     */
    public List<Dataset> partition(String splitAttribute){
        List<Dataset> subset = new ArrayList<>();
        List<String> attList = this.getDistinctValues(splitAttribute); //the list returned from getSpecificAttribute
//        List<String> newAttList = this.removeAttribute(this.attributeNames, splitAttribute);

        List<String> newAttribute = this.removeAttribute(this.attributeNames, splitAttribute); //the attributes left after removing splitAttribute

        for (String attribute: attList){ //for each attribute returned from getSpecificAttribute
            List<Row> currValue = new ArrayList<>(); //create a new row
            for(Row row: this.rowList){
                if(row.getAttributeValue(splitAttribute).equals(attribute)){
                    currValue.add(row);
                }
            }
            Dataset ds = new Dataset(newAttribute, currValue);
            subset.add(ds);
        }
        return subset;
    }


    public boolean sameValue(String attribute){
        if(this.getDataObjects()==null) {
            for (Row row : this.rowList) {
                row.getAttributeValue(attribute).equals(this.rowList.get(0).getAttributeValue(attribute));
            }
            return true;
        }
        return false;
    }

    public String getDefaultValues(String attribute){
        if(!isEmpty()) {
            int length = 0;
            String defValue = null;

            for(Dataset dataset : this.partition(attribute)){ //for each row in the dataset
                if(dataset.size() > length){ //if the dataset size > length
                    length = dataset.size();
                    defValue = dataset.getSharedValue(attribute);
                }
            }
            return defValue;
        }
        else {
            throw new RuntimeException("Dataset is empty");
        }
    }

    public String random(){
        Random random = new Random();

        if (this.attributeNames.size() == 0){
            throw new RuntimeException("Data is empty");
        }
        else{
            int random1 = random.nextInt(this.attributeNames.size());
            return this.attributeNames.get(random1);
        }
    }

    public boolean isEmpty(){
        return this.rowList == null;
    }

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
