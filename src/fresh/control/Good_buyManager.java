package fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fresh.model.BeanGood_buy;
import fresh.model.BeanUser;
import fresh.util.DBUtil;

public class Good_buyManager {
	public static void main(String[] args) throws Exception {
		Good_buyManager gbm = new Good_buyManager();
		GoodsManager gm = new GoodsManager();
		gbm.add(2, "31802323", 40, "入库");
	}
	
	public BeanGood_buy LoadbyBuy_num(int buy_num) throws Exception {
		if("".equals(buy_num)) throw new Exception("采购单号不能为空");
		BeanGood_buy bg = new BeanGood_buy();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="SELECT *\r\n" + 
					"FROM good_buy\r\n" + 
					"WHERE buy_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, buy_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next())throw new Exception("不存在采购单");
			else {
				bg.setBuy_num(rs.getInt(1));
				bg.setGoods_num(rs.getInt(2));
				bg.setAdmin_num(rs.getString(3));
				bg.setBuy_count(rs.getInt(4));
				bg.setBuy_state(rs.getString(5));
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
		return bg;
	}
	
	public List<BeanGood_buy> LoadAll() throws Exception {
		List<BeanGood_buy> result = new ArrayList<BeanGood_buy>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql="SELECT *\r\n" + 
					"FROM good_buy";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanGood_buy bg = new BeanGood_buy();
				bg.setBuy_num(rs.getInt(1));
				bg.setGoods_num(rs.getInt(2));
				bg.setAdmin_num(rs.getString(3));
				bg.setBuy_count(rs.getInt(4));
				bg.setBuy_state(rs.getString(5));
				result.add(bg);
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
		return result;
	}
	
	public void ChangeGoods_CountByState(int buy_num,int goods_num,String buy_state) throws Exception {
		Good_buyManager gbm = new Good_buyManager();
		GoodsManager gm = new GoodsManager();
		if("".equals(String.valueOf(buy_num))) throw new Exception("采购编号不可为空");
		if("".equals(String.valueOf(goods_num))) throw new Exception("商品编号不可为空");
		if("".equals(String.valueOf(buy_state))) throw new Exception("采购状态不可为空");
		try {
			if(buy_state == "入库") {
				gm.AddGoods_count(goods_num, gbm.LoadBuy_CountByBuy_num(buy_num));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int LoadBuy_CountByBuy_num(int buy_num) throws Exception {
		if("".equals(String.valueOf(buy_num))) throw new Exception("采购编号不可为空");
		int Count = 0;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT buy_count\r\n" + 
					"FROM good_buy\r\n" + 
					"WHERE buy_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, buy_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("不存在此采购单");
			else {
				Count = rs.getInt(1);
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
		return Count;
	}
	
	public void add(int goods_num,String admin_num,int buy_count,String buy_state) throws Exception {
		AdminManager am = new AdminManager();
		GoodsManager gm = new GoodsManager();
		Good_buyManager gb = new Good_buyManager();
		if("".equals(String.valueOf(goods_num))) throw new Exception("商品编号不可为空");
		if("".equals(String.valueOf(admin_num))) throw new Exception("管理员编号不可为空");
		if("".equals(String.valueOf(buy_count))) throw new Exception("采购数量不可为空");
		if("".equals(String.valueOf(buy_state))) throw new Exception("采购状态不可为空");
		Connection conn = null;
		try {
			if(am.loadbynum(admin_num)==null) throw new Exception("管理员不存在");
			if(gm.loadbyGoodsnum(goods_num)==null) throw new Exception("采购商品不存在");
			conn = DBUtil.getConnection();
			String sql = "INSERT \r\n" + 
					"INTO good_buy(goods_num,admin_num,buy_count,buy_state)\r\n" + 
					"VALUES(?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, goods_num);
			pst.setString(2, admin_num);
			pst.setInt(3, buy_count);
			pst.setString(4, buy_state);
			pst.execute();
			pst.close();
			if(buy_state=="入库") {
				gm.AddGoods_count(goods_num, buy_count);
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
	}
	
	
	public void Delete(int buy_num) throws Exception {
		Good_buyManager gb = new Good_buyManager();
		if("".equals(String.valueOf(buy_num))) throw new Exception("采购编号不可为空");
		if(gb.LoadbyBuy_num(buy_num)==null) throw new Exception("采购单不存在");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE \r\n" + 
					"FROM good_buy\r\n" + 
					"WHERE buy_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, buy_num);
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
