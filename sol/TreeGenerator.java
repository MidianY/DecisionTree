package sol;

import src.ITreeGenerator;
import src.Row;

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
    public ITreeNode generateTreeHelper(Dataset trainingData, String targetAttribute, List<String> unusedAttribute){
        if (trainingData.sameValue(targetAttribute)){ //if all the rows have the same value
            new Leaf(targetAttribute, trainingData.getSharedValue());
        }
        else {
            Random randomData = new Random();
            int randNumber = randomData.nextInt(unusedAttribute.size());
            List<Dataset> splitData = trainingData.partition(targetAttribute);


        }

        return null;
    }


    //target attribute should be changed in generate tree.. get target attribute and remove it from the list
    //
    @Override
    public void generateTree(Dataset trainingData, String targetAttribute){

    }
    @Override
    public String getDecision(Row datum){

        return null;

    }





}
