package ThreadTest02;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class WorderThread extends Thread {
	private static int count = 0;
	private boolean busy = false;
	private boolean stop = false;
	private TaskQueue queue;
	private List<File> fileList;
	private HashMap<String,Integer> hMap,maps ;
	
	public WorderThread(ThreadGroup group, TaskQueue queue, List<File> fileList){
		super(group,"worker-"+count);
		count++;
		this.queue = queue;
		this.fileList = fileList;
		//this.maps = (HashMap<String, Integer>) maps;
	}
	public void shutDown(){
		stop = true;
		this.interrupt();
		try {
			this.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  boolean isIdle(){
		return !busy;
	}
	public  void run(){
		
		//System.out.println(getName() + "start..");
		maps = MainTest.maps;
		//System.out.println(maps+this.getName());
		while(!stop){
			Task task = queue.getTask();
	
			if (task!=null){
				busy = true;
				//System.out.println("filelist: "+fileList);
				synchronized(this){
				for(Iterator<File> it = this.fileList.iterator();it.hasNext();){
					File file = (File)it.next();
					hMap = task.execute(file);
				    //maps.putAll(hMap);
					Iterator it0 = maps.keySet().iterator();
					while(it0.hasNext()){
						String key1 = (String) it0.next();//mapsµÄkeyÖµ
						if(hMap.containsKey(key1)){
							
							int v1 = maps.get(key1);
							int v2 = hMap.get(key1);
							//System.out.println(key1+"--" + "v1:"+ v1+ "v2: "+v2);
							maps.put(key1, v1+v2);
							hMap.remove(key1);
						}
					}
					if(hMap != null){
						//System.out.println("maps: "+ maps+ this.getName());
						maps.putAll(hMap);
						//System.out.println(hMap+"----" +this.getName());
						hMap.clear();
					}
				}
				
			}
				//task.execute();
				busy = false;
			}
		}
		//System.out.println(getName() + "end");
			
	}
}
