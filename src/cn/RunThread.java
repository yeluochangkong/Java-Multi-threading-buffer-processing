package cn;

import java.util.LinkedList;
import  java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunThread {
private LinkedList<String> liststart=new  LinkedList<String>();
private LinkedList<String> listbuffer1=new LinkedList<String>();
private LinkedList<String> listbuffer2=new LinkedList<String>();
private LinkedList<String> listbuffer3=new LinkedList<String>();
private LinkedList<String> listend=new LinkedList<String>();
private int bur1number=5;
private int bur2number=5;
private int bur3number=5;
public static Semaphore empty1=new Semaphore(0,true);
public static Semaphore empty2=new Semaphore(0,true);
public static Semaphore empty3=new Semaphore(0,true);
public static Semaphore full1=new Semaphore(0,true);
public static Semaphore full2=new Semaphore(0,true);
public static Semaphore full3=new Semaphore(0,true);
public static Semaphore bur1=new Semaphore(1,true);
public static Semaphore bur2=new Semaphore(1,true);
public static Semaphore bur3=new Semaphore(1,true);
int startnumber;
 public int GetStartNumber()
 {
	 return startnumber;
 }
 public void SetStartNumber(int a)
 {
	  startnumber=a;
 }
public LinkedList getlistbuffer1(){
	return listbuffer1;
}
public LinkedList getlistbuffer2(){
	return listbuffer2;
}
public LinkedList getlistbuffer3(){
	return listbuffer3;
}
public LinkedList getliststart(){
	return liststart;
}
public LinkedList getlistend(){
	return listend;
}
public void setnumber(int a,int b,int c){
	empty1=new Semaphore(a,true);
	empty2=new Semaphore(b,true);
	empty3=new Semaphore(c,true);
	  bur1number=a;
	  bur2number=b;
	  bur3number=c;
}
public void setliststart(String a){
	String b[]=a.split(" |,");
	for(int i=0;i<b.length;i++){
	liststart.add(b[i]);	
	}
	}
public RunThread(int a,int b,int c){
	empty1=new Semaphore(a,true);
	empty2=new Semaphore(b,true);
	empty3=new Semaphore(c,true);
	  bur1number=a;
	  bur2number=b;
	  bur3number=c;
}
public int getbur1number(){
	return bur1number;
}
public int getbur2number(){
	return bur2number;
}
public int getbur3number(){
	return bur3number;
}
 public Semaphore getempty1(){
	 return empty1;
 }
 public Semaphore getempty2(){
	 return empty2;
 }
 public Semaphore getempty3(){
	 return empty3;
 }
 public Semaphore getfull1(){
	 return full1;
 }
 public Semaphore getfull2(){
	 return full2;
 }
 public Semaphore getfull3(){
	 return full3;
 }
 public Semaphore getbur1(){
	 return bur1;
 }
 public Semaphore getbur2(){
	 return bur2;
 }
 public Semaphore getbur3(){
	 return bur3;
 }
public void put( ){
		 if(liststart!=null)
			listbuffer1.add(liststart.pollFirst());
			 
		}
public void move1 ( ) {
		 
			listbuffer2.add(listbuffer1.pollFirst());
		 
		}

public void move2 ( ) {
 
	listbuffer3.add(listbuffer1.pollFirst());
 

}
public void get1(){
	 
		listend.add(listbuffer2.pollFirst());
	}
public void get2(){
 
	listend.add(listbuffer3.pollFirst());
	}
}
 



 
