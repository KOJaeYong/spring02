package com.example.spring02.controller.shop;

import java.io.File;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring02.model.shop.dto.ProductVO;
import com.example.spring02.service.shop.ProductService;

@Controller
@RequestMapping("/shop/product/*")
public class ProductController {
	@Inject
	ProductService productService;
	
	@RequestMapping("edit/{product_id}")
	public ModelAndView edit(
@PathVariable("product_id") int product_id
	, ModelAndView mav) {
		mav.setViewName("/shop/product_edit");
		mav.addObject(
"vo", productService.detailProduct(product_id));
		return mav;
	}
	
	@RequestMapping("write.do")
	public String write(){
		//views/shop/product_write.jsp 로 이동
		return "shop/product_write";
	}
	@RequestMapping("update.do")
	public String update(ProductVO vo){
		String filename="";
		if(!vo.getFile1().isEmpty()){//첨부파일이 있으면
			filename = 
					vo.getFile1().getOriginalFilename();
String path="D:\\work_spring\\.metadata\\.plugins\\"
+ "org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\"
+ "spring02\\WEB-INF\\views\\images\\";			
			try {
				//디렉토리 생성
				new File(path).mkdir();
// 임시디렉토리에 저장된 파일을 지정된 디렉토리로 이동
				vo.getFile1().transferTo(
						new File(path+filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			vo.setPicture_url(filename);
		}else{
			//첨부파일이 변경되지 않을 경우
			ProductVO vo2=
productService.detailProduct(vo.getProduct_id());
			vo.setPicture_url(vo2.getPicture_url());
		}
		productService.updateProduct(vo); 
		return "redirect:/shop/product/list.do";
	}	
	
	@RequestMapping("delete.do")
	public String delete( int product_id ) {
		//첨부파일 이름 조회
		String filename=
				productService.fileInfo(product_id);
		//첨부파일 삭제
		if(filename != null){
			String path=
"D:\\work_spring\\.metadata\\.plugins\\"
+ "org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\"
+ "spring02\\WEB-INF\\views\\images\\";
			File f = new File(path+filename);
			if(f.exists()){ //파일이 존재하면
				f.delete(); //파일 삭제
			}
		}
		//레코드 삭제
		productService.deleteProduct(product_id);
		return "redirect:/shop/product/list.do";
	}
	
	@RequestMapping("insert.do")
	public String insert(ProductVO vo){
		String filename="-";
		if(!vo.getFile1().isEmpty()){//첨부파일이 있으면
			filename = 
					vo.getFile1().getOriginalFilename();
//개발 디렉토리			
//			String path=
//"D:\\work_spring\\spring02\\src\\main\\webapp\\WEB-INF"
//+ "\\views\\images\\"; //파일업로드 경로
//배포 디렉토리
String path="D:\\work_spring\\.metadata\\.plugins\\"
+ "org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\"
+ "spring02\\WEB-INF\\views\\images\\";			
			try {
				//디렉토리 생성
				new File(path).mkdir();
// 임시디렉토리에 저장된 파일을 지정된 디렉토리로 이동
				vo.getFile1().transferTo(
						new File(path+filename));
			} catch (Exception e) {
				e.printStackTrace();
			}
			vo.setPicture_url(filename);
		}
		productService.insertProduct(vo); 
		return "redirect:/shop/product/list.do";
	}
	
	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("/shop/product_list");
		mav.addObject("list",
				productService.listProduct());
		return mav;
	}
//http://localhost:8080/spring02/shop/product/detail/6	
	@RequestMapping("/detail/{product_id}")
	//@RequestMapping("/detail?product_id=${product_id}")
	public ModelAndView detail(
@PathVariable("product_id") int product_id
	, ModelAndView mav) {
		mav.setViewName("/shop/product_detail");
		mav.addObject(
"vo", productService.detailProduct(product_id));
		return mav;
	}
}











