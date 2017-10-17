package jobsearching;

public class MyHashEntry <T> {
	private T content;
	private MyHashEntry<T> next;
	public MyHashEntry<T> getNext() {
		return next;
	}
	public MyHashEntry(T content) {
		this.content = content;
	}
	public void setNext(MyHashEntry<T> next) {
		this.next = next;
	}
	public T getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder("[");
		MyHashEntry<T> pointer = this;
		sb.append(pointer.getContent());
		while (pointer.getNext() != null){
			pointer = pointer.getNext();
			sb.append(",");
			sb.append(pointer.getContent());
		}
		sb.append("]");
		return sb.toString();
	}
}
