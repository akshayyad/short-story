// --== CS400 File Header Information ==--
// Name: Akshay Yadlapalli
// Email: ayadlapalli@wisc.edu
// Team: LE
// Role: Test Engineer 2
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: N/A

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestTimeMachine {

    private InitialGraph initial;
    private TimeMachine machine;

    /**
     * Before each test started, create a new initial graph and a testing time
     * machine
     * 
     */
    @BeforeEach
    public void createGraph() {
        initial = new InitialGraph();
        machine = new TimeMachine();
    }


    /**
     * This method is testing the readFile() method from InitialGraph.java
     */
    @Test
    public void testLoad() {
        // Scenario 1: The file you are looking for doesn't Exist
        try {
            initial.readFile("input.txt");
        } catch (FileNotFoundException e) {
            fail("File Couldn't be found");
        }

        // Scenario 2: Check the Initial Number of Edges and Vertices from input.txt
        if (initial.getEdgeCount() != 15 || initial.getVertexCount() != 10) {
            fail("Error with number of Edges and Vertices.");
        }

        // Scenario 3: Check if a Node from Input.txt is in the Time Machine
        try {
            if (!machine.get(1998).equals("Year: " + 1998 + ", Energy: " + 11 + ", Description: \""
                + "Google founded." + "\"")) {
                fail("Some Nodes are Missing");
            }
        } catch (NoSuchElementException e) {
            e.getMessage();
        }
    }


    /**
     * This method is testing the insert() method from TimeMachine.java
     */
    @Test
    public void testAdd() {
        TimePeriod test = new TimePeriod(2001, 50, "Birthday");
        machine.insert(test);
        // Scenario 1: Check if a single Node is Added
        if (!machine.get(2001).equals(
            "Year: " + 2001 + ", Energy: " + 50 + ", Description: \"" + "Birthday" + "\"")) {
            fail("Error with Adding the Data");
        }
        // Scenario 2: Node shouldn't be added if node already exists
        TimePeriod timePeriodTest2 = new TimePeriod(2001, 52, "Birthday2");
        if (machine.insert(timePeriodTest2)) {
            fail("Cannot add a node with already existing year");
        }
    }


    /**
     * This method is testing the find() method from TimeMachine.java
     */
    @Test
    public void testSearch() {
        // Scenario 1: Search Through Inputed Data to see if Node Exists
        if (!machine.get(1955).equals("Year: " + 1955 + ", Energy: " + 63 + ", Description: \""
            + "Artificial intelligence created." + "\"")) {
            fail("Data error on loading");
        }

        // Scenario 2: Insert Nodes and Search for One
        for (int i = 0; i < 20; i++) {
            TimePeriod test = new TimePeriod(2000 + i, i, "Test" + i);
            machine.insert(test);
        }
        if (!machine.get(2005)
            .equals("Year: " + 2005 + ", Energy: " + 5 + ", Description: \"" + "Test5" + "\"")) {
            fail("Data error on loading");
        }

        // Scenario 3: Search for Non-existent Node
        try {
            machine.get(30000);
            fail("Didn't throw Exception when looking for Non-existent Node");
        } catch (NoSuchElementException e) {
            e.getMessage();
        }
    }


    /**
     * This method is testing the shortestPath() method from TimeMachine.java
     */
    @Test
    public void testShortestPath() {
        // Scenario 1: Test the Shortest Path between two nodes
        if (machine.shortestPath(1998, 1640) != 4) {
            fail("Error on shortestPath");
        }

        // Scenario 2: Trying to Find a Path to a Nonexistent Year
        try {
            machine.shortestPath(1998, 2001);
            fail("Error on non existing path");
        } catch (NoSuchElementException e) {
            e.getMessage();
        }

        // Scenario 2: Test the Shortest Path after Inserting a Node
        TimePeriod test = new TimePeriod(1566, 80, "testing");
        machine.insert(test);
        int testing = machine.shortestPath(1702, 1566);
        if (testing < 1 || testing > 22) {
            fail("Error with the ShortestPath Method");
        }
    }


    /**
     * This method is testing the Delete() Method from TimeMachine.java
     */
    @Test
    public void testDelete() {
        // Scenario 1: Deleting something that Doesn't Exist
        TimePeriod test = new TimePeriod(1111, 11, "11");
        try {
            machine.delete(test);
            machine.find(1111);
            fail("Didn't throw exception when deleting Non-existent Node");
        } catch (NoSuchElementException e) {
            e.getMessage();
        }

        // Scenario 2: Deleting Nodes that Are Added
        for (int i = 0; i < 20; i++) {
            TimePeriod test1 = new TimePeriod(2000 + i, i, "Test" + i);
            machine.insert(test1);
        }
        try {
            TimePeriod testing = machine.find(2005);
            machine.delete(testing);
            machine.get(2005);
            fail("Didn't throw exception in Scenario 2");
        } catch (NoSuchElementException e) {
            e.getMessage();
        }
    }


    /**
     * This method is testing the clear() method from TimeMachine.java
     */
    @Test
    public void testReset() {
        // Need to insert some new data in the Time Machine
        for (int i = 0; i < 20; i++) {
            TimePeriod test1 = new TimePeriod(2000 + i, i, "Test" + i);
            machine.insert(test1);
        }
        machine.clear();
        // Now Check if these new nodes were removed
        for (int i = 0; i < 20; i++) {
            try {
                machine.get(2000 + i);
                fail("Node still exists after clear");
            } catch (NoSuchElementException e) {
                e.getMessage();
            }
        }

        // Check if original data is still in the Time Machine
        if (!machine.get(1955).equals("Year: " + 1955 + ", Energy: " + 63 + ", " + "Description: \""
            + "Artificial intelligence created." + "\"")) {
            fail("Original Data Doesn't Exist");
        }
    }


    /**
     * This method is testing the writeFile() from InitialGraph.java
     */
    @Test
    public void testSave() {
        // Insert Data into the machine
        for (int i = 0; i < 20; i++) {
            TimePeriod test1 = new TimePeriod(2000 + i, i, "Test" + i);
            machine.insert(test1);
        }

        // Write the Data
        if (!machine.write("output.txt")) {
            fail("Did not write to a file");
        }

        // Now check if Data was Added
        try {
            File file = new File("output.txt");
            Scanner scan = new Scanner(file);
            String firstLine = scan.nextLine();
            String[] vertex = firstLine.split(", ");
            if (vertex.length != 30) {
                System.out.println(vertex.length);
                fail("Not every vertex is accounted for.");
            }
        scan.close();
        } catch (Exception e) {
            fail("There is an error in the Writing Process.");
        }
    }

}
