package fresh.model;

public class BeanGoods_discount {
	private int goods_num;
	private int Dis_num;
	private java.sql.Timestamp start_Date;
	private java.sql.Timestamp end_Date;
	
	public int getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(int goods_num) {
		this.goods_num = goods_num;
	}
	public int getDis_num() {
		return Dis_num;
	}
	public void setDis_num(int dis_num) {
		Dis_num = dis_num;
	}
	public java.sql.Timestamp getStart_Date() {
		return start_Date;
	}
	public void setStart_Date(java.sql.Timestamp start_Date) {
		this.start_Date = start_Date;
	}
	public java.sql.Timestamp getEnd_Date() {
		return end_Date;
	}
	public void setEnd_Date(java.sql.Timestamp end_Date) {
		this.end_Date = end_Date;
	}
	
}
