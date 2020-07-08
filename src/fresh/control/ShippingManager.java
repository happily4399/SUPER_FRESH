package fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fresh.model.BeanShipping;
import fresh.util.DBUtil;

public class ShippingManager {
	public static void main(String[] args) throws Exception {
		ShippingManager sm = new ShippingManager();
		sm.add(1, "浙江省", "杭州市", "拱墅区", "zucc", "张三", "110");
	}
	
	public BeanShipping loadbyShip_num(int Ship_num) throws Exception {
		if("".equals(String.valueOf(Ship_num))) throw new Exception("地址编号不可为空");
		BeanShipping ship = new BeanShipping();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM shipping\r\n" + 
					"WHERE ship_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Ship_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("该地址不存在");
			else {
				ship.setShip_num(rs.getInt(1));
				ship.setUser_num(rs.getInt(2));
				ship.setShip_pro(rs.getString(3));
				ship.setShip_city(rs.getString(4));
				ship.setShip_area(rs.getString(5));
				ship.setShip_address(rs.getString(6));
				ship.setShip_con(rs.getString(7));
				ship.setShip_tele(rs.getString(8));
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
		return ship;
	}
	
	public List<BeanShipping> loadAll() throws Exception {
		List<BeanShipping> result = new ArrayList<BeanShipping>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM shipping";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanShipping ship = new BeanShipping();
				ship.setShip_num(rs.getInt(1));
				ship.setUser_num(rs.getInt(2));
				ship.setShip_pro(rs.getString(3));
				ship.setShip_city(rs.getString(4));
				ship.setShip_area(rs.getString(5));
				ship.setShip_address(rs.getString(6));
				ship.setShip_con(rs.getString(7));
				ship.setShip_tele(rs.getString(8));
				result.add(ship);
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
	
	public List<BeanShipping> loadbyUser_num(int User_num) throws Exception {
		if("".equals(String.valueOf(User_num))) throw new Exception("用户编号不可为空");
		List<BeanShipping> result = new ArrayList<BeanShipping>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM shipping\r\n" + 
					"WHERE User_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, User_num);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanShipping ship = new BeanShipping();
				ship.setShip_num(rs.getInt(1));
				ship.setUser_num(rs.getInt(2));
				ship.setShip_pro(rs.getString(3));
				ship.setShip_city(rs.getString(4));
				ship.setShip_area(rs.getString(5));
				ship.setShip_address(rs.getString(6));
				ship.setShip_con(rs.getString(7));
				ship.setShip_tele(rs.getString(8));
				result.add(ship);
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
	
	public void add(int User_num,String Ship_pro,String Ship_city,String Ship_area,
			String Ship_address,String Ship_con,String Ship_tele) throws Exception {
		if("".equals(String.valueOf(User_num))) throw new Exception("用户编号不可为空");
		if("".equals(String.valueOf(Ship_pro))) throw new Exception("省信息不可为空");
		if("".equals(String.valueOf(Ship_city))) throw new Exception("市信息不可为空");
		if("".equals(String.valueOf(Ship_area))) throw new Exception("区信息不可为空");
		if("".equals(String.valueOf(Ship_address))) throw new Exception("用户地址不可为空");
		if("".equals(String.valueOf(Ship_con))) throw new Exception("联系人不可为空");
		if("".equals(String.valueOf(Ship_tele))) throw new Exception("联系人电话不可为空");
		
		Connection conn = null;
		try {
			UserManager um = new UserManager();
			um.loadbyUser_num(User_num);
			conn = DBUtil.getConnection();
			String sql = "INSERT\r\n" + 
					"INTO shipping(User_num,Ship_pro,Ship_city,Ship_area,Ship_address,Ship_con,Ship_tele)\r\n" + 
					"VALUES(?,?,?,?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, User_num);
			pst.setString(2, Ship_pro);
			pst.setString(3, Ship_city);
			pst.setString(4, Ship_area);
			pst.setString(5, Ship_address);
			pst.setString(6, Ship_con);
			pst.setString(7, Ship_tele);
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
	
	public void Delete(int Ship_num) throws Exception {
		Goods_orderManager gom = new Goods_orderManager();
		if("".equals(String.valueOf(Ship_num))) throw new Exception("地址编号不可为空");
		Connection conn = null;
		try {
			if(!(gom.LoadbyShip_num(Ship_num).size()==0)) throw new Exception("在Goods_order表中仍存在此地址的信息，拒绝删除");
			conn = DBUtil.getConnection();
			String sql = "DELETE \r\n" + 
					"FROM shipping\r\n" + 
					"WHERE ship_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Ship_num);
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
	
	public void ChangeShip(int Ship_num,String Ship_pro,String Ship_city,String Ship_area,String Ship_address) throws Exception {
		if("".equals(String.valueOf(Ship_num))) throw new Exception("地址编号不可为空");
		if("".equals(String.valueOf(Ship_pro))) throw new Exception("省信息不可为空");
		if("".equals(String.valueOf(Ship_city))) throw new Exception("市信息不可为空");
		if("".equals(String.valueOf(Ship_area))) throw new Exception("区信息不可为空");
		if("".equals(String.valueOf(Ship_address))) throw new Exception("用户地址不可为空");
		ShippingManager sm = new ShippingManager();
		Connection conn = null;
		sm.loadbyShip_num(Ship_num);
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE shipping\r\n" + 
					"SET ship_pro=?,ship_city=?,ship_area=?,ship_address=?\r\n" + 
					"where ship_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Ship_pro);
			pst.setString(2, Ship_city);
			pst.setString(3, Ship_area);
			pst.setString(4, Ship_address);
			pst.setInt(5, Ship_num);
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
	
	public void ChangeTel(int Ship_num,String Ship_tele) throws Exception {
		if("".equals(String.valueOf(Ship_num))) throw new Exception("地址编号不可为空");
		if("".equals(String.valueOf(Ship_tele))) throw new Exception("手机号不可为空");
		ShippingManager sm = new ShippingManager();
		Connection conn = null;
		sm.loadbyShip_num(Ship_num);
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE shipping\r\n" + 
					"SET ship_tele=?\r\n" + 
					"where ship_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Ship_tele);
			pst.setInt(2, Ship_num);
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
	
	public void ChangeCon(int Ship_num,String Ship_Con) throws Exception {
		if("".equals(String.valueOf(Ship_num))) throw new Exception("地址编号不可为空");
		if("".equals(String.valueOf(Ship_Con))) throw new Exception("联系人不可为空");
		ShippingManager sm = new ShippingManager();
		Connection conn = null;
		sm.loadbyShip_num(Ship_num);
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE shipping\r\n" + 
					"SET ship_con=?\r\n" + 
					"where ship_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, Ship_Con);
			pst.setInt(2, Ship_num);
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
