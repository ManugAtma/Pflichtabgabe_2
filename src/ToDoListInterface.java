import java.util.GregorianCalendar;

public interface ToDoListInterface {
	
	/**
	 * liefert die Laenge der ToDo-Liste zurueck
	 * @return laenge der ToDo-Liste
	 */
	//2 Punkte
	public abstract int length();
	
	
	/**
	 * liefert die Aufgabe zurueck, die am Index index in der ToDo-Liste vorhanden ist, und null sonst
	 * @param index gegebener Index
	 * @return Aufgabe, die am gegebenen Index in der ToDo-Liste vorhanden ist, null sonst
	 */
	//2 Punkte
	public abstract Task getTask(int index);
	
	
	/**
	 * liefert die Indizes der Aufgaben, die bis zur gegebenen deadline faellig sind
	 * @param deadline gegebene Deadline
	 * @return Indizes der faelligen Aufgaben
	 */
	//3 Punkte
	public int[] getPendingTasksUntilDeadline(GregorianCalendar deadline);
	
	
	/** 
	 * fuegt eine neue Aufgabe sortiert nach Deadline in die ToDo-Liste ein
	 * @param task einzufuegende Aufgabe
	 * @throws NullPointerException wenn Aufgabe null ist
	 */
	//4 Punkte
	public abstract void addTask(Task task) throws NullPointerException;
	
	
	/**
	 * entfernt eine Aufgabe am gegebenen Index aus der ToDo-Liste 
	 * @param index Index der zu entfernenden Aufgabe
	 * @throws IndexOutOfBoundsException wenn index unzulaessig ist
	 * @throws IllegalStateException wenn Liste leer ist
	 */
	//3 Punkte
	public abstract void removeTask(int index) throws IndexOutOfBoundsException, IllegalStateException;
	
	
	/**
	 * entfernt alle erledigten Aufgaben aus der ToDo-Liste
	 * @throws IllegalStateException wenn Liste leer ist
	 */
	//4 Punkte
	public void removeAllDoneTasks() throws IllegalStateException;
	
	
	/**
	 * markiert eine Aufgabe als erledigt
	 * @param index Index der zu markierten Aufgabe
	 * @throws IndexOutOfBoundsException wenn index unzulaessig ist
	 * @throws IllegalStateException wenn Liste leer ist
	 */
	//3 Punkte
	public abstract void markTaskAsDone(int index) throws IndexOutOfBoundsException, IllegalStateException;
	

	/**
	 * gibt die ToDo-Liste als String zurueck
	 */
	//2 Punkte
	public abstract String toString();
}
