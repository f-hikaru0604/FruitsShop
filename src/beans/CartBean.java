package beans;

import java.io.Serializable;

public class CartBean implements Serializable {

	private ItemBean item ;
	private int count;

	public CartBean() {
	}

	public ItemBean getItemBean() {
		return item;
	}

	public void setItemBean(ItemBean item) {
		this.item = item;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
