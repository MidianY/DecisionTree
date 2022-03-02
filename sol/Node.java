package sol;
import src.Row;
import java.util.List;

public class Node implements ITreeNode{
    public List<Edge> edgeList;
    public String attribute;
    public String result;

    public Node(String attribute, List<Edge> edgeList, String result){
        this.attribute = attribute;
        this.edgeList = edgeList;
        this.result = result;
    }

    @Override
    public String getDecision(Row forDatum){
        for(Edge item: this.edgeList){ //go through the tree checking for equal to value for given data
            if(item.value == forDatum.getAttributeValue(item.value)){
                return item.nextNode.getDecision(forDatum);
            }
        }
        return this.result;
    }



    //do we need to do this
    public void printNode(String print){
        System.out.println(print + attribute);
        for(Edge item: this.edgeList){
            //item.printEdge
        }
    }


}
