package fresh.model;

public class BeanGoods_order {
	private int order_num;
	private int User_num;
	private int ship_num;
	private int Coupon_num;
	private float Ori_price;
	private float Fin_price;
	private java.util.Date Service_time;
	private String Order_state;
	
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public int getUser_num() {
		return User_num;
	}
	public void setUser_num(int user_num) {
		User_num = user_num;
	}
	public int getShip_num() {
		return ship_num;
	}
	public void setShip_num(int ship_num) {
		this.ship_num = ship_num;
	}
	public int getCoupon_num() {
		return Coupon_num;
	}
	public void setCoupon_num(int coupon_num) {
		Coupon_num = coupon_num;
	}
	public float getOri_price() {
		return Ori_price;
	}
	public void setOri_price(float ori_price) {
		Ori_price = ori_price;
	}
	public float getFin_price() {
		return Fin_price;
	}
	public void setFin_price(float fin_price) {
		Fin_price = fin_price;
	}
	public java.util.Date getService_time() {
		return Service_time;
	}
	public void setService_time(java.util.Date service_time) {
		Service_time = service_time;
	}
	public String getOrder_state() {
		return Order_state;
	}
	public void setOrder_state(String order_state) {
		Order_state = order_state;
	}
	
	
}
