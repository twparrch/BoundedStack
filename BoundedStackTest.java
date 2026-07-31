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

// Test 1 ตรวจสอบ Stack ที่เพิ่งสร้างมาใหม่ ต้องเป็น Stack ว่าง เพราะยังไม่ได้ใส่ข้อมูลอะไร
check("New stack should be empty", stack.isEmpty());

// Test 2 ตรวจสอบข้อมูลใน Stack เพิ่งสร้างใหม่ข้อมูลต้องเป็น 0
check("New stack size should be 0", stack.size() == 0);

// Test 3 ตรวจสอบว่า Stack จำค่าความจุไว้ถูกต้อง ก็คือ 3
check("Capacity should be 3", stack.capacity() == 3);

// Test 4 ใส่ Alice เข้าไปใน Stack จำนวนข้อมูลจะเพิ่มจาก 0 เป็น 1
stack.push("Alice");
check("Size should be 1 after push", stack.size() == 1);

// Test 5 Peek ใช้ดูข้อมูลบนสุดของ Stack ไม่ลบข้อมูลออก หลังเรียกข้อมูลยังอยู่เหมือนเดิม 
check("Peek should return Alice", stack.peek().equals("Alice"));

// Test 6 เอา Bob เข้า stack แล้วก็หลังจาก push ต้องเพิ่มจำนวนไซร์ จาก1เป็น2
stack.push("Bob"); 
check("Size should be 2 after push", stack.size() == 2);

// Test 7  peek คืนค่าข้อมูลบนสุด บนสุดคือ Bob
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

// Test 12 เก็บค่า capacity
check("Capacity should be 3", stack.capacity() == 3);

// Test 13 สร้าง Stack ใหม่แต่ข้อมูลเหมือนเดิม
BoundedStack<String> copy = stack.duplicate();
check("Duplicate has same size", copy.size() == stack.size());

// Test 14 ตรวจ top ของ copy กับ top ของ stack
check("Duplicate peek is same", copy.peek().equals(stack.peek()));

// Test 15 ที่ต้อง pop() 2 ครั้งเพราะจะลบ bob กับ alice แล้วค่อยตรวจสอบ isEmpty() ว่าเป็นจริง
stack.pop();
stack.pop();
check("Stack should be empty after removing all elements", stack.isEmpty());

// Test 16 Stack ว่างละตอนนี้ ถ้าเรียก pop() อีกต้องเป็น IllegalStateException 
boolean popException = false;
try {
    stack.pop();
} catch (IllegalStateException e) {
    popException = true;
}
check("Pop on empty stack should throw exception", popException);

// Test 17 ทดสอบว่า peek() บน Stack ว่าง ต้องเกิด IllegalStateException
boolean peekException = false;
try {
    stack.peek();
} catch (IllegalStateException e) {
    peekException = true;
}
check("Peek on empty stack should throw exception", peekException);

// Test 18 เพราะใน push() มี item == null พอเป็น  null ต้องเป็น IllegalArgumentException
boolean nullException = false;
try {
    stack.push(null);
} catch (IllegalArgumentException e) {
    nullException = true;
}
check("Push null should throw exception", nullException);

// Test 19 ก็คือความจุคือ 3 เลยแทน A,B,C ให้เต็ม และพอใส่่ D อีกก็จะเกิด IllegalStateException
stack.push("A");
stack.push("B");
stack.push("C");
boolean fullException = false;
try {
    stack.push("D");
} catch (IllegalStateException e) {
    fullException = true;
}
check("Push on full stack should throw exception", fullException);

// Test 20 Push ไม่สำเร็จ ขนาดของ Stack ต้องเป็น 3 เท่าเดิมไม่เปลี่ยน
check("Size should remain 3 after failed push", stack.size() == 3);

// Test 21 เมื่อ pop ออกแล้วต้อง pust ข้อมูลใหม่เข้าไปได้ปกติ
stack.pop();
stack.push("NewData");
check("Should be able to pust new item after pop", stack.size() == 3 && stack.peek().equals("NewData"));

// Test 22 ทดสอบลำดับ lifoS เข้าทีหลังต้องออกก่อน
BoundedStack<Integer> lifoS = new BoundedStack<>(2);
lifoS.push(10);
lifoS.push(20);
check("Pop should return last pushed element first (LIFO)", lifoS.pop() == 20 && lifoS.pop() == 10);

// Test 23 ต้องคืนค่าความจุสูงสุดความความจริงเสมอ ไม่ว่าข้อมูลจะมีกี่ตัว
BoundedStack<Integer> capStack = new BoundedStack<>(10);
capStack.push(1);
check("Capacity should remain constant regardless of size",capStack.capacity() == 10 && capStack.size() == 1);

// Test 24 แก้ไขข้อมูลใน stack ที่คัดลอกมา ต้องไม่กระทบกับ stack ต้นฉบับ
BoundedStack<String> copyS = stack.duplicate();
copyS.pop();
check("Modifying duplicate stack should not affect original stack", stack.size() == 3 && copyS.size() == 2);

// Test 25 stack ที่ใส่ข้อมูลจนเต็มแล้ว เมื่อเช็ค isEmpty ต้องได้ false
check("Full stack shoul not empty", !stack.isEmpty());

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