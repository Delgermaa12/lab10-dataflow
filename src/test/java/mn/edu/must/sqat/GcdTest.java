package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GcdTest {

    @Test
    @DisplayName("G1: gcd(7,0) - loop skipped; pairs x13->20, y13->15F")
    void gcd_7_0_skipsLoop() {
        assertEquals(7, DataFlowTargets.gcd(7, 0));
    }

    @Test
    @DisplayName("G2: gcd(12,8) - 2 iterations; all loop pairs")
    void gcd_12_8_twoIterations() {
        assertEquals(4, DataFlowTargets.gcd(12, 8));
    }
}
