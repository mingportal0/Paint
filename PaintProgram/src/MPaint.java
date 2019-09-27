import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.*;

class Painting extends Frame implements ActionListener, KeyListener{
	//현재위치 변수
	private String s = System.getProperty("user.dir");
	private int x,y,x1,y1;
	private Color penColor = Color.black;
	private Color eraserColor = Color.white;
	public String tool = "pen";
	
	//dim 변수
	public static Dimension dim_fr = new Dimension(900, 900);
	public static Dimension dim_tb = new Dimension(40, 0); //툴바 공백 설정
	
	//메뉴바_파일
	private MenuBar mb = new MenuBar();
	private Menu file = new Menu("파일");
	private MenuItem file_new = new MenuItem("새로만들기");
	private MenuItem file_save = new MenuItem("저장");      
	private MenuItem file_load = new MenuItem("불러오기");
	
	//메뉴바_설정
	private Menu option = new Menu("옵션");
	private MenuItem setting = new MenuItem("설정");       
	private MenuItem version = new MenuItem("버전정보");    
	
	//툴바
	private JToolBar upbar = new JToolBar();
	private JButton pen_bt = new JButton(new ImageIcon(s+"\\pen.png"));
	private JButton eraser_bt = new JButton(new ImageIcon(s+"\\eraser.png"));
	private JButton line_bt = new JButton(new ImageIcon(s+"\\line.png"));
	private JButton rec_bt = new JButton(new ImageIcon(s+"\\rec.png"));	
	private JButton circle_bt = new JButton(new ImageIcon(s+"\\circle.png"));
	private JLabel color_lb = new JLabel("Color");
	private JButton colorSelect_bt = new JButton(new ImageIcon(s+"\\color.png"));
	private JLabel thickness_lb = new JLabel("Width");
	private  JTextField thickness_tf = new JTextField("10", 3);
	private JToolBar bottombar = new JToolBar();
	private JLabel bottombar_lb = new JLabel("Frame Size : "+dim_fr.width+":"+dim_fr.height+"    ||    "
			+ "Tool : "+tool+"    ||    Color : "+penColor.getRed()+":"+penColor.getGreen()+":"+penColor.getBlue());
	
	Board bd = new Board();
	
	Painting(String title){
		super(title);
		this.init();
		this.start();
		super.setSize(dim_fr);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = super.getSize();
		int xpos = (int)(screen.getWidth()/2 - frm.getWidth()/2);
		int ypos = (int)(screen.getHeight()/2 - frm.getHeight()/2);
		super.setLocation(xpos,ypos);
		super.setResizable(false);
		super.setVisible(true);
	}
	public void init() {
		file.add(file_new);
		file.add(file_save);
		file.add(file_load);
		option.add(setting);
		option.add(version);
		mb.add(file);
		mb.add(option);
		upbar.setBackground(Color.lightGray);
		upbar.setLayout(new FlowLayout(FlowLayout.LEFT));
		upbar.add(pen_bt);
		upbar.add(eraser_bt);
		upbar.addSeparator(dim_tb);
		upbar.add(line_bt);
		upbar.add(rec_bt);
		upbar.add(circle_bt);
		upbar.addSeparator(dim_tb);
		upbar.add(color_lb);
		upbar.add(colorSelect_bt);
		upbar.addSeparator(dim_tb);
		upbar.add(thickness_lb);
		upbar.add(thickness_tf);
		thickness_tf.setHorizontalAlignment(JTextField.CENTER);  
		bottombar.setLayout(new FlowLayout(FlowLayout.LEFT));
		bottombar.setBackground(Color.GRAY);
		bottombar.add(bottombar_lb);
		this.setMenuBar(mb);
		this.add(upbar, BorderLayout.NORTH);
		this.add(bd, BorderLayout.CENTER);
		this.add(bottombar, BorderLayout.SOUTH);

	}
	public void start(){
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		eraser_bt.addActionListener(this);
		pen_bt.addActionListener(this);
		line_bt.addActionListener(this);
		circle_bt.addActionListener(this);
		rec_bt.addActionListener(this);
		colorSelect_bt.addActionListener(this);
		thickness_tf.addKeyListener(this);
		file_new.addActionListener(this);
		file_save.addActionListener(this);
		file_load.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == eraser_bt) {
			bd.shape = 0;
			bd.color = eraserColor;
			tool = "Eraser";
			bottombar_lb.setText("Frame Size : "+dim_fr.width+":"+dim_fr.height+"    ||    "
					+ "Tool : "+tool+"    ||    Color : "+eraserColor.getRed()+":"+penColor.getGreen()+":"+penColor.getBlue());
			System.out.println(bd.shape);
		}
		else if(e.getSource() == pen_bt) {
			bd.shape = 1;
			bd.color = penColor;
			tool = "Pen";
			bottombar_lb.setText("Frame Size : "+dim_fr.width+":"+dim_fr.height+"    ||    "
					+ "Tool : "+tool+"    ||    Color : "+penColor.getRed()+":"+penColor.getGreen()+":"+penColor.getBlue());
			System.out.println(bd.shape);
		}
		else if(e.getSource() == line_bt) {
			bd.shape = 2;
			bd.color = penColor;
			tool = "Line";
			bottombar_lb.setText("Frame Size : "+dim_fr.width+":"+dim_fr.height+"    ||    "
					+ "Tool : "+tool+"    ||    Color : "+penColor.getRed()+":"+penColor.getGreen()+":"+penColor.getBlue());
			System.out.println(bd.shape);
		}
		else if(e.getSource() == circle_bt) {
			bd.shape = 3;
			bd.color = penColor;
			tool = "Circle";
			bottombar_lb.setText("Frame Size : "+dim_fr.width+":"+dim_fr.height+"    ||    "
					+ "Tool : "+tool+"    ||    Color : "+penColor.getRed()+":"+penColor.getGreen()+":"+penColor.getBlue());
			System.out.println(bd.shape);
		}
		else if(e.getSource() == rec_bt) {
			bd.shape = 4;
			bd.color = penColor;
			tool = "Rect";
			bottombar_lb.setText("Frame Size : "+dim_fr.width+":"+dim_fr.height+"    ||    "
					+ "Tool : "+tool+"    ||    Color : "+penColor.getRed()+":"+penColor.getGreen()+":"+penColor.getBlue());
			System.out.println(bd.shape);
		}
		else if(e.getSource() == colorSelect_bt) {
			penColor = JColorChooser.showDialog(null, "Select Color", Color.black);
			bd.color = penColor;
			bottombar_lb.setText("Frame Size : "+dim_fr.width+":"+dim_fr.height+"    ||    "
					+ "Tool : "+tool+"    ||    Color : "+penColor.getRed()+":"+penColor.getGreen()+":"+penColor.getBlue());
		}
		else if(e.getSource() == file_new) {
			bd.vc.clear();
			bd.x = 0;
			bd.x1 = 0;
			bd.y = 0;
			bd.y1 = 0;			
			System.out.println(bd.vc.size());
			bd.repaint();
		}
		else if(e.getSource() == file_save) {
			FileDialog fdlg = new FileDialog(this, "Save", FileDialog.SAVE);				
			fdlg.setVisible(true);
			/*fdlg.setFilenameFilter(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith(".jpg");
				}				
			});*/
			String dir = fdlg.getDirectory();
			String file = fdlg.getFile();
			if (dir == null || file == null)
				return;
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File(dir, file))));
				oos.writeObject(bd.vc);
				System.out.println(bd.vc.size());
				oos.close();
			} catch (IOException ee) {
			}
		}
		else if(e.getSource() == file_load) {
			FileDialog fdlg = new FileDialog(this, "Load", FileDialog.LOAD);
			fdlg.setVisible(true);
			String dir = fdlg.getDirectory();
			String file = fdlg.getFile();
			if (dir == null || file == null)
				return;
			try {
				ObjectInputStream oos = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(dir, file))));
				bd.vc = (Vector) oos.readObject();
				oos.close();
				System.out.println(bd.vc.size());
				bd.repaint();
				}
			catch (IOException ee) {
			} 
			catch (ClassNotFoundException ee) {
			}

		}
	}
	@Override
	public void keyTyped(KeyEvent e) {	
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
		bd.thick = Integer.parseInt(thickness_tf.getText());
	}
}
public class MPaint{
	public static void main(String[] args) {
		Painting pt = new Painting("MingMing's Paint");
	}
}
