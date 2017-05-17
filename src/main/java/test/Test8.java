package test;
// F11 - debug
// F5 - step into
// F6 - step over
// F7 - 
public class Test8 {
	
	int test(){
		return 10;
	}
	
	public static void main(String[] args) {
		Test8 t=new Test8();
		int a = t.test();
		System.out.println(a);
	}
}
