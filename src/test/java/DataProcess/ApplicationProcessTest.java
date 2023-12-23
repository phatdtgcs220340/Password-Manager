package DataProcess;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.JUnitException;
import com.phatdo.DataProcess.ApplicationProcess;

public class ApplicationProcessTest {
    @Test
    public void testFindApplication() {
        try {
            assertEquals(ApplicationProcess.findApplication("Udemy"), "Udemy");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
