import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

class MainTest {
    @Test
    @Timeout(value = 22000, unit = TimeUnit.MILLISECONDS)
    @Disabled
    void testMain() throws Exception {
        Main.main(null);
    }
}
