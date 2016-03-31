package ThreadTest02;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author WenxinChen
 *1.文件默认按照行读取
 *2.没有设置多线程读单个文件
 *3.多线程控制还有问题
 *4.问题：递归获取文件要不要考虑进去，各个线程按照什么方式读取文件（分开文件容器还是多个线程读一个文件）
 */

public class MainTest {
	public static HashMap<String,Integer> maps  = new HashMap<String,Integer>();
	public static boolean udFlag = true;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		TaskQueue queue = new TaskQueue();
		ThreadPool pool = new ThreadPool(queue);
		List<File> files = new ArrayList<File>();
		String path = null;
		String type = ".txt";
		boolean flag ;
		int num = 1;//线程数目
		int size;//文件个数
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入文件夹的绝对路径：");
		path = sc.nextLine();
		System.out.println("请输入线程数：");
		try{
			num = sc.nextInt();		
		}catch(InputMismatchException e){
			System.out.println("输入为非整数！");
			return;
		}

		flag = ReadFile.getFiles(path, type, files);
		if(flag){
			size = files.size();
			//文件数比输入线程数小
			if(size<num){
				System.out.println("文件数目为： "+size+"，所输入线程数较多，将重设置");
				num = size;
			}
			for(int i =0 ; i<num; i++){
				List<File> fileList = new LinkedList<File>();
				fileList = getFileList(fileList,files,i,size,num);
				//System.out.println(fileList);
				queue.putTask(new CalcTask());
				pool.addWorderThread(fileList);
			}
			
			doSleep(8000);
			System.out.println(maps);
		}		
	}
	private static List getFileList(List<File> fileList, List<File> files, int i, int size, int num) {
		// TODO Auto-generated method stub
		if (i<(num -1)){
	    	for(int j = i; j<i+(size/num); j++){
	    		fileList.add(files.get(j));	    		
	    	}
	    }else{
	    	for(int j =i; j<i+(size/num + size%num); j++){
	    		fileList.add(files.get(j));
	    	}
	    }
		//System.out.println(fileList);
		return fileList;
	}
	
	private static void doSleep(long ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
