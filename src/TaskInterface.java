import java.util.GregorianCalendar;

public interface TaskInterface {


	/**
	 * sets a description text for the task with the given value
	 * @param description - a String containing the description
	 * @throws NullPointerException if description is null
	 * @throws IllegalArgumentException if description is an empty String
	 */
	public abstract void setDescription(String description) throws NullPointerException, IllegalArgumentException;

	/**
	 * sets a deadline of the task with the given value
	 * @param deadline - an object of type GregorianCalendar
	 * @throws NullPointerException if deadline is null
	 */
	public abstract void setDeadline(GregorianCalendar deadline) throws NullPointerException;

	/**
	 * sets a status - done or not done - for the task with the given value
	 * @param done - a boolean value, true for done and false for not done
	 */
	public abstract void setDone(boolean done);

	/** returns the description of the task
	 * @return a String containing the description of the task
	 */
	public abstract String getDescription();

	/** returns the deadline of the task
	 * @return an object of type GregorianCalendar that contains the deadline for the given task
	 */
	public abstract GregorianCalendar getDeadline();

	/**
	 * returns a value indicating if the task is done
	 * @return true if the task was marked as done, false otherwise
	 */
	public abstract boolean getDone();
	
	/**
	 * vergleicht this mit dem als Parameter uebergebenen Objekt anotherTask
	 * @param anotherTask uebergebenes Objekt, mit dem this verglichen wird
	 * @return -1, falls this kleiner ist als anotherTask; 
	 * 			0, falls beide Objekte gleich gross sind; 
	 * 			1, falls this groesser ist als anotherTask 
	 * @throws NullPointerException wenn p null ist
	 */
	public abstract int compareTo(Task anotherTask) throws NullPointerException;


	/**
	 * provides essential info about the task and its attributes as String
	 * @return a String in the format: <description>, <deadline> <✔ or ✘>
	 *     ✔ if task is done and ✘ if task is not done
	 */
	public abstract String toString();
}
