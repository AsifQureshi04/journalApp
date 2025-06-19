package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void sendTestEmail() {
        emailService.sendSimpleMessage("asifq0407@gmail.com",
                "Testing java mail sender",
                "Hi, How are you");
    }
}
