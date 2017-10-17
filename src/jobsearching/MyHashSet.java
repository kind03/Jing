package jobsearching;

import java.util.Arrays;

public class MyHashSet <T>{
	private int size=0;
	private int bucketSize=0;
//	Actually this bucket should be a integer or long array;
//	the index numbers should be the bucket number,
//	and the values should be the memory address of the element in Hashset
	private MyHashEntry<T>[] buckets;
	public MyHashSet(){
		bucketSize = 12;
		buckets = new MyHashEntry[bucketSize];
		
	}
	public MyHashSet(int bucketSize){
		this.bucketSize = bucketSize;
		buckets = new MyHashEntry[bucketSize];
//		buckets = (T[]) new Object[bucketSize];
	}
	public void add(T element){
//		Linked List Length count. 
//		when reaches certain number, will activate resize procedure.
		int i=1;
		MyHashEntry<T> e1 = new MyHashEntry<T>(element);
//		elements cannot be the same in a set
		boolean duplicated = false;
		int hashNo = element.hashCode() % bucketSize;
		if (buckets[hashNo]==null){
			buckets[hashNo]=e1;	
		} else {
//			deal with bucket conflict

//			deal with the first element in the linked list
			MyHashEntry<T> pointer = buckets[hashNo];
			if (pointer.getContent().equals(element)) duplicated=true;
//			use while loop to move to the end of the Linked List
			while (pointer.getNext()!=null){
				pointer = pointer.getNext();
				if (pointer.getContent().equals(element)) duplicated=true;
				i++;
			}
//			append the new element to the end of the linked list
			if (duplicated==false){
				pointer.setNext(new MyHashEntry<T>(element));
				i++;
				}
		}
		if (duplicated==false) size++;
		if(i>7){
//			Attention: this procedure cannot be moved before size++, or it will be done twice
			System.out.println("Bucket size over 7. Bucket will resize");
			reSizeBucket(bucketSize*2);
		}
	}
	public void reSizeBucket(int newSize){
//		clear the old buckets and create a new bucket
		int oldBucketSzie = bucketSize;
		this.bucketSize = newSize;
		size=0;
		MyHashEntry<T>[] oldBuckets = this.buckets;
		buckets = new MyHashEntry[bucketSize];
//		traverse each buckets 
		for(int i=0;i<oldBucketSzie;i++){
			MyHashEntry<T> pointer = oldBuckets[i];
			while(pointer!=null){
				this.add(pointer.getContent());
				pointer = pointer.getNext();
			}
		}
	}
	public T[] toArray(){
		T[] array = (T[]) new Object[size];
		int no=0;
		for(int i=0;i<bucketSize;i++){
			if (buckets[i]!=null){
				array[no]=buckets[i].getContent();
				no=no+1;
//		Traverse the linked list in the same bucket forming when bucket conflict happens
				MyHashEntry<T> pointer = buckets[i];
				while(pointer.getNext()!=null){
					pointer = pointer.getNext();
					array[no]=pointer.getContent();
					no=no+1;
				}
			}
		}
		return array;
	}
	public String toString(){
		return Arrays.toString(this.toArray());
	}
	public int size(){
		return size;
	}
	public static void main(String[] args) {
		MyHashSet<Integer> mhs = new MyHashSet<Integer>();
		mhs.add(0);
		mhs.add(1);
		mhs.add(2);
		mhs.add(12);
		mhs.add(13);
		mhs.add(14);
		mhs.add(24);
		mhs.add(36);
		mhs.add(48);
		mhs.add(60);
		mhs.add(72);
		System.out.println(mhs);
		System.out.println(mhs.size());
		mhs.add(96);
		mhs.add(84);
		mhs.add(1);
		mhs.add(2);
		System.out.println(mhs);
		System.out.println(mhs.size());
	}
}
