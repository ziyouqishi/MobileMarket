package com.css.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.css.domain.Product;
import com.css.service.ProductService;
import com.css.utitls.UUIDUtils;


public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ���
		request.setCharacterEncoding("utf-8");
		
		
		try {
			//��װ����
			Product product=new Product();
			BeanUtils.populate(product, request.getParameterMap());
			//����product��pid
			product.setPid(UUIDUtils.getId());
			//����ʱ��
			product.setPdate(new Date());
			System.out.println(product);
			
			//����Service �������
			new ProductService().addProduct(product);
			
			//��ӳɹ���ҳ����ת����ת����ѯ������Ʒ��servlet����ѯ�����󣬻��Զ���ת��������Ʒ��ҳ��
			request.getRequestDispatcher("/findAll").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("msg", "�����Ʒʧ��");
			request.getRequestDispatcher("/msg.jsp").forward(request, response);
			e.printStackTrace();
		} 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
