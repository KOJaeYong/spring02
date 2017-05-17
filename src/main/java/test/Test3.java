package test;

interface C {
	public void print();
}

class BB implements C {
	public void print(){
		System.out.println("bb");
	}
}

public class Test3 {
	public static void main(String[] args) {
		//BB b=new BB();
		C b=new BB();
		b.print();
	}
}
