package cn;

public class Mover1  extends Thread {
	  private RunThread run;
	 private static   boolean shutdown=true; 
	 private int speed=5000;
	 public void setspeed(int a){
	 	speed=a;
	 }
	  public void setshutdown(){
			shutdown=false;
		}
	  public Mover1(RunThread r){
			 run=r;
		 }
		public void run(){
			
			while(shutdown)
			{
				try{
					run.getfull1().acquire();
					run.getempty2().acquire();
					run.getbur1().acquire();
					run.getbur2().acquire();
					 
					Thread.sleep((int)(Math.random()*1000));
					run.move1();
					run.getempty1().release();
					run.getbur1().release();
					run.getfull2().release();
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

