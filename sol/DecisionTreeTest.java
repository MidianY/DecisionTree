package sol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Before;
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
        this.list = new ArrayList<>();

    }
    
    @Test
    public void testExample() { 
        assertEquals(6, 1 + 2 + 3);
    }
    // TODO: Add your tests here!


    @Test
    public void test(){
        this.singleRow = DecisionTreeCSVParser.parse("/Users/midianyoseph/Desktop/cs200/projects/decision-tree-Kyen24-midianY/data/food/food.csv");
        this.list.add("color");
        this.list.add("highProtein");
        this.list.add("foodType");
        this.singleData = new Dataset(this.list, this.singleRow);
        List<Dataset> datasets = this.singleData.partition("color");
        for(Dataset dataset: datasets){
            System.out.println(dataset.getDataObjects().get(0).getAttributeValue("color"));
        }
    }

    @Test
    public void test1(){
        List<Row> dataObjects = DecisionTreeCSVParser.parse("/Users/midianyoseph/Desktop/cs200/projects/decision-tree-Kyen24-midianY/data/food/food.csv");
        List<String> attributeList = new ArrayList<>(dataObjects.get(0).getAttributes());
        Dataset training = new Dataset(attributeList, dataObjects);

        TreeGenerator generator = new TreeGenerator();
        generator.generateTree(training, "color");

        Row tangerine = new Row("tang");
        tangerine.setAttributeValue("color", "orange");
        tangerine.setAttributeValue("highProtein", "false");
        tangerine.setAttributeValue("calories", "high");
        String decision = generator.getDecision(tangerine);

        System.out.println(decision);
    }

}
