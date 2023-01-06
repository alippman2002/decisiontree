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
    String outcome;
    Node root;

    /**
     * This method takes in a Dataset, trainingData and a String,
     * outcomeAttribute and generates a tree which recurs down the other
     * attributes partitioning the data into a tree based on dinstinct
     * versus different attributeValues for the attributes stored in
     * each node to create a tree that can accurately predict the
     * value of input datums outcomeAttribute based on the input data
     * from trainingData.
     *
     * @param: trainingData, a dataset used to intiailize the tree with
     * information and outcomeAttribute, a string that refers to the
     * attribute for which the tree is generated for to predict the
     * attribute values of datum for the outcomeAttribute.
     */

    @Override
    public void generateTree(Dataset trainingData, String outcomeAttribute) {
        this.outcome = outcomeAttribute;
        trainingData.removeAttribute(this.outcome);
        this.root = new Node(trainingData, this.outcome);
        this.createSubTrees(this.root);
    }

    /**
     * This inputs the root node and recurs down the list of edges, generating
     * subtrees for each respective edge.
     *
     * @param: root, the initialized root Node from geenrateTree.
     * @returns: NA, just makes a the subtree for the root node.
     * @Throws: NA
     */

    private void createSubTrees(Node root) {
        for (Edge edge : root.getEdges()) {
            Dataset newData = root.getData().partition(edge.getValue(), root.getAttribute());
            List<Storage> checker = newData.getDistinctOutcomes(this.outcome);
            if ((checker.size() == 1)) { // if all outcomes same (when compareOutcomes == true)
                edge.setNext(new Leaf(checker.get(0).getOutcomeVal()));
            } else if (newData.checkIfNull()) {
                edge.setNext(new Leaf(newData.getDefault(this.outcome)));
            } else {
                Node newNode = new Node(newData, this.outcome);
                edge.setNext(newNode);
                this.createSubTrees(newNode);
            }
        }
    }

    /**
     * Returns the output of the root node by going down the subtree comparing
     * the input forDatum values with the edgeValues for the attributes of the tree.
     *
     * @param: forDatum, a row which is the object of data that the method is finding
     * the decision for.
     * @returns: String, corresponding to the value stored in the leaf identified
     * that best matches the values of the forDatum
     * checked with the attributes in the tree.
     * to best fit the inputted row.
     * @Throws: NA
     */

    @Override
    public String getDecision(Row datum) {
        return this.root.getDecision(datum);
    }
}
