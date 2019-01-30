package cn;

public class Mover2  extends Thread{
private RunThread run;
private static   boolean shutdown=true; 
private int speed=5000;
public void setspeed(int a){
	speed=a;
}
public void setshutdown(){
	shutdown=false;
}
public Mover2(RunThread r){
	 run=r;
}
	public void run(){
		while(shutdown)
		{
			try{
				run.getfull1().acquire();
				run.getempty3().acquire();
				run.getbur1().acquire();
				run.getbur3().acquire();
			 
				Thread.sleep((int)(Math.random()*1000));
				run.move2();
				
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			run.getempty1().release();
			run.getbur1().release();
			run.getfull3().release();
			run.getbur3().release();
			
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

