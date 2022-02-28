package sol;
import src.Row;
import java.util.LinkedList;

public class NodeClass implements ITreeNode{
    public LinkedList<EdgeClass> edgeList;
    public String attribute;
    public String result;

    public NodeClass(String attribute, LinkedList<EdgeClass> edgeList, String result){
        this.attribute = attribute;
        this.edgeList = edgeList;
        this.result = result;
    }

    @Override
    public String getDecision(Row forDatum){
        for(EdgeClass item: this.edgeList){ //go through the tree checking for equal to value for given data
            if(item.decision == forDatum.getAttributeValue(item.value)){
                return item.nextNode.getDecision(forDatum);
            }
        }
        return this.result;
    }


    public void printNode(String print){
        System.out.println(print + attribute);
        for(EdgeClass item: this.edgeList){
            //item.printEdge
        }
    }


}
