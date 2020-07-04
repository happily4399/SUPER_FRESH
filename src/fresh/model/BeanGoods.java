package fresh.model;

public class BeanGoods {
	private int goods_num;
	private String goods_name;
	private int Cate_gory_number;
	private int Pro_num;
	private float goods_price;
	private float vip_price;
	private int goods_count;
	private String goods_spe;
	private String goods_det;
	
	public int getCate_gory_number() {
		return Cate_gory_number;
	}
	public void setCate_gory_number(int cate_gory_number) {
		Cate_gory_number = cate_gory_number;
	}
	public int getPro_num() {
		return Pro_num;
	}
	public void setPro_num(int pro_num) {
		Pro_num = pro_num;
	}
	public int getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(int goods_num) {
		this.goods_num = goods_num;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public float getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(float goods_price) {
		this.goods_price = goods_price;
	}
	public float getVip_price() {
		return vip_price;
	}
	public void setVip_price(float vip_price) {
		this.vip_price = vip_price;
	}
	public int getGoods_count() {
		return goods_count;
	}
	public void setGoods_count(int goods_count) {
		this.goods_count = goods_count;
	}
	public String getGoods_spe() {
		return goods_spe;
	}
	public void setGoods_spe(String goods_spe) {
		this.goods_spe = goods_spe;
	}
	public String getGoods_det() {
		return goods_det;
	}
	public void setGoods_det(String goods_det) {
		this.goods_det = goods_det;
	}
	
}
