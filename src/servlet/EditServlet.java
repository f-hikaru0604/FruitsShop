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

import beans.ItemBean;
import models.DAO;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit-servlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditServlet() {
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
		String item_name = request.getParameter("item_name");
		String item_color = request.getParameter("item_color");
		String item_count0 = request.getParameter("item_count");
		String item_price0 = request.getParameter("item_price");
		String item_info = request.getParameter("item_info");
		String png_path = request.getParameter("png_path");

		List<String> list = new ArrayList<String>();
		list.add(item_name);
		list.add(item_color);
		list.add(item_count0);
		list.add(item_price0);
		list.add(item_info);
		list.add(png_path);
		list.add(item_no0);

		if (item_name == null || item_name.equals("") || item_name.toString().trim().length() == 0
				|| item_name.length() > 20) {
			session.setAttribute("list", list);
			request.setAttribute("msg", "変更に失敗しました");

		} else if (item_color == null || item_color.equals("") || item_color.length() > 20) {
			session.setAttribute("list", list);
			request.setAttribute("msg", "変更に失敗しました");

		} else if (item_count0 == null || item_count0.equals("0") || item_count0.length() > 20) {
			session.setAttribute("list", list);
			request.setAttribute("msg", "変更に失敗しました");

		} else if (item_price0 == null || item_price0.equals("") || item_price0.length() > 5) {
			session.setAttribute("list", list);
			request.setAttribute("msg", "変更に失敗しました");

		} else if (item_info == null || item_info.equals("") || item_info.toString().trim().length() == 0
				|| item_info.length() > 100) {
			session.setAttribute("list", list);
			request.setAttribute("msg", "変更に失敗しました");

		} else if (png_path == null || png_path.equals("") || png_path.toString().trim().length() == 0
				|| png_path.length() > 50) {
			session.setAttribute("list", list);
			request.setAttribute("msg", "変更に失敗しました");

		} else {

			try {
				int item_no = Integer.parseInt(item_no0);
				int item_count = Integer.parseInt(item_count0);
				int item_price = Integer.parseInt(item_price0);

				ItemBean itemBean = new ItemBean();
				itemBean.setItem_no(item_no);
				itemBean.setItem_name(item_name);
				itemBean.setItem_color(item_color);
				itemBean.setItem_count(item_count);
				itemBean.setItem_price(item_price);
				itemBean.setItem_info(item_info);
				itemBean.setPng_path(png_path);

				DAO dao = new DAO();

				int processingNumber = 0;

				processingNumber = dao.update(itemBean);

				if (processingNumber > 0) {

					request.setAttribute("msg", "変更に成功しました");
					session.setAttribute("itemBean", itemBean);
					session.removeAttribute("list");

				} else {
					session.setAttribute("list", list);
					request.setAttribute("msg", "変更に失敗しました");
				}
			} catch (Exception e) {
				session.setAttribute("list", list);
				request.setAttribute("msg", "変更に失敗しました");

			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/views/adEdit.jsp");
		rd.forward(request, response);

	}

}
