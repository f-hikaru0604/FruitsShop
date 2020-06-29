package beans;

import java.io.Serializable;

public class ItemBean implements Serializable {

	private int item_no;
	private String item_name;
	private String item_color;
	private int item_count;
	private int item_price;
	private String item_info;
	private String png_path;

	public ItemBean() {
	}

	public int getItem_no() {
		return item_no;
	}

	public void setItem_no(int item_no) {
		this.item_no = item_no;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getItem_color() {
		return item_color;
	}

	public void setItem_color(String item_color) {
		this.item_color = item_color;
	}

	public int getItem_count() {
		return item_count;
	}

	public void setItem_count(int item_count) {
		this.item_count = item_count;
	}

	public int getItem_price() {
		return item_price;
	}

	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}

	public String getItem_info() {
		return item_info;
	}

	public void setItem_info(String item_info) {
		this.item_info = item_info;
	}

	public String getPng_path() {
		return png_path;
	}

	public void setPng_path(String png_path) {
		this.png_path = png_path;
	}

}
