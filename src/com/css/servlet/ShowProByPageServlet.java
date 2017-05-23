package com.css.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.css.domain.PageBean;
import com.css.domain.Product;
import com.css.service.ProductService;

/**
 * 分页查询
 */
public class ShowProByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取第几页
		int page=Integer.parseInt(request.getParameter("currPage"));
		//设置每页展示的条数
		int pageSize=3;
		//调用Service进行分页查询
		
		PageBean<Product> pageBean = null;
		try {
			pageBean = new ProductService().showProductsByPage(page,pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//将查询结果放入request中，并请求转发
		request.setAttribute("pb", pageBean);
		request.getRequestDispatcher("/product_page.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
