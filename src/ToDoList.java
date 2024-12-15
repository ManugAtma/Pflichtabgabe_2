import java.util.Arrays;
import java.util.GregorianCalendar;

public class ToDoList implements ToDoListInterface {

    private Entry head; // private
    private int length = 0;  // private

    public ToDoList() {
        head = null;
    }

    /**
     * liefert die Laenge der ToDo-Liste zurueck
     *
     * @return laenge der ToDo-Liste
     */
    @Override
    public int length() {
        return length;
    }

    /**
     * liefert die Aufgabe zurueck, die am Index index in der ToDo-Liste vorhanden ist, und null sonst
     *
     * @param index gegebener Index
     * @return Aufgabe, die am gegebenen Index in der ToDo-Liste vorhanden ist, null sonst
     */
    @Override
    public Task getTask(int index) {
        if (index < 0 || index >= length) return null;
        Entry p = head;
        int i = 0;
        while (i < index) {
            p = p.next;
            i++;
        }
        return p.task;
    }


    /**
     * liefert die Indizes der Aufgaben, die bis zur gegebenen deadline faellig sind
     *
     * @param deadline gegebene Deadline
     * @return Indizes der faelligen Aufgaben
     */
    @Override
    public int[] getPendingTasksUntilDeadline(GregorianCalendar deadline) {

        if (head == null) return new int[0];

        int[] arr = new int[length];
        int i = 0;
        Entry p = head;
        Task task = new Task("x", deadline, false); // to be able to use compareTo method from Task
        while (p != null && (p.task.compareTo(task) <= 0)) {
            arr[i] = i;
            p = p.next;
            i++;
        }
        return Arrays.copyOfRange(arr, 0, i);
    }


    /**
     * fuegt eine neue Aufgabe sortiert nach Deadline in die ToDo-Liste ein
     *
     * @param task einzufuegende Aufgabe
     * @throws NullPointerException wenn Aufgabe null ist
     */
    @Override
    public void addTask(Task task) throws NullPointerException {

        if (task == null) throw new NullPointerException("task cannot be null");
        Entry newEntry = new Entry(task);
        Entry p = head;

        // empty list
        if (head == null) {
            head = newEntry;
            length++;
            return;
        }

        // traverse
        while (p.next != null && p.next.task.compareTo(task) < 0) {
            p = p.next;
        }

        // insert
        if (p.next != null) newEntry.next = p.next;  // if end of list not reached
        p.next = newEntry;

        length++;
    }

    /**
     * deletes an Entry from list
     *
     * @param entry the Entry object whose successor should be deleted
     * @throws IndexOutOfBoundsException wenn index unzulaessig ist
     * @throws IllegalStateException     wenn Liste leer ist
     */
    private void deleteNextEntry(Entry entry) {
        entry.next = entry.next.next;
    }

    /**
     * entfernt eine Aufgabe am gegebenen Index aus der ToDo-Liste
     *
     * @param index Index der zu entfernenden Aufgabe
     * @throws IndexOutOfBoundsException wenn index unzulaessig ist
     * @throws IllegalStateException     wenn Liste leer ist
     */
    @Override
    public void removeTask(int index) throws IndexOutOfBoundsException, IllegalStateException {

        if (head == null) throw new IllegalStateException("list is empty");
        if (index < 0 || index >= length) throw new IndexOutOfBoundsException();

        // if first task is to be removed
        if (index == 0) {
            head = head.next;
            length--;
            return;
        }

        // traverse
        Entry p = head;
        int i = 0;
        while (i < index - 1) {
            p = p.next;
            i++;
        }

        // remove task
        this.deleteNextEntry(p);
        length--;
    }

    /**
     * entfernt alle erledigten Aufgaben aus der ToDo-Liste
     *
     * @throws IllegalStateException wenn Liste leer ist
     */
    @Override
    public void removeAllDoneTasks() throws IllegalStateException {

        if (length == 0) throw new IllegalStateException("list is empty");

        // remove at beginning of list
        while (head != null && head.task.getDone()) {
            head = head.next;
            length--;
        }

        // if list is now empty
        if (head == null) return;

        // traverse
        Entry p = head;
        while (p.next != null) {
            if (p.next.task.getDone()) {
                deleteNextEntry(p);
                length--;
            } else {
                p = p.next;
            }
        }
    }


    /**
     * markiert eine Aufgabe als erledigt
     *
     * @param index Index der zu markierten Aufgabe
     * @throws IndexOutOfBoundsException wenn index unzulaessig ist
     * @throws IllegalStateException     wenn Liste leer ist
     */
    @Override
    public void markTaskAsDone(int index) throws IndexOutOfBoundsException, IllegalStateException {

        if (head == null) throw new IllegalStateException("list is empty");
        if (index < 0 || index >= length) throw new IndexOutOfBoundsException();

        // traverse
        Entry p = head;
        int i = 0;
        while (i < index) {
            p = p.next;
            i++;
        }

        // mark task as done
        p.task.setDone(true);
    }

    /**
     * gibt die ToDo-Liste als String zurueck
     */
    @Override
    public String toString() {

        String result = "ToDos:" + "\n";
        Entry p = head;
        for (int i = 0; i < length; i++) {
            result += "[" + i + "] " + p.task.toString() + "\n";
            p = p.next;
        }
        return result;
    }
}

// Fragen
// 1. Aufgabenstellung: alle unerledigten Aufgaben erfragen vs. Interface removeAllDoneTasks
// 2. zwei Objekte vom Typ GregorianCalendar die das gleiche Datum, aber unterschiedliche (Milli)Sekunden haben,
// gelten nicht als gleich. Sie sollten es aber laut Aufgabenstellung, oder nicht?
// 3. keine NullpointerException für getPendingTasksUntilDeadline?
// 4. getPendingTasksUntilDeadline: sollen Tasks mit gleicher Deadline im Ergebnis berücksichtigt werden?