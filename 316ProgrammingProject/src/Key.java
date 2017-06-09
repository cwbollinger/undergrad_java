
public class Key {
	// size of the keys, found in first line of file
	private static int key_size = 0;
	private static int[] max_vals = null;
	// holds the key's insert order in the list (1st, 2nd, etc.)
	private int index;

	private int[] vals = null;
	
	public static void initKey(int keySize, int[] maxVals) {
		key_size = keySize;
		max_vals = maxVals;
	}
	
	public static boolean checkVals(int[] check) {
		for (int i = 0; i < key_size; i++) {
			if(check[i] > max_vals[i]) {
				return false;
			}
		}
		return true;
	}
	
	public Key(int[] values) {
		setVals(values.clone());
		index = 0;
	}
	
	public Key(int[] values, int index) {
		setVals(values.clone());
		this.index = index;
	}

	public int[] getVals() {
		return vals;
	}

	public void setVals(int[] vals) {
		this.vals = vals;
	}
	
	public static void printStats() {
		System.out.print("Key Size: "+key_size+", Max Vals: ");
		for (int i = 0; i < max_vals.length; i++) {
			System.out.print(max_vals[i]+" ");
		}
		System.out.println("");
	}
	
	@Override
	public String toString() {
		String s = "(";
		for (int i = 0; i < key_size-1; i++) {
			s = s + vals[i]+", ";
		}
		s = s + vals[key_size-1]+")";
		return s+" :"+index;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Key)) {
			return false;
		}
		Key comp = (Key) o;
		int[] compVals = comp.getVals();
		for (int i = 0; i < key_size; i++) {
			if(vals[i] != compVals[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static int getByteSize() {
		int bytes = 0;
		bytes += 2*4; // size of references to key_size and max_vals
		bytes += 4*key_size; // size of vals array
		bytes += 4; // size of index integer
		return bytes;
	}

	public static int getKeySize() {
		return key_size;
	}

	public static int[] getMaxVals() {
		return max_vals;
	}
	
	public int getIndex() {
		return index;
	}
}