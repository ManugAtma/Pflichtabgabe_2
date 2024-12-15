import java.util.GregorianCalendar;

public class Task implements TaskInterface {
    private String description;
    private GregorianCalendar deadline;
    private boolean done;

    public Task() {
        this.description = "unknown";
        this.deadline = new GregorianCalendar();
        this.done = false;
        System.out.println("default task object created");
    }

    public Task(String description, GregorianCalendar deadline, boolean done) throws NullPointerException, IllegalArgumentException {
        if (description == null || deadline == null) throw new NullPointerException();
        if (description.isEmpty()) throw new IllegalArgumentException("description cannot be empty");

        this.description = description;
        this.deadline = deadline;
        this.done = done;
    }

    @Override
    public void setDescription(String description) throws NullPointerException, IllegalArgumentException {
        if (description == null) throw new NullPointerException("description cannot be null");
        if (description.isEmpty()) throw new IllegalArgumentException("description cannot be empty");
        this.description = description;
    }

    @Override
    public void setDeadline(GregorianCalendar deadline) throws NullPointerException {
        if (deadline == null) throw new NullPointerException("deadline cannot be null");
        this.deadline = deadline;
    }

    @Override
    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public GregorianCalendar getDeadline() {
        return deadline;
    }

    @Override
    public boolean getDone() {
        return done;
    }

    /**
     * vergleicht this mit dem als Parameter uebergebenen Objekt anotherTask
     *
     * @param anotherTask uebergebenes Objekt, mit dem this verglichen wird
     * @return -1, falls this kleiner ist als anotherTask;
     * 0, falls beide Objekte gleich gross sind;
     * 1, falls this groesser ist als anotherTask
     * @throws NullPointerException wenn p null ist
     */
    @Override
    public int compareTo(Task anotherTask) throws NullPointerException {

        if (anotherTask == null) throw new NullPointerException("anotherTask cannot be null");

        if ((this.deadline.get(GregorianCalendar.YEAR) == anotherTask.deadline.get(GregorianCalendar.YEAR))
                && (this.deadline.get(GregorianCalendar.MONTH) == anotherTask.deadline.get(GregorianCalendar.MONTH))
                && (this.deadline.get(GregorianCalendar.DAY_OF_MONTH) == anotherTask.deadline.get(GregorianCalendar.DAY_OF_MONTH))
        ) return 0;

        if (this.deadline.compareTo(anotherTask.getDeadline()) < 0) return -1;
        if (this.deadline.compareTo(anotherTask.getDeadline()) > 0) return 1;
        return 0;
    }

    @Override
    public String toString() {

        String status;
        if (done) status = "✔";
        else status = "x";

        int day = deadline.get(GregorianCalendar.DAY_OF_MONTH);
        int month = deadline.get(GregorianCalendar.MONTH) + 1;
        int year = deadline.get(GregorianCalendar.YEAR);

        return description + ", "
                + day + "."
                + month + "."
                + year + " "
                + status;
    }
}