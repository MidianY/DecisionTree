package sol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Before;
import src.DecisionTreeCSVParser;
import src.Row;

import java.util.List;

public class DecisionTreeTest {
    List<Row> singleRow;
    Dataset singleData;
    List<String> list;
    
    // Constructor for DecisionTreeTest tester class - don't need to add anything in here!
    public DecisionTreeTest() {

    }
    
    @Test
    public void testExample() { 
        assertEquals(6, 1 + 2 + 3);
    }
    
    // TODO: Add your tests here!


    @Test
    public void test(){
//        this.singleRow = DecisionTreeCSVParser.parse("data/to/food.csv");
//        this.list.add("color");
//        this.list.add("highProtein");
//        this.list.add("foodType");
//        this.singleData = new Dataset(this.list, this.singleRow);
//        this.singleData.partition("Color");
//        System.out.println(this.singleData);
    }
}
