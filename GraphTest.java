// --== CS400 File Header Information ==--
// Name: Akshay Yadlapalli
// Email: ayadlapalli@wisc.edu
// Team: LE
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: N/A

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.NoSuchElementException;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

    private CS400Graph<Integer> graph;
    @BeforeEach
    /** 
     * Instantiate graph from last week's shortest path activity.
     */
    public void createGraph() {
        graph = new CS400Graph<>();
        // insert vertices 0-9
        for(int i=0;i<10;i++)
            graph.insertVertex(i);
        // insert edges from Week 08. Dijkstra's Activity
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,7,2);
        graph.insertEdge(1,8,4);
        graph.insertEdge(2,4,4);
        graph.insertEdge(2,6,3);
        graph.insertEdge(3,1,6);
        graph.insertEdge(3,7,2);
        graph.insertEdge(4,5,4);
        graph.insertEdge(5,0,2);
        graph.insertEdge(5,1,4);
        graph.insertEdge(5,9,1);
        graph.insertEdge(6,3,1);
        graph.insertEdge(7,0,3);
        graph.insertEdge(7,6,1);
        graph.insertEdge(8,9,3);
        graph.insertEdge(9,4,5);
    }

   
    /**
     * Checks the distance/total weight cost from the vertex labeled 0 to 8
     * (should be 15), and from the vertex labeled 9 to 8 (should be 17).
     */
    @Test 
    public void providedTestToCheckPathCosts() {
        System.out.println(graph.getPathCost(0,8));
        System.out.println(graph.getPathCost(9,8));
        System.out.println(graph.getPathCost(0,2));
        assertTrue(graph.getPathCost(0,8) == 15);    
        assertTrue(graph.getPathCost(9,8) == 17);
        assertTrue(graph.getPathCost(0,2) == 1);
    }

    
    /**
     * Checks the ordered sequence of data within vertices from the vertex 
     * labeled 0 to 8, and from the vertex labeled 9 to 8.
     */
    @Test
    public void providedTestToCheckPathContents() {
        System.out.println(graph.shortestPath(0,8).toString());
        assertTrue(graph.shortestPath(0, 8).toString().equals(
            "[0, 2, 6, 3, 1, 8]"
        ));
        assertTrue(graph.shortestPath(9, 8).toString().equals(
            "[9, 4, 5, 1, 8]"
        ));
    }
    
    
    /**
     * Checks the distance/total weight from the vertex and target from the Activity.
     * In my case, the Source is 6, and the Target is 5. This should result in a
     * value of 15.
     */
    @Test
    public void pathCostFromActivity() {
        assertTrue(graph.getPathCost(6,5) == 15);
    }
    
    
    /**
     * Checks the ordered sequence from the vertex and target from the Activity.
     * In my case, the Source is 6, and the Target is 5.
     */
    @Test
    public void sequenceFromActivity() {
        assertTrue(graph.shortestPath(6,5).toString().equals(
            "[6, 3, 7, 0, 2, 4, 5]"));
    }
    
    
    /**
     * This test makes sure that the algorithm throws a NoSuchElement Exception 
     * if the Path between a Source and Target doesn't exist.
     */
    @Test
    public void testImpossiblePath() {
        int testConditional = 0;

        //11 points to 9, but nothing currently points to 11
        graph.insertVertex(11);
        graph.insertEdge(11, 9, 5);
        try {
            graph.shortestPath(6, 11);
        } catch (NoSuchElementException e) {
            testConditional++;
        }
        assertTrue(testConditional == 1);
    }
    
    
    /**
     * This test makes sure that the algorithm throws a NoSuchElement Exception 
     * if null or nonexistent values are inputed for the Source and/or Target
     */
    @Test
    public void testForNullorFakeInputs() {
        int testConditional = 0;
        //Scenario 1: Source and Target Vertex don't exist
        try {
            graph.shortestPath(12, 13);
        } catch (NoSuchElementException e) {
            testConditional++;
        }
        assertTrue(testConditional == 1);
        
        //Scenario 2: Source and Target are Null
        try {
            graph.shortestPath(null, null);
        } catch (NoSuchElementException e) {
            testConditional++;
        }
        assertTrue(testConditional == 2);
    }
}