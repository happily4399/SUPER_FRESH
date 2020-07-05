package fresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fresh.model.BeanGoods_recipe;
import fresh.util.DBUtil;

public class Goods_recipeManager {
	
	public List<BeanGoods_recipe> loadbyRecipe_num(int Recipe_num) throws Exception {
		List<BeanGoods_recipe> result = new ArrayList<BeanGoods_recipe>();
		if("".equals(String.valueOf(Recipe_num))) throw new Exception("菜谱编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods_recipe\r\n" + 
					"WHERE Recipe_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Recipe_num);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanGoods_recipe bgr = new BeanGoods_recipe();
				bgr.setGoods_num(rs.getInt(1));
				bgr.setRecipe_num(rs.getInt(2));
				bgr.setRecipe_des(rs.getString(3));
				result.add(bgr);
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
	
	public List<BeanGoods_recipe> loadbyGoods_num(int Goods_num) throws Exception {
		List<BeanGoods_recipe> result = new ArrayList<BeanGoods_recipe>();
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods_recipe\r\n" + 
					"WHERE Goods_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Goods_num);
			java.sql.ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				BeanGoods_recipe bgr = new BeanGoods_recipe();
				bgr.setGoods_num(rs.getInt(1));
				bgr.setRecipe_num(rs.getInt(2));
				bgr.setRecipe_des(rs.getString(3));
				result.add(bgr);
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
	
	public BeanGoods_recipe loadbyGood_Recipe_num(int Goods_num,int Recipe_num) throws Exception {
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不可为空");
		if("".equals(String.valueOf(Recipe_num))) throw new Exception("菜谱编号不可为空");
		BeanGoods_recipe bgr = new BeanGoods_recipe();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT *\r\n" + 
					"FROM goods_recipe\r\n" + 
					"WHERE goods_num=? AND Recipe_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Goods_num);
			pst.setInt(2, Recipe_num);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) throw new Exception("此推荐表不存在");
			else {
				bgr.setGoods_num(rs.getInt(1));
				bgr.setRecipe_num(rs.getInt(2));
				bgr.setRecipe_des(rs.getString(3));
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
		return bgr;
	}
	
	public void add(int Goods_num,int Recipe_num,String Recipe_des) throws Exception {
		GoodsManager gm = new GoodsManager();
		RecipeManager rm = new RecipeManager();
		Goods_recipeManager grm = new Goods_recipeManager();
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不可为空");
		if("".equals(String.valueOf(Recipe_num))) throw new Exception("菜谱编号不可为空");
		Connection conn = null;
		try {
			if(gm.loadbyGoodsnum(Goods_num)==null) throw new Exception("此商品不存在，无法添加");
			if(rm.LoadbyNum(Recipe_num)==null) throw new Exception("此菜谱不存在，无法添加");
			if(!(grm.loadbyGood_Recipe_num(Goods_num, Recipe_num)==null)) throw new Exception("推荐表已存在");
			conn = DBUtil.getConnection();
			String sql = "INSERT \r\n" + 
					"into goods_recipe\r\n" + 
					"values(?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Goods_num);
			pst.setInt(2, Recipe_num);
			pst.setString(3, Recipe_des);
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
	
	public void DeleteByGoods_Recipe_num(int Goods_num,int Recipe_num) throws Exception {
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不可为空");
		if("".equals(String.valueOf(Recipe_num))) throw new Exception("菜谱编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE\r\n" + 
					"FROM goods_recipe\r\n" + 
					"WHERE goods_num=? and recipe_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Goods_num);
			pst.setInt(2, Recipe_num);
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

	public void DeleteByRecipe_num(int Recipe_num) throws Exception {
		if("".equals(String.valueOf(Recipe_num))) throw new Exception("菜谱编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE\r\n" + 
					"FROM goods_recipe\r\n" + 
					"WHERE recipe_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Recipe_num);
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
	
	public void DeleteByGoods_num(int Goods_num) throws Exception {
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不可为空");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE\r\n" + 
					"FROM goods_recipe\r\n" + 
					"WHERE goods_num=? and recipe_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, Goods_num);
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
	
	public void ChangeGoods_num_one(int Goods_num,int Recipe_num,int newGoods_num) throws Exception {
		GoodsManager gm = new GoodsManager();
		Goods_recipeManager grm = new Goods_recipeManager();
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不可为空");
		if("".equals(String.valueOf(Recipe_num))) throw new Exception("菜谱编号不可为空");
		if("".equals(String.valueOf(newGoods_num))) throw new Exception("新商品编号不可为空");
		gm.loadbyGoodsnum(newGoods_num);
		grm.loadbyGood_Recipe_num(Goods_num, Recipe_num);
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods_recipe\r\n" + 
					"SET goods_num=?\r\n" + 
					"WHERE goods_num=? AND Recipe_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, newGoods_num);
			pst.setInt(2, Goods_num);
			pst.setInt(3, Recipe_num);
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
	
	public void ChangeRecipe_num_one(int Goods_num,int Recipe_num,int newRecipe_num) throws Exception {
		RecipeManager rm = new RecipeManager();
		Goods_recipeManager grm = new Goods_recipeManager();
		if("".equals(String.valueOf(Goods_num))) throw new Exception("商品编号不可为空");
		if("".equals(String.valueOf(Recipe_num))) throw new Exception("菜谱编号不可为空");
		if("".equals(String.valueOf(newRecipe_num))) throw new Exception("新菜谱编号不可为空");
		rm.LoadbyNum(newRecipe_num);
		grm.loadbyGood_Recipe_num(Goods_num, Recipe_num);
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE goods_recipe\r\n" + 
					"SET Recipe_num=?\r\n" + 
					"WHERE goods_num=? AND Recipe_num=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, newRecipe_num);
			pst.setInt(2, Goods_num);
			pst.setInt(3, Recipe_num);
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
