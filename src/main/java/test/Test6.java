package test;

public class Test6 {
	public static void main(String[] args) 
	throws Exception {
		String str="가나다라마바사";
		//스트링을 바이트배열로 ( 스트링.getBytes() )
		System.out.println( new String(
str.getBytes("ms949"),"ms949") ); //서유럽언어
		System.out.println( new String(
str.getBytes("ms949"),"utf-8") ); //한국어
		//new String(str.getBytes("utf-8"),"iso-8859-1");
	}
}
