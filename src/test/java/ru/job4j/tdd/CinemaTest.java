package ru.job4j.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Disabled
class CinemaTest {
    @Test
    void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions).isNull();
    }

    @Test()
    void whenInvalidPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThrows(IllegalArgumentException.class, () -> {
            cinema.buy(account, -1, 1, date);
        });
    }

    @Test
    void whenAdd() {
       Cinema cinema = new Cinema3D();
       Session session = new Session3D();
       cinema.add(session);
       assertThat(cinema.find(s -> true)).isNotNull()
           .hasSize(1)
           .contains(session);
    }

    @Test
    void whenInvalidDateThenMustGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = new GregorianCalendar(2022, Calendar.OCTOBER, 32 );
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, date))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(String.valueOf(date));
    }

    @Test
    void whenBuyingSameTicketsThenMustGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThatThrownBy(() -> cinema.buy(account, 1, 1, date))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(String.valueOf(ticket));
    }

}