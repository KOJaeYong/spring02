package com.example.spring02.controller.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring02.model.board.dto.BoardVO;

//p.350
@RestController  // 데이터를 리턴
//@Controller // 뷰(화면)을 리턴
@RequestMapping("/sample/*")
public class SampleController {
	
// http://localhost:8080/spring02/sample/hello	
	@RequestMapping("/hello")
	public String sayHello(){
		return "Hello World";
	}
//뷰가 아닌 객체를 리턴함	
	@RequestMapping("/sendVO")
	//@ResponseBody // 객체를 json으로 변환
	public BoardVO sendVO(){
		BoardVO vo=new BoardVO();
		vo.setBno(1);
		vo.setWriter("kim");
		vo.setContent("내용....");
		return vo;
	}
	
	@RequestMapping("/sendList")
	public List<BoardVO> sendList(){
		//ArrayList 객체 생성
		List<BoardVO> items=new ArrayList<>();
		for(int i=1; i<=10; i++){
			BoardVO vo=new BoardVO(); //vo 객체 생성
			vo.setBno(i);
			vo.setWriter("kim"+i);
			vo.setContent("내용"+i);
			items.add(vo); //리스트에 vo 추가
		}
		return items; //리스트를 리턴함
	}
// ResponseEntity : 데이터 + 에러메시지	
	@RequestMapping("/sendMap") 
	public ResponseEntity<Map<Integer,BoardVO>> sendMap(){
// Map<Key자료형, Value자료형>		
		Map<Integer,BoardVO> map
			=new HashMap<Integer,BoardVO>();
//		Map<Integer,BoardVO> map=null;
		for(int i=1; i<=5; i++){
			BoardVO vo=new BoardVO(); //vo 객체 생성
			vo.setBno(i);
			vo.setWriter("kim"+i);
			vo.setContent("내용"+i);
			map.put(i,vo); //맵에 vo 추가
		}
		//return map;
		return new ResponseEntity<>(
				map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
















