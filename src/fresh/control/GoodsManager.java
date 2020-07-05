package fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fresh.model.BeanGoods;
import fresh.util.DBUtil;

public class GoodsManager {
	public void Add(int category_num,String goods_name,
			float goods_price,int goods_count,String goods_spe,String goods_det) throws Exception {
		
		if("".equals(String.valueOf(category_num))) throw new Exception("类别编号不可为空");
		if("".equals(goods_name)) throw new Exception("商品名不可为空");
		if("".equals(String.valueOf(goods_price))) throw new Exception("商品价格不可为空");
		if("".equals(String.valueOf(goods_count))) throw new Exception("商品数量不可为空");
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="INSERT\r\n" + 
					"INTO goods(category_number,goods_name,goods_price,goods_count,goods_spe,goods_det)\r\n" + 
					"VALUES(?,?,?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, category_num);
			pst.setString(2, goods_name);
			pst.setFloat(3, goods_price);
			pst.setInt(4, goods_count);
			pst.setString(5, goods_spe);
			pst.setString(6, goods_det);
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
	
	public void DELETE(int goods_num) throws Exception {
		if("".equals(String.valueOf(goods_num))) throw new Exception("商品编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="SELECT *\r\n" + 
					"FROM good_buy\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) throw new Exception("Goods_buy表中仍存在此商品，拒绝删除");
			rs.close();
			pst.close();
			
			sql="SELECT *\r\n" + 
					"FROM goods_recipe\r\n" + 
					"WHERE goods_num=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			rs=pst.executeQuery();
			if(rs.next()) throw new Exception("Goods_recipe表中仍存在此商品，拒绝删除");
			rs.close();
			pst.close();
			
			sql="SELECT *\r\n" + 
					"FROM goods_discount\r\n" + 
					"WHERE goods_num=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			rs=pst.executeQuery();
			if(rs.next()) throw new Exception("Goods_discount表中仍存在此商品，拒绝删除");
			rs.close();
			pst.close();
			
			sql="SELECT *\r\n" + 
					"FROM order_detail\r\n" + 
					"WHERE goods_num=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			rs=pst.executeQuery();
			if(rs.next()) throw new Exception("Order_detail表中仍存在此商品，拒绝删除");
			rs.close();
			pst.close();
			
			sql="SELECT *\r\n" + 
					"FROM promotion\r\n" + 
					"WHERE goods_num=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			rs=pst.executeQuery();
			if(rs.next()) throw new Exception("Promotion表中仍存在此商品，拒绝删除");
			rs.close();
			pst.close();
			
			sql="SELECT *\r\n" + 
					"FROM goods_evaluation\r\n" + 
					"WHERE goods_num=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			rs=pst.executeQuery();
			if(rs.next()) throw new Exception("Goods_evaluation表中仍存在此商品，拒绝删除");
			rs.close();
			pst.close();
			
			sql="DELETE\r\n" + 
					"FROM goods\r\n" + 
					"WHERE goods_num=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			pst.execute();
			pst.close();
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
	
	public BeanGoods loadbyGoodsnum (int goods_num) throws Exception {
		if("".equals(String.valueOf(goods_num))) throw new Exception("商品编号不可为空");
		BeanGoods bg = new BeanGoods();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("商品编号对应的商品不存在");
			else {
				bg.setGoods_num(rs.getInt(1));
				bg.setCate_gory_number(rs.getInt(2));
				bg.setGoods_name(rs.getString(3));
				bg.setGoods_price(rs.getFloat(4));
				bg.setVip_price(rs.getFloat(5));
				bg.setGoods_count(rs.getInt(6));
				bg.setGoods_spe(rs.getString(7));
				bg.setGoods_det(rs.getString(8));
			}
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
		return bg;
	}
	
	public int LoadGoods_count(int goods_num) throws Exception {
		if("".equals(String.valueOf(goods_num))) throw new Exception("商品编号不可为空");
		int count=0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT goods_count\r\n" + 
					"FROM goods\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("商品编号对应的商品不存在");
			else {
				count = rs.getInt(1);
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
		return count;
	}
	
	public float LoadGoods_price(int goods_num) throws Exception {
		if("".equals(String.valueOf(goods_num))) throw new Exception("商品编号不可为空");
		 float price=0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT goods_price\r\n" + 
					"FROM goods\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("商品编号对应的商品不存在");
			else {
				price = rs.getFloat(1);
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
		return price;
	}
	
	public List<BeanGoods> Loadall () throws Exception {
		List<BeanGoods> result = new ArrayList<BeanGoods>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanGoods bg = new BeanGoods();
				bg.setGoods_num(rs.getInt(1));
				bg.setCate_gory_number(rs.getInt(2));
				bg.setPro_num(rs.getInt(3));
				bg.setGoods_name(rs.getString(4));
				bg.setGoods_price(rs.getFloat(5));
				bg.setVip_price(rs.getFloat(6));
				bg.setGoods_count(rs.getInt(7));
				bg.setGoods_spe(rs.getString(8));
				bg.setGoods_det(rs.getString(9));
				result.add(bg);
			}
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
	
	public void ChangeGoods_name(int goods_num,String goods_name) throws Exception {
		if("".equals(String.valueOf(goods_num))) throw new Exception("商品编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods\r\n" + 
					"SET goods_name=?\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, goods_name);
			pst.setInt(2, goods_num);
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
	
	public void ChangeGoods_count(int goods_num,int goods_count) throws Exception {
		if("".equals(String.valueOf(goods_num))) throw new Exception("商品编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods\r\n" + 
					"SET goods_count=?\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, goods_count);
			pst.setInt(2, goods_num);
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
	
	public void AddGoods_count(int goods_num,int Add_count) throws Exception {
		if("".equals(String.valueOf(goods_num))) throw new Exception("商品编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods\r\n" + 
					"SET goods_count=goods_count+?\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Add_count);
			pst.setInt(2, goods_num);
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
	
	public void ReduceGoods_count(int goods_num,int Reduce_count) throws Exception {
		if("".equals(String.valueOf(goods_num))) throw new Exception("商品编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods\r\n" + 
					"SET goods_count=goods_count-?\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Reduce_count);
			pst.setInt(2, goods_num);
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
	
	public void ChangeGoods_price(int goods_num,float goods_price) throws Exception {
		if("".equals(String.valueOf(goods_num))) throw new Exception("商品编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods\r\n" + 
					"SET goods_price=?\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setFloat(1, goods_price);
			pst.setInt(2, goods_num);
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
