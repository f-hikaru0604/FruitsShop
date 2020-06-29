package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.DAO;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/delete-servlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteServlet() {
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

		String[] item_no0 = request.getParameterValues("item_no");
		if (item_no0 == null) {
			request.setAttribute("msg", "削除に失敗しました");
		} else {

			int[] item_no = new int[item_no0.length];

			for (int i = 0; i < item_no0.length; i++) {
				int item = Integer.parseInt(item_no0[i]);
				item_no[i] = item;
			}

			DAO dao = new DAO();

			int processingNumber = 0; //処理件数

			try {
				// DAOの利用
				processingNumber = dao.delete(item_no);

				if (processingNumber > 0) {
					request.setAttribute("msg", "削除に成功しました");
				} else {
					request.setAttribute("msg", "削除に失敗しました");
				}

			} catch (Exception e) {
				request.setAttribute("msg", "削除に失敗しました");
			}

		}
		// リクエストの転送
		RequestDispatcher rd = request.getRequestDispatcher("/servlet/deleteSupport-servlet");
		rd.forward(request, response);
	}

}
