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

    public TreeGenerator(){
    }


    public ITreeNode generateTreeHelper(Dataset trainingData, String targetAttribute){
//        if(trainingData.size() == 0){
//            System.out.println("love");
//            throw new RuntimeException("Dataset is empty");
//        }

        if (trainingData.sameValue(targetAttribute) || trainingData.getAttributeList().size()==0){ //if all the rows have the same value
           return new Leaf(targetAttribute, trainingData.getSharedValue(targetAttribute));
        }
        else {
//            List<String> unusedAttributes = new ArrayList(trainingData.getAttributeList());
//            unusedAttributes.remove(targetAttribute);
//            Random randomData = new Random();
//            int randNumber = randomData.nextInt(trainingData.getAttributeList().size());

            String randAtt = trainingData.random();

            List<Dataset> splitData = trainingData.partition(randAtt);
            List<Edge> edgeList = new ArrayList<>();

            for(Dataset dataset: splitData){
                Edge edge = new Edge(dataset.rowList.get(0).getAttributeValue(targetAttribute),
                        this.generateTreeHelper(dataset, randAtt));
                edgeList.add(edge);
            }

            return new Node(targetAttribute,edgeList, trainingData.getDefaultValues(targetAttribute));
        }
    }

    //target attribute should be changed in generate tree.. get target attribute and remove it from the list
    @Override
    public void generateTree(Dataset trainingData, String targetAttribute){
        ITreeNode newNode = this.generateTreeHelper(trainingData, targetAttribute);
        this.rootNode = newNode;
    }

    @Override
    public String getDecision(Row datum){
        return this.rootNode.getDecision(datum);
    }

}
