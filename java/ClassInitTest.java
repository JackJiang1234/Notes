public class ClassInitTest {
	static int i = 1;
	
	static {
		i = 0;
		System.out.println(i);
	}
	
	
}