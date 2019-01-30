package cn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Timer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

class MyActionListener  implements ActionListener {
private	 Puter put[]=new Puter[10];
private		  Mover1 move1[]=new Mover1[10];
private		  Geter1 get1[]=new Geter1[10];
private		  int a1;
private		  int a2;
private		  int a3;
private MultiProgram mul;
private RunThread run1;
private String field;

 
public MyActionListener( MultiProgram m,RunThread r){
	mul=m;
	run1=r;
   
}

public void actionPerformed(ActionEvent e){
	
	if(e.getSource()==mul.textfield[0]){
				  field=mul.textfield[0].getText();
				 if(field!=null){
					run1.setliststart(field);
					 run1.SetStartNumber(run1.getliststart().size());
				 }
					 
			}
	
	
			else if(e.getSource()==mul.button1){//随机产生按钮
			
				 
					String str="abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
					Random random=new Random();
					StringBuffer sb = new StringBuffer();
					for (int j = 0; j < 20; ++j) {
						int number = random.nextInt(52);
						sb.append(str.charAt(number));
						sb.append(" ");
					}
					field=sb.toString();
			     run1.setliststart(field);	//给初始数组数据	
			     run1.SetStartNumber(run1.getliststart().size());
				 mul.textfield[0].setText(field);	//在数据框显示
				 
			}
	
	
	
			else if(e.getSource()==mul.button2){  //开始按钮
				 if(run1.getliststart().isEmpty()){
					JOptionPane.showMessageDialog(null,"您需要输入数据");
				 }
				 else{
					  int a= Integer.parseInt(mul.combutton1[0].getSelectedItem().toString());//获取buffer1容量
					  int b= Integer.parseInt(mul.combutton1[1].getSelectedItem().toString());//获取buffer2容量
					  int c= Integer.parseInt(mul.combutton1[2].getSelectedItem().toString());//获取buffer3容量
					  run1.setnumber(a, b, c);
					  Timer timer=new Timer();
					  timer.schedule(new Timerflash(run1,mul),10,1*100);
					
					  a1=Integer.parseInt(mul.combutton2[0].getSelectedItem().toString());    //获取Put数量
					  a2=Integer.parseInt(mul.combutton2[0].getSelectedItem().toString());    //获取Move数量
					  a3=Integer.parseInt(mul.combutton2[0].getSelectedItem().toString());    //获取Get数量
					 
					for(int i=0;i<a1;i++){
						 put[i]=new Puter(run1);
						 int putspeed=0;
						 putspeed=6000- Integer.parseInt(mul.combutton3[0].getSelectedItem().toString());
						 put[i].setspeed(putspeed);
					     put[i].start();
						 
					}
					for(int i=0;i<a1;i++){
						 move1[i]=new Mover1(run1);
						 int move1speed=0;
						 move1speed=6000- Integer.parseInt(mul.combutton3[1].getSelectedItem().toString());
						 move1[i].setspeed(move1speed);
						 move1[i].start();
						 }
					for(int i=0;i<a1;i++){
						 get1[i]=new Geter1(run1);
						 int get1speed=0;
						 get1speed=6000- Integer.parseInt(mul.combutton3[2].getSelectedItem().toString());
						 move1[i].setspeed(get1speed);
						 get1[i].start();
						 }
					   Mover2 m2=new Mover2(run1);
					   Geter2 g2=new Geter2(run1);
					   m2.start();
					   g2.start();
			       
		 }
				  
			} 
			else if(e.getSource()==mul.button3){   //停止
				
          
          for(int i=0;i<a1;i++){
          put[i].setshutdown();
				 }
          for(int i=0;i<a2;i++){
          move1[i].setshutdown();
    }
         for(int i=0;i<a3;i++) {
         get1[i].setshutdown();
										  
     }  
			}
	
	
	
         else if(e.getSource()==mul.button4){     //退出
             for(int i=0;i<a1;i++){
                 put[i].setshutdown();
       				 }
                 for(int i=0;i<a2;i++){
                 move1[i].setshutdown();
           }
                for(int i=0;i<a3;i++) {
                get1[i].setshutdown();
       										  
            }  
        	 run1.getliststart().clear();
             run1.getlistbuffer1().clear();
             run1.getlistbuffer2().clear();
             run1.getlistbuffer3().clear();
             run1.getlistend().clear();
             
         }
        // m2.setshutdown();
	      // g2.setshutdown();
								 
				   
	 
	
	
	
				 else if(e.getSource()==mul.itemFile2){
					  File fs=new File("G:/java.workplace1/MultiProgram/test1.txt");
					  BufferedInputStream in=null;
					  byte[] buf=null;
					  try {
						in=new BufferedInputStream(new FileInputStream(fs));
						buf=new byte[in.available()];
						in.read(buf);
						 field= new String(String.valueOf((char)buf[0]));
						 for(int i=1;i<buf.length;i++){
							 field+=" ";
							 field+=(char)buf[i];
						 }
						 run1.setliststart(field);	//给初始数组数据	
					     run1.SetStartNumber(run1.getliststart().size());
						 mul.textfield[0].setText(field);	//在数据框显示
						 
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					  finally{
						  try {
							in.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					  }
					 
				 
					 
					
				 }		 
				 
	}
	
 }

					   
					  
				 
			
		
	
		