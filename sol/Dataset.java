package sol;

import src.IDataset;
import src.Row;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * A class that implements the IDataset interface,
 * representing a training data set.
 */

public class Dataset implements IDataset {
    List<Row> dataObject;
    List<String> attributeList;

    public Dataset(List<String> attributesList, List<Row> dataObjects) {
        this.dataObject = dataObjects;
        this.attributeList = attributesList;
    }

    /**
     * Checks the database to see if it is null (having no attributes).
     *
     * @param: no inputs
     * @returns: true if the size is 0, so null and false otherwise
     * @throws: NA
     */
    public boolean checkIfNull() {
        if (this.attributeList.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Partitions the dataset based on the attribute and edge value input.
     *
     * @param: edgeValue, the edgeValue for the attribute that you are making a partitioned
     * dataset for. attribute is the attribute that you are going to look at for the
     * attribute values.
     *
     * @returns: partitionedData, a partitioned dataset
     */
    public Dataset partition(String edgeValue, String attribute) {
        List<Row> newDataObjects = new LinkedList<>();
        List<String> copyList = new ArrayList<>(this.attributeList);
        copyList.remove(attribute);
        for (int i = 0; i < dataObject.size(); i++) {
            if (this.dataObject.get(i).getAttributeValue(attribute).equals(edgeValue)) {
                newDataObjects.add(this.dataObject.get(i));
            }
        }
        Dataset partitionedData = new Dataset(copyList, newDataObjects);
        return partitionedData;
    }

    /**
     * Generates a list of edges of unique attribute values for a inputted attribute.
     *
     * @param: attributeToGetValues, a string of the attribute for which you will
     * make a list of edges for.
     *
     * @returns: edges, a list of edges of unique attribute values.
     */
    public List<Edge> getEdges(String attributeToGetValues) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < this.dataObject.size(); i++) {
            boolean edgeFound = false;
            String edgeVal = this.dataObject.get(i).
                    getAttributeValue(attributeToGetValues); //gets the value of the attribute for each row
            Edge edgeToAdd = new Edge(edgeVal);
            for (Edge edge : edges) {
                if (edge.getValue().equals(edgeVal)) {
                    edgeFound = true;
                }
            }
            if (!edgeFound) {
                edges.add(edgeToAdd);
            }
        }
        return edges;
    }

    /**
     * Returns a random attribute from the list of attributes.
     *
     * @returns: randomAttribute, a random attribute as a string.
     */
    public String makeRandomAttribute() {
        Random random = new Random();
        int upperBound = this.attributeList.size();
        int randomNum = random.nextInt(upperBound);
        return this.attributeList.get(randomNum);
    }

    /**
     * Generates a list of storage of the distinct outcomes for a input outcome attribute.
     *
     * @param: outocme, the attribute for which you are comparing attribute values
     * to generate distinct outcomes.
     * @returns: storageList, a list of storage which contains distinct outcome values.
     */
    public List<Storage> getDistinctOutcomes(String outcome) {
        List<Storage> storageList = new ArrayList<>();
        String val = this.dataObject.get(0).getAttributeValue(outcome);
        storageList.add(new Storage(val, 0));
        for (int i = 0; i < this.dataObject.size(); i++) {
            String value = this.dataObject.get(i).getAttributeValue(outcome);
            if (!this.storageHelper(storageList, value)) {
                storageList.add(new Storage(value, 0));
            }
        }
        return storageList;
    }

    /**
     * Generates the string that is the default for the tree
     * for data it does not have a edge for.
     *
     * @param: outocme, the attribute for which you are
     * trying to find a outcome for the data.
     * @returns: defaultAttribute, the most common distinct outcome in the dataset.
     */
    public String getDefault(String outcome) {
        List<Storage> storageList = this.getDistinctOutcomes(outcome);
        String defaultAttribute = storageList.get(0).getOutcomeVal();
        for (int j = 0; j < storageList.size(); j++) {
            for (int k = j + 1; k < storageList.size(); k++) {
                if (storageList.get(j).getCount() < storageList.get(k).getCount()) {
                    defaultAttribute = storageList.get(k).getOutcomeVal();
                }
            }
        }
        return defaultAttribute;
    }

    /**
     * Helper for storage for determing there are no repeated values in storage.
     *
     * @param: storedObjs, a list of storage you are comparing
     * with value, a string that you are
     * maybe going to put into the list of storage.
     *
     * @returns: true if value equals any value in the storage list
     * and false otherwise. If true add to counter
     * which is used for determining the most common
     * distinct outcome for setting the default attribute.
     */
    private boolean storageHelper(List<Storage> storedObjs, String value) {
        boolean itemFound = false;
        for (Storage access : storedObjs) {
            if (access.getOutcomeVal().equals(value)) {
                access.addToCounter();
                itemFound = true;
            }
        }
        return itemFound;
    }

    /**
     * Returns the attributeList of the dataset.
     *
     * @returns: attributeList, a List<String>
     */
    @Override
    public List<String> getAttributeList() {
        return this.attributeList;
    }

    /**
     * Returns the dataObjects of the dataset.
     *
     * @param: NA
     * @returns: dataObject, a List<Row>
     * @throws: NA
     */
    @Override
    public List<Row> getDataObjects() {
        return this.dataObject;
    }

    /**
     * Returns the size of the dataset in terms of how many rows are in datgaObject.
     *
     * @returns: int size, the size of this.dataObject
     */
    @Override
    public int size() {
        return this.dataObject.size();
    }

    /**
     * Removes the outcomeAttribute from the attributeList so it is not picked for a node.
     *
     * @param: outcomeAttribute, the outcome attribute the tree is being generated for.
     */
    public void removeAttribute(String outcomeAttribute) {
        this.attributeList.remove(outcomeAttribute);
    }

}
