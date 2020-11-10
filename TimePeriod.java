// --== CS400 File Header Information ==--
// Name: John Petrakian
// Email: petrakian@wisc.edu
// Team: LE
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader: N/A

// NOTICE: DO NOT USE THE FOLLOWING PUNCTUATION IN TIMEPERIOD DESCRIPTIONS: ,[]()"-
// using these will affect reading files and will produce a faulty read.

/**
 * The time period object contains the year, energy, and description of a certain time period
 * 
 * @author John Petrakian
 *
 */
public class TimePeriod implements Comparable<TimePeriod> {
  private int year;
  private int energy;
  private String description;

  /**
   * TimePeriod constructor initializes the year, energy, and description variables.
   * 
   * @param year        the year of the time period
   * @param energy      the energy of a time period
   * @param description the description of a time period
   */
  public TimePeriod(int year, int energy, String description) {
    this.year = year;
    this.energy = energy;
    this.description = description;
  }

  /**
   * Provides the energy of the time period.
   * 
   * @return the energy of the time period
   */
  public int getEnergy() {
    return energy;
  }

  /**
   * Provides the year of the time period.
   * 
   * @return the year of the time period
   */
  public int getYear() {
    return year;
  }

  /**
   * Provides the description of the time period.
   * 
   * @return the description of the time period
   */
  public String getDescription() {
    return description;
  }

  /**
   * Provides a String representation of the time period in the following format "Year: yyyy,
   * Energy: eee, Description: "description"".
   * 
   * @return a String representation of the time period
   */
  public String toString() {
    return "Year: " + year + ", Energy: " + energy + ", Description: \"" + description + "\"";
  }

  /**
   * Provides a comparison to another TimePeriod represented as an int.
   * 
   * @param other the TimePeriod object that this one is being compared with
   * @return int representation of comparison
   */
  @Override
  public int compareTo(TimePeriod other) {
    return ((Integer) energy).compareTo((Integer) other.getEnergy());
  }

}