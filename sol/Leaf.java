package sol;

import src.Row;

public class Leaf implements ITreeNode{
    public String attribute;
    public String finalDecision;

    public Leaf(String attribute, String finalDecision){
        this.attribute = attribute;
        this.finalDecision = finalDecision;
    }

    @Override
    public String getDecision(Row forDatum){
        return this.finalDecision;
    }

}
