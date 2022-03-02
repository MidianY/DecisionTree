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

    public ITreeNode generateTreeHelper(Dataset trainingData, String targetAttribute, List<String> unusedAttributes){
        if (trainingData.sameValue(targetAttribute)){ //if all the rows have the same value
           return new Leaf(targetAttribute, trainingData.getSharedValue());
        }

        else {
            //choose a previously unused attribute randomly
            Random randomData = new Random();
            int randNumber = randomData.nextInt(unusedAttributes.size());
            //int randNumber = randomData.nextInt(trainingData.getAttributeList().size());

            //create a list of edges and a random attribute to split the data on
            List<Edge> edgeList = new ArrayList<>();
            String partAttribute = trainingData.getAttributeList().get(randNumber);

            //removing the attribute from the list once it has been used
            List<String> copyUnusedAttributes = new ArrayList<>(unusedAttributes);
            copyUnusedAttributes.remove(trainingData.getAttributeList().get(randNumber));

            for(Dataset dataset: trainingData.partition(partAttribute)){
                Edge newEdge = new Edge(dataset.getSharedValue(), this.generateTreeHelper(trainingData, targetAttribute, copyUnusedAttributes));
                edgeList.add(newEdge);
            }

            Node newNode = new Node(partAttribute, edgeList, trainingData.getDefaultValues(targetAttribute));
            return newNode;
        }
    }


    //target attribute should be changed in generate tree.. get target attribute and remove it from the list
    @Override
    public void generateTree(Dataset trainingData, String targetAttribute){
        List<String> unusedAttributes = new ArrayList(trainingData.getAttributeList());
        unusedAttributes.remove(targetAttribute);
        ITreeNode newNode = this.generateTreeHelper(trainingData, targetAttribute, unusedAttributes);
        this.rootNode = newNode;
    }

    @Override
    public String getDecision(Row datum){
        return this.rootNode.getDecision(datum);
    }
}
