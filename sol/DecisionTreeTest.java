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
        //this.singleRow = DecisionTreeCSVParser.parse("data/to/food.csv");
        //this.singleData = new Dataset("color", this.singleRow);
        //System.out.println(dataset.size());
    }
}
