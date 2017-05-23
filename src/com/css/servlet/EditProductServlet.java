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
 * �޸���Ʒ
 */
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ���
		request.setCharacterEncoding("utf-8");
		//��װ����
		Product product=new Product();
		
		try {
			BeanUtils.populate(product, request.getParameterMap());
			
			//����Service����ɸ���
			new ProductService().updateProduct(product);
			
			//��ɺ���תҳ�棬�ض���
			response.sendRedirect(request.getContextPath()+"/findAll");
			
			
		} catch (Exception e) {
			request.setAttribute("msg", "�޸ĳ�������");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
			
			e.printStackTrace();
			return;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
