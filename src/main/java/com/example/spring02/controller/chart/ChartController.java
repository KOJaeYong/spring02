package com.example.spring02.controller.chart;

import java.util.List;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dto.CartVO;
import com.example.spring02.service.shop.CartService;
/* v : value, c: column, f : format */
@RestController
@RequestMapping("/chart/*")
public class ChartController {
	
	@Inject
	CartService cartService;
	
	@RequestMapping("chart1.do")
	public ModelAndView chart1(){
// views/chart_exam/chart01.jsp로 이동		
		return new ModelAndView("chart_exam/chart01");
	}
	@RequestMapping("chart2.do")
	public ModelAndView chart2(){
// views/chart_exam/chart02.jsp로 이동		
		return new ModelAndView("chart_exam/chart02");
	}
// { "변수명": [{},{},{} ], "변수명":"값" }		
	@RequestMapping("cart_money_list.do") 
	public JSONObject cart_money_list(){
		List<CartVO> items=cartService.cartMoney();
		//리턴할 json 객체
		JSONObject data=new JSONObject(); // {    }
		//json의 컬럼 객체
		JSONObject col1=new JSONObject();
		JSONObject col2=new JSONObject();
		//json 배열 객체
		JSONArray title=new JSONArray();
		col1.put("label", "상품명");
		col1.put("type", "string");
		col2.put("label", "금액");
		col2.put("type", "number");
		//타이틀행에 컬럼 추가
		title.add(col1);
		title.add(col2);
		//json객체에 타이틀행 추가
		data.put("cols", title); 
//{ "cols": [{"label":"상품명","type":"string"}
//		,{"label":"금액","type":"number"}]} 
		
		JSONArray body=new JSONArray(); //rows
		for(CartVO vo : items){
			JSONObject name=new JSONObject();
			name.put("v", vo.getProduct_name()); //상품명
			JSONObject money=new JSONObject();
			money.put("v", vo.getMoney()); //금액
			JSONArray row=new JSONArray();
			row.add(name);
			row.add(money);
			JSONObject cell=new JSONObject();
			cell.put("c", row); 
			body.add(cell); //레코드 1개 추가
		}
		data.put("rows", body);
		return data;
	}
}













