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
 *1.�ļ�Ĭ�ϰ����ж�ȡ
 *2.û�����ö��̶߳������ļ�
 *3.���߳̿��ƻ�������
 *4.���⣺�ݹ��ȡ�ļ�Ҫ��Ҫ���ǽ�ȥ�������̰߳���ʲô��ʽ��ȡ�ļ����ֿ��ļ��������Ƕ���̶߳�һ���ļ���
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
		int num = 1;//�߳���Ŀ
		int size;//�ļ�����
		Scanner sc = new Scanner(System.in);
		System.out.println("�������ļ��еľ���·����");
		path = sc.nextLine();
		System.out.println("�������߳�����");
		try{
			num = sc.nextInt();		
		}catch(InputMismatchException e){
			System.out.println("����Ϊ��������");
			return;
		}

		flag = ReadFile.getFiles(path, type, files);
		if(flag){
			size = files.size();
			//�ļ����������߳���С
			if(size<num){
				System.out.println("�ļ���ĿΪ�� "+size+"���������߳����϶࣬��������");
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
