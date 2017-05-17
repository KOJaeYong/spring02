package test;

import javax.inject.Inject;

class B {
	int num=10;
	public static B instance;
	public static B getInstance() {
		if( instance == null ) {
			instance = new B();
		}
		return instance;
	}
	private B() {}
	
	public void print(){
		System.out.println(num);
	}		
}
// Test2 클래스는 B 클래스에 의존한다.
// 느슨한 결합 관계
// DI(Dependency Injection, 의존관계 주입)
// 스프링 프레임워크 => B => Test2
public class Test2 {
	@Inject
	B b;
	public static void print(B b){
		b.print();
	}
}




