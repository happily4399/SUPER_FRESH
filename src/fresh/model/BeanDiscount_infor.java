package fresh.model;

public class BeanDiscount_infor {
	private int Dis_num;
	private String Dis_content;
	private int Dis_count;
	private float dicount;
	private java.sql.Timestamp Dis_start_date;
	private java.sql.Timestamp Dis_end_date;
	
	public int getDis_num() {
		return Dis_num;
	}
	public void setDis_num(int dis_num) {
		Dis_num = dis_num;
	}
	public String getDis_content() {
		return Dis_content;
	}
	public void setDis_content(String dis_content) {
		Dis_content = dis_content;
	}
	public int getDis_count() {
		return Dis_count;
	}
	public void setDis_count(int dis_count) {
		Dis_count = dis_count;
	}
	public float getDicount() {
		return dicount;
	}
	public void setDicount(float dicount) {
		this.dicount = dicount;
	}
	public java.sql.Timestamp getDis_start_date() {
		return Dis_start_date;
	}
	public void setDis_start_date(java.sql.Timestamp dis_start_date) {
		Dis_start_date = dis_start_date;
	}
	public java.sql.Timestamp getDis_end_date() {
		return Dis_end_date;
	}
	public void setDis_end_date(java.sql.Timestamp dis_end_date) {
		Dis_end_date = dis_end_date;
	}
	
}
