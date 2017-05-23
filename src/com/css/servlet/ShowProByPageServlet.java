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
 * ��ҳ��ѯ
 */
public class ShowProByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�ڼ�ҳ
		int page=Integer.parseInt(request.getParameter("currPage"));
		//����ÿҳչʾ������
		int pageSize=3;
		//����Service���з�ҳ��ѯ
		
		PageBean<Product> pageBean = null;
		try {
			pageBean = new ProductService().showProductsByPage(page,pageSize);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//����ѯ�������request�У�������ת��
		request.setAttribute("pb", pageBean);
		request.getRequestDispatcher("/product_page.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
