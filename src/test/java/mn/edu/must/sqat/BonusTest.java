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

    @Test
    @DisplayName("B3: null attendance | attendance31->32T")
    void b3_nullAttendance() {
        org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class, () -> DataFlowTargets.bonus(null, 3));
    }

    @Test
    @DisplayName("B4: negative threshold | threshold31->32T")
    void b4_negativeThreshold() {
        org.junit.jupiter.api.Assertions.assertThrows(
                IllegalArgumentException.class, () -> DataFlowTargets.bonus(new int[]{1}, -1));
    }

    @Test
    @DisplayName("B5: empty array, thr=1 | present35->50F, score49->56")
    void b5_emptyArrayThresholdOne() {
        assertEquals(0, DataFlowTargets.bonus(new int[]{}, 1));
    }

    @Test
    @DisplayName("B6: five in a row, present==thr | score51->54, best43->53T")
    void b6_fiveInARowExactThreshold() {
        assertEquals(8, DataFlowTargets.bonus(new int[]{1, 1, 1, 1, 1}, 5));
    }

    @Test
    @DisplayName("B7: 1,0,1 | best43->42F, streak46->41, score51->56")
    void b7_shortStreakAfterBreak() {
        assertEquals(5, DataFlowTargets.bonus(new int[]{1, 0, 1}, 1));
    }

}
