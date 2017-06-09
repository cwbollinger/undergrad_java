
public class TestList {

	public static void main(String[] args)
	{
		List L1 = new AList(6);
		L1.append(12);
		L1.append(32);
		L1.append(15);
		L1.print();
		L1.setFirst();
		L1.next();
		L1.insert(99);
		L1.print();
	}
}
