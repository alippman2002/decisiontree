package sol;

import src.Row;

import java.util.List;

public class Leaf implements ITreeNode {
    String output;

    /**
     * The Leaf constructor creates a new Leaf with a specified outcome
     * passed in the createSubTrees method.
     **/
    public Leaf(String output) {
        this.output = output;
    }


    /**
     * Returns the output of the leaf, which is outcome or decision the method
     * has arrived at through recurring down the genereatedTree.
     *
     * @param: forDatum, a row which is the object of data that the method is
     * finding the decision for.
     * @returns: String, this.output corresponding to the value stored in the
     * leaf identified
     * to best fit the inputted row.
     **/
    @Override
    public String getDecision(Row forDatum) {
        return this.output;
    }
}

