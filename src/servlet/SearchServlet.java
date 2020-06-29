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

import beans.ItemBean;
import models.DAO;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search-servlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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

		String category = request.getParameter("category");
		String conditions = request.getParameter("conditions");

		List<ItemBean> itemList = null;

		DAO dao = new DAO();

		try {
			itemList = dao.select(category, conditions);

			if (itemList == null) {
				request.setAttribute("msg", "条件に一致する商品がありません");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		session.setAttribute("itemList", itemList);

		RequestDispatcher rd = request.getRequestDispatcher("/views/itemList.jsp");
		rd.forward(request, response);
	}

}
