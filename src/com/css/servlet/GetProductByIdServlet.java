package com.css.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.css.domain.Product;
import com.css.service.ProductService;


public class GetProductByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取商品ID；
		String pid=request.getParameter("pid");
		//通过Service进行查询
		Product product = null;
		try {
			product = new ProductService().getProductById(pid);
		} catch (SQLException e) {
			request.setAttribute("msg", "获取商品失败");
			//请求转发
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
			e.printStackTrace();
		}
		//将查询结果放入request域中
		request.setAttribute("bean", product);
		//请求转发
		request.getRequestDispatcher("/edit.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
