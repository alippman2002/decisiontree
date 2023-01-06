package sol;

import src.Row;

import java.util.List;

public class Node implements ITreeNode {
    String attribute;
    String defaultAttribute;
    List<Edge> possibleValues;
    Dataset data;

    /**
     * this.data = data is a dataset that is input when generating the node.
     * this.attribute is the attribute of the node found using a randomizer.
     * this.defaultAttribute uses the passed in outcome to find most common
     * distinct outcome. this.possibleValues is the list of edges or unique
     * attribute values from the this.attribute.
     */
    public Node(Dataset data, String outcome) {
        this.data = data;
        this.attribute = data.makeRandomAttribute(); // random attribute
        this.defaultAttribute = data.getDefault(outcome);
        this.possibleValues = data.getEdges(this.attribute);
    }

    /**
     * Returns the attribute of the node
     *
     * @returns: A string which is the attribute of the node
     */
    public String getAttribute() {
        return this.attribute;
    }

    /**
     * Returns the dataset of the node, which is this.data
     *
     * @returns: this.data, A dataset which is the data used by the
     * node to generate the following list of edges.
     */
    public Dataset getData() {
        return this.data;
    }

    /**
     * Returns the list of edges of the node, (possibleValues)
     *
     * @returns: this.possibleValues, A list of edges which is the list of
     * distinct attribute values for the attribute of the node
     * used by the node to generate the following subtrees for each edge.
     */
    public List<Edge> getEdges() {
        return this.possibleValues;
    }

    /**
     * Returns the output of the node by going down the subtree comparing
     * the input forDatum values with the edgeValues for the attributes
     * of the tree.
     *
     * @param: forDatum, a row which is the object of data that the
     * method is finding the decision for.
     *
     * @returns: String, corresponding to the value stored in the leaf
     * identified that best matches the values of the forDatum
     * checked with the attributes in the tree.
     * to best fit the inputted row.
     */
    @Override
    public String getDecision(Row forDatum) {
        if (!this.possibleValues.isEmpty()) {
            for (Edge edge : this.possibleValues) {
                if (forDatum.getAttributeValue(this.attribute).equals(edge.getValue())) {
                    return edge.getNext().getDecision(forDatum);
                }
            }
        }
        return this.defaultAttribute;
    }
}
