package ThreadTest02;

import java.util.LinkedList;
import java.util.List;

public class TaskQueue {
	
	private List queue = new LinkedList();
	public synchronized Task getTask(){
		while(queue.size() == 0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return (Task)queue.remove(0);
	}
	
	public synchronized void putTask(Task task){
		queue.add(task);
		this.notifyAll();
	}
}
