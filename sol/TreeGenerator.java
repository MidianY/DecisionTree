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
        if (trainingData.sameValue(targetAttribute)){ //if all the rows have the same value
           return new Leaf(targetAttribute, trainingData.getSharedValue());
        }

        else {
            Random randomData = new Random();
            int randNumber = randomData.nextInt(trainingData.getAttributeList().size());

            List<Dataset> splitData = trainingData.partition(targetAttribute);
            List<Edge> edgeList = new ArrayList<>();

            for(Dataset dataset: splitData){
                Edge edge = new Edge(dataset.rowList.get(0).getAttributeValue(targetAttribute),
                        this.generateTreeHelper(dataset, dataset.getAttributeList().get(randNumber)));
                edgeList.add(edge);
            }

            Node newNode = new Node(targetAttribute,edgeList, trainingData.getDefaultValues(targetAttribute));
            return newNode;
        }
    }


    //target attribute should be changed in generate tree.. get target attribute and remove it from the list
    @Override
    public void generateTree(Dataset trainingData, String targetAttribute){
        List<String> unusedAttributes = new ArrayList(trainingData.getAttributeList());
        unusedAttributes.remove(targetAttribute);
        ITreeNode newNode = this.generateTreeHelper(trainingData, targetAttribute);
        this.rootNode = newNode;
    }

    @Override
    public String getDecision(Row datum){
        return this.rootNode.getDecision(datum);
    }
}
