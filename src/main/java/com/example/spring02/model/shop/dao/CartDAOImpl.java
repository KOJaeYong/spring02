package com.example.spring02.model.shop.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.shop.dto.CartVO;

@Repository
public class CartDAOImpl implements CartDAO {

	@Inject
	SqlSession sqlSession;
	
	@Override
	public void insert(CartVO vo) {
		sqlSession.insert("cart.insert", vo); 
	}

	@Override
	public List<CartVO> listCart(String userid) {
		return 
sqlSession.selectList("cart.listCart", userid); 
	}

	@Override
	public void delete(int cart_id) {
		sqlSession.delete("cart.delete", cart_id);
	}

	@Override
	public void update(int cart_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public int sumMoney(String userid) {
		return 
sqlSession.selectOne("cart.sumMoney",userid);
	}

	@Override
	public int countCart(String userid, int product_id) {
		Map<String,Object> map=
				new HashMap<>();
		map.put("userid", userid);
		map.put("product_id", product_id);
		return sqlSession.selectOne("cart.countCart",map);
	}

	@Override
	public void updateCart(CartVO vo) {
		sqlSession.update("cart.update", vo);
	}

	@Override
	public void modifyCart(CartVO vo) {
		sqlSession.update("cart.modify", vo); 
	}

	@Override
	public List<CartVO> cartMoney() {
		return sqlSession.selectList("cart.cart_money"); 
	}

}
