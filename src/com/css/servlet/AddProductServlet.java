package com.css.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.css.domain.Product;
import com.css.service.ProductService;
import com.css.utitls.UUIDUtils;


public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		request.setCharacterEncoding("utf-8");
		
		
		try {
			//封装数据
			Product product=new Product();
			BeanUtils.populate(product, request.getParameterMap());
			//设置product的pid
			product.setPid(UUIDUtils.getId());
			//设置时间
			product.setPdate(new Date());
			System.out.println(product);
			
			//调用Service 添加数据
			new ProductService().addProduct(product);
			
			//添加成功后页面跳转，跳转到查询所有商品的servlet，查询结束后，会自动跳转到所有商品的页面
			request.getRequestDispatcher("/findAll").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", "添加商品失败");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
			e.printStackTrace();
		} 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
