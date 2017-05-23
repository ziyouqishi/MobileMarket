package com.css.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.css.service.ProductService;

/**
 * 删除商品
 */
public class DeletetProductByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 //获取商品id
		String id=request.getParameter("pid");
		//调用Service完成删除操作
		try {
			new ProductService().deleteProduct(id);
		} catch (SQLException e) {
			request.setAttribute("msg", "删除出问题了");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
			
			e.printStackTrace();
			return;
		}
		//删除成功后，重定向
		
	   response.sendRedirect(request.getContextPath()+"/findAll");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
