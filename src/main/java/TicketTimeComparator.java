import java.util.Comparator;

/////////////////////////Данный класс инмпелементирует компоратор (сравниватель) по времени полёта//////////////////////
public class TicketTimeComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket t1, Ticket t2) { //сравниваем 2 объекта - время полёта в 2-х билетах
        int time1 = t1.getTimeTo() - t1.getTimeFrom();
        int time2 = t2.getTimeTo() - t2.getTimeFrom();
        if (time1 < time2) {                                   //timeFrom - время вылета (по москве)
            return -1;                                         //timeTo - время прилёта (по москве)
        } else if (time1 > time2) {
            return +1;
        } else {
            return 0;
        }
    }
}