package jobsearching;

public class MyHashEntry <T,E> {
	private T key;
	private E value;
	private MyHashEntry<T, E> next;
	public MyHashEntry(T key, E value) {
		this.key = key;
		this.value = value;
	}
	public MyHashEntry<T, E> getNext() {
		return next;
	}
	public void setNext(MyHashEntry<T,E> next) {
		this.next = next;
	}
	public T getKey() {
		return key;
	}
	public void setKey(T key) {
		this.key = key;
	}
	public E getValue() {
		return value;
	}
	public void setValue(E value) {
		this.value = value;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder("[");
		MyHashEntry<T,E> pointer = this;
		sb.append(pointer.getKey()+"="+pointer.getValue());
		while (pointer.getNext() != null){
			pointer = pointer.getNext();
			sb.append(",");
			sb.append(pointer.getKey()+"="+pointer.getValue());
		}
		sb.append("]");
		return sb.toString();
	}
}
