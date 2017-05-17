package com.example.spring02.service.shop;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring02.model.shop.dao.CartDAO;
import com.example.spring02.model.shop.dto.CartVO;

@Service
public class CartServiceImpl implements CartService {

	@Inject
	CartDAO cartDao;
	@Override
	public void insert(CartVO vo) {
		cartDao.insert(vo); 
	}

	@Override
	public List<CartVO> listCart(String userid) {
		return cartDao.listCart(userid); 
	}

	@Override
	public void delete(int cart_id) {
		cartDao.delete(cart_id); 
	}

	@Override
	public void update(int cart_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public int sumMoney(String userid) {
		return cartDao.sumMoney(userid); 
	}

	@Override
	public int countCart(String userid, int product_id) {
		return cartDao.countCart(userid, product_id); 
	}

	@Override
	public void updateCart(CartVO vo) {
		cartDao.updateCart(vo); 
	}

	@Override
	public void modifyCart(CartVO vo) {
		cartDao.modifyCart(vo); 
	}

	@Override
	public List<CartVO> cartMoney() {
		return cartDao.cartMoney(); 
	}

}







