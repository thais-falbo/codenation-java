package challenge;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class MainTest {

    @Test
    public void q1() throws Exception {
        assertNotEquals(0, new Main().q1());
    }

    @Test
    public void q2() throws Exception {
        int result = new Main().q2();
        assertEquals(647, new Main().q2());
    }

    @Test
    public void q3() throws Exception {
        List<String> result = new Main().q3();

        assertNotNull(result);
        assertEquals(20, result.size());
    }

    @Test
    public void q4() throws Exception {
        List<String> result = new Main().q4();

        List<String> expected = new ArrayList<>(
                Arrays.asList(
                        "Neymar da Silva Santos Jr.",
                        "Lionel Messi",
                        "Luis Suárez",
                        "C. Ronaldo dos Santos Aveiro",
                        "Eden Hazard",
                        "Toni Kroos",
                        "Kevin De Bruyne",
                        "Antoine Griezmann",
                        "Robert Lewandowski",
                        "Gareth Bale"
                )
        );

        assertEquals(expected, result);
        assertNotNull(result);
        assertEquals(10, result.size());
    }

    @Test
    public void q5() throws Exception {
        List<String> result = new Main().q5();

        List<String> expected = new ArrayList<>(
                Arrays.asList(
                        "Barry Richardson",
                        "Essam El Hadary",
                        "Óscar Pérez",
                        "Jimmy Walker",
                        "Danny Coyne",
                        "Chris Day",
                        "Joaquim Manuel Sampaio Silva",
                        "Kjetil Wæhler",
                        "Timmy Simons",
                        "Benjamin Nivet"
                )
        );

        assertEquals(expected, result);
        assertNotNull(result);
        assertEquals(10, result.size());
    }

    @Test
    public void q6() throws Exception {
        Map<Integer, Integer> result = new Main().q6();

        assertNotNull(result);
        assertNotEquals(0, result.size());
    }

}
