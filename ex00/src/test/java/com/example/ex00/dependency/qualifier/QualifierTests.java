package com.example.ex00.dependency.qualifier;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@Slf4j
@WebAppConfiguration
public class QualifierTests {

    @Autowired
    private Computer computer;
    @Autowired
    @Qualifier("outback")
    private Restuarant restuarant;

    @Test
    public void QualifierTests() {
        log.info("---------------");
        log.info("steak : " + Restuarant.steakPrice);
        log.info("sidebar : " + restuarant.checkSideBar());
        log.info("----------------------");
    }
}
