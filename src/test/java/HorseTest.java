import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    @Test
    void HorseNullExceptionMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse(null, 2, 2);
                });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void HorseEmptyExceptionMessage(String one) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse(one, 2, 2);
                });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void HorseTestInvalidSecondParameterExceptionMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse("Name", -2, 2);
                });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void HorseTestInvalidThirdParameterExceptionMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    Horse horse = new Horse("Name", 2, -2);
                });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void TestGetName() {
        Horse horse = new Horse("Name", 2, 2);
        String name = horse.getName();
        assertEquals("Name", name);
    }

    @Test
    void TestGetSpeed() {
        Horse horse = new Horse("name", 2, 2);
        double speed = horse.getSpeed();
        assertEquals(2, speed);
    }

    @Test
    void TestGetDistance() {
        Horse horse = new Horse("name", 2, 2);
        double distance = horse.getDistance();
        Horse anotherHorse = new Horse("name", 2);
        double anotherDistance = anotherHorse.getDistance();
        assertAll("Тест дистанций с разными конструкторами",
                () -> assertEquals(2, distance),
                () -> assertEquals(0, anotherDistance));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.3, 0.7})
    void TestMove(double arg) {
        try (MockedStatic<Horse> horses = Mockito.mockStatic(Horse.class)) {
            horses.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(arg);
            Horse horse = new Horse("name", 2);
            horse.move();
            assertEquals(horse.getSpeed() * arg, horse.getDistance());
        }


    }
}
