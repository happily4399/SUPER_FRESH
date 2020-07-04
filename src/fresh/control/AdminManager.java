package fresh.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fresh.model.BeanAdmin;
import fresh.util.DBUtil;

public class AdminManager {
	
	public BeanAdmin reg(String admin_num,String admin_name,String admin_pwd,String admin_pwd2) throws Exception{
		if(admin_num==null) throw new Exception("员工编号不能为空");
		if(admin_name==null) throw new Exception("注册的姓名不能为空！！");
		if(admin_pwd==null) throw new Exception("密码不能为空");
		if(!(admin_pwd.equals(admin_pwd2))) throw new Exception("两次输入的密码不一致");
		BeanAdmin ba=new BeanAdmin();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="SELECT *\r\n" + 
					"FROM admin\r\n" + 
					"WHERE admin_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, admin_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new Exception("存在相同账户名的员工编号，请修改");
			pst.close();
			rs.close();
			sql="INSERT\r\n" + 
					"into admin(admin_num,admin_name,admin_pwd,admin_state)\r\n" + 
					"VALUEs (?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, admin_num);
			pst.setString(2, admin_name);
			pst.setString(3, admin_pwd);
			pst.setString(4, "在职");
			pst.execute();
			pst.close();
			ba.setAdmin_num(admin_num);
			ba.setAdmin_name(admin_name);
			ba.setAdmin_pwd(admin_pwd);
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return ba;
	}
	
	public BeanAdmin login(String admin_num,String admin_pwd) throws Exception {
		BeanAdmin ba=new BeanAdmin();
		if("".equals(admin_num)) throw new Exception("员工编号不能为空");
		if("".equals(admin_pwd)) throw new Exception("员工密码不能为空");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT *\r\n" + 
					"FROM admin\r\n" + 
					"WHERE admin_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, admin_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!(rs.next())) throw new Exception("员工编号不存在");
			else {
				if(!(admin_pwd.equals(rs.getString(3)))) throw new Exception("密码不正确");
				else if("注销".equals(rs.getString(4))) throw new Exception("此员工已注销");
				else 
				{
					ba.setAdmin_num(rs.getString(1));
					ba.setAdmin_name(rs.getString(2));
					ba.setAdmin_pwd(rs.getString(3));
				}
			}
			pst.close();
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return ba;
	}
	
	public void Changpwd(String admin_num,String old_admin_pwd,String new_admin_pwd) throws Exception {
		if("".equals(old_admin_pwd)) throw new Exception("旧密码不可为空");
		if("".equals(new_admin_pwd)) throw new Exception("新密码不可为空");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="SELECT admin_pwd,admin_state\r\n" + 
					"FROM admin\r\n" + 
					"WHERE admin_num=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, admin_num);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!(rs.next())) throw new Exception("员工编号不存在");
			else if("注销".equals(rs.getString(2))) throw new Exception("员工已注销");
			else{
				if(!old_admin_pwd.equals(rs.getString(1))) throw new Exception("旧密码不匹配");
			}
			rs.close();
			pst.close();
			sql="UPDATE admin\r\n" + 
					"set admin_pwd=?\r\n" + 
					"where admin_num=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, new_admin_pwd);
			pst.setString(2, admin_num);
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	public void cancell(String admin_num) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE admin\r\n" + 
					"SET admin_state=?\r\n" + 
					"WHERE admin_num=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, "注销");
			pst.setString(2, admin_num);
			pst.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	public void delete(String admin_num) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="DELETE \r\n" + 
					"FROM adin\r\n" + 
					"WHERE admin_num=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, admin_num);
			pst.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	
	public BeanAdmin loadbynum(String num) throws Exception {
		if(num==null) throw new Exception("员工编号不能为空");
		BeanAdmin ba = new BeanAdmin();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="SELECT *\r\n" + 
					"FROM admin\r\n" + 
					"WHERE admin_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("员工不存在");
			else {
				ba.setAdmin_num(rs.getString(1));
				ba.setAdmin_name(rs.getString(2));
				ba.setAdmin_pwd(rs.getString(3));
				ba.setAdmin_state(rs.getString(4));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return ba;
	}
	
	public List<BeanAdmin> loadbynum() throws Exception {
		List<BeanAdmin> result = new ArrayList<BeanAdmin>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="SELECT *\r\n" + 
					"FROM admin";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanAdmin ba = new BeanAdmin();
				ba.setAdmin_num(rs.getString(1));
				ba.setAdmin_name(rs.getString(2));
				ba.setAdmin_pwd(rs.getString(3));
				ba.setAdmin_state(rs.getString(4));
				result.add(ba);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		return result;
	}
}
