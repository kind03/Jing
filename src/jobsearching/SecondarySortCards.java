package jobsearching;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

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
		System.out.println(Arrays.toString(bubbleSort(card)));
		System.out.println(Arrays.toString(treeSort(card)));
	}
	public static HashMap<Integer,Integer> countCards(int[] cards){
//		put into HashMap to count the same card repeat times
		HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
		for (int s:cards){
			if (hm.containsKey(s)){
				int value = hm.get(s) + 1;
				hm.put(s, value);
			}else{
				hm.put(s, 1);
			}
		}
		return hm;
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
	public static String[] treeSort(int[] args){
//		count cards
		HashMap<Integer,Integer> hm = countCards(args);
//		new a treeset for sorting since treeset is sorted set
		Set<CustomizedKey> set = new TreeSet<CustomizedKey>();
		for (Entry<Integer,Integer> e: hm.entrySet()){
			set.add(new CustomizedKey(e.getKey(),e.getValue()));
		}
		String[] sorted = new String[10];
		int i=0;
//		pull every element out to the String array, it is naturally sorted
		for (CustomizedKey ck : set){
			for (int j=0;j<ck.getCount();j++){
				sorted[i] = getCardName(ck.getCardVal());
				i++;
			}
		}
		return sorted;
		
	}
	public static String[] bubbleSort(int[] args){
//		count cards
		HashMap<Integer,Integer> hashMap = countCards(args);
//		Get the size of the HashMap. since duplicated cards exisits,
//		the size (the number of entries) is often less then 10 -- the number of cards
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
//		Transform back into String array
		String[] s = new String[10];
		int c=0;	//the index of the returned String array
		for (Entry<Integer,Integer> e : entry){
			for (int x=0;x<e.getValue();x++){
//				use getCardName() to transform card values like 11,12 to card names like J, Q
				s[c]=getCardName(e.getKey());
				c++;
			}
		}
		return s;
	}
}
