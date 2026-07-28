import java.util.*;

public class BoundedStack<T> {

    private final T[] elements; 
    private final int capacity;
    private int size; //ตัวแปรเก็บข้อมูลปัจจุบัน

    private void checkRep() { //เมธอดตรวจสอบ
        assert capacity > 0 : "Capacity must be positive"; //ความจุสูงสุดต้องมากกว่า0
        assert size >= 0 && size <= capacity : "Size out of bounds"; //ข้อมูลปัจจุบันต้องไม่ติดลบและไม่เกินความจุที่เก็บสูงสุด
        assert elements != null : "Elements array cannot be null"; // ตรวจสอบอาเรย์ต้องไม่เป็น null
        assert elements.length == capacity : "Elements length must match capacity"; //ตรวจขนาดอาร์เรย์ต้องเท่ากับความจุที่กำหนดไว้พอดี

        for (int i = 0; i < size; i++) {
            assert elements[i] != null : "Element inside valid range cannot be null";  //ตรวจตำแหน่งข้อมูลที่มีอยู่ต้องไม่เป็น null
        }
        for (int i = size; i < capacity; i++) { //วนลูปตรวจช่องว่างที่เหลือในอาร์เรย์
            assert elements[i] == null : "Element outside valid range must be null"; //ตรวจช่องว่างที่ยังไม่ได้ใช้งานต้องเป็น null ทั้งหมด
        }
    }

    @SuppressWarnings("unchecked")//ปิดการเตือนของตัวคอมไพเลอร์
    public BoundedStack(int capacity) { //รับค่าความจุสูงสุดที่ต้องการ
        if (capacity <= 0) { //ความจุต้อง > 0
            throw new IllegalArgumentException("Capacity must be greater than zero. Given: " + capacity); //โยนกลับเมื่อผู้ใช้ส่งค่าความจุ <= 0 มา
        }
        this.capacity = capacity; //กำหนดค่าความจุสูงสุดให้ตัวแปรภายใน
        this.size = 0;
        this.elements = (T[]) new Object[capacity];
        checkRep();
    }

    public void push(T item) { //รับข้อมูล
        checkRep();
        if (item == null) {
            throw new IllegalArgumentException("Cannot push null item into stack");
        }
        if (isFull()) {
            throw new IllegalStateException("Stack overflow: Stack is full with capacity " + capacity);
        }

        elements[size] = item;
        size++;
        checkRep();
    }

    public T pop() { //ดึงและคืนค่าข้อมูลตัวบนสุดออก
        checkRep();
        if (isEmpty()) {
            throw new IllegalStateException("Stack underflow: Cannot pop from empty stack");
        }

        int topIndex = size - 1;
        T result = elements[topIndex];
        elements[topIndex] = null; // ป้องกันข้อมูลรั่ว
        size--;

        checkRep();
        return result;
    }

    public BoundedStack<T> duplicate() { //สร้างและคืนค่า BoundedStack ที่มีข้อมูลเหมือนกับอันเดิมทุกอัน
        checkRep();
        BoundedStack<T> copy = new BoundedStack<>(this.capacity);
        for (int i = 0; i < this.size; i++) {
            copy.push(this.elements[i]);
        }
        checkRep();
        return copy;
    }

    public T peek() { //อ่านค่าข้อมูลตัวบนสุดแบบไม่ลบออก
        checkRep();
        if (isEmpty()) {
            throw new IllegalStateException("Cannot peek an empty stack");
        }
        return elements[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    
    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }
}
