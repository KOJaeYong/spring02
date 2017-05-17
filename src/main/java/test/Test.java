package test;

class A {
	int num=10;
	public static A instance;
	public static A getInstance() {
		if( instance == null ) {
			instance = new A();
		}
		return instance;
	}
	private A() {}
	
	public void print(){
		System.out.println(num);
	}	
}
// 의존관계 : Test 클래스는 A 클래스에 의존
// 결합관계 : Test 와 A는 강한 결합
// Test => A


public class Test {
	public static void main(String[] args) {
		//A a = new A();
		A a = A.getInstance();
		a.print();
	}
}


