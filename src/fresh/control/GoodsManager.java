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
		GoodsManager gm = new GoodsManager();
		if(gm.loadbyGoodsname(goods_name)!=null) throw new Exception("商品名称已重复");
		
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
				bg.setSales_count(rs.getInt(9));
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
	
	public BeanGoods loadbyGoodsname (String goods_name) throws Exception {
		if("".equals(String.valueOf(goods_name))) throw new Exception("商品名称不可为空");
		BeanGoods bg = new BeanGoods();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods\r\n" + 
					"WHERE goods_name=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, goods_name);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				bg.setGoods_num(rs.getInt(1));
				bg.setCate_gory_number(rs.getInt(2));
				bg.setGoods_name(rs.getString(3));
				bg.setGoods_price(rs.getFloat(4));
				bg.setVip_price(rs.getFloat(5));
				bg.setGoods_count(rs.getInt(6));
				bg.setGoods_spe(rs.getString(7));
				bg.setGoods_det(rs.getString(8));
				bg.setSales_count(rs.getInt(9));
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
	
	public List<BeanGoods> LoadBycate (int Fresh_num) throws Exception {
		List<BeanGoods> result = new ArrayList<BeanGoods>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods\r\n" + 
					"WHERE Category_number = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Fresh_num);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanGoods bg = new BeanGoods();
				bg.setGoods_num(rs.getInt(1));
				bg.setCate_gory_number(rs.getInt(2));
				bg.setGoods_name(rs.getString(3));
				bg.setGoods_price(rs.getFloat(4));
				bg.setVip_price(rs.getFloat(5));
				bg.setGoods_count(rs.getInt(6));
				bg.setGoods_spe(rs.getString(7));
				bg.setGoods_det(rs.getString(8));
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
				bg.setGoods_name(rs.getString(3));
				bg.setGoods_price(rs.getFloat(4));
				bg.setVip_price(rs.getFloat(5));
				bg.setGoods_count(rs.getInt(6));
				bg.setGoods_spe(rs.getString(7));
				bg.setGoods_det(rs.getString(8));
				bg.setSales_count(rs.getInt(9));
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
		GoodsManager gm = new GoodsManager();
		if(gm.loadbyGoodsname(goods_name)!=null) throw new Exception("商品名称已重复");
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
	
	public void ChangeGoods_spe(int goods_num,String goods_spe) throws Exception {
		if("".equals(String.valueOf(goods_num))) throw new Exception("商品编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods\r\n" + 
					"SET goods_spe=?\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, goods_spe);
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
	
	public void ChangeGoods_des(int goods_num,String goods_des) throws Exception {
		if("".equals(String.valueOf(goods_num))) throw new Exception("商品编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods\r\n" + 
					"SET goods_des=?\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, goods_des);
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
	
	public void SubGoods_count(int goods_num,int Sub_count) throws Exception {
		if("".equals(String.valueOf(goods_num))) throw new Exception("商品编号不可为空");
		BeanGoods bg = new BeanGoods();
		GoodsManager gm = new GoodsManager();
		bg = gm.loadbyGoodsnum(goods_num);
		if(Sub_count > bg.getGoods_count()) throw new Exception("减去的数量大于商品数量");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods\r\n" + 
					"SET goods_count=goods_count-?,sales_count=sales_count+?\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Sub_count);
			pst.setInt(2, Sub_count);
			pst.setInt(3, goods_num);
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
	
	public void ChangeVip_price(int goods_num,float Vip_price) throws Exception {
		if("".equals(String.valueOf(goods_num))) throw new Exception("商品编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods\r\n" + 
					"SET Vip_price=?\r\n" + 
					"WHERE goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setFloat(1, Vip_price);
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
	public List<BeanGoods> loadbyGoods_nameFresh(int Fresh_num,String Goods_name){
		List<BeanGoods> result = new ArrayList<BeanGoods>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods\r\n" + 
					"WHERE goods_name like '%"+Goods_name+"%' AND Category_number="+Fresh_num;
			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				BeanGoods bg = new BeanGoods();
				bg.setGoods_num(rs.getInt(1));
				bg.setCate_gory_number(rs.getInt(2));
				bg.setGoods_name(rs.getString(3));
				bg.setGoods_price(rs.getFloat(4));
				bg.setVip_price(rs.getFloat(5));
				bg.setGoods_count(rs.getInt(6));
				bg.setGoods_spe(rs.getString(7));
				bg.setGoods_det(rs.getString(8));
				bg.setSales_count(rs.getInt(9));
				result.add(bg);
			}
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
	
	public List<BeanGoods> loadbyGoods_name(String Goods_name){
		List<BeanGoods> result = new ArrayList<BeanGoods>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods\r\n" + 
					"WHERE goods_name like '%"+Goods_name+"%' or goods_det like '%"+Goods_name+"%'";
			java.sql.Statement st = conn.createStatement();
			java.sql.ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				BeanGoods bg = new BeanGoods();
				bg.setGoods_num(rs.getInt(1));
				bg.setCate_gory_number(rs.getInt(2));
				bg.setGoods_name(rs.getString(3));
				bg.setGoods_price(rs.getFloat(4));
				bg.setVip_price(rs.getFloat(5));
				bg.setGoods_count(rs.getInt(6));
				bg.setGoods_spe(rs.getString(7));
				bg.setGoods_det(rs.getString(8));
				bg.setSales_count(rs.getInt(9));
				result.add(bg);
			}
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
