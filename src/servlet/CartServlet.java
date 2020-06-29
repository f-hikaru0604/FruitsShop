package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CartBean;
import beans.ItemBean;
import models.DAO;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart-servlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		Object status = session.getAttribute("username");

		if (status == null) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		}

		String count0 = request.getParameter("count");
		String item_no0 = request.getParameter("item_no");
		List<CartBean> cartList = (List<CartBean>) session.getAttribute("cartList");

		try {
			int count = Integer.parseInt(count0);
			int item_no = Integer.parseInt(item_no0);

			DAO dao = new DAO();
			List<ItemBean> itemList = dao.cart(item_no);

			CartBean cartBean = new CartBean();
			cartBean.setItemBean(itemList.get(0));
			cartBean.setCount(count);

			if (cartList == null) {
				cartList = new ArrayList<CartBean>();

			}
			cartList.add(cartBean);

			session.setAttribute("cartList", cartList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("/views/cart.jsp");
		rd.forward(request, response);

	}

}
