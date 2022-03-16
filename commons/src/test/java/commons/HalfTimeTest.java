package commons;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class HalfTimeTest {
    @Test
    void TestEquals(){
        Timer timer = new Timer(0,20);
        Timer timer2 = new Timer(0,19);
        String time = timer.toString();
        String time2 = timer2.toString();
        HalfTime a = new HalfTime("username",time);
        HalfTime b = new HalfTime("username",time);
        HalfTime c = new HalfTime("talkingbenthedog",time2);
        assertTrue(a.equals(b));
        assertFalse(b.equals(c));
        assertFalse(a.equals(c));
    }
    @Test
    void TestHashCode(){
        Timer timer = new Timer(0,20);
        String time = timer.toString();
        HalfTime a = new HalfTime("username",time);
        HalfTime b = new HalfTime("username",time);
        assertEquals(a.hashCode(),b.hashCode());

    }
    @Test
    void TestToString(){
        Timer timer = new Timer(0,20);
        String time = timer.toString();
        HalfTime a = new HalfTime("username",time);
        assertLinesMatch(List.of(
                "commons.HalfTime@[0-9a-f]*\\[",
                "  id=0",
                "  time="+time,
                "  username=username",
                "]"
        ), Arrays.asList(a.toString().split(System.lineSeparator())));


    }
    @Test
    void TestGetStatus(){
        Timer timer = new Timer(0,20);
        String time = timer.toString();
        HalfTime a = new HalfTime ("username",time);
        String b = "username USED HALF TIME at "+time;
        assertEquals(b,a.getStatus());


    }

  
}