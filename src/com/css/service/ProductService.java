package com.css.service;

import java.sql.SQLException;
import java.util.List;

import com.css.dao.ProductDao;
import com.css.domain.PageBean;
import com.css.domain.Product;

public class ProductService {
    // 查询所有商品
	public List<Product> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return new ProductDao().findAll();
	}

	public void addProduct(Product product) throws SQLException {
		new ProductDao().addProduct(product);
	}

	public Product getProductById(String pid) throws SQLException {
		// TODO Auto-generated method stub
		return new ProductDao().getProduct(pid);
	}

	public void updateProduct(Product product) throws SQLException {
		new ProductDao().updateProductById(product);
	}

	public void deleteProduct(String id) throws SQLException {
		new ProductDao().deleteProductById(id);
	}

	//多条件查询
	public List<Product> findByCondition(String name, String kw) throws SQLException {
		// TODO Auto-generated method stub
		return new ProductDao().findProductByCondition(name,kw);
	}

	//分页查询
	public PageBean<Product> showProductsByPage(int page, int pageSize) throws SQLException {
		//查询当前页面显示的数据
		ProductDao pdDao=new ProductDao();
		List<Product> list=pdDao.showCurrentPage(page,pageSize); 
		//查询总条数
		int totalCount =pdDao.getCount();
		return new PageBean<>(list, page, pageSize, totalCount);
	}




}
