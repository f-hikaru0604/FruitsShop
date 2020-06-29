package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LoginBean;
import models.DAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 文字のエンコードを UTF-8 とする。これがないと文字化け。
		request.setCharacterEncoding("UTF-8");

		// ユーザによって入力された情報を取り出す
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// 取り出した情報を loginBean に格納する
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);

		// モデルをインスタンス化する
		DAO dao = new DAO();
		String url = null;

		// 入力された情報がDBに存在するか調べる
		if (dao.loginCheck(username, password)) {

			if (username.contentEquals("Administrator")) {
				//管理者
				url = "/servlet/menu-servlet";
			} else {
				// 一般ユーザー
				url = "/servlet/shopping-servlet";
			}

			// ログインが失敗したときの処理
		} else {
			// エラーメッセージを設定
			request.setAttribute("emsg", "ユーザ名またはパスワードが違います");

		}
		HttpSession session = request.getSession();
		session.setAttribute("username", username);

		// forwaed_jsp に設定されているJSPへディスパッチ
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);

	}

}
