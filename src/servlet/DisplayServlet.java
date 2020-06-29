package servlet;

import java.io.IOException;

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
 * Servlet implementation class DisplayServlet
 */
@WebServlet("/display-servlet")
public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DisplayServlet() {
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

		String item_no0 = request.getParameter("item_no");

		try {

			int item_no = Integer.parseInt(item_no0);

			DAO dao = new DAO();

			ItemBean itemBean = dao.display(item_no);

			if (itemBean.getItem_name() == null) {
				request.setAttribute("msg", "検索に失敗しました");
			}

			session.setAttribute("itemBean", itemBean);

		} catch (Exception e) {
			request.setAttribute("msg", "検索に失敗しました");
		}

		RequestDispatcher rd = request.getRequestDispatcher("/views/adEdit.jsp");
		rd.forward(request, response);

	}

}
