package test;

import java.util.Arrays;

import com.example.spring02.model.message.dto.MessageVO;

public class Test5 {
	public static void main(String[] args) {
		Object[] obj={"aaa",new MessageVO()};
		System.out.println(obj);
		System.out.println(Arrays.toString(obj));
	}
}




