package sol;

import src.ITreeGenerator;
import src.Row;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.LinkedList;
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



    //generateTree helper
    //generate all the subtrees and you call on the original dataset
    //generate tree split data

    //1) everything has the same output value... all are a fruit so we make a leaf base case

    //2) unfinished red items have fruit and veggies.. now ur gonna generate a new tree that only
    //contains the red items, call recursion on your small dataset

    //getdefault
    //gonna be used when you're classifying a piece of data that isn't the list
    //
    //new list = dataset.partition
    //partition returns a list of dataset, if you want to know which appears the most
    //you wanna find the size of the list.size();

    //count up the number of true or false

    //random on unusedAttributeList so you can randomly build the decision tree


    //each recursive call returns the root of the subtree
    public ITreeNode generateTreeHelper(Dataset trainingData, String targetAttribute){
        if (trainingData.sameValue(targetAttribute)){ //if all the rows have the same value
           return new Leaf(targetAttribute, trainingData.getSharedValue());
        }

        else {
            //choose a previously unused attribute randomly
            Random randomData = new Random();
            int randNumber = randomData.nextInt(trainingData.getAttributeList().size());

            //create a list of edges and a random attribute to split the data on
            List<Edge> edgeList = new ArrayList<>();
            String partAttribute = trainingData.getAttributeList().get(randNumber);

            //removing the attribute from the list once it has been used
            List<String> copyUnusedAttributes = new ArrayList<>(trainingData.getAttributeList());
            copyUnusedAttributes.remove(trainingData.getAttributeList().get(randNumber));

            //where we are going to split the data
//            List<Dataset> splitData = trainingData.partition(targetAttribute);

            for(Dataset dataset: trainingData.partition(partAttribute)){
                Edge newEdge = new Edge(dataset.getSharedValue(), this.generateTreeHelper(trainingData, targetAttribute));
                edgeList.add(newEdge);
            }

            Node newNode = new Node(partAttribute, edgeList, trainingData.getDefaultValues);
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
