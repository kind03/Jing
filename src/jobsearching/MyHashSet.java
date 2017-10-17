package jobsearching;

import java.util.Arrays;
import java.util.HashMap;

public class MyHashSet <T,E>{
	private int size=0;
	private int bucketSize=0;
//	Actually this bucket should be a integer or long array;
//	the index numbers should be the bucket number,
//	and the values should be the memory address of the element in Hashset
	private MyHashEntry<T,E>[] buckets;
	public MyHashSet(){
		bucketSize = 12;
		buckets = new MyHashEntry[bucketSize];
		
	}
	public MyHashSet(int bucketSize){
		this.bucketSize = bucketSize;
		buckets = new MyHashEntry[bucketSize];
//		buckets = (T[]) new Object[bucketSize];
	}
	public void put(T key, E value){
//		Linked List Length count. 
//		when reaches certain number, will activate resize procedure.
		int i=1;
		MyHashEntry<T,E> e1 = new MyHashEntry<T,E>(key,value);
//		elements cannot be the same in a set
		boolean duplicated = false;
		int hashNo = key.hashCode() % bucketSize;
		if (buckets[hashNo]==null){
			buckets[hashNo]=e1;	
		} else {
//			deal with bucket conflict

//			deal with the first element in the linked list
			MyHashEntry<T,E> pointer = buckets[hashNo];
			if (pointer.getKey().equals(key)) duplicated=true;
//			use while loop to move to the end of the Linked List
			while (pointer.getNext()!=null){
				pointer = pointer.getNext();
				if (pointer.getKey().equals(key)) duplicated=true;
				i++;
			}
//			append the new element to the end of the linked list
			if (duplicated==false){
				pointer.setNext(new MyHashEntry<T,E>(key,value));
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
		MyHashEntry<T,E>[] oldBuckets = this.buckets;
		buckets = new MyHashEntry[bucketSize];
//		traverse each buckets 
		for(int i=0;i<oldBucketSzie;i++){
			MyHashEntry<T,E> pointer = oldBuckets[i];
			while(pointer!=null){
				this.put(pointer.getKey(),pointer.getValue());
				pointer = pointer.getNext();
			}
		}
	}
	public String[] toArray(){
		String[] array = new String[size];
		int no=0;
		for(int i=0;i<bucketSize;i++){
			if (buckets[i]!=null){
				array[no]=buckets[i].getKey()+"="+buckets[i].getValue();
				no=no+1;
//		Traverse the linked list in the same bucket forming when bucket conflict happens
				MyHashEntry<T,E> pointer = buckets[i];
				while(pointer.getNext()!=null){
					pointer = pointer.getNext();
					array[no]=pointer.getKey()+"="+buckets[i].getValue();
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
		MyHashSet<Integer,Integer> mhs = new MyHashSet<Integer,Integer>();
		mhs.put(0,1);
		mhs.put(1,1);
		mhs.put(2,1);
		mhs.put(3,1);
		mhs.put(14,1);
		mhs.put(13,1);
		mhs.put(12,1);
		mhs.put(24,1);
		mhs.put(36,1);
		mhs.put(48,1);
		mhs.put(60,1);
		mhs.put(72,1);
		System.out.println(mhs);
		System.out.println(mhs.size());
		mhs.put(96,1);
		mhs.put(84,1);
		mhs.put(1,1);
		System.out.println(mhs);
		System.out.println(mhs.size());
		HashMap<Integer,Integer> mh = new HashMap<Integer,Integer>();
		mh.put(1, 2);
		
	}
}
