package com.hnust.wx_ticket;

import com.hnust.wx_ticket.entity.Film;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class WxTicketApplicationTests {

    @Test
    void contextLoads() {
        Film film = new Film();
        System.out.println(film);
    }

}
