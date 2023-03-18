package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entity.Cart;

//@WebServlet(name = "AddToCartServlet", urlPatterns = "/Add")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			ArrayList<Cart> cartList = new ArrayList<Cart>();
			int id = Integer.parseInt(request.getParameter("id"));

			Cart cart = new Cart();
			cart.setId(id);
			cart.setQuantity(1);

			HttpSession session = request.getSession();
			ArrayList<Cart> cart_List = (ArrayList<Cart>) session.getAttribute("cart-list");

			if (cart_List == null) {
				cartList.add(cart);
				session.setAttribute("cart-list", cartList);

				out.print("create");
			} else {
				cartList = cart_List;
				boolean exist = false;

				for (Cart c : cartList) {
					if (c.getId() == id) {
						exist = true;
						out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='cart.jsp'>Go to Cart Page</a></h3>");
					}
				}
				if (!exist) {
					cartList.add(cart);
					response.sendRedirect("index.jsp");
				}
			}
			for (Cart c : cartList) {
				out.print(c.getId());
				response.sendRedirect("index.jsp");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
