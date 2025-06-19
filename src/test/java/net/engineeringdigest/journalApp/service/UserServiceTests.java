package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired UserService userService;

    @ParameterizedTest
//    @CsvSource({
//            "ram",
//            "Asif",
//            "vipul"
//    })
//    @ValueSource(strings = {
//            "ram",
//            "Asif",
//            "vipul"
//    })
    @Disabled
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user) {
        assertTrue(userService.saveNewUser(user));
    }

    @ParameterizedTest
    @Disabled
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,5"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected,a+b);
    }
}
