package sol;

public class Edge {
    String value;
    ITreeNode next;

    /**
     * this.value is a value associated with the edge that represents data of the attribute of the
     * corresponding node.
     * */

    public Edge (String value) {
        this.value = value;
    }

    /**
     * Returns the value of the edge
     * @param: NA
     * @returns: this.value, the value as a string of the edge.
     * @throws: NA
     * */

    public String getValue () {
        return this.value;
    }

    /**
     * Sets the next field in the edge to the inputted next node or leaf.
     * @param: next, a leaf or node to be set as next for the edge.
     * @returns: NA
     * @throws: NA
     * */

    public void setNext(ITreeNode next) {
        this.next = next;
    }

    /**
     * Returns the ITreeNode this.next of the edge.
     * @param: NA
     * @returns: this.next, the ITreeNode of the edge.
     * @throws: NA
     * */

    public ITreeNode getNext () {
        return this.next;
    }

}
