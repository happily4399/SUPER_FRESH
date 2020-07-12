package fresh.model;

public class BeanCoupon {
	private int Coupon_num;
	private String Coupon_con;
	private float app_amount;
	private float Ded_amount;
	private java.sql.Timestamp Coupon_start_date;
	private java.sql.Timestamp Coupon_end_date;
	
	public int getCoupon_num() {
		return Coupon_num;
	}
	public void setCoupon_num(int coupon_num) {
		Coupon_num = coupon_num;
	}
	public String getCoupon_con() {
		return Coupon_con;
	}
	public void setCoupon_con(String coupon_con) {
		Coupon_con = coupon_con;
	}
	public float getApp_amount() {
		return app_amount;
	}
	public void setApp_amount(float app_amount) {
		this.app_amount = app_amount;
	}
	public float getDed_amount() {
		return Ded_amount;
	}
	public void setDed_amount(float f) {
		Ded_amount = f;
	}
	public java.sql.Timestamp getCoupon_start_date() {
		return Coupon_start_date;
	}
	public void setCoupon_start_date(java.sql.Timestamp coupon_start_date) {
		Coupon_start_date = coupon_start_date;
	}
	public java.sql.Timestamp getCoupon_end_date() {
		return Coupon_end_date;
	}
	public void setCoupon_end_date(java.sql.Timestamp coupon_end_date) {
		Coupon_end_date = coupon_end_date;
	}
	
}
