package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.ItemBean;
import util.DBUtil;

public class DAO {

	public boolean loginCheck(String username, String password) {
		// 実行結果を格納する変数
		ResultSet rs = null;

		try {
			// DBに接続するための手続
			Connection conn = DBUtil.makeConnection();
			DBUtil.makeStatement();

			// SQLを実行
			String SQL = "SELECT * FROM `users` WHERE `username`='" + username + "' AND `password`='" + password + "'";
			rs = DBUtil.execute(SQL);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return rs != null;
	}

	public int insert(ItemBean itemBean) throws SQLException, ClassNotFoundException, Exception {
		int processingNumber = 0;
		String sql = "INSERT INTO items (item_name, item_color, item_count, item_price, item_info, png_path) VALUES(?,?,?,?,?,?)";

		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			DBUtil.makeStatement();

			String item_name = itemBean.getItem_name();
			String item_color = itemBean.getItem_color();
			int item_count = itemBean.getItem_count();
			int item_price = itemBean.getItem_price();
			String item_info = itemBean.getItem_info();
			String png_path = itemBean.getPng_path();

			//SQLを実行

			pstmt.setString(1, item_name);
			pstmt.setString(2, item_color);
			pstmt.setInt(3, item_count);
			pstmt.setInt(4, item_price);
			pstmt.setString(5, item_info);
			pstmt.setString(6, "../png/" + png_path);

			processingNumber = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return processingNumber;

	}

	public List<ItemBean> selectAll() throws SQLException, ClassNotFoundException, Exception {

		List<ItemBean> itemList = new ArrayList<ItemBean>();

		try (Connection conn = DBUtil.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM items")) {

			while (rs.next()) {
				int item_no = rs.getInt("item_no");
				String item_name = rs.getString("item_name");
				String item_color = rs.getString("item_color");
				int item_count = rs.getInt("item_count");
				int item_price = rs.getInt("item_price");
				String item_info = rs.getString("item_info");
				String png_path = rs.getString("png_path");

				ItemBean itemBean = new ItemBean();
				itemBean.setItem_no(item_no);
				itemBean.setItem_name(item_name);
				itemBean.setItem_color(item_color);
				itemBean.setItem_count(item_count);
				itemBean.setItem_price(item_price);
				itemBean.setItem_info(item_info);
				itemBean.setPng_path(png_path);

				itemList.add(itemBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return itemList;

	}

	public ItemBean display(int item_no) throws SQLException, ClassNotFoundException, Exception {
		ItemBean itemBean = new ItemBean();

		String sql = "SELECT * FROM items WHERE item_no = ?";

		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			DBUtil.makeStatement();

			pstmt.setInt(1, item_no);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				itemBean.setItem_no(rs.getInt("item_no"));
				itemBean.setItem_name(rs.getString("item_name"));
				itemBean.setItem_color(rs.getString("item_color"));
				itemBean.setItem_count(rs.getInt("item_count"));
				itemBean.setItem_price(rs.getInt("item_price"));
				itemBean.setItem_info(rs.getString("item_info"));
				itemBean.setPng_path(rs.getString("png_path"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return itemBean;

	}

	public int update(ItemBean itemBean) throws SQLException, ClassNotFoundException, Exception {
		int processingNumber = 0;
		String sql = "UPDATE items SET item_name = ?, item_color = ?, item_count = ?, item_price = ?, item_info = ?, png_path = ? WHERE item_no = ?";

		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			DBUtil.makeStatement();

			String item_name = itemBean.getItem_name();
			String item_color = itemBean.getItem_color();
			int item_count = itemBean.getItem_count();
			int item_price = itemBean.getItem_price();
			String item_info = itemBean.getItem_info();
			String png_path = itemBean.getPng_path();
			int item_no = itemBean.getItem_no();

			// SQLを実行

			pstmt.setString(1, item_name);
			pstmt.setString(2, item_color);
			pstmt.setInt(3, item_count);
			pstmt.setInt(4, item_price);
			pstmt.setString(5, item_info);
			pstmt.setString(6, png_path);
			pstmt.setInt(7, item_no);

			processingNumber = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return processingNumber;

	}

	public int delete(int[] item_no) throws SQLException, ClassNotFoundException, Exception {

		int processingNumber = 0; //処理件数

		for (int i = 0; i < item_no.length; i++) {

			String sql = "DELETE FROM items WHERE item_no = ?";

			// データベースへの接続の取得、PreparedStatementの取得
			try (Connection conn = DBUtil.makeConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {

				//プレースホルダーに値を挿入
				pstmt.setInt(1, item_no[i]);

				// SQLステートメントの実行
				processingNumber = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeConnection();
			}
		}
		return processingNumber;
	}

	public List<ItemBean> select(String category, String conditions)
			throws SQLException, ClassNotFoundException, Exception {
		List<ItemBean> itemList = new ArrayList<ItemBean>();

		String sql = "SELECT * FROM items WHERE item_name LIKE ?";

		if (category.equals("item_color")) {
			sql = "SELECT * FROM items WHERE item_color LIKE ?";
		}
		if (category.equals("item_price1")) {
			sql = "SELECT * FROM items WHERE item_price <= ?";
		}
		if (category.equals("item_price2")) {
			sql = "SELECT * FROM items WHERE item_price >= ?";
		}

		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			if (category.equals("item_name") || category.equals("item_color")) {
				pstmt.setString(1, "%" + conditions + "%");
			} else {
				pstmt.setString(1, conditions);
			}

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int item_no = rs.getInt("item_no");
				String item_name = rs.getString("item_name");
				String item_color = rs.getString("item_color");
				int item_count = rs.getInt("item_count");
				int item_price = rs.getInt("item_price");
				String png_path = rs.getString("png_path");

				ItemBean itemBean = new ItemBean();
				itemBean.setItem_no(item_no);
				itemBean.setItem_name(item_name);
				itemBean.setItem_color(item_color);
				itemBean.setItem_count(item_count);
				itemBean.setItem_price(item_price);
				itemBean.setPng_path(png_path);

				itemList.add(itemBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return itemList;

	}

	public List<ItemBean> cart(int item_no) throws SQLException, ClassNotFoundException, Exception {
		List<ItemBean> itemList = new ArrayList<ItemBean>();

		String sql = "SELECT * FROM items WHERE item_no = ?";

		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, item_no);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String item_name = rs.getString("item_name");
				String item_color = rs.getString("item_color");
				int item_count = rs.getInt("item_count");
				int item_price = rs.getInt("item_price");
				String png_path = rs.getString("png_path");

				ItemBean itemBean = new ItemBean();
				itemBean.setItem_no(item_no);
				itemBean.setItem_name(item_name);
				itemBean.setItem_color(item_color);
				itemBean.setItem_count(item_count);
				itemBean.setItem_price(item_price);
				itemBean.setPng_path(png_path);

				itemList.add(itemBean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return itemList;
	}

	public List<ItemBean> buy(int[] item) throws SQLException, ClassNotFoundException, Exception {
		List<ItemBean> buyList = new ArrayList<ItemBean>();

		for (int i = 0; i < item.length; i++) {

			String sql = "SELECT * FROM items WHERE item_no = ?";

			try (Connection conn = DBUtil.makeConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setInt(1, item[i]);

				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					int item_no = rs.getInt("item_no");
					String item_name = rs.getString("item_name");
					String item_color = rs.getString("item_color");
					int item_count = rs.getInt("item_count");
					int item_price = rs.getInt("item_price");
					String png_path = rs.getString("png_path");

					ItemBean itemBean = new ItemBean();
					itemBean.setItem_no(item_no);
					itemBean.setItem_name(item_name);
					itemBean.setItem_color(item_color);
					itemBean.setItem_count(item_count);
					itemBean.setItem_price(item_price);
					itemBean.setPng_path(png_path);

					buyList.add(itemBean);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeConnection();
			}
		}
		return buyList;
	}

	public int result(int[] item_no, int[] count) throws SQLException, ClassNotFoundException, Exception {
		int processingNumber = 0;

		for (int i = 0; i < item_no.length; i++) {

			String sql = "UPDATE items SET item_count = item_count - ? WHERE item_no = ?";

			try (Connection conn = DBUtil.makeConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setInt(1, count[i]);
				pstmt.setInt(2, item_no[i]);

				processingNumber = pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeConnection();
			}
		}

		return processingNumber;
	}

}
