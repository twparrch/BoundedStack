import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class BoundedStackTest {

    private static int passed = 0;
    private static int failed = 0;

    /** helper กลาง — พิมพ์ PASS/FAIL และนับผลให้เอง */
    private static void check(String name, boolean condition) {
        if (condition) {
            passed++;
            System.out.println("[PASS] " + name);
        } else {
            failed++;
            System.out.println("[FAIL] " + name);
        }
    }
    public static void main(String[] args)  { 
        boolean assertsOn = false;
        assert assertsOn = true; // ใช้ตรวจสอบวาสเปิด Assertion หรือไม่
        if (!assertsOn) { //กรณีถ้ายังไม่เปิด Assertion
            System.out.println("WARNING: assertions disabled"
                    + " - re-run with: java -ea PlaylistTest\n"); 
        } //เพื่อบอกให้เปิด Assertion

        System.out.println("=== Playlist Test Suite ===\n");

        testCreators(); //เรียกเมธอดทดสอบสร้าง Playlist
        testAdd(); // ทดสอบการเพิ่มเพลง
        testRemove(); // ทดสอบการลบเพลง
        testObservers(); //ทดสอบว่ามีการแจ้งเตือนเมื่อ Playlist หรือไม่
        testProducer(); // ทดสอบProducer หรือส่วนสร้างข้อมูล
        testExposure(); //ตรวจสอบว่าไม่ได้คืนค่าตัวแปรภายในออกไปจนถูกแก้ไขได้

        System.out.println("\n=== Summary ==="); 
        System.out.println("Passed: " + passed); //แสดงจำนวน test ที่ผ่าน
        System.out.println("Failed: " + failed); //แสดงจำนวน test ที่ไม่ผ่าน
        System.out.println("Total : " + (passed + failed)); //แสดงทั้งหมด = ผ่าน+ไม่ผ่าน
        System.out.println(failed == 0 ? "ALL TESTS PASSED" : "SOME TESTS FAILED");
        // เงื่อนไข ? ถ้าเป็นจริง : ค่าถ้าเป็นเท็จ
        if (failed > 0) { // ถ้ามีการทดสอบไม่ผ่านอย่างน้อย 1 รายการ ให้ทำคำสั่งในด้าน
            System.exit(1); // จบการทำงานของโปรแกรมทันที
        }
    }
}