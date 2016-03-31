package ThreadTest02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class CalcTask implements Task {
	//private String path ;
	private HashMap<String,Integer> wordsMap = new HashMap<String,Integer>();
	
	@Override
	public HashMap<String, Integer> execute(File f) {
		// TODO Auto-generated method stub
		File file = new File(f.getAbsolutePath());
		String line = null;
		if(!file.exists()){
			return null;
		}else{
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				try {
					//line = br.readLine();
					
					while((line = br.readLine()) != null){
						line.toLowerCase();//ignore case
						//按照中英文逗号和空格分割行
						String[] words = line.split(",|，|\\s+");
						for(String str :words){
							//只包含字母的字符串才计算
							if (str.matches("^[A-Za-z]+$")){   
								if( wordsMap.containsKey(str)){   //exsits already
									wordsMap.put(str, wordsMap.get(str).intValue()+1);
								}else{
									wordsMap.put(str, 1);
								}
							}											
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			return wordsMap;
		}
	}

}
