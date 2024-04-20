import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HippodromeTest {

    @Test
    void HippodromeNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    Hippodrome hippodrome = new Hippodrome(null);
                });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }
    @Test
    void HippodromeEmpty() {
        List<Horse> horses = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    Hippodrome hippodrome = new Hippodrome(horses);
                });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void TestGetHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> horses1 = hippodrome.getHorses();
        assertEquals(horses, horses1);

    }

    @Test
    void TestMove() {
        List<Horse> horseList = Mockito.spy(new ArrayList<>());
        for (int i = 0; i < 50; i++) {
            horseList.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        for (Horse horse : horseList) {
            verify(horse).move();
        }
    }

    @Test
    void TestGetWinner() {
        Horse horseOne = new Horse("ssd", 2, 5);
        Horse horseTwo = new Horse("ssj", 2, 9);
        List<Horse> horses = List.of(horseOne, horseTwo);
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();
        assertSame(horseTwo, winner);
    }
}
