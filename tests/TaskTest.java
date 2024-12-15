import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    Task t1;
    Task t2;
    GregorianCalendar cal1;
    GregorianCalendar cal2;

    @BeforeEach
    public void createTasks() {

        cal1 = new GregorianCalendar();
        cal1.set(Calendar.DAY_OF_MONTH, 3);
        System.out.println(cal1.getTime());
        t1 = new Task("work out", cal1, false);

        cal2 = new GregorianCalendar();
        cal2.set(Calendar.DAY_OF_MONTH, 20);
        System.out.println(cal2.getTime());
        t2 = new Task("shop", cal2, false);
    }

    @Test
    public void testTaskConstructor() {
        assertThrows(NullPointerException.class, () -> new Task(null, cal1, false));
        assertThrows(NullPointerException.class, () -> new Task("work out", null, false));
        assertThrows(IllegalArgumentException.class, () -> new Task("", cal1, false));
    }


    @Test
    public void testTaskDeadlineGettersAndSetters() {
        assertEquals(t1.getDeadline(), cal1);

        GregorianCalendar cal3 = new GregorianCalendar();
        cal3.set(Calendar.DAY_OF_MONTH, 13);
        t1.setDeadline(cal3);
        assertEquals(t1.getDeadline(), cal3);

        assertThrows(NullPointerException.class, () -> t1.setDeadline(null));
    }

    @Test
    public void testTaskDescriptionGettersAndSetters() {
        assertEquals("work out", t1.getDescription());

        assertThrows(NullPointerException.class, () -> t1.setDescription(null));

        assertThrows(IllegalArgumentException.class, () -> t2.setDescription(""));
    }

    @Test
    public void testTaskDoneGettersAndSetters() {
        assertFalse(t1.getDone());

        t1.setDone(true);
        assertTrue(t1.getDone());
    }

    @Test
    public void testTaskCompareToNotEqual() throws Exception {
        assertTrue(t1.compareTo(t2) < 0);
        assertTrue(t2.compareTo(t1) > 0);
    }

    @Test
    public void testTaskCompareToEqual() throws Exception {

        cal1 = new GregorianCalendar();
        cal1.set(Calendar.DAY_OF_MONTH, 3);
        Thread.sleep(2000); // make sure cal1 and cal2 have different values of (milli)seconds
        cal2 = new GregorianCalendar();
        cal2.set(Calendar.DAY_OF_MONTH, 3);

        t1.setDeadline(cal1);
        t2.setDeadline(cal2);
        System.out.println(t1);
        System.out.println(t2);
        assertTrue(t1.compareTo(t2) == 0);
    }
}
