package jobsearching;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class SecondarySortCards {
	public static void main(String[] args) {
		int[] card = new int[10];
//		Generate 10 cards randomly
		for(int i=0;i<10;i++){
				card[i]=(int)(Math.random()*13);
			while (card[i]==0){
				card[i]=(int)(Math.random()*13);
			}
		}
//		print the sorted result
		System.out.println(Arrays.toString(sortCards(card)));
	}
	public static String[] sortCards(int[] args) {
//		put into HashMap to count the same card repeat times
		HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
		for (int s:args){
			if (hm.containsKey(s)){
				int value = hm.get(s) + 1;
				hm.put(s, value);
			}else{
				hm.put(s, 1);
			}
		}
//		Sort using bubble sort method
		Entry<Integer,Integer>[] sortedEntry = bubbleSort(hm);
//		Transform back into String array
		String[] s = new String[10];
		int c=0;	//the index of the returned String array
		for (Entry<Integer,Integer> entry : sortedEntry){
			for (int i=0;i<entry.getValue();i++){
//				use getCardName() to transform card values like 11,12 to card names like J, Q
				s[c]=getCardName(entry.getKey());
				c++;
			}
		}
		return s;
	}
	public static String getCardName (int cardVal){
//		Transform the card of which value is 1,11,12, or 13 to A, J, Q, or K correspondingly
		switch(cardVal){ 
		case 1:
			return "A";
		case 11:
			return "J";
		case 12:
			return "Q";
		case 13:
			return "K";
		default: 
			return Integer.toString(cardVal);
		}
	}
	public static Entry<Integer,Integer>[] bubbleSort(HashMap<Integer,Integer> hashMap){
//		Get the size of the HashMap. since duplicated cards exisits,
//		the size -- the number of entries is often less then 10 -- the number of cards
		int size = hashMap.entrySet().size();
//		get a new Entry array
		Entry<Integer,Integer>[] entry = new Entry[size];
//		declare the index variable for entry array
		int i=0;
//		give every Entry in the HashMap an index number, to facilitate the sorting procedure
		for (Entry<Integer,Integer> e: hashMap.entrySet()){
			entry[i] = e;
			i++;
		}
//		bubble sort, remember: j start from 0, not 1 !!!!
		for (int j=0;j<size-1;j++){
			for (int k=j+1;k<size;k++)
			if (entry[j].getValue().compareTo(entry[k].getValue())<0){
				Entry<Integer,Integer> temp = entry[k];
				entry[k] = entry[j];
				entry[j] = temp;
//				Secondary Sort: if the values are the same, sort by keys.
			} else if(entry[j].getValue().compareTo(entry[k].getValue())==0){
				if(entry[j].getKey().compareTo(entry[k].getKey())<0){
					Entry<Integer,Integer> temp = entry[j];
					entry[j] = entry[k];
					entry[k] = temp;
				}
			}
		}
		return entry;
	}
}
