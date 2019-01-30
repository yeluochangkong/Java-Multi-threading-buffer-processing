package cn;

public class Geter1   extends Thread{
	private static   boolean shutdown=true; 
	private int speed=5000;
	public void setspeed(int a){
		speed=a;
	}
	public void setshutdown(){
		shutdown=false;
	}
	 private RunThread run;
	 public Geter1(RunThread r){
		 run=r;
	 }
		public void run(){
			while(shutdown)
			{
				try{
					run.getfull2().acquire();
					run.getbur2().acquire();
					Thread.sleep((int)(Math.random()*1000));
					run.get1();
					run.getempty2().release();
					run.getbur2().release();
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

