package fresh.model;

public class BeanPromotion {
	private int Pro_num;
	private int goods_num;
	private String Pro_name;
	private float Pro_price;
	private int Pro_count;
	private java.sql.Timestamp Pro_start_date;
	private java.sql.Timestamp Pro_end_date;
	
	public String getPro_name() {
		return Pro_name;
	}
	public void setPro_name(String pro_name) {
		Pro_name = pro_name;
	}
	public int getPro_num() {
		return Pro_num;
	}
	public void setPro_num(int pro_num) {
		Pro_num = pro_num;
	}
	public int getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(int goods_num) {
		this.goods_num = goods_num;
	}
	public float getPro_price() {
		return Pro_price;
	}
	public void setPro_price(float pro_price) {
		Pro_price = pro_price;
	}
	public int getPro_count() {
		return Pro_count;
	}
	public void setPro_count(int pro_count) {
		Pro_count = pro_count;
	}
	public java.sql.Timestamp getPro_start_date() {
		return Pro_start_date;
	}
	public void setPro_start_date(java.sql.Timestamp pro_start_date) {
		Pro_start_date = pro_start_date;
	}
	public java.sql.Timestamp getPro_end_date() {
		return Pro_end_date;
	}
	public void setPro_end_date(java.sql.Timestamp pro_end_date) {
		Pro_end_date = pro_end_date;
	}
	
}
