package com.example.spring02.controller.memo;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.memo.dto.MemoVO;
import com.example.spring02.service.memo.MemoService;

@Controller
@RequestMapping("/memo/*")
public class MemoController {

	@Inject 
	MemoService memoService;
	
	@RequestMapping("delete/{idx}")
	public String delete(@PathVariable int idx){
		memoService.delete(idx); //레코드 삭제
		return "redirect:/memo/list.do"; //리스트로 이동
	}	
	
	@RequestMapping("update/{idx}")
	public String update(
			@PathVariable int idx, MemoVO vo){
		memoService.update(vo); //레코드 수정
		return "redirect:/memo/list.do"; //리스트로 이동
	}
	
//http://localhost:8080/spring02/memo/view/7	
	@RequestMapping("view/{idx}")
	public ModelAndView view(
			@PathVariable int idx, ModelAndView mav	){
		// views/memo/view.jsp
		mav.setViewName("memo/view");
		mav.addObject("vo", memoService.memo_view(idx)); 
		return mav;
	}
	
	@RequestMapping("insert.do")
	public String insert(MemoVO vo){
		//memoService.insert(vo); //글쓰기
		memoService.insert(vo.getWriter(), vo.getMemo()); 
		return "redirect:/memo/list.do"; //리스트로 이동
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav){
		//메모 목록을 리턴받음
		List<MemoVO> items=memoService.list();
		//뷰의 이름 설정 : views/memo/memo_list.jsp
		mav.setViewName("memo/memo_list");
		//뷰에 출력할 데이터 저장
		mav.addObject("list", items);
		// 화면 전환
		return mav;
	}
}









