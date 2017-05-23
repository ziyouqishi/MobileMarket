package com.css.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.css.domain.Product;
import com.css.service.ProductService;

/**
 * 多条件查询的servlet
 */
public class FindProductByConditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置编码方式
		request.setCharacterEncoding("utf-8");
		
		//接收两个参数
		String name=request.getParameter("name");
		String kw=request.getParameter("kw");
		
		//在Service中实现查询
		
		List<Product> list = null;
		try {
			list = new ProductService().findByCondition(name,kw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "查询错误");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
			return;
		}
		
		//将查询结果放入request域中
		request.setAttribute("list", list);
		//请求转发（不能用重定向，否则request域失去作用）
		
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
	}

}
