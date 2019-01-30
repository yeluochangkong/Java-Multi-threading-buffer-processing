package cn;

import java.util.TimerTask;

class Timerflash extends TimerTask {
	private RunThread run1;
	private MultiProgram mul;
    public Timerflash(RunThread r,MultiProgram m){
		run1=r;
        mul=m;
	}
public void run(){
	paint();
}

@SuppressWarnings("unchecked")
public void  paint(){
	/************************向缓冲区添加内容******************************/
   String s1[]=new String[100];
   run1.getlistbuffer1().toArray(s1);
	 mul.list1.setListData(s1);
	 
	 String s2[]=new String[100];
	 run1.getlistbuffer2().toArray(s2);
	 mul.list2.setListData(s2);
	 String s3[]=new String[100];
	run1.getlistbuffer3().toArray(s3);
	 mul.list3.setListData(s3);
	  
	 /****************************显示*************************************/
	mul.textfield[1].setText(String.valueOf(run1.getlistbuffer1().size()));
	mul.textfield[3].setText(String.valueOf(run1.getlistbuffer2().size()));
	mul.textfield[5].setText(String.valueOf(run1.getlistbuffer3().size()));
    mul.textfield[2].setText(String.valueOf(run1.getbur1number()-run1.getlistbuffer1().size()));
	mul.textfield[4].setText(String.valueOf(run1.getbur2number()-run1.getlistbuffer2().size()));
	mul.textfield[6].setText(String.valueOf(run1.getbur3number()-run1.getlistbuffer3().size()));
	mul.textfield[8].setText(String.valueOf(run1.GetStartNumber()-run1.getliststart().size()));
    mul.textfield[9].setText(String.valueOf(run1.getlistend().size()));
}	
}