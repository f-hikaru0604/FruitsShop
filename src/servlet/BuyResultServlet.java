package servlet;

import java.io.IOException;
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
 * Servlet implementation class BuyResultServlet
 */
@WebServlet("/buyResult-servlet")
public class BuyResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyResultServlet() {
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

		String customer_name = request.getParameter("customer_name");
		String customer_name_kana = request.getParameter("customer_name_kana");
		String customer_tel = request.getParameter("customer_tel");
		String customer_address = request.getParameter("customer_address");
		String pay_method = request.getParameter("pay_method");

		session.setAttribute("customer_name", customer_name);
		session.setAttribute("customer_name_kana", customer_name_kana);
		session.setAttribute("customer_tel", customer_tel);
		session.setAttribute("customer_address", customer_address);
		session.setAttribute("pay_method", pay_method);

		List<CartBean> buyList = (List<CartBean>) session.getAttribute("cartList");
		int[] item_no = new int[buyList.size()];

		int[] count = (int[]) session.getAttribute("count");

		int processingNumber = 0;

		try {
			int i = 0;
			for (CartBean buy : buyList) {
				ItemBean item = (ItemBean) buy.getItemBean();

				int no = item.getItem_no();
				item_no[i] = no;
				i++;

			}
			DAO dao = new DAO();

			processingNumber = dao.result(item_no, count);

		} catch (Exception e) {
			request.setAttribute("msg", "購入に失敗しました");
		}

		RequestDispatcher rd = request.getRequestDispatcher("/views/result.jsp");
		rd.forward(request, response);
	}

}
