 package cn;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MultiProgram {
private JFrame frame=new JFrame("多道程序缓冲区协调操作");                   //顶层容器
private JPanel panelmain=new JPanel();                               //面板
private JLabel[] label=new JLabel[20];                               //标签
protected   JTextField [] textfield=new JTextField[11];              //可编辑区
                                                                     //3个列表作为缓冲显示区
JList list1=new JList();                                             //buffer1
JList list2=new JList();                                             //buffer2
JList list3=new JList();                                             //buffer3
private   JScrollPane spane1=new JScrollPane(list1);  
private   JScrollPane spane2=new JScrollPane(list2);
private   JScrollPane spane3=new JScrollPane(list3);

JButton button1=new JButton("随机产生");                               //按钮
JButton button2=new JButton("开始");
JButton button3=new JButton("停止");
JButton button4=new JButton("退出");
private JToggleButton togglebutton=new JToggleButton("暂停");
JComboBox []combutton1=new JComboBox[3]; 
JComboBox [] combutton2=new JComboBox[3];//线程选择按钮
JComboBox [] combutton3=new JComboBox[3];//速度选择按钮


 
private	String field1[]=new String[20];                             //接收随机产生的数据
private	String field=new String();                                  //将随机产生数据变为字符串形式
                                           
                                                                 
private JMenuBar bar=new JMenuBar();                                //菜单组件
private JMenu menuFile=new JMenu("文件");
private JMenuItem itemFile1=new JMenuItem("新建");
JMenuItem itemFile2=new JMenuItem("打开");
JMenuItem itemFile3=new JMenuItem("保存");
private  JMenuItem itemFile4=new JMenuItem("另存为");
private  JMenuItem itemFile5=new JMenuItem("退出");
private  JMenu menuTool=new JMenu("工具");
private  JMenuItem itemTool1=new JMenuItem("历史");
private  JMenu menuHelp=new JMenu("帮助");
private  JMenuItem itemAbout1=new JMenuItem("关于");
 private MyActionListener actionlistener;
 private RunThread runthread;  
                                                                //缓冲区类，用于模拟三个缓冲区
/***************************************************************/
 public MultiProgram(){    
	 runthread=new RunThread(5,5,5);
                         //构造函数
	 actionlistener=new MyActionListener(this,runthread);
 }
  
  

/*****************************************************/
public void addMenu(){                                                           //添加菜单组件
	
	 itemFile1.setAccelerator(KeyStroke.getKeyStroke('N',KeyEvent.CTRL_MASK));   //为菜单组件设置快捷键
		itemFile2.setAccelerator(KeyStroke.getKeyStroke('O',KeyEvent.CTRL_MASK));
		itemFile3.setAccelerator(KeyStroke.getKeyStroke('S',KeyEvent.CTRL_MASK));
		itemFile4.setAccelerator(KeyStroke.getKeyStroke('A',KeyEvent.CTRL_MASK));
		itemFile5.setAccelerator(KeyStroke.getKeyStroke('E',KeyEvent.CTRL_MASK));
		itemTool1.setAccelerator(KeyStroke.getKeyStroke('H',KeyEvent.CTRL_MASK));
		itemAbout1.setAccelerator(KeyStroke.getKeyStroke('A',KeyEvent.CTRL_MASK));
		menuFile.add(itemFile1);
		menuFile.add(itemFile2);
		menuFile.add(itemFile3);
		menuFile.add(itemFile4);
		menuFile.addSeparator();
		menuFile.add(itemFile5);
		menuTool.add(itemTool1);
		menuHelp.add(itemAbout1);
		bar.add(menuFile);
		bar.add(menuHelp);
		bar.add(menuTool);

frame.getContentPane().add(bar);
frame.setJMenuBar(bar);
itemFile1.addActionListener(actionlistener);                           //监听菜单事件
itemFile2.addActionListener(actionlistener);
itemFile3.addActionListener(actionlistener);
itemFile4.addActionListener(actionlistener);
itemFile5.addActionListener(actionlistener);
itemAbout1.addActionListener(actionlistener);
itemTool1.addActionListener(actionlistener);

}



@SuppressWarnings({ "rawtypes", "unchecked" })
public void addcomponent(){                                        //添加组件
	 label[0]=new JLabel("数据");
	 for(int i=1;i<=3;i++){
		 label[i]=new JLabel("设置容量");
	 }
	 for(int i=4;i<=6;i++){
		 label[i]=new JLabel("当前数据个数");
	 }
	 for(int i=7;i<=9;i++){
		 label[i]=new JLabel("空闲容量");
	 }
	 for(int i=17;i<=19;i++){
		 label[i]=new JLabel("速度");
	 }
		 label[10]=new JLabel("运行时间");
		 label[11]=new JLabel("已放入数据个数");
		 label[12]=new JLabel("已取出数据个数");
		 label[13]=new JLabel("当前阻塞进程数");
		 label[14]=new JLabel("Put个数");
		 label[15]=new JLabel("Move个数");
		 label[16]=new JLabel("Get个数");
     textfield[0]=new JTextField(12);
	  for(int i=1;i<textfield.length;i++){
		 textfield[i]=new JTextField(2);
	  }
	 textfield[0].addActionListener(actionlistener);          //监听字符输入区事件
  
	int arrays1[]={5,6,7,8,9,10};
	for(int i=0;i<=2;i++){
		combutton1[i]=new JComboBox();
		combutton1[i].addActionListener(actionlistener );     //监听下拉选框事件，设置缓冲区容量
		  //监听下拉选框条款事件
		for(int j=0;j<arrays1.length;j++){
			combutton1[i].addItem(arrays1[j]);
		}
	}
 
	
	int arrays2[]={1,2,3};
	for(int i=0;i<=2;i++){
		combutton2[i]=new JComboBox();
		combutton2[i].addActionListener(actionlistener );    //监听下拉选框事件 设置Put Move Get数量
		
		for(int j=0;j<arrays2.length;j++){
			combutton2[i].addItem(arrays2[j]);
		}
	}
	 
	
	int arrays3[]={1000,2000,3000,4000,5000};
	for(int i=0;i<=2;i++){
		combutton3[i]=new JComboBox();
		combutton3[i].addActionListener(actionlistener );   //监听下拉选框事件 设置线程速度
		
		for(int j=0;j<arrays3.length;j++){
			combutton3[i].addItem(arrays3[j]);
		}
	}
 
	 list1.setVisibleRowCount(6);                         // 设置缓冲区可显示列数
	 list2.setVisibleRowCount(6);
	 list3.setVisibleRowCount(6);
 
    button1.addActionListener(actionlistener);            //监听按钮事件
	button2.addActionListener(actionlistener);
	button3.addActionListener(actionlistener);
	button4.addActionListener(actionlistener);

 
	GroupLayout layout=new GroupLayout(panelmain);       //布局方式，采用GroupLayout布局方式
	panelmain.setLayout(layout);
	layout.setAutoCreateGaps(true);
	/*                               水平分组                                                                  */
	 GroupLayout.SequentialGroup s0=layout.createSequentialGroup();
	s0.addComponent(label[0]).addComponent(button1);
	   s0.addGap(5);
	GroupLayout.ParallelGroup p1=layout.createParallelGroup();
	p1.addComponent(label[1]).addComponent(label[4]).addComponent(label[7]);
	p1.addGap(10);
	GroupLayout.ParallelGroup p2=layout.createParallelGroup();
	p2.addComponent(combutton1[0]).addComponent(textfield[1]).addComponent(textfield[2]);
	GroupLayout.SequentialGroup s1=layout.createSequentialGroup();  
	s1.addGroup(p1).addGroup(p2);
	s1.addGap(10);
	GroupLayout.ParallelGroup p3=layout.createParallelGroup();
	p3.addComponent(label[2]).addComponent(label[5]).addComponent(label[8]);
	p3.addGap(10);
	GroupLayout.ParallelGroup p4=layout.createParallelGroup();
	p4.addComponent(combutton1[1]).addComponent(textfield[3]).addComponent(textfield[4]);
	p4.addGap(10);
	GroupLayout.SequentialGroup s2=layout.createSequentialGroup(); 
	s2.addGroup(p3).addGroup(p4);
	s2.addGap(10);
	GroupLayout.ParallelGroup p5=layout.createParallelGroup( );
	p5.addComponent(label[3]).addComponent(label[6]).addComponent(label[9]);
	p5.addGap(10);
	GroupLayout.ParallelGroup p6=layout.createParallelGroup( );
	p6.addComponent(combutton1[2]).addComponent(textfield[5]).addComponent(textfield[6]);
	p6.addGap(10);
	GroupLayout.SequentialGroup s3=layout.createSequentialGroup(); 
	s3.addGroup(p5).addGroup(p6);
	s3.addGap(10);
	GroupLayout.ParallelGroup p7=layout.createParallelGroup();
    p7.addGroup(s1).addComponent(spane1);
    p7.addGap(10);
    GroupLayout.ParallelGroup p17=layout.createParallelGroup();
    p17.addGroup(s0).addGroup(p7);
    p17.addGap(10);
    GroupLayout.ParallelGroup p8=layout.createParallelGroup();
    p8.addGroup(s2).addComponent(spane2);
    p8.addGap(10);
    GroupLayout.ParallelGroup p9=layout.createParallelGroup();
    p9.addGroup(s3).addComponent(spane3);
    p9.addGap(10);
    GroupLayout.ParallelGroup p10=layout.createParallelGroup();
     p10.addComponent(label[10]).addComponent(label[11])
    .addComponent(button2).addComponent(label[14]).addComponent(label[15])
    .addComponent(label[16]);
    p10.addGap(10);
    GroupLayout.ParallelGroup p11=layout.createParallelGroup();
    p11.addComponent(textfield[7]).addComponent(textfield[8])
    .addComponent(togglebutton).addComponent(combutton2[0])
    .addComponent(combutton2[1]).addComponent(combutton2[2]);
    p11.addGap(10);
    GroupLayout.ParallelGroup p12=layout.createParallelGroup();
    p12.addComponent(label[12]).addComponent(label[13])
    .addComponent(button3).addComponent(label[17])
    .addComponent(label[18]).addComponent(label[19]);
    p12.addGap(10);
    GroupLayout.ParallelGroup p13=layout.createParallelGroup();
    p13.addComponent(textfield[9]).addComponent(textfield[10])
    .addComponent(button4).addComponent(combutton3[0])
    .addComponent(combutton3[1]).addComponent(combutton3[2]);
    p13.addGap(10);
    GroupLayout.SequentialGroup s4=layout.createSequentialGroup();
    s4.addGroup(p8).addGroup(p9).addGroup(p10).addGroup(p11).addGroup(p12).addGroup(p13);
    GroupLayout.ParallelGroup p14=layout.createParallelGroup();
    p14.addComponent(textfield[0]).addGroup(s4);
    layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(p17).addGroup(p14));
    p14.addGap(10);
     /*                               垂直分组                                                                                     */
    GroupLayout.ParallelGroup v1 = layout.createParallelGroup();
    v1.addComponent(label[0]).addComponent(button1).addComponent(textfield[0]);
    GroupLayout.ParallelGroup v2= layout.createParallelGroup();
    v1.addGap(10);
    v2.addComponent(label[1]).addComponent(combutton1[0]).addComponent(label[2])
    .addComponent(combutton1[1]).addComponent(label[3]).addComponent(combutton1[2])
    .addComponent(label[10]).addComponent(textfield[7]).addComponent(label[12])
    .addComponent(textfield[9]);
    v2.addGap(10);
    GroupLayout.ParallelGroup v3= layout.createParallelGroup();
    v3.addComponent(label[4]).addComponent(textfield[1]).addComponent(label[5])
    .addComponent( textfield[3]).addComponent(label[6]).addComponent(textfield[5])
    .addComponent(label[11]).addComponent(textfield[8]).addComponent(label[13])
    .addComponent(textfield[10]);
    v3.addGap(10);
    GroupLayout.ParallelGroup v4= layout.createParallelGroup();
    v4.addComponent(label[7]).addComponent(textfield[2]).addComponent(label[8])
    .addComponent( textfield[4]).addComponent(label[9]).addComponent(textfield[6])
    .addComponent(button2).addComponent(togglebutton).addComponent(button3)
    .addComponent(button4);
    v4.addGap(10);
    GroupLayout.ParallelGroup pr1=layout.createParallelGroup();
    pr1.addComponent(label[14]).addComponent(combutton2[0]).addComponent(label[17])
    .addComponent(combutton3[0]);
    pr1.addGap(10);
    GroupLayout.ParallelGroup pr2=layout.createParallelGroup();
    pr2.addComponent(label[15]).addComponent(combutton2[1] ).addComponent(label[18])
    .addComponent(combutton3[1]);
    pr2.addGap(10);
     GroupLayout.ParallelGroup pr3=layout.createParallelGroup();
    pr3.addComponent(label[16]).addComponent(combutton2[2] ).addComponent(label[19])
    .addComponent(combutton3[2]);
    pr3.addGap(10);
    GroupLayout.SequentialGroup pr4=layout.createSequentialGroup();
    pr4.addGroup(pr1).addGroup(pr2).addGroup(pr3);
    pr4.addGap(20);
     GroupLayout.ParallelGroup v5= layout.createParallelGroup();
    v5.addComponent(spane1).addComponent(spane2)
    .addComponent(spane3).addGroup(pr4);
      layout.setVerticalGroup(layout.createSequentialGroup().addGroup(v1).addGroup(v2)
    		.addGroup(v3).addGroup(v4).addGroup(v5));
      v5.addGap(20);
	 
}
 
public void addPanel(){                                //添加面板
	 addcomponent();
	panelmain.setSize(50, 300);
	panelmain.setBackground(Color.LIGHT_GRAY);
	frame.getContentPane().add(panelmain, BorderLayout.CENTER);
}
 
 


 
private  void showGUI(){                                //显示界面
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setPreferredSize(new Dimension(800,400));
	frame.setLocation(260, 80);
	addMenu();
	addPanel();
	frame.pack();
	frame.setVisible(true);
}

	public static void main(String[] args) {            //主程序，入口程序
		// TODO Auto-generated method stub
MultiProgram multiprogram=new MultiProgram();
multiprogram.showGUI();
	}
	
	}
