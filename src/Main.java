import java.util.GregorianCalendar;

import static java.util.Calendar.HOUR_OF_DAY;

public class Main {
    public static void main(String[] args) {

        GregorianCalendar cal1 = new GregorianCalendar();
        cal1.set(HOUR_OF_DAY,3);
        System.out.println(cal1.getTime());
        Task t1 = new Task("work out", cal1, false);

        GregorianCalendar cal2 = new GregorianCalendar();
        cal2.set(HOUR_OF_DAY,20);
        System.out.println(cal2.getTime());
        Task t2 = new Task("shop", cal2, false);

        GregorianCalendar cal3 = new GregorianCalendar();
        cal3.set(HOUR_OF_DAY,9);
        System.out.println(cal3.getTime());
        Task t3 = new Task("call neo", cal3, false);


        ToDoList list = new ToDoList();
        System.out.println(list.length());
        list.addTask(t1);
        System.out.println(list.length());
        list.addTask(t2);
        System.out.println(list.length());
        list.addTask(t3);
        System.out.println(list.length());


        list.markTaskAsDone(0);
        list.markTaskAsDone(1);
        list.markTaskAsDone(2);


        ToDoList list2 = new ToDoList();
        System.out.println(list2);

        GregorianCalendar cal4 = new GregorianCalendar();
        cal4.set(HOUR_OF_DAY,10);
        int [] arr = list.getPendingTasksUntilDeadline(cal4);
        System.out.println(arr.length);

        /*list.removeTask(0);
        System.out.println(list.head.next.task);
        list.removeTask(1);
        System.out.println(list.length());
        System.out.println(list.head.task);*/

        /*list.removeAllDoneTasks();
        System.out.println(list.length());*/



    }
}