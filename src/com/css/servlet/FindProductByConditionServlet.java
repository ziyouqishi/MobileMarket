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
 * ��������ѯ��servlet
 */
public class FindProductByConditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ���ñ��뷽ʽ
		request.setCharacterEncoding("utf-8");
		
		//������������
		String name=request.getParameter("name");
		String kw=request.getParameter("kw");
		
		//��Service��ʵ�ֲ�ѯ
		
		List<Product> list = null;
		try {
			list = new ProductService().findByCondition(name,kw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "��ѯ����");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
			return;
		}
		
		//����ѯ�������request����
		request.setAttribute("list", list);
		//����ת�����������ض��򣬷���request��ʧȥ���ã�
		
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
	}

}
