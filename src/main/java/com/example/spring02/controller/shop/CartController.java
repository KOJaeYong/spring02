package com.example.spring02.controller.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dto.CartVO;
import com.example.spring02.service.shop.CartService;

@Controller
@RequestMapping("/shop/cart/*")
public class CartController {
	@Inject 
	CartService cartService;
	
	@RequestMapping("list.do")
	public ModelAndView list(HttpSession session
			, ModelAndView mav){
		Map<String,Object> map=new HashMap<>();
		//세션에 저장된 userid 
		String userid=
				(String)session.getAttribute("userid");
		List<CartVO> list=cartService.listCart(userid);
		//장바구니 합계 금액
		int sumMoney=cartService.sumMoney(userid);
		//배송료(3만원 이상=>무료, 미만=>2500원)
		int fee=sumMoney>=30000 ? 0 : 2500;
		//맵에 저장
		map.put("list", list);
		map.put("count", list.size());
		map.put("sumMoney", sumMoney);
		map.put("fee", fee);
		//총합계(sumMoney+fee)
		map.put("sum", sumMoney + fee);
		//view(jsp 페이지)의 이름
		mav.setViewName("shop/cart_list");
		mav.addObject("map", map);
		return mav;
	}
	@RequestMapping("insert.do")
	public String insert(
			@ModelAttribute CartVO vo
			, HttpSession session) {
		String userid=
				(String)session.getAttribute("userid");
		vo.setUserid(userid);
		//장바구니에 기존 상품이 있는지 검사
		int count=cartService.countCart(
				userid, vo.getProduct_id());
		if(count==0){
			//없으면 insert
			cartService.insert(vo);
		}else{
			//있으면 update
			cartService.updateCart(vo);
		}
		return "redirect:/shop/cart/list.do";
	}
	
	@RequestMapping("delete.do")
	public String delete(@RequestParam int cart_id) {
		cartService.delete(cart_id);
		return "redirect:/shop/cart/list.do";
	}
	@RequestMapping("update.do")
	public String update(@RequestParam int[] amount
			, @RequestParam int[] product_id
			, HttpSession session){
		String userid
			=(String)session.getAttribute("userid");
		for(int i=0; i<product_id.length; i++) {
			CartVO vo=new CartVO();
			vo.setUserid(userid);
			vo.setProduct_id(product_id[i]);
			vo.setAmount(amount[i]);
			cartService.modifyCart(vo); 
		}
		return "redirect:/shop/cart/list.do";
	}
}








