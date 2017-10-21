package jobsearching;

public class CustomizedKey implements Comparable<CustomizedKey> {
	private int cardVal;
	private int count;
	public int getCardVal() {
		return cardVal;
	}
	public CustomizedKey(int cardVal, int count) {
		super();
		this.cardVal = cardVal;
		this.count = count;
	}
	public void setCardVal(int cardVal) {
		this.cardVal = cardVal;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public int compareTo(CustomizedKey o) {
		// TODO Auto-generated method stub
		if (this.getCount() != o.getCount() ){
			return -Integer.compare(this.getCount() , o.getCount() );
		} else {
			return -Integer.compare(this.getCardVal(), o.getCardVal());
		}
	}
}
