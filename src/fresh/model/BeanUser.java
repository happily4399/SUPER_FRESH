package fresh.model;

public class BeanUser {
	private int User_num;
	private String User_name;
	private String User_sex;
	private String User_pwd;
	private String User_Pnum;
	private String User_email;
	private String User_city;
	private java.util.Date User_reg_date;
	private int User_vip;
	private java.util.Date vip_ddl;
	private String User_state;
	public static BeanUser currentloginUser = null; 
	public String getUser_state() {
		return User_state;
	}
	public void setUser_state(String user_state) {
		User_state = user_state;
	}
	public int getUser_num() {
		return User_num;
	}
	public void setUser_num(int user_num) {
		User_num = user_num;
	}
	public String getUser_name() {
		return User_name;
	}
	public void setUser_name(String user_name) {
		User_name = user_name;
	}
	public String getUser_sex() {
		return User_sex;
	}
	public void setUser_sex(String user_sex) {
		User_sex = user_sex;
	}
	public String getUser_pwd() {
		return User_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		User_pwd = user_pwd;
	}
	public String getUser_Pnum() {
		return User_Pnum;
	}
	public void setUser_Pnum(String user_Pnum) {
		User_Pnum = user_Pnum;
	}
	public String getUser_email() {
		return User_email;
	}
	public void setUser_email(String user_email) {
		User_email = user_email;
	}
	public String getUser_city() {
		return User_city;
	}
	public void setUser_city(String user_city) {
		User_city = user_city;
	}
	public java.util.Date getUser_reg_date() {
		return User_reg_date;
	}
	public void setUser_reg_date(java.util.Date user_reg_date) {
		User_reg_date = user_reg_date;
	}
	public int getUser_vip() {
		return User_vip;
	}
	public void setUser_vip(int user_vip) {
		User_vip = user_vip;
	}
	public java.util.Date getVip_ddl() {
		return vip_ddl;
	}
	public void setVip_ddl(java.util.Date vip_ddl) {
		this.vip_ddl = vip_ddl;
	}
	
}
