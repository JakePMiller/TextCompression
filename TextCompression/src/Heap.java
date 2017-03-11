import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 
 * The keys in the heap must be stored in an array.
 * 
 * There may be duplicate keys in the heap.
 * 
 * The constructor takes an argument that specifies how objects in the 
 * heap are to be compared. This argument is a java.util.Comparator, 
 * which has a compare() method that has the same signature and behavior 
 * as the compareTo() method found in the Comparable interface. 
 * 
 * Here are some examples of a Comparator<String>:
 *    (s, t) -> s.compareTo(t);
 *    (s, t) -> t.length() - s.length();
 *    (s, t) -> t.toLowerCase().compareTo(s.toLowerCase());
 *    (s, t) -> s.length() <= 3 ? -1 : 1;  
 */

public class Heap<E> implements PriorityQueue<E> {
	protected List<E> keys;
	private Comparator<E> comparator;

	/**
	 * 
	 * Creates a heap whose elements are prioritized by the comparator.
	 */
	public Heap(Comparator<E> comparator) {
		this.comparator = comparator;
		keys = new ArrayList();
	}

	/**
	 * Returns the comparator on which the keys in this heap are prioritized.
	 */
	public Comparator<E> comparator() {
		return comparator;
	}

	/**
	 * 
	 * Returns the top of this heap. This will be the highest priority key. 
	 * @throws NoSuchElementException if the heap is empty.
	 */
	public E peek() {
		if(!keys.isEmpty())
			return keys.get(0);
		else
			throw new NoSuchElementException();
	}

	/**
	 * 
	 * Inserts the given key into this heap. Uses siftUp().
	 */
	public void insert(E key) {
		keys.add(key);
		int n = keys.size()-1;
		siftUp(n);
	}

	/**
	 * 
	 * Removes and returns the highest priority key in this heap.
	 * @throws NoSuchElementException if the heap is empty.
	 */
	public E delete() {
		if(keys.size() != 0){
		int n = keys.size() -1;
		E tobedel = keys.get(0);
		swap(0, n);
		keys.remove(n);
		siftDown(0);
		return tobedel;
		}
		else throw new NoSuchElementException();
	}

	/**
	 * 
	 * Restores the heap property by sifting the key at position p down
	 * into the heap.
	 */
	public void siftDown(int p) {
		int size = keys.size() -1;
		int leftChild = getLeft(p);
		if (leftChild <= size) {
			int maxChild = leftChild;
			int rightChild = leftChild + 1;
			if (rightChild <= size && comparator.compare(keys.get(rightChild), keys.get(maxChild)) < 0)
				maxChild = rightChild;
			if (comparator.compare(keys.get(maxChild) , keys.get(p)) < 0) {
				swap(p, maxChild);
				siftDown(maxChild);
			}
		}
	}

	/**
	 * 
	 * Restores the heap property by sifting the key at position q up
	 * into the heap. (Used by insert()).
	 */
	public void siftUp(int q) {
		int parent = getParent(q);
		while(q>0 && comparator.compare(keys.get(q), keys.get(parent)) <= 0){
				swap(q, parent);
				q = parent;
				parent = getParent(parent);
			}
		}

	/**
	 * 
	 * Exchanges the elements in the heap at the given indices in keys.
	 */
	public void swap(int i, int j) {
		E temp = keys.get(i);
		keys.set(i, keys.get(j));
		keys.set(j, temp);
	}

	/**
	 * Returns the number of keys in this heap.
	 */
	public int size() {
		return keys.size();
	}

	/**
	 * Returns a textual representation of this heap.
	 */
	public String toString() {
		return keys.toString();
	}

	/**
	 * 
	 * Returns the index of the left child of p.
	 */
	public static int getLeft(int p) {
		return 2 * p + 1;
	}

	/**
	 * 
	 * Returns the index of the right child of p.
	 */
	public static int getRight(int p) {
		return getLeft(p) + 1;
	}

	/**
	 * 
	 * Returns the index of the parent of p.
	 */
	public static int getParent(int p) {
		return (p - 1) / 2;
	}
}
