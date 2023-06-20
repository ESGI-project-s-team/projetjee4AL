package fr.esgi.rent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTest {
    @Test
    public void applicationContextLoaded() {
    }
    @Test
    void contextLoads() {
        Application.main(new String[] {});
    }

}


