// --== CS400 File Header Information ==--
// Name: Arjun Lahiri
// Email: alahiri3@wisc.edu
// Team: LE
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader:

import java.io.*;
import java.util.*;

 
/**
 * This class builds upon the TimeWeb interface and utilizes the
 */
public class TimeMachine implements TimeWeb<TimePeriod>{

    // Max connections between time periods
    private static int maxConnectionsPerPeriod = 2; 

    // Maximum Energy for connection
    private static int maxEnergy = 10; 


    // Input file to read from
    private static String inputFile = "input.txt";


    // Implement graph with Time Period Elements
    public CS400Graph<TimePeriod> graph;

    // Store record of visited time periods
    public LinkedList<TimePeriod> catalogue; 


    public TimeMachine() {
        InitialGraph initGraph = new InitialGraph();
        try {
            initGraph.readFile(inputFile);
        }catch (FileNotFoundException e) {
            System.out.println("File not found, using empty graph!");
            graph = new CS400Graph<>();
        }
        graph = initGraph.getInternalGraph();
        catalogue = new LinkedList<>(graph.vertices.keySet());
    }

    /**
     * Inserts a time period into the time machine with an energy within 0-10 and a maximum connections
     * between the time periods of 2
     * @param timePeriod insert
     * @return true if time period has been inserted
     */
    @Override
    public boolean insert(TimePeriod timePeriod) {

        // First make sure there is not another time period with same year
        for (TimePeriod time : catalogue) {
            if (time.getYear() == timePeriod.getYear()) {
                return false;
            }
        }

        try {
            graph.insertVertex(timePeriod);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return false;
        } 

        // add timePeriod to visited time periods
        catalogue.add(timePeriod);




        // creates random connections between the Time period inserted with other time periods orginating from target
        int connections = (int) (Math.random() * maxConnectionsPerPeriod + 1);

        connections = Math.min(connections, catalogue.size() - 1);

        for (int i = 0; i < connections; i++) {

            TimePeriod target = catalogue.get((int) (Math.random() * catalogue.size() ));
            // adds a connecting edge to target
            if(!(graph.containsEdge(target, timePeriod) || graph.containsEdge(timePeriod, target)) && !target.equals(timePeriod)) {
                graph.insertEdge(target, timePeriod, genWeight(timePeriod));
            } else
                i--; // makes sure that a connections is made
        }


        // creates random number of connections between timePeriod to insert and previously catalogued connections
        // ending at target
        connections = (int) (Math.random() * maxConnectionsPerPeriod + 1);
        connections = Math.min(connections, catalogue.size() - 1);

        for (int i = 0; i < connections; i++) {
            TimePeriod target = catalogue.get((int) (Math.random() * catalogue.size() ));
            // adds a connecting edge to target
            if(!(graph.containsEdge(target, timePeriod) || graph.containsEdge(timePeriod, target)) && !target.equals(timePeriod))
                graph.insertEdge(timePeriod, target, genWeight(timePeriod));
            else
                i--; // makes sure a connection is made
        }

        return true;
    }

    /**
     * Returns the String representation of time period based on year if it exists in the catalogue of goable times
     * @param year to find
     * @return String representation of time Period
     * @throws NoSuchElementException if time period of year does not exist
     */
    @Override
    public String get(int year) throws NoSuchElementException {
        for (TimePeriod timePeriod : catalogue)
            if(timePeriod.getYear() == year)
                return timePeriod.toString();
        throw new NoSuchElementException("Year does not exist in record!");
    }

    
    


    /** 
     * @return the shortest distance between 2 years
     */
    @Override
    public int shortestPath(int startYear, int endYear) {
        TimePeriod startPeriod = find(startYear);
        TimePeriod endPeriod = find(endYear);
        return graph.getPathCost(startPeriod, endPeriod);
    }


    /**
     * Helper method for shortestPath()
     * @param year to find
     * @return TimePeriod of year
     * @throws NoSuchElementException if time period of year does not exist
     */
    public TimePeriod find(int year) throws NoSuchElementException {
        for (TimePeriod timePeriod : catalogue)
            if(timePeriod.getYear() == year)
                return timePeriod;
        throw new NoSuchElementException("Year does not exist in record!");
    }




   /**
     * removes a time period from the time machine
     * @param timePeriod
     * @return either true or false if inserted
     */
    @Override
    public boolean delete(TimePeriod timePeriod) {
        if (graph.containsVertex(timePeriod))
            return graph.removeVertex(timePeriod) && catalogue.remove(timePeriod);
        return false;
    }

    
    /**
     * Decided to use clear method to simply reset to default graph given
     */
    @Override
    public void clear() {

       InitialGraph initializer = new InitialGraph();
        try {
            initializer.readFile(inputFile);
            graph = initializer.getInternalGraph();
            catalogue = new LinkedList<>(graph.vertices.keySet());

        }catch (FileNotFoundException e) {
            catalogue = new LinkedList<>();
            graph = new CS400Graph<>();
        }

    }


 





/************************************************* Implement TimePeriod Attributes utilizing file *************************************************
    /**
     * writes the current graph to a file. 
     * @param fileName
     * @return
     */
    public boolean write(String fileName) {
        InitialGraph writer = new InitialGraph(graph);
        try {
            writer.writeFile(fileName);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * generates a random weight
     * @param timePeriod the weight may or may not be based on
     * @return weight
     */
    private int genWeight(TimePeriod timePeriod){
        return (int) (Math.random() * maxEnergy + 1);
    }

}