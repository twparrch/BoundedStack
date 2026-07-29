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
                    + " - re-run with: java -ea BoundedStackTest\n"); 
        } //เพื่อบอกให้เปิด Assertion

        System.out.println("=== Playlist Test Suite ===\n");

        // test case อันนี้เป็นเคสตัวอย่าง

        BoundedStack<String> stack = new BoundedStack<>(3);

// Test 1
check("New stack should be empty", stack.isEmpty());

// Test 2
check("New stack size should be 0", stack.size() == 0);

// Test 3
check("Capacity should be 3", stack.capacity() == 3);

// Test 4
stack.push("Alice");
check("Size should be 1 after push", stack.size() == 1);

// Test 5
check("Peek should return Alice", stack.peek().equals("Alice"));

// Test 6
stack.push("Bob"); // เอา Bob เข้า stack แล้วก็หลังจาก push ต้องเพิ่มจำนวนไซร์ จาก1เป็น2
check("Size should be 2 after push", stack.size() == 2);

// Test 7 // peek คืนค่าข้อมูลบนสุด บนสุดคือ Bob
check("Peek should return Bob", stack.peek().equals("Bob"));

// Test 8 เมื่อใส่ข้อมูลครบความจุ จะรู้ว่าเต็มจริงหรือไม่
stack.push("Charlie");
check("Stack should be full when reaching capacity", stack.isFull());

// Test 9 ทดสอบดึงข้อมูลบนสุดออก และตรวจว่าขนาดลดลงจริงหรือไม่
String popped = stack.pop();
check("Pop should return Charlie and reduce size to 2",popped.equals("Charlie") && stack.size() ==2);

// Test 10 ทดสอบว่าตำแหน่ง Top ย้อนกลับไปเป็น Bobมั้ย
check("Peek after pop should return Bob",stack.peek().equals("Bob"));

// Test 11 ทดสอบการสร้างคัดลอกและตรวจว่าข้อมูลขนาดของสแตกใหม่ตรงเหมือนเดิมมั้ย
BoundedStack<String> clonedStack = stack.duplicate();
check("Duplicate stack should have same top element as original", clonedStack.peek().equals(stack.peek()) && clonedStack.size() == stack.size());


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