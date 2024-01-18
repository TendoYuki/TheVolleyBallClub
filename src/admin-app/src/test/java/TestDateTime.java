import com.volleyball.club.datetime.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class TestDateTime extends TestCase{
    private static DateTime dtByString;
    private static DateTime dtByDt;
    private static DateTime dtbyLdLt;

    @BeforeAll
    public static void initiate(){
        try{
            dtByString = new DateTime("2002-06-15 14:42:00");
            dtByDt = new DateTime(dtByString);
            dtbyLdLt = new DateTime(LocalDate.parse("2006-04-16"),LocalTime.parse("10:30:00"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testGetLocalDate() {
        Assertions.assertEquals("2002-06-15", dtByString.getLocalDate().toString());
        Assertions.assertEquals("2002-06-15", dtByDt.getLocalDate().toString());
        Assertions.assertEquals("2006-04-16", dtbyLdLt.getLocalDate().toString());
    }

    @Test
    public void testGetLocalTime() {
        Assertions.assertEquals("14:42", dtByString.getLocalTime().toString());
        Assertions.assertEquals("14:42", dtByDt.getLocalTime().toString());
        Assertions.assertEquals("10:30", dtbyLdLt.getLocalTime().toString());
    }

    @Test
    public void testSetLocalDate() {
        dtByString.setLocalDate(LocalDate.parse("2004-11-16"));
        Assertions.assertEquals("2004-11-16", dtByString.getLocalDate().toString());
    }

    @Test
    public void testSetLocalTime() {
        dtByString.setLocalTime(LocalTime.parse("15:45:00"));
        Assertions.assertEquals("15:45", dtByString.getLocalTime().toString());
    }

    @Test
    public void testEquals() {
        Assertions.assertTrue(dtByString.equals(dtByDt));
    }

    @Test
    public void testCompareTo() {
        Assertions.assertEquals(-1, dtByString.compareTo(dtbyLdLt)); // dtByString avant dtbyLdLt
        Assertions.assertEquals(1, dtbyLdLt.compareTo(dtByString)); // dtbyLdLt avant dtByString
        Assertions.assertEquals(0, dtByDt.compareTo(dtByString)); // dtByString et dtByDt egaux
    }
}