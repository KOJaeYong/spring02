package com.example.spring02.service.shop;

import java.util.List;

import com.example.spring02.model.shop.dto.CartVO;

public interface CartService {
	public List<CartVO> cartMoney();
	public void insert(CartVO vo);
	public List<CartVO> listCart(String userid);
	public void delete(int cart_id);
	public void update(int cart_id);
	public int sumMoney(String userid);
	//장바구니에 이미 상품이 담겼는지 확인
	public int countCart(String userid, int product_id);
	//장바구니 수량 변경
	public void updateCart(CartVO vo);
	public void modifyCart(CartVO vo);
}





