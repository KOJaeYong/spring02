package test;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
class SessionExam{
	@Around(
"execution(* com.example.spring02.test..**.P*.*(..))")
	public void a(){
		
	}
}
class P1{ //회계
	
}
class P2{ //고객

}
class P3{ //판매
	
}

public class Exam2 {

}
