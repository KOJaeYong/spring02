package test;

import javax.inject.Inject;

//의존관계 : AAA <- BBB <- Exam <- 개발자
//강한 결합관계
class AAA {
	
}
class BBB extends Test22 {
	
}
// 스프링 <- AAA <- BBB <- Exam <- 개발자
// IoC(Inversion of Control, 제어의 역전) 
public class Exam {
	@Inject   // DI(의존관계 주입)
	BBB b;
	public static void main(String[] args) {
		//BBB b=new BBB();
	}
}
