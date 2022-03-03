package sol;

import static org.junit.Assert.assertEquals;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.DecisionTreeCSVParser;
import src.Row;
import java.util.ArrayList;
import java.util.List;

public class DecisionTreeTest {
    List<Row> singleRow;
    Dataset singleData;
    List<String> list;
    
    // Constructor for DecisionTreeTest tester class - don't need to add anything in here!
    public DecisionTreeTest() {
    }

    @Before
    public void setupData(){
        this.list = new ArrayList<>();
        this.singleRow = DecisionTreeCSVParser.parse("/Users/midianyoseph/Desktop/cs200/projects/decision-tree-Kyen24-midianY/data/food/food.csv");
        this.list.add("color");
        this.list.add("highProtein");
        this.list.add("calories");
        this.singleData = new Dataset(this.list, this.singleRow);
    }

    public boolean compareTwoLists(List<String> l1, List<String> l2) {
        if (l1.size() != l2.size()) {
            return false;
        } else {
            for (int i = 0; i < l1.size(); i++) {
                if (!(l1.get(i).equals(l2.get(i)))) {
                    return false;
                }
            }
            return true;
        }
    }

    //tests getAttributeList method on Dataset and list created in this class
    @Test
    public void getAttributeValue(){
        List<String> list1 = new ArrayList<>();
        list1.add("color");
        list1.add("highProtein");
        list1.add("calories");
        Assert.assertTrue(this.compareTwoLists(list1, this.singleData.getAttributeList()));
    }

    //expected output is 3 colors based on dataset that is created
    @Test
    public void partitionTest(){
        List<Dataset> datasets = this.singleData.partition("color");
        for(Dataset dataset: datasets){
            System.out.println(dataset.getDataObjects().get(0).getAttributeValue("color"));
        }
    }

    //test that makes sure dataset doesn't partition on an unknown attribute
    @Test(expected = RuntimeException.class)
    public void partitionRuntimeException() {
        this.singleData.partition("sports");
    }

    //testing
    @Test
    public void getDecisionTest(){
        List<Row> dataObjects = DecisionTreeCSVParser.parse("/Users/midianyoseph/Desktop/cs200/projects/decision-tree-Kyen24-midianY/data/food/food.csv");
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects);

        TreeGenerator generator = new TreeGenerator();
        generator.generateTree(training, "foodType");

        Row tangerine = new Row("tangerine");
        tangerine.setAttributeValue("name", "tangerine");
        tangerine.setAttributeValue("color", "orange");
        tangerine.setAttributeValue("highProtein", "false");
        tangerine.setAttributeValue("calories", "high");
        String decision = generator.getDecision(tangerine);

        System.out.println(decision);
    }

}
