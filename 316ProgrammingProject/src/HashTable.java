
public class HashTable {

	private Key[] table;
	private int tableSize;
	
	private int numInserts = 0;
	private int num_collisions = 0;
	
	public HashTable(int tableSize) {
		table = new Key[tableSize];
		this.tableSize = tableSize;
	}
	
	public boolean addKey(Key k) {
		if (numInserts == tableSize) {
			return false;
		}
		int location = hash(k);
		int jump = hash2(k);
		while (table[location] != null) {
			location += jump;
			num_collisions++;
			location %= tableSize;
		}
		table[location] = k;
		numInserts++;
		return true;
	}
	
	public int getIndex(Key k) {
		if (k == null) {
			return -1;
		}
		int location = hash(k);
		int jump = hash2(k);
		while (!k.equals(table[location])) {
			if (table[location] == null) { // we have reached the end of the chain but there is no matching key
				return -1;
			}
			location += jump;
			location %= tableSize;
		}
		return table[location].getIndex();
	}
	
	public int hash(Key k) {
		int hashSum = 0;
		int[] vals = k.getVals();
		for (int i = 0; i < Key.getKeySize(); i++) {
			hashSum += vals[i]*Math.pow(Key.getMaxVals()[i], i);
		}
		return hashSum % tableSize;
	}
	
	public int hash2(Key k) {
		int hashSum = 0;
		String hashString = "";
		int[] vals = k.getVals();
		for (int i = 0; i < Key.getKeySize(); i++) {
			hashString += Integer.toString(vals[i]);
			hashSum += vals[i];
		}
		return (hashSum % tableSize)+1;
	}
	
	public int size() {
		return tableSize;
	}
}
