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
        if(trainingData.size() == 0){
            System.out.println("love");
            throw new RuntimeException("Dataset is empty");
        }

        if (trainingData.sameValue(targetAttribute) || trainingData.getAttributeList().size()==0){ //if all the rows have the same value
           return new Leaf(targetAttribute, trainingData.getDefaultValues(targetAttribute));
        }
        else {
            Random attr = new Random();
            int newAttr = attr.nextInt(trainingData.getAttributeList().size());
            String randAtt = trainingData.getAttributeList().get(newAttr);
            //this.data.remove(randAtt);

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

    /**
     *
     * @param trainingData    the dataset to train on
     * @param targetAttribute the attribute to predict
     *  Method calls on the generate tree helper function and sets the root node value to the nodes generated to
     */
    @Override
    public void generateTree(Dataset trainingData, String targetAttribute){
        Dataset dataset = new Dataset(trainingData.getAttributeList(), trainingData.getDataObjects());
        dataset.getAttributeList().remove(targetAttribute);
        ITreeNode newNode = this.generateTreeHelper(dataset, targetAttribute);
        this.rootNode = newNode;
    }

    @Override
    public String getDecision(Row datum){
        return this.rootNode.getDecision(datum);
    }

}
