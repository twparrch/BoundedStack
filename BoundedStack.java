import java.util.*;

public class BoundedStack<T> {

    // น.ส.ทิพย์วรรณ ศรีอ่ำ 6821651248
    // น.ส.ธวัลหทัย ชำนาญ 6821651361

    //AF
    //elements[0..size-1] คือข้อมูลที่อยู่ใน Stack
    //elements[size-1] ข้อมูลบนสุดของ Stack

    //RI
    //capacity > 0
    // 0 <= size <= capacity
    //elements.length  == capacity
    //elements[0..size-1] != null
    //elements[size..capacity-1] == null

    private final T[] elements;
    private final int capacity;
    private int size; //ตัวแปรเก็บข้อมูล

    private void checkRep() { //เมธอดตรวจสอบ
        assert capacity > 0 : "Capacity must be positive";  // ความจุสูงสุดต้องมากกว่า0
        assert size >= 0 && size <= capacity : "size out of bounds"; //ข้อมูลปัจจุบันต้องไม่ติดลบและไม่เป็นไม่เกินความจุที่เก็บสูงสุด
        assert elements != null :  "Elements cannot be null"; //ตรวจอาเรย์ต้องไม่เป็น null
        assert elements.length == capacity :"Elements lengt must match capacity"; //ตรวจขนาดอาเรย์ต้องเท่ากับความจุที่กำหนด

        for(int i =0; i < size; i++){
            assert elements[i] != null : "Element inside valid range cannot be null"; //ตรวจตำแหน่งข้อมูลที่มีอยู่ต้องไม่เป็น null
        }
        for(int i = size; i < capacity; i++){
            assert elements[i] == null : "Element outside valid range must be null"; //ตรวจช่องว่างที่ยังไม่ได้ใช้งานต้องเป็น null ทั้งหมด
        }

    }

    @SuppressWarnings("unchecked")//ปิดการเตือนของตัวคอมไพเลอร์
    public BoundedStack(int capacity){ //รับค่าความจุสูงสุดที่ต้องการ
        if (capacity <= 0){ //ความจุต้อง > 0
            throw new IllegalArgumentException("Capacity must be greater than zero. Given: " + capacity); //โยนกลับเมื่อผู้ใข้ส่งค่าความจุ <= 0 มา
        }

        this.capacity = capacity; //กำหนดค่าความจุสูงสุดให้ตัวแปรภายใน
        this.size = 0;
        this.elements = (T[]) new Object[capacity];
        checkRep();
    }

    // producer
    public void push(T item){ //รับข้อมูล
        checkRep();
        if (item == null){
            throw new IllegalArgumentException("Cannot push null item into stack");
        }
        if (isFull()){
            throw new IllegalStateException("Stack overflow: Stack is full with capacity " + capacity);
        }

        elements[size] = item;
        size++;
        checkRep();
    }

    //mutator
    public T pop(){ //ดึงและคืนค่าข้อมูลตัวบนสุดออก
        checkRep();
        if (isEmpty()){
            throw new IllegalStateException("Stack underflow: Cannot pop from empty stack");
        }

        int topIndex = size - 1;
        T result = elements[topIndex];
        elements[topIndex] = null; //ป้องกันข้อมูลรั่ว
        size--;

        checkRep();
        return result;
    }

    public BoundedStack<T> duplicate() { //สร้างและคืนค่า BoundedStack ที่มีข้อมูลเหมือนกับอันเดิมทุกอัน
        checkRep();
        BoundedStack<T> copy = new BoundedStack<>(this.capacity);
        for (int i = 0; i < this.size; i++){
            copy.push(this.elements[i]);
        }
        checkRep();
        return copy;
    }

    //observer
    public T peek() { //อ่านค่าข้อมูลตัวบนสุดแบบไม่ลบออก
        checkRep();
        if (isEmpty()){
            throw new IllegalStateException("Cannot peek an empty stack");
        }
        return elements[size - 1];
    }

    public boolean isEmpty(){ //ตรวจว่า stack ว่างมั้ย
        return size == 0;
    }

    public boolean isFull(){ //ตรวจว่าเต็มมั้ย
        return size == capacity;
    }   

    public int size(){ //คืนค่าข้อมูลปัจจุบัน
        return size;
    }

    public int capacity(){ //คืนค่าความจุสูงสุด
        return capacity;
    }
}

