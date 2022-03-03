package sol;

import src.ITreeGenerator;
import src.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that implements the ITreeGenerator interface
 * used to generate a tree
 */
public class TreeGenerator implements ITreeGenerator<Dataset> {
    ITreeNode rootNode;
    List<String> data;

    public TreeGenerator(){
    }


    public ITreeNode generateTreeHelper(Dataset trainingData, String targetAttribute){
//        if(trainingData.size() == 0){
//            System.out.println("love");
//            throw new RuntimeException("Dataset is empty");
//        }

        if (trainingData.sameValue(targetAttribute) || this.data.size()==0){ //if all the rows have the same value
           return new Leaf(targetAttribute, trainingData.getDefaultValues(targetAttribute));
        }
        else {
            Random attr = new Random();
            int newAttr = attr.nextInt(this.data.size());
            String randAtt = this.data.get(newAttr);
            this.data.remove(randAtt);

            List<Dataset> splitData = trainingData.partition(randAtt);
            List<Edge> edgeList = new ArrayList<>();

            for(Dataset dataset: splitData){
                Edge edge = new Edge(dataset.getSharedValue(randAtt),
                        this.generateTreeHelper(dataset, targetAttribute));
                edgeList.add(edge);
            }
            return new Node(randAtt, edgeList, trainingData.getDefaultValues(targetAttribute));
        }
    }

    //target attribute should be changed in generate tree.. get target attribute and remove it from the list
    @Override
    public void generateTree(Dataset trainingData, String targetAttribute){
        this.data = new ArrayList<>(trainingData.getAttributeList());
        this.data.remove(targetAttribute);
        ITreeNode newNode = this.generateTreeHelper(trainingData, targetAttribute);
        this.rootNode = newNode;
    }

    @Override
    public String getDecision(Row datum){
        return this.rootNode.getDecision(datum);
    }

}
