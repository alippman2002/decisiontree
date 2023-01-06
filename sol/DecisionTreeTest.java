package sol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import com.sun.source.tree.Tree;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import src.DecisionTreeCSVParser;
import src.Row;

import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

public class DecisionTreeTest {
    private List<Row> dataObjectList;
    private List<String> dataAttributeList;
    private Dataset main;

    // Constructor for DecisionTreeTest tester class - don't need to add anything in here!
    public DecisionTreeTest() {
    }

    @Before
    public void setup() {
        this.dataObjectList =  DecisionTreeCSVParser.parse(
                "/Users/imacpro/Desktop/cs200/projects/decision-tree-adeendar-alippman2002/data/candidates/" +
                        "training-gender-equal.csv");
        this.dataAttributeList = new ArrayList<>(dataObjectList.get(0).getAttributes());
        Row spinach = new Row("spinach");
        spinach.setAttributeValue("color", "green");
        spinach.setAttributeValue("calories", "low");
        spinach.setAttributeValue("foodType", "vegetable");
        Row orange = new Row("orange");
        orange.setAttributeValue("color", "orange");
        orange.setAttributeValue("calories", "high");
        orange.setAttributeValue("foodType", "fruit");
        Row cucumber = new Row("cucumber");
        cucumber.setAttributeValue("color", "green");
        cucumber.setAttributeValue("calories", "low");
        cucumber.setAttributeValue("foodType", "vegetable");
        Row carrot = new Row("carrot");
        carrot.setAttributeValue("color", "orange");
        carrot.setAttributeValue("calories", "medium");
        carrot.setAttributeValue("foodType", "vegetable");
        Row sweetPotato = new Row("sweet potato");
        sweetPotato.setAttributeValue("color", "orange");
        sweetPotato.setAttributeValue("calories", "high");
        sweetPotato.setAttributeValue("foodType", "vegetable");
        Row avocado = new Row("avocado");
        avocado.setAttributeValue("color", "green");
        avocado.setAttributeValue("calories", "high");
        avocado.setAttributeValue("foodType", "fruit");
        Row banana = new Row("banana");
        banana.setAttributeValue("color", "yellow");
        banana.setAttributeValue("calories", "high");
        banana.setAttributeValue("foodType", "fruit");
        List<Row> dataObjectsSmallT = new ArrayList<Row>();
        dataObjectsSmallT.add(spinach);
        dataObjectsSmallT.add(orange);
        dataObjectsSmallT.add(cucumber);
        dataObjectsSmallT.add(carrot);
        dataObjectsSmallT.add(sweetPotato);
        dataObjectsSmallT.add(avocado);
        dataObjectsSmallT.add(banana);
        List<String> attributeListSmallT = new ArrayList<String>();
        attributeListSmallT.add("color");
        attributeListSmallT.add("calories");
        attributeListSmallT.add("foodType");
        Dataset smallTestData = new Dataset(attributeListSmallT, dataObjectsSmallT);
        this.main = smallTestData;
    }

    /**
     * Testing that the default method works as intended
     */
    @Test
    public void defaultTest() {
        Assert.assertEquals(this.main.getDefault("foodType"), "vegetable");
    }

    /**
     * Testing that dataAttributeList size is accurate
     */
    @Test
    public void datasetSizeTest () {
        Assert.assertEquals(dataAttributeList.size(), 8);
    }

    /**
     * Testing that getAttirbtueList returns the correct attributes
     */
    @Test
    public void getAttributeList() {
        Assert.assertEquals(this.main.getAttributeList().toString(),
                "[color, calories, foodType]");
    }

    /**
     * Testing for empty dataset
     */
    @Test(expected = IndexOutOfBoundsException.class)
        public void emptyEmptyCSV() {
            List<Row> newDataObjectList =  DecisionTreeCSVParser.parse(
                    "/Users/imacpro/Desktop/cs200/projects/decision-tree-adeendar-alippman2002/data" +
                            "/empty/empty.csv");
            List <String> newDataAttributeList = new ArrayList<>(newDataObjectList.get(0).getAttributes());
            TreeGenerator tree = new TreeGenerator();
            Dataset data = new Dataset(newDataAttributeList, newDataObjectList);
            tree.generateTree(data, "outcome");
            tree.getDecision(newDataObjectList.get(0));
        }


    /**
     * Testing for accurate getDecision on smaller tree
     */
    @Test
    public void treeGeneratorTest1() {
        Row spinach = new Row("spinach");
        spinach.setAttributeValue("color", "green");
        spinach.setAttributeValue("calories", "low");
        spinach.setAttributeValue("foodType", "vegetable");
        TreeGenerator tree = new TreeGenerator();
        tree.generateTree(this.main, "foodType");
        Assert.assertEquals(tree.getDecision(spinach), "vegetable");
    }
}
