package fresh.model;

import java.io.File;

public class BeanGoods_evaluation {
	private int goods_num;
	private int User_num;
	private String eva_con;
	private java.util.Date eva_date;
	private int star;
	private File photo;
	
	public int getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(int goods_num) {
		this.goods_num = goods_num;
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
	public java.util.Date getEva_date() {
		return eva_date;
	}
	public void setEva_date(java.util.Date eva_date) {
		this.eva_date = eva_date;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public File getPhoto() {
		return photo;
	}
	public void setPhoto(File photo) {
		this.photo = photo;
	}
	
	
}
