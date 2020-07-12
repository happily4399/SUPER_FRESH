package fresh.model;

import java.io.File;

public class BeanGoods_evaluation {
	private int Order_num;
	private int Good_num;
	private int User_num;
	private String eva_con;
	private java.sql.Timestamp eva_date;
	private int star;
	private String photo;
	
	
	public int getGood_num() {
		return Good_num;
	}
	
	public void setGood_num(int good_num) {
		Good_num = good_num;
	}
	public int getOrder_num() {
		return Order_num;
	}
	public void setOrder_num(int Order_num) {
		this.Order_num = Order_num;
	}
	public int getUser_num() {
		return User_num;
	}
	public void setUser_num(int user_num) {
		User_num = user_num;
	}
	public String getEva_con() {
		return eva_con;
	}
	public void setEva_con(String eva_con) {
		this.eva_con = eva_con;
	}
	public java.sql.Timestamp getEva_date() {
		return eva_date;
	}
	public void setEva_date(java.sql.Timestamp eva_date) {
		this.eva_date = eva_date;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
}
