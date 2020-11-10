// --== CS400 File Header Information ==--
// Name: John Petrakian
// Email: petrakian@wisc.edu
// Team: LE
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: N/A
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

// NOTICE: DO NOT USE THE FOLLOWING PUNCTUATION IN TIMEPERIOD DESCRIPTIONS: ,[]()"-
// using these will affect reading files and will produce a faulty read.

/**
 * This class represents the Data Wrangler class for the initial graph which contains a CS400Graph
 * and has the ability to read and write files.
 * 
 * @author John Petrakian
 *
 */
public class InitialGraph extends CS400Graph<TimePeriod> {
  CS400Graph<TimePeriod> graph;
  private LinkedList<Vertex> vertices;

  /**
   * Constructor for InitialGraph with no parameters which creates a new empty graph.
   */
  public InitialGraph() {
    graph = new CS400Graph<TimePeriod>();
  }

  /**
   * Constructor for InitialGraph with a CS400Graph<TimePeriod> parameter which sets this graph to
   * be the initial graph.
   * 
   * @param graph the initial graph
   */
  public InitialGraph(CS400Graph<TimePeriod> graph) {
    this.graph = graph;
  } 

  /**
   * Provides the CS400Graph<TimePeriod> contained within InitialGraph.
   * 
   * @return the CS400Graph<TimePeriod> contained within InitialGraph
   */
  public CS400Graph<TimePeriod> getInternalGraph() {
    return graph;
  }

  /**
   * Insert a new vertex into the graph.
   * 
   * @param data the data item stored in the new vertex
   * @return true if the data can be inserted as a new vertex, false if it is already in the graph
   * @throws NullPointerException if data is null
   */
  @Override
  public boolean insertVertex(TimePeriod data) {
    return graph.insertVertex(data);
  }

  /**
   * Remove a vertex from the graph. Also removes all edges adjacent to the vertex from the graph
   * (all edges that have the vertex as a source or a destination vertex).
   * 
   * @param data the data item stored in the vertex to remove
   * @return true if a vertex with *data* has been removed, false if it was not in the graph
   * @throws NullPointerException if data is null
   */
  @Override
  public boolean removeVertex(TimePeriod data) {
    return graph.removeVertex(data);
  }

  /**
   * Insert a new directed edge with a positive edge weight into the graph.
   * 
   * @param source the data item contained in the source vertex for the edge
   * @param target the data item contained in the target vertex for the edge
   * @param weight the weight for the edge (has to be a positive integer)
   * @return true if the edge could be inserted or its weight updated, false if the edge with the
   *         same weight was already in the graph
   * @throws IllegalArgumentException if either source or target or both are not in the graph, or if
   *                                  its weight is < 0
   * @throws NullPointerException     if either source or target or both are null
   */
  @Override
  public boolean insertEdge(TimePeriod source, TimePeriod target, int weight) {
    return graph.insertEdge(source, target, weight);
  }

  /**
   * Remove an edge from the graph.
   * 
   * @param source the data item contained in the source vertex for the edge
   * @param target the data item contained in the target vertex for the edge
   * @return true if the edge could be removed, false if it was not in the graph
   * @throws IllegalArgumentException if either source or target or both are not in the graph
   * @throws NullPointerException     if either source or target or both are null
   */
  @Override
  public boolean removeEdge(TimePeriod source, TimePeriod target) {
    return graph.removeEdge(source, target);
  }

  /**
   * Check if the graph contains a vertex with data item *data*.
   * 
   * @param data the data item to check for
   * @return true if data item is stored in a vertex of the graph, false otherwise
   * @throws NullPointerException if *data* is null
   */
  @Override
  public boolean containsVertex(TimePeriod data) {
    return graph.containsVertex(data);
  }

  /**
   * Check if edge is in the graph.
   * 
   * @param source the data item contained in the source vertex for the edge
   * @param target the data item contained in the target vertex for the edge
   * @return true if the edge is in the graph, false if it is not in the graph
   * @throws NullPointerException if either source or target or both are null
   */
  @Override
  public boolean containsEdge(TimePeriod source, TimePeriod target) {
    return graph.containsEdge(source, target);
  }

  /**
   * Return the weight of an edge.
   * 
   * @param source the data item contained in the source vertex for the edge
   * @param target the data item contained in the target vertex for the edge
   * @return the weight of the edge (0 or positive integer)
   * @throws IllegalArgumentException if either sourceVertex or targetVertex or both are not in the
   *                                  graph
   * @throws NullPointerException     if either sourceVertex or targetVertex or both are null
   * @throws NoSuchElementException   if edge is not in the graph
   */
  @Override
  public int getWeight(TimePeriod source, TimePeriod target) {
    return graph.getWeight(source, target);
  }

  /**
   * Return the number of edges in the graph.
   * 
   * @return the number of edges in the graph
   */
  @Override
  public int getEdgeCount() {
    return graph.getEdgeCount();
  }

  /**
   * Return the number of vertices in the graph
   * 
   * @return the number of vertices in the graph
   */
  @Override
  public int getVertexCount() {
    return graph.getVertexCount();
  }

  /**
   * Check if the graph is empty (does not contain any vertices or edges).
   * 
   * @return true if the graph does not contain any vertices or edges, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return graph.isEmpty();
  }

  /**
   * Returns the shortest path between start and end. Uses Dijkstra's shortest path algorithm to
   * find the shortest path.
   * 
   * @param start the data item in the starting vertex for the path
   * @param end   the data item in the destination vertex for the path
   * @return list of data item in vertices in order on the shortest path between vertex with data
   *         item start and vertex with data item end, including both start and end
   * @throws NoSuchElementException when no path from start to end can be found including when no
   *                                vertex containing start or end can be found
   */
  @Override
  public List<TimePeriod> shortestPath(TimePeriod start, TimePeriod end) {
    return graph.shortestPath(start, end);
  }

  /**
   * Returns the cost of the path (sum over edge weights) between start and end. Uses Dijkstra's
   * shortest path algorithm to find the shortest path.
   * 
   * @param start the data item in the starting vertex for the path
   * @param end   the data item in the end vertex for the path
   * @return the cost of the shortest path between vertex with data item start and vertex with data
   *         item end, including all edges between start and end
   * @throws NoSuchElementException when no path from start to end can be found including when no
   *                                vertex containing start or end can be found
   */
  @Override
  public int getPathCost(TimePeriod start, TimePeriod end) {
    return graph.getPathCost(start, end);
  }

  /**
   * This method writes the graph to a file in a specific format.
   * 
   * @param fileName the name of the file to be written to
   * @throws IOException if an I/O exception of some sort has occurred
   */
  public void writeFile(String fileName) throws IOException {
    String output = "";
    String vertices = "Vertices[";
    String edges = "";
    Iterator<Vertex> vertexIterator = graph.vertices.values().iterator();
    // Adds vertices and edges lists to the file
    while (vertexIterator.hasNext()) {
      // Adds each vertex individually
      Vertex vertex = vertexIterator.next();
      TimePeriod data = vertex.data;
      String time =
          "(" + data.getYear() + "-" + data.getEnergy() + "-\"" + data.getDescription() + "\")";
      vertices += time;
      if (vertexIterator.hasNext())
        vertices += ", ";
      Iterator<Edge> edgeIterator = vertex.edgesLeaving.iterator();
      while (edgeIterator.hasNext()) {
        // Adds each edge from current vertex to edges list
        Edge edge = edgeIterator.next();
        String edgeString =
            data.getEnergy() + "->" + edge.target.data.getEnergy() + ", " + edge.weight;
        if (vertexIterator.hasNext() || edgeIterator.hasNext())
          edgeString += "\n";
        edges += edgeString;
      }
    }
    vertices += "]\n";
    // Combines the edges and vertices lists to make a single output for a file
    output += vertices + edges;
    File file = new File(fileName);
    FileWriter write = new FileWriter(file);
    // Writes the output to the file
    write.write(output);
    write.close();
  }

  /**
   * This method reads from a correctly formatted file and produces a graph based on that file or
   * adds vertices and edges to an already existing graph.
   * 
   * @param fileName the name of the file to be read from
   * @throws FileNotFoundException if the file can not be found
   */
  public void readFile(String fileName) throws FileNotFoundException {
    File file = new File(fileName);
    try {
      // Reads from provided file
      Scanner read = new Scanner(file);
      // Gets the vertices from the file
      ArrayList<TimePeriod> vertices = getVerticesFromFile(read.nextLine());
      // Gets the edges from the file
      int[][] edges = getEdgesFromFile(read);
      // Adds the vertices to the graph
      for (int i = 0; i < vertices.size(); i++) {
        graph.insertVertex(vertices.get(i));
      }
      // Adds the edges to the graph
      for (int i = 0; i < edges.length; i++) {
        TimePeriod start = getTimePeriod(edges[i][0], vertices);
        TimePeriod target = getTimePeriod(edges[i][1], vertices);
        int weight = edges[i][2];
        graph.insertEdge(start, target, weight);
      }
      read.close();
    } catch (FileNotFoundException e) {
      throw e;
    }
  }

  /**
   * Helper method which retrieves a TimePeriod object from the vertices in the graph based off of
   * and energy value.
   * 
   * @param energy   the TimePeriod energy
   * @param vertices the ArrayList containing the graph's vertices
   * @return the matching TimePeriod
   */
  private TimePeriod getTimePeriod(int energy, ArrayList<TimePeriod> vertices) {
    // Loops through vertices ArrayList to get matching TimePeriod
    for (int i = 0; i < vertices.size(); i++)
      if (vertices.get(i).getEnergy() == energy)
        return vertices.get(i);
    return null;
  }

  /**
   * This helper method retrieves the edges from a file represented as a 2D array of ints.
   * 
   * @param read the Scanner that reads from the provided file
   * @return the edges from a file represented as a 2D array of ints
   */
  private int[][] getEdgesFromFile(Scanner read) {
    ArrayList<Integer[]> edgesArray = new ArrayList<Integer[]>();
    // Each line from the second line contains an edge
    while (read.hasNext()) {
      // Gets edge int the following format: int[3]{start, target, weight}
      int[] edge = new int[3];
      String line = read.nextLine();
      edge[0] = Integer.parseInt(line.substring(0, line.indexOf("->")));
      line = line.substring(line.indexOf("->") + 2);
      edge[1] = Integer.parseInt(line.substring(0, line.indexOf(", ")));
      edge[2] = Integer.parseInt(line.substring(line.indexOf(",") + 2));
      Integer[] edgeIntegers = new Integer[3];
      edgeIntegers[0] = edge[0];
      edgeIntegers[1] = edge[1];
      edgeIntegers[2] = edge[2];
      edgesArray.add(edgeIntegers);
    }
    // Converts the ArrayList to an array
    int[][] edges = new int[edgesArray.size()][3];
    for (int i = 0; i < edgesArray.size(); i++) {
      edges[i][0] = edgesArray.get(i)[0];
      edges[i][1] = edgesArray.get(i)[1];
      edges[i][2] = edgesArray.get(i)[2];
    }
    return edges;
  }

  /**
   * Helper method that retrieves the vertices to add to the graph from the first line of a file.
   * 
   * @param firstLine the first line from the file
   * @return and ArrayList containing the data for the vertices
   */
  private ArrayList<TimePeriod> getVerticesFromFile(String firstLine) {
    String vertices = firstLine;
    vertices = vertices.substring(vertices.indexOf("[") + 1, vertices.length());
    ArrayList<TimePeriod> list = new ArrayList<TimePeriod>();
    // Retrieves the data for each vertex individually
    while (vertices.length() > 0) {
      String vertex = "";
      TimePeriod data = null;
      if (vertices.indexOf(", ") != -1) {
        vertex = vertices.substring(0, vertices.indexOf(", "));
        data = getTimePeriodFromString(vertex);
        vertices = vertices.substring(vertices.indexOf(", ") + 1);
      } else {
        vertex = vertices.substring(0, vertices.length() - 1);
        // Calls helper method once data is stripped
        data = getTimePeriodFromString(vertex);
        vertices = "";
      }
      list.add(data);
    }
    // Returns an ArrayList containing the data for each vertex
    return list;
  }

  /**
   * Helper method that retrieves a single vertex from a String representation of a vertex.
   * 
   * @param vertex String representation of a vertex
   * @return data for a vertex
   */
  private TimePeriod getTimePeriodFromString(String vertex) {
    // Converts String representation of vertex data to TimePeriod
    String s = vertex.trim();
    int year = Integer.parseInt(s.substring(1, s.indexOf("-")));
    s = s.substring(s.indexOf("-") + 1);
    int energy = Integer.parseInt(s.substring(0, s.indexOf("-")));
    String description = s.substring(s.indexOf("\"") + 1, s.indexOf(")") - 1);
    return new TimePeriod(year, energy, description);
  }

}