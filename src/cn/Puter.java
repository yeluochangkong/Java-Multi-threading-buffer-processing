package cn;

public class Puter  extends Thread {
private RunThread run;
private static   boolean shutdown=true; 
private int speed=5000;
public void setspeed(int a){
	speed=a;
}
public void setshutdown(){
	shutdown=false;
}
public Puter(RunThread r){
	 run=r;
}
public void run(){
	
	while(!run.getliststart().isEmpty()&&shutdown) 
	{ 
try{
		run.getempty1().acquire();
		run.getbur1().acquire();
		 
		 
		Thread.sleep((int)(Math.random()*1000));
		run.put();
		run.getfull1().release();
		run.getbur1().release();
	
	}catch(InterruptedException e){
		e.printStackTrace();
	}
      try {
		Thread.sleep(speed);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
		 
}
}
