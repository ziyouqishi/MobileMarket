package com.css.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.css.service.ProductService;

/**
 * ɾ����Ʒ
 */
public class DeletetProductByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 //��ȡ��Ʒid
		String id=request.getParameter("pid");
		//����Service���ɾ������
		try {
			new ProductService().deleteProduct(id);
		} catch (SQLException e) {
			request.setAttribute("msg", "ɾ����������");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
			
			e.printStackTrace();
			return;
		}
		//ɾ���ɹ����ض���
		
	   response.sendRedirect(request.getContextPath()+"/findAll");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
