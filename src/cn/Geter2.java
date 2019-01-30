package cn;

public class Geter2   extends Thread{
private RunThread run;
private static   boolean shutdown=true; 
private int speed=5000;
public void setspeed(int a){
	speed=a;
}
public void setshutdown(){
	shutdown=false;
}
 public Geter2(RunThread r){
			 run=r;
		 }
		public void run(){
			
			while(shutdown)
			{
				try{
					run.getfull3().acquire();
					run.getbur3().acquire();
					 
					Thread.sleep((int)(Math.random()*1000));
					run.get2();
					run.getempty3().release();
					run.getbur3().release();
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

