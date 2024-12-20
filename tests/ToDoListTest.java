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
    Task t2;
    Task t3;

    @BeforeEach
    public void createLists() {

        GregorianCalendar cal1 = new GregorianCalendar(2024, Calendar.MARCH, 3);
        t1 = new Task("work out", cal1, false);

        GregorianCalendar cal2 = new GregorianCalendar(2024, Calendar.MARCH, 20);
        t2 = new Task("shop", cal2, false);

        GregorianCalendar cal3 = new GregorianCalendar(2024, Calendar.MARCH, 9);
        t3 = new Task("call neo", cal3, false);

        threeEntriesList = new ToDoList();
        threeEntriesList.addTask(t1);
        threeEntriesList.addTask(t2);
        threeEntriesList.addTask(t3);
        // System.out.println(threeEntriesList);

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
        assertEquals(t2, threeEntriesList.getTask(2));
        assertEquals(t3, threeEntriesList.getTask(1));

        GregorianCalendar cal4 = new GregorianCalendar(2024, Calendar.MARCH, 1);
        Task t4 = new Task("text batman", cal4, false);
        threeEntriesList.addTask(t4);
        assertEquals(t4, threeEntriesList.getTask(0));

        assertEquals(t1, oneEntryList.getTask(0));

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
        GregorianCalendar cal4 = new GregorianCalendar(2024, Calendar.MARCH, 9);
        Task t4 = new Task("text batman", cal4, false);
        threeEntriesList.addTask(t4);
        System.out.println(threeEntriesList);
        assertEquals(threeEntriesList.getTask(1), t4);


        GregorianCalendar cal5 = new GregorianCalendar(2024, Calendar.MARCH, 9);
        Task t5 = new Task("text robin", cal5, false);
        threeEntriesList.addTask(t5);
        System.out.println(threeEntriesList);
        assertEquals(threeEntriesList.getTask(1), t5);
    }

    @Test
    public void testAddTaskInFirstPlace(){
        GregorianCalendar cal4 = new GregorianCalendar(2024, Calendar.MARCH, 1);
        Task t4 = new Task("text batman", cal4, false);
        threeEntriesList.addTask(t4);
        System.out.println(threeEntriesList);
        assertEquals(threeEntriesList.getTask(0), t4);
    }

    @Test
    public void testAddTaskInLastPlace(){
        GregorianCalendar cal4 = new GregorianCalendar(2024, Calendar.MARCH, 25);
        Task t4 = new Task("text batman", cal4, false);
        threeEntriesList.addTask(t4);
        System.out.println(threeEntriesList);
        assertEquals(threeEntriesList.getTask(3), t4);
    }

    @Test
    public void testRemoveTaskFromBeginning() {
        System.out.println(threeEntriesList);

        threeEntriesList.removeTask(0);
        assertEquals(threeEntriesList.length(), 2);
        System.out.println(threeEntriesList);

        threeEntriesList.removeTask(0);
        System.out.println(threeEntriesList);
        assertEquals(threeEntriesList.length(), 1);

        threeEntriesList.removeTask(0);
        System.out.println(threeEntriesList);
        assertEquals(threeEntriesList.length(), 0);

    }

    @Test
    public void testRemoveTaskFromEnd() {
        System.out.println(threeEntriesList);
        threeEntriesList.removeTask(2);
        assertEquals(threeEntriesList.length(), 2);
        System.out.println(threeEntriesList);

        // test for one entry list
        oneEntryList.removeTask(0);
        assertEquals(oneEntryList.length(), 0);
    }

    @Test
    public void testRemoveTaskFromMiddle() {
        System.out.println(threeEntriesList);
        threeEntriesList.removeTask(1);
        assertEquals(threeEntriesList.length(), 2);
        System.out.println(threeEntriesList);

        assertThrows(IllegalStateException.class, () -> emptyList.removeTask(0));
    }

    @Test
    public void testRemoveTaskFromBMiddleMultipleTimes() {


        GregorianCalendar cal4 = new GregorianCalendar(2024, Calendar.MARCH, 25);
        Task t4 = new Task("text batman", cal4, false);
        threeEntriesList.addTask(t4);
        System.out.println(threeEntriesList);

        threeEntriesList.removeTask(3);
        assertEquals(threeEntriesList.length(), 3);
        System.out.println(threeEntriesList);

        threeEntriesList.addTask(t4);
        assertEquals(threeEntriesList.length(), 4);
        System.out.println(threeEntriesList);

        threeEntriesList.removeTask(3);
        System.out.println(threeEntriesList);
        assertEquals(threeEntriesList.length(), 3);

        threeEntriesList.markTaskAsDone(0);
        threeEntriesList.markTaskAsDone(2);
        threeEntriesList.removeAllDoneTasks();
        System.out.println("after removeAllDoneTasks");
        System.out.println(threeEntriesList);

        threeEntriesList.removeTask(0);
        System.out.println(threeEntriesList);
        assertEquals(threeEntriesList.length(), 0);

       /* threeEntriesList.removeTask(0);
        System.out.println(threeEntriesList);
        assertEquals(threeEntriesList.length(), 1);*/

    }


    @Test
    public void testRemoveTaskFromOneEntryList() {
        System.out.println(oneEntryList);
        oneEntryList.removeTask(0);
        System.out.println(oneEntryList);
    }

    @Test
    public void testRemoveTaskFromEmptyList() {
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
        GregorianCalendar cal = new GregorianCalendar(2024, Calendar.MARCH, 10);
        t1.setDone(true);
        System.out.println(threeEntriesList);
        assertEquals(1, threeEntriesList.getPendingTasksUntilDeadline(cal).length);
    }

    @Test
    public void testGetPendingTasksUntilDeadlineUntilEnd() {

        GregorianCalendar cal = new GregorianCalendar(2024, Calendar.MARCH, 25);

        t1.setDone(true);
        t2.setDone(true);
        System.out.println(threeEntriesList);

        assertEquals(1, threeEntriesList.getPendingTasksUntilDeadline(cal).length);
    }

    @Test
    public void testGetPendingTasksEmptyList() {

        GregorianCalendar cal = new GregorianCalendar(2024, Calendar.MARCH, 25);
        assertEquals(0, emptyList.getPendingTasksUntilDeadline(cal).length);
    }

    @Test
    public void testGetPendingTasksResult0() {

        GregorianCalendar cal = new GregorianCalendar(2024, Calendar.MARCH, 1);
        assertEquals(0, threeEntriesList.getPendingTasksUntilDeadline(cal).length);
    }

    @Test
    public void testGetPendingTasksEqualDeadline() {

        t1.setDone(true);
        System.out.println(threeEntriesList);
        GregorianCalendar cal = new GregorianCalendar(2024, Calendar.MARCH, 9);
        assertEquals(1, threeEntriesList.getPendingTasksUntilDeadline(cal).length);
    }
}
