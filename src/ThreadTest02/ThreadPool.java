package ThreadTest02;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ThreadPool extends ThreadGroup {
	private List threads = new LinkedList();
	private TaskQueue queue;
	private List<File> fileList;
	public ThreadPool(TaskQueue queue){
		super("thread-Pool");
		this.queue = queue;
	}
	public synchronized void addWorderThread(List<File> fileList){
		Thread t = new WorderThread(this,queue,fileList);
		threads.add(t);
		t.start();
	}
	public synchronized void removeWorderThread(){
		if(threads.size() > 0){
			WorderThread t = (WorderThread)threads.remove(0);
			t.shutDown();
		}
	}
	
	public synchronized void currentStatus(){
		System.out.println("-----------------------------");
		System.out.println("Thread count = " + threads.size());
		Iterator it = threads.iterator();
		while(it.hasNext()){
			WorderThread t = (WorderThread)it.next();
			//System.out.println(t.getName() + ":" + (t.isIdle()?"idle":"busy"));
			if(t.isIdle()){
				t.shutDown();
			}else{
				MainTest.udFlag = false;
			}
		}
		System.out.println("-----------------------------");
	}
}
