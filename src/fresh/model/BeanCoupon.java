package fresh.model;

public class BeanCoupon {
	private int Coupon_num;
	private String Coupon_con;
	private int app_amount;
	private int Ded_amount;
	private java.util.Date Coupon_start_date;
	private java.util.Date Coupon_end_date;
	
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
	public int getApp_amount() {
		return app_amount;
	}
	public void setApp_amount(int app_amount) {
		this.app_amount = app_amount;
	}
	public int getDed_amount() {
		return Ded_amount;
	}
	public void setDed_amount(int ded_amount) {
		Ded_amount = ded_amount;
	}
	public java.util.Date getCoupon_start_date() {
		return Coupon_start_date;
	}
	public void setCoupon_start_date(java.util.Date coupon_start_date) {
		Coupon_start_date = coupon_start_date;
	}
	public java.util.Date getCoupon_end_date() {
		return Coupon_end_date;
	}
	public void setCoupon_end_date(java.util.Date coupon_end_date) {
		Coupon_end_date = coupon_end_date;
	}
	
}
