package Ex1;

public class MinHeap {
    private Node[] Heap;
    private int size;
    private final int capacity;

    MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.Heap = new Node[capacity + 1];
    }

    public Node[] getHeap() {
        return Heap;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public int left_child(int index) {
        return (2 * index) + 2;
    }

    public int right_child(int index) {
        return (2 * index) + 1;
    }

    public int parent(int index) {
        return (index - 1) / 2;
    }

    public void swap(int a, int b) {
        Node tmp = this.Heap[a];
        this.Heap[a] = this.Heap[b];
        this.Heap[b] = tmp;
    }

    public void Heapify_down(int position) {
        int smallest = position;
        if (this.left_child(position) < this.size && this.Heap[this.left_child(position)].getData() < this.Heap[smallest].getData()) {
            smallest = this.left_child(position);
        }
        if (this.right_child(position) < this.size && this.Heap[this.right_child(position)].getData() < this.Heap[smallest].getData()) {
            smallest = this.right_child(position);
        }
        if (smallest != position) {
            swap(smallest, position);
            Heapify_down(smallest);
        }

    }

    public void Heapify_up(int position) {
        if (position <= 0)
            return;
        if (this.Heap[this.parent(position)].getData() > this.Heap[position].getData()) {
            this.swap(position, this.parent(position));
        }
        this.Heapify_up(this.parent(position));
    }

    public void BuildHeap() {
        int start = this.size / 2 - 1;
        for (int i = start; i >= 0; i--) {
            this.Heapify_down(i);
        }
    }

    public void add(Node node) {
        if (this.size > this.capacity) {
            System.out.println("Max capacity ");
            return;
        }
        this.size += 1;
        this.Heap[size - 1] = node;
    }

    public Node Extract_Min() {
        if (this.isEmpty()) {
            System.out.println("Queue is empty ");
            return null;
        }
        this.swap(0, this.size - 1);
        this.size -= 1;
        this.Heapify_up(this.size-1);
        return this.Heap[size];
    }

    public Boolean isEmpty() {
        return this.size <= 0;
    }

    public Boolean isExist(Node node) {
        for (int i = 0; i < this.size; i++) {
            if (this.Heap[i].equals(node))
                return true;
        }
        return false;
    }

    public int Whereis(Node node) {
        for (int i = 0; i < this.size; i++) {
            if (this.Heap[i].equals(node)) {
                return i;
            }
        }
        return -1;
    }
    //For debug
    public void print_heap() {
        while (!this.isEmpty()) {
            System.out.println(this.Extract_Min().getData());
        }
    }

}
