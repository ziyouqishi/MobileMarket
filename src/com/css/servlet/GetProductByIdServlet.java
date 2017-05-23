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
		//��ȡ��ƷID��
		String pid=request.getParameter("pid");
		//ͨ��Service���в�ѯ
		Product product = null;
		try {
			product = new ProductService().getProductById(pid);
		} catch (SQLException e) {
			request.setAttribute("msg", "��ȡ��Ʒʧ��");
			//����ת��
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
			e.printStackTrace();
		}
		//����ѯ�������request����
		request.setAttribute("bean", product);
		//����ת��
		request.getRequestDispatcher("/edit.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
