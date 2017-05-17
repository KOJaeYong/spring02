package test;

public class WrapperTest {
	public static void main(String[] args) {
		int a=10; //기본자료형
// auto boxing		
		//Integer i=10; //객체 내부의 value 변수에 저장
		Integer i=new Integer(10);
		System.out.println(a);
		//객체의 주소가 아닌 value 변수값(auto unboxing)		
		System.out.println(i);
		System.out.println(i.intValue()); 
		WrapperTest w=new WrapperTest();
		System.out.println(w);
		//Wrapper class
		// int Integer
		// double Double
		// float Float
		// char  Character
		// boolean Boolean
	}
}











