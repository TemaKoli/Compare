import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AviaSoulsTest {
    Ticket ticket = new Ticket("MSK", "SPB", 3000, 20, 22);
    Ticket ticket4 = new Ticket("MSK", "SPB", 2500, 10, 13);
    Ticket ticket2 = new Ticket("MSK", "SPB", 6500, 15, 23);
    Ticket ticket3 = new Ticket("MSK", "SPB", 4000, 7, 12);
    Ticket ticket5 = new Ticket("MSK", "SPB", 4500, 12, 17);
    Ticket ticket6 = new Ticket("MSK", "NY", 4500, 18, 23);
    Ticket ticket7 = new Ticket("MSK", "LA", 6700, 18, 23);
    Ticket ticket8 = new Ticket("KR", "SPB", 5800, 18, 23);

    @Test
    public void TicketTestCompareToTiLessTi3() {

        ticket.compareTo(ticket);
        ticket.compareTo(ticket3);

        int expected = -1;
        int actual = ticket.compareTo(ticket3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void TicketTestCompareToTi2MoreTi() {

        ticket.compareTo(ticket2);
        ticket.compareTo(ticket);

        int expected = 1;
        int actual = ticket2.compareTo(ticket);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void TicketTestCompareToSamePrice() {

        ticket.compareTo(ticket5);
        ticket.compareTo(ticket6);

        int expected = 0;
        int actual = ticket5.compareTo(ticket6);

        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void TicketTestsSearchLotsOfMatchesSortPrice() {
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);

        //Т.к. сортируемые билеты отсортированы по Откуда/куда,
        // то после Arrays.sort(result) следующий параметр для сортировки будет - цена по возрастанию

        Ticket[] expected = {ticket4, ticket, ticket3, ticket5, ticket2};
        Ticket[] actual = aviaSouls.search("MSK", "SPB");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void TicketTestsSearchOneOfMatchesSortPrice() {
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);
        aviaSouls.add(ticket7);


        //Т.к. сортируемые билеты отсортированы по Откуда/куда,
        // то после Arrays.sort(result) следующий параметр для сортировки будет - цена по возрастанию

        Ticket[] expected = {ticket5};
        Ticket[] actual = aviaSouls.search("MSK", "SPB");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void TicketTestsSearchNoMoreMatchesSortPrice() {
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket6);
        aviaSouls.add(ticket7);
        aviaSouls.add(ticket8);


        //Т.к. сортируемые билеты отсортированы по Откуда/куда,
        // то после Arrays.sort(result) следующий параметр для сортировки будет - цена по возрастанию

        Ticket[] expected = {};
        Ticket[] actual = aviaSouls.search("MSK", "SPB");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void TicketTestsComparatorSortTimeTiLessTi4() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket); //time1 = 2
        aviaSouls.add(ticket4);//time2 = 3

        int expected = -1;
        int actual = timeComparator.compare(ticket, ticket4);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void TicketTestsComparatorSortTimeTi2MoreTi4() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket2); //time1 = 8
        aviaSouls.add(ticket4); //time2 = 3

        int expected = 1;
        int actual = timeComparator.compare(ticket2, ticket4);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void TicketTestsComparatorSortTimeTi6EqualsTi7() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket6); //time1 = 5
        aviaSouls.add(ticket7); //time2 = 5

        int expected = 0;
        int actual = timeComparator.compare(ticket6, ticket7);

        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void SearchAndSortByTestsComparatorLotsOfMatches() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket); //time1 = 2
        aviaSouls.add(ticket2);//time2 = 8
        aviaSouls.add(ticket3);//time3 = 5
        aviaSouls.add(ticket4);//time4 = 3
        aviaSouls.add(ticket5);//time5 = 5

        Ticket[] expected = {ticket, ticket4, ticket3, ticket5, ticket2};
        Ticket[] actual = aviaSouls.searchAndSortBy("MSK", "SPB", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchAndSortByTestsComparatorOneMatches() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls aviaSouls = new AviaSouls();
        aviaSouls.add(ticket5); //time1 = 5
        aviaSouls.add(ticket6);//time2 = 5
        aviaSouls.add(ticket7);//time3 = 5
        aviaSouls.add(ticket8);//time4 = 5


        Ticket[] expected = {ticket5};
        Ticket[] actual = aviaSouls.searchAndSortBy("MSK", "SPB", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchAndSortByTestsComparatorNoMoreMatches() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket6);//time2 = 5
        aviaSouls.add(ticket7);//time3 = 5
        aviaSouls.add(ticket8);//time4 = 5


        Ticket[] expected = {};
        Ticket[] actual = aviaSouls.searchAndSortBy("MSK", "SPB", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}