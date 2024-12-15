import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.junit.jupiter.api.Assertions.*;


public class ToDoListTest {

    ToDoList threeEntriesList;
    ToDoList oneEntryList;
    ToDoList emptyList;
    Task t1;

    @BeforeEach
    public void createLists() {

        GregorianCalendar cal1 = new GregorianCalendar();
        cal1.set(Calendar.DAY_OF_MONTH, 3);
        // System.out.println(cal1.getTime());
        t1 = new Task("work out", cal1, false);

        GregorianCalendar cal2 = new GregorianCalendar();
        cal2.set(Calendar.DAY_OF_MONTH, 20);
        // System.out.println(cal2.getTime());
        Task t2 = new Task("shop", cal2, false);

        GregorianCalendar cal3 = new GregorianCalendar();
        cal3.set(Calendar.DAY_OF_MONTH, 9);
        // System.out.println(cal3.getTime());
        Task t3 = new Task("call neo", cal3, false);

        threeEntriesList = new ToDoList();
        threeEntriesList.addTask(t1);
        threeEntriesList.addTask(t2);
        threeEntriesList.addTask(t3);
        System.out.println(threeEntriesList);

        oneEntryList = new ToDoList();
        oneEntryList.addTask(t1);

        emptyList = new ToDoList();
    }

    @Test
    public void testLength() {
        assertEquals(threeEntriesList.length(), 3);
    }

    @Test
    public void testGetTask() {
        assertEquals(t1, threeEntriesList.getTask(0));
    }

    @Test
    public void testGetTaskEmptyList() {
        assertEquals(null, threeEntriesList.getTask(6));
        assertEquals(null, emptyList.getTask(2));

    }

    @Test
    public void testAddTask() {
        assertEquals(threeEntriesList.length(), 3);
        assertEquals(oneEntryList.length(), 1);
        assertEquals(emptyList.length(), 0);

        assertThrows(NullPointerException.class, () -> threeEntriesList.addTask(null));

        System.out.println(threeEntriesList);
        System.out.println(oneEntryList);
        System.out.println(emptyList);
    }

    @Test
    public void testAddTaskWithEqualDeadline() {
        GregorianCalendar cal4 = new GregorianCalendar();
        cal4.set(Calendar.DAY_OF_MONTH, 9);
        Task t4 = new Task("text batman", cal4, false);
        threeEntriesList.addTask(t4);
        System.out.println(threeEntriesList);
        assertEquals(threeEntriesList.getTask(1), t4);
    }

    @Test
    public void testRemoveTaskFromBeginning() {
        threeEntriesList.removeTask(0);
        assertEquals(threeEntriesList.length(), 2);
        System.out.println(threeEntriesList);
    }

    @Test
    public void testRemoveTaskFromEnd() {
        threeEntriesList.removeTask(2);
        assertEquals(threeEntriesList.length(), 2);
        System.out.println(threeEntriesList);

        // test for one entry list
        oneEntryList.removeTask(0);
        assertEquals(oneEntryList.length(), 0);
    }

    @Test
    public void testRemoveTaskFromMiddle() {
        threeEntriesList.removeTask(1);
        assertEquals(threeEntriesList.length(), 2);
        System.out.println(threeEntriesList);


        assertThrows(IllegalStateException.class, () -> emptyList.removeTask(0));
    }

    @Test
    public void testRemoveTaskFromMEmptyList() {
        assertThrows(IllegalStateException.class, () -> emptyList.removeTask(0));
    }



    @Test
    public void testRemoveAllDoneTasksOnlyFromBeginning() {
        threeEntriesList.markTaskAsDone(0);
        threeEntriesList.markTaskAsDone(1);
        threeEntriesList.markTaskAsDone(2);
        threeEntriesList.removeAllDoneTasks();
        assertEquals(threeEntriesList.length(), 0);
    }

    @Test
    public void testRemoveAllDoneTasksFromBeginningAndEnd() {
        threeEntriesList.markTaskAsDone(0);
        threeEntriesList.markTaskAsDone(2);
        threeEntriesList.removeAllDoneTasks();
        assertEquals(threeEntriesList.length(), 1);
        System.out.println(threeEntriesList);
    }

    @Test
    public void testRemoveAllDoneTasksFromMiddleAndEnd() {
        threeEntriesList.markTaskAsDone(1);
        threeEntriesList.markTaskAsDone(2);
        threeEntriesList.removeAllDoneTasks();
        assertEquals(threeEntriesList.length(), 1);
        System.out.println(threeEntriesList);
    }

    @Test
    public void testRemoveAllDoneTasksOnlyFromMiddle() {
        threeEntriesList.markTaskAsDone(1);
        threeEntriesList.removeAllDoneTasks();
        assertEquals(threeEntriesList.length(), 2);
        System.out.println(threeEntriesList);
    }

    @Test
    public void testRemoveAllDoneTasksOnlyFromEnd() {
        threeEntriesList.markTaskAsDone(2);
        threeEntriesList.removeAllDoneTasks();
        assertEquals(threeEntriesList.length(), 2);
        System.out.println(threeEntriesList);
    }

    @Test
    public void testRemoveAllDoneTasksNoTasksDone() {
        threeEntriesList.removeAllDoneTasks();
        assertEquals(threeEntriesList.length(), 3);
        System.out.println(threeEntriesList);
    }

    @Test
    public void testGetPendingTasksUntilDeadlineUntilMiddle() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_MONTH, 15);
        System.out.println(cal.getTime());
        assertEquals(2, threeEntriesList.getPendingTasksUntilDeadline(cal).length);
    }

    @Test
    public void testGetPendingTasksUntilDeadlineUntilEnd() {

        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_MONTH, 25);
        assertEquals(3, threeEntriesList.getPendingTasksUntilDeadline(cal).length);
        assertEquals(1, oneEntryList.getPendingTasksUntilDeadline(cal).length);
    }

    @Test
    public void testGetPendingTasksEmptyList() {

        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_MONTH, 25);
        assertEquals(0, emptyList.getPendingTasksUntilDeadline(cal).length);
    }

    @Test
    public void testGetPendingTasksResult0() {

        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        assertEquals(0, threeEntriesList.getPendingTasksUntilDeadline(cal).length);
    }

    @Test
    public void testGetPendingTasksEqualDeadline() {

        threeEntriesList.markTaskAsDone(0);
        threeEntriesList.markTaskAsDone(1);

        GregorianCalendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_MONTH, 9);

        assertEquals(2, threeEntriesList.getPendingTasksUntilDeadline(cal).length);
    }
}
