package fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fresh.model.BeanFresh_category;
import fresh.util.DBUtil;

public class Fresh_categoryManager {
	public void add(String name,String des) throws Exception {
		if("".equals(name)) throw new Exception("类别名称不能为空");
		if("".equals(des)) throw new Exception("类别描述不能为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="INSERT\r\n" + 
					"INTO fresh_category(Category_name,Category_des)\r\n" + 
					"VALUES(?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, des);
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
	
	public void changename(int category_num,String category_name) throws Exception {
		if("".equals(String.valueOf(category_num))) throw new Exception("类别编号不能为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="SELECT *\r\n" + 
					"FROM fresh_category\r\n" + 
					"WHERE Category_number=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, category_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("类别不存在");
			rs.close();
			pst.close();
			sql="UPDATE fresh_category\r\n" + 
					"SET Category_name=?\r\n" + 
					"where Category_number=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,category_name);
			pst.setInt(2, category_num);
			pst.execute();
			conn.commit();
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
	
	public void changedes(int category_num,String category_des) throws Exception {
		if("".equals(String.valueOf(category_num))) throw new Exception("类别编号不能为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="SELECT *\r\n" + 
					"FROM fresh_category\r\n" + 
					"WHERE Category_number=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, category_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("类别不存在");
			rs.close();
			pst.close();
			sql="UPDATE fresh_category\r\n" + 
					"SET Category_des=?\r\n" + 
					"where Category_number=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1,category_des);
			pst.setInt(2, category_num);
			pst.execute();
			conn.commit();
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
	
	public void deletebynum(int category_num) throws Exception {
		if("".equals(String.valueOf(category_num))) throw new Exception("类别编号不能为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="SELECT *\r\n" + 
					"FROM goods\r\n" + 
					"WHERE Category_number = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,category_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new Exception("此类别下还有商品，不可删除");
			pst.close();
			rs.close();
			sql = "DELETE \r\n" + 
					"FROM fresh_category\r\n" + 
					"WHERE category_number=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, category_num);
			pst.execute();
			conn.commit();
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
	
	public BeanFresh_category loadbynum(int category_num) throws Exception {
		if("".equals(String.valueOf(category_num))) throw new Exception("类别编号不能为空");
		BeanFresh_category bf = new BeanFresh_category();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="SELECT *\r\n" + 
					"FROM fresh_category\r\n" + 
					"WHERE Category_number=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, category_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("没找到相关的类别");
			else {
				bf.setCategory_number(category_num);
				bf.setCategory_name(rs.getString(2));
				bf.setCategory_des(rs.getString(3));
			}
			rs.close();
			pst.close();
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
		return bf;
	}
	
	public List<BeanFresh_category> loadall() throws Exception {
		List<BeanFresh_category> result = new ArrayList<BeanFresh_category>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="SELECT *\r\n" + 
					"FROM fresh_category";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanFresh_category bf = new BeanFresh_category();
				bf.setCategory_number(rs.getInt(1));
				bf.setCategory_name(rs.getString(2));
				bf.setCategory_des(rs.getString(3));
				result.add(bf);
			}
			rs.close();
			pst.close();
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
	
	public List<BeanFresh_category> loadbyname(String Fresh_name) throws Exception {
		List<BeanFresh_category> result = new ArrayList<BeanFresh_category>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="SELECT *\r\n" + 
					"FROM fresh_category\r\n" + 
					"WHERE Category_name like '%"+Fresh_name+"%' or category_des like '%"+Fresh_name+"%'";
			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				BeanFresh_category bf = new BeanFresh_category();
				bf.setCategory_number(rs.getInt(1));
				bf.setCategory_name(rs.getString(2));
				bf.setCategory_des(rs.getString(3));
				result.add(bf);
			}
			rs.close();
			st.close();
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
