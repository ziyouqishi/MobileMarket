package com.css.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.css.domain.Product;
import com.css.service.ProductService;

/**
 * 修改商品
 */
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		request.setCharacterEncoding("utf-8");
		//封装数据
		Product product=new Product();
		
		try {
			BeanUtils.populate(product, request.getParameterMap());
			
			//调用Service，完成更新
			new ProductService().updateProduct(product);
			
			//完成后跳转页面，重定向
			response.sendRedirect(request.getContextPath()+"/findAll");
			
			
		} catch (Exception e) {
			request.setAttribute("msg", "修改出问题了");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
			
			e.printStackTrace();
			return;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
