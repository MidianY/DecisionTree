package sol;

import java.util.LinkedList;

public class EdgeClass {
    public String value;
    public Object decision;
    public ITreeNode nextNode;

    public EdgeClass(String value, ITreeNode nextNode, Object decision){
        this.value = value;
        this.nextNode = nextNode;
        this.decision = decision;
    }




}
