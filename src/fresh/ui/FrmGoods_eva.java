package fresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import fresh.control.DiscountManager;
import fresh.control.Good_buyManager;
import fresh.control.Goods_evaluationManager;
import fresh.model.BeanDiscount_infor;
import fresh.model.BeanGoods_evaluation;

public class FrmGoods_eva extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnDelete = new Button("删除评价");
	private JLabel Labelstar = new JLabel("根据星级查询：");
	private Button btnSearch = new Button("查询");
	private JComboBox cmbbuytype=null;
	String[] listData ={"5星","4星","3星","2星","1星"};
	
	private Object tblTitle[]={"订单编号","用户编号","评价内容","评价星级","相关图片","评价日期"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable Table=new JTable(tablmod);
	private int Goods_num = 0;
	
	private void reloadTable(int Goods_num){
		try {
			Goods_evaluationManager gem = new Goods_evaluationManager();
			List<BeanGoods_evaluation> eva = gem.loadbyGoods_num(Goods_num);
			tblData =new Object[eva.size()][6];
			for(int i=0;i<eva.size();i++){
				tblData[i][0]=eva.get(i).getOrder_num();
				tblData[i][1]=eva.get(i).getUser_num();
				tblData[i][2]=eva.get(i).getEva_con();
				tblData[i][3]=eva.get(i).getStar();
				if(!"".equals(eva.get(i).getPhoto())) {
					ImageIcon icon = new ImageIcon(eva.get(i).getPhoto());
					JLabel label = new JLabel(icon);
					tblData[i][4]=label;
				}
				tblData[i][5]=eva.get(i).getEva_date();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.Table.validate();
			this.Table.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void reloadTablebystar(int Goods_num,int star){
		try {
			Goods_evaluationManager gem = new Goods_evaluationManager();
			List<BeanGoods_evaluation> eva = gem.loadbystar(Goods_num, star);
			tblData =new Object[eva.size()][4];
			for(int i=0;i<eva.size();i++){
				tblData[i][0]=eva.get(i).getOrder_num();
				tblData[i][1]=eva.get(i).getUser_num();
				tblData[i][2]=eva.get(i).getEva_con();
				tblData[i][3]=eva.get(i).getStar();
				if(!"".equals(eva.get(i).getPhoto())) {
					ImageIcon icon = new ImageIcon(eva.get(i).getPhoto());
					JLabel label = new JLabel(icon);
					tblData[i][4]=label;
				}
				tblData[i][5]=eva.get(i).getEva_date();
			}
			tablmod.setDataVector(tblData,tblTitle);
			this.Table.validate();
			this.Table.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public FrmGoods_eva(Frame f,String s,boolean b,int Goods_num) {
		super(f,s,b);
		this.Goods_num = Goods_num;
		this.cmbbuytype = new JComboBox(listData);
		toolBar.add(this.btnDelete);
		toolBar.add(cmbbuytype);
		toolBar.add(this.btnSearch);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		this.reloadTable(Goods_num);
		this.getContentPane().add(new JScrollPane(this.Table), BorderLayout.CENTER);
		this.setSize(1000, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		
		this.btnSearch.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnDelete) {
			int i = this.Table.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择评价","提示",JOptionPane.ERROR_MESSAGE);
			}
			String order = this.tblData[i][0].toString();
			String user = this.tblData[i][1].toString();
			int order_num = Integer.parseInt(order);
			int user_num = Integer.parseInt(user);
			try {
				Goods_evaluationManager gm = new Goods_evaluationManager();
				gm.Delete(order_num, Goods_num, user_num);
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
			this.reloadTable(Goods_num);
		}
		
		else if(e.getSource()==this.btnSearch) {
			String star_name = this.cmbbuytype.getSelectedItem().toString();
			int star=0;
			if("五星".equals(star_name)) {
				star = 5;
			}
			
			else if("四星".equals(star_name)) {
				star = 4;
			}
			
			else if("三星".equals(star_name)) {
				star = 3;
			}
			
			else if("两星".equals(star_name)) {
				star = 2;
			}
			
			else if("一星".equals(star_name)) {
				star = 1;
			}
			
			this.reloadTablebystar(Goods_num, star);
		}
	}
}
