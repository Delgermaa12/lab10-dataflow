package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusTest {

    // ===== ШАТ 1: ALL-DEFS (2 тест) — энэ шатаар commit хий =====

    @Test
    @DisplayName("B1: break in streak, present<thr, best>=5 | score49->54, score54->56, streak46->41, best43->42F")
    void b1_breakThenLongStreak() {
        assertEquals(3, DataFlowTargets.bonus(new int[]{1, 1, 0, 1, 1, 1, 1, 1}, 10));
    }

    @Test
    @DisplayName("B2: empty array, thr=0 | score51->56, present35->50T, best37->53F")
    void b2_emptyArrayThresholdZero() {
        assertEquals(5, DataFlowTargets.bonus(new int[]{}, 0));
    }

}
