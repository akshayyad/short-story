// --== CS400 File Header Information ==--
// Name: Arjun Lahiri
// Email: alahiri3@wisc.edu
// Team: LE
// TA: Divyanshu Saxena
// Lecturer: Gary Dahl
// Notes to Grader:
import java.util.List;
import java.util.NoSuchElementException;

public interface TimeWeb<T extends Comparable<T>> {
    public boolean insert(T key);
    public String get(int year) throws NoSuchElementException;
    public int shortestPath(int startYear, int endYear);
    public boolean delete(T key);
    public void clear();
}
