package fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fresh.model.BeanGoods;
import fresh.model.BeanGoods_evaluation;
import fresh.model.BeanUser;
import fresh.util.DBUtil;

public class Goods_evaluationManager {
	
	public static void main(String[] args) throws Exception {
		Goods_evaluationManager gem = new Goods_evaluationManager();
		gem.Add(3, 2, "真的菜", 3);
	}
	
	public BeanGoods_evaluation loadbyGoods_User_num(int Order_num,int User_num) throws Exception {
		if("".equals(String.valueOf(Order_num))) throw new Exception("订单编号不能为空");
		if("".equals(String.valueOf(User_num))) throw new Exception("用户编号不能为空");
		BeanGoods_evaluation bge = new BeanGoods_evaluation();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods_evaluation\r\n" + 
					"WHERE Order_num=? AND User_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Order_num);
			pst.setInt(2, User_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("用户商品评论不存在");
			else {
				bge.setUser_num(rs.getInt(1));
				bge.setOrder_num(rs.getInt(2));
				bge.setEva_con(rs.getString(3));
				bge.setEva_date(rs.getTimestamp(4));
				bge.setStar(rs.getInt(5));
			}
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
		return bge;
	}
	
	public List<BeanGoods_evaluation> loadbyUser_num(int User_num) throws Exception {
		List<BeanGoods_evaluation> result = new ArrayList<BeanGoods_evaluation>();
		if("".equals(String.valueOf(User_num))) throw new Exception("用户编号不能为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods_evaluation\r\n" + 
					"WHERE User_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, User_num);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanGoods_evaluation bge = new BeanGoods_evaluation();
				bge.setUser_num(rs.getInt(1));
				bge.setOrder_num(rs.getInt(2));
				bge.setEva_con(rs.getString(3));
				bge.setEva_date(rs.getTimestamp(4));
				bge.setStar(rs.getInt(5));
				result.add(bge);
			}
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
		return result;
	}
	
	public List<BeanGoods_evaluation> loadbyOrder_num(int Order_num) throws Exception {
		List<BeanGoods_evaluation> result = new ArrayList<BeanGoods_evaluation>();
		if("".equals(String.valueOf(Order_num))) throw new Exception("订单编号不能为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods_evaluation\r\n" + 
					"WHERE Order_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Order_num);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanGoods_evaluation bge = new BeanGoods_evaluation();
				bge.setUser_num(rs.getInt(1));
				bge.setOrder_num(rs.getInt(2));
				bge.setEva_con(rs.getString(3));
				bge.setEva_date(rs.getTimestamp(4));
				bge.setStar(rs.getInt(5));
				result.add(bge);
			}
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
		return result;
	}
	
	public void Add(int Order_num,int User_num,String eva_con,int star) throws Exception {
		if("".equals(String.valueOf(Order_num))) throw new Exception("订单编号不能为空");
		if("".equals(String.valueOf(User_num))) throw new Exception("用户编号不能为空");
		if("".equals(String.valueOf(eva_con))) throw new Exception("评论内容不能为空");
		if("".equals(String.valueOf(star))) throw new Exception("星级不能为空");
		
		Goods_orderManager gom = new Goods_orderManager();
		gom.LoadbyOrder_num(Order_num);
		UserManager um = new UserManager();
		um.loadbyUser_num(User_num);
		
		if(!("送达".equals(gom.LoadbyOrder_num(Order_num).getOrder_state())||
				"退货".equals(gom.LoadbyOrder_num(Order_num).getOrder_state()))) throw new Exception("订单未抵达，无法评价");
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT\r\n" + 
					"INTO goods_evaluation(Order_num,user_num,eva_con,eva_date,star)\r\n" + 
					"VALUES(?,?,?,CURRENT_TIMESTAMP(),?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Order_num);
			pst.setInt(2, User_num);
			pst.setString(3, eva_con);
			pst.setInt(4, star);
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
	
	public void Delete(int Order_num,int User_num) throws Exception {
		if("".equals(String.valueOf(Order_num))) throw new Exception("订单编号不能为空");
		if("".equals(String.valueOf(User_num))) throw new Exception("用户编号不能为空");
		UserManager um = new UserManager();
		Goods_orderManager gom = new Goods_orderManager();
		gom.LoadbyOrder_num(Order_num);
		um.loadbyUser_num(User_num);
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE\r\n" + 
					"FROM goods_discount\r\n" + 
					"WHERE Order_num = ? and User_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Order_num);
			pst.setInt(2, User_num);
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
	
	public void DeleteByUser_num(int User_num) throws Exception {
		if("".equals(String.valueOf(User_num))) throw new Exception("用户编号不能为空");
		UserManager um = new UserManager();
		
		um.loadbyUser_num(User_num);
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE\r\n" + 
					"FROM goods_discount\r\n" + 
					"WHERE User_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, User_num);
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
	
	public void DeleteByOrder_num(int Order_num) throws Exception {
		if("".equals(String.valueOf(Order_num))) throw new Exception("订单编号不能为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE\r\n" + 
					"FROM goods_discount\r\n" + 
					"WHERE Order_num = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Order_num);
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
	
	public void ChangeEva_con(int Order_num,int User_num,String eva_con) throws Exception {
		if("".equals(String.valueOf(Order_num))) throw new Exception("订单编号不能为空");
		if("".equals(String.valueOf(User_num))) throw new Exception("用户编号不能为空");
		if("".equals(String.valueOf(eva_con))) throw new Exception("评论内容不能为空");
		UserManager um = new UserManager();
		Goods_orderManager gom = new Goods_orderManager();
		gom.LoadbyOrder_num(Order_num);
		um.loadbyUser_num(User_num);
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods_evaluation\r\n" + 
					"SET eva_con=?\r\n" + 
					"WHERE Order_num=? AND User_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, eva_con);
			pst.setInt(2, Order_num);
			pst.setInt(3, User_num);
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
	
	public void ChangeEva_star(int Order_num,int User_num,int star) throws Exception {
		if("".equals(String.valueOf(Order_num))) throw new Exception("商品编号不能为空");
		if("".equals(String.valueOf(User_num))) throw new Exception("用户编号不能为空");
		if("".equals(String.valueOf(star))) throw new Exception("星级不能为空");
		Goods_orderManager gom = new Goods_orderManager();
		gom.LoadbyOrder_num(Order_num);
		UserManager um = new UserManager();
		um.loadbyUser_num(User_num);
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods_evaluation\r\n" + 
					"SET star=?\r\n" + 
					"WHERE Order_num=? AND User_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, star);
			pst.setInt(2, Order_num);
			pst.setInt(3, User_num);
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
}
