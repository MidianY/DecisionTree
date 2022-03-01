package sol;

import java.util.LinkedList;

public class Edge {
    public String value;
    public String decision;
    public ITreeNode nextNode;

    public Edge(String value, ITreeNode nextNode, String decision){
        this.value = value;
        this.nextNode = nextNode;
        this.decision = decision;
    }




}
