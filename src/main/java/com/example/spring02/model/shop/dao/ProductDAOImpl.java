package com.example.spring02.model.shop.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.spring02.model.shop.dto.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Inject
	SqlSession sqlSession;
	@Override
	public List<ProductVO> listProduct() {
		return sqlSession.selectList(
				"product.list_product");
	}

	@Override
	public ProductVO detailProduct(int product_id) {
		return sqlSession.selectOne(
				"product.detail_product", product_id);
	}

	@Override
	public void updateProduct(ProductVO vo) {
		sqlSession.update("product.update_product", vo);
	}

	@Override
	public void deleteProduct(int product_id) {
		sqlSession.delete("product.delete",product_id);
	}
	@Override
	public void insertProduct(ProductVO vo) {
		sqlSession.insert("product.insert", vo); 
	}

	@Override
	public String fileInfo(int product_id) {
		return 
sqlSession.selectOne("product.file_info", product_id);
	}

}
