package fresh.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fresh.model.BeanUser;
import fresh.util.DBUtil;
 
public class UserManager {
	
	public static void main(String[] args) throws Exception {
		UserManager um = new UserManager();
		um.Changpwd("31803225", "123456", "31802323");
	}
	
	public BeanUser reg(String User_name,String User_sex,String User_pwd,String User_Pnum,String User_email,String User_city) throws Exception {
		if("".equals(User_name)) throw new Exception("�û���������Ϊ��");
		if("".equals(User_sex)) throw new Exception("�û��Ա���Ϊ��");
		if("".equals(User_pwd)) throw new Exception("�û����벻��Ϊ��");
		if("".equals(User_Pnum)) throw new Exception("�ֻ��Ų���Ϊ��");
		if("".equals(User_email)) throw new Exception("�û����䲻��Ϊ��");
		if("".equals(User_city)) throw new Exception("���ڳ��в���Ϊ��");
		BeanUser bu = new BeanUser();
		Connection conn = null;
		try {
			
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="SELECT *\r\n" + 
					"FROM user\r\n" + 
					"WHERE User_Pnum=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, User_Pnum);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new Exception("�ֻ�����ע��");
			pst.close();
			rs.close();
			
			sql="INSERT\r\n" + 
					"INTO user(user_name,user_sex,user_Pnum,user_pwd,user_email,user_city,user_reg_date,user_state)\r\n" + 
					"VALUES(?,?,?,?,?,?,current_timestamp(),?)";
			pst =conn.prepareStatement(sql);
			pst.setString(1, User_name);
			pst.setString(2, User_sex);
			pst.setString(3, User_Pnum);
			pst.setString(4, User_pwd);
			pst.setString(5, User_email);
			pst.setString(6, User_city);
			pst.setString(7, "�ڿ�");
			pst.execute();
			pst.close();
			
			bu.setUser_Pnum(User_Pnum);
			bu.setUser_name(User_name);
			bu.setUser_email(User_email);
			bu.setUser_sex(User_sex);
			bu.setUser_pwd(User_pwd);
			bu.setUser_city(User_city);
			
			conn.commit();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		return bu;
	}
	
	public BeanUser login(String user_Pnum,String user_pwd) throws Exception {
		if("".equals(user_Pnum)) throw new Exception("�ֻ��Ų���Ϊ��");
		if("".equals(user_pwd)) throw new Exception("�û����벻��Ϊ��");
		BeanUser bu = new BeanUser();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="SELECT *\r\n" + 
					"FROM user\r\n" + 
					"WHERE User_Pnum=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user_Pnum);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!(rs.next())) throw new Exception("�ֻ���δע��");
			else {
				if(!user_pwd.equals(rs.getString(4))) throw new Exception("�û����벻ƥ��");
				else if("ע��".equals(rs.getString(9))) throw new Exception("Ա����ע��");
				else {
					bu.setUser_num(rs.getInt(1));
					bu.setUser_name(rs.getString(2));
					bu.setUser_sex(rs.getString(3));
					bu.setUser_pwd(rs.getString(4));
					bu.setUser_Pnum(rs.getString(5));
					bu.setUser_email(rs.getString(6));
					bu.setUser_city(rs.getString(7));
					bu.setUser_vip(rs.getInt(9));
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		return bu;
	}
	
	public void Changpwd(String user_Pnum,String old_User_pwd,String new_User_pwd) throws Exception {
		if("".equals(old_User_pwd)) throw new Exception("�����벻��Ϊ��");
		if("".equals(new_User_pwd)) throw new Exception("�����벻��Ϊ��");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="SELECT user_pwd\r\n" + 
					"FROM user\r\n" + 
					"WHERE user_Pnum=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, user_Pnum);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!(rs.next())) throw new Exception("�ֻ�����ע��");
			else{
				if(!old_User_pwd.equals(rs.getString(1))) throw new Exception("�����벻ƥ��");
			}
			rs.close();
			pst.close();
			sql="UPDATE user\r\n" + 
					"set user_pwd=?\r\n" + 
					"where user_Pnum=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, new_User_pwd);
			pst.setString(2, user_Pnum);
			pst.execute();
			pst.close();
			conn.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	
	public void cancell(String user_Pnum) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE user \r\n" + 
					"SET user_state=?\r\n" + 
					"WHERE user_Pnum=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,"ע��");
			pst.setString(2, user_Pnum);
			pst.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	
	public void Delete(String user_Pnum) throws Exception {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT *\r\n" + 
					"FROM shipping\r\n" + 
					"WHERE user_Pnum=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user_Pnum);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new Exception("shipping�����Դ��ڴ���Ʒ���ܾ�ɾ��");
			rs.close();
			pst.close();
			
			sql="SELECT *\r\n" + 
					"FROM goods_order\r\n" + 
					"WHERE user_Pnum=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_Pnum);
			rs = pst.executeQuery();
			if(rs.next()) throw new Exception("goods_order�����Դ��ڴ��û���Ϣ���ܾ�ɾ��");
			rs.close();
			pst.close();
			
			sql="SELECT *\r\n" + 
					"FROM Goods_evaluation\r\n" + 
					"WHERE user_Pnum=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_Pnum);
			rs = pst.executeQuery();
			if(rs.next()) throw new Exception("Goods_evaluation�����Դ��ڴ��û���Ϣ���ܾ�ɾ��");
			rs.close();
			pst.close();
			
			sql="DELETE \r\n" + 
					"FROM user\r\n" + 
					"WHERE user_Pnum=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, user_Pnum);
			pst.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	
	public BeanUser loadbyPnum(String Pnum) throws Exception {
		if("".equals(Pnum)) throw new Exception("�ֻ��Ų���Ϊ��");
		BeanUser bu = new BeanUser();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="SELECT *\r\n" + 
					"FROM USER\r\n" + 
					"WHERE user_Pnum=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Pnum);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())throw new Exception("�������ֻ��Ź����û�");
			else {
				bu.setUser_num(rs.getInt(1));
				bu.setUser_name(rs.getString(2));
				bu.setUser_sex(rs.getString(3));
				bu.setUser_pwd(rs.getString(4));
				bu.setUser_Pnum(rs.getString(5));
				bu.setUser_email(rs.getString(6));
				bu.setUser_city(rs.getString(7));
				bu.setUser_reg_date(rs.getTimestamp(8));
				bu.setUser_vip(rs.getInt(9));
				bu.setVip_ddl(rs.getTimestamp(10));
				bu.setUser_state(rs.getString(11));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		return bu;
	}
	
	public List<BeanUser> loadall() throws Exception {
		List<BeanUser> result = new ArrayList<BeanUser>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="SELECT *\r\n" + 
					"FROM USER";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanUser bu = new BeanUser();
				bu.setUser_num(rs.getInt(1));
				bu.setUser_name(rs.getString(2));
				bu.setUser_sex(rs.getString(3));
				bu.setUser_pwd(rs.getString(4));
				bu.setUser_Pnum(rs.getString(5));
				bu.setUser_email(rs.getString(6));
				bu.setUser_city(rs.getString(7));
				bu.setUser_reg_date(rs.getTimestamp(8));
				bu.setUser_vip(rs.getInt(9));
				bu.setVip_ddl(rs.getTimestamp(10));
				bu.setUser_state(rs.getString(11));
				result.add(bu);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		return result;
	}
}
