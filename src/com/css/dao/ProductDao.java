package com.css.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.css.domain.Product;
import com.css.utitls.DataSourceUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;

//查询所有商品
public class ProductDao {

	public List<Product> findAll() throws SQLException {
		QueryRunner qRunner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product";
		return qRunner.query(sql, new BeanListHandler<>(Product.class));
	}

	public void addProduct(Product p) throws SQLException {
		QueryRunner qRunner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into product(pid,pname,market_price,shop_price,pdate,pdesc) values(?,?,?,?,?,?)";
		qRunner.update(sql,p.getPid(),p.getPname(),p.getMarket_price(),p.getShop_price(),
				p.getPdate(),p.getPdesc());
	}

	public Product getProduct(String pid) throws SQLException {
		QueryRunner qRunner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where pid= ?";
		
		return qRunner.query(sql, new BeanHandler<>(Product.class),pid);
	}

	public void updateProductById(Product p) throws SQLException {
		QueryRunner qRunner=new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql="update product set pname=?,market_price=?,shop_price=?,"
				+ "pdesc=? where pid=?";
		qRunner.update(sql,p.getPname(),p.getMarket_price(),p.getShop_price(),
				p.getPdesc(),p.getPid());
		
	}

	public void deleteProductById(String id) throws SQLException {
		QueryRunner qRunner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="delete from product where pid= ?";
		qRunner.update(sql,id);
	}

	public List<Product> findProductByCondition(String name, String kw) throws SQLException {
		QueryRunner qRunner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product where 1=1 ";
		
		ArrayList<String>params=new ArrayList<>();
		//对sql语句进行拼接
		if(name!=null&& name.trim().length()>0){
			sql+=("and pname like ?");
			params.add("%"+name+"%");
		}
		
		if(kw!=null&& kw.trim().length()>0){
			sql+=("and pdesc like ?");
			params.add("%"+kw+"%");
		}
		return qRunner.query(sql, new BeanListHandler<>(Product.class),params.toArray());
	}

	//分页查询中，查询相应的页面
	public List<Product> showCurrentPage(int page, int pageSize) throws SQLException {
		QueryRunner qRunner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from product limit ?,?";
		return qRunner.query(sql, new BeanListHandler<>(Product.class),(page-1)*pageSize,pageSize);
	}

	//分页查询，查询总的数据个数
	public int getCount() throws SQLException {
		QueryRunner qRunner=new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select count(*) from product";
		return ((Long)qRunner.query(sql, new ScalarHandler())).intValue();
		
	}

}
