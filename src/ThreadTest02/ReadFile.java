package ThreadTest02;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
private List<File> txtFiles = new ArrayList<File>();
	

	public  static boolean getFiles(String iniPath,String fileType,List<File> txtFiles){
		
		String fileName;
		
		File iniFile = new File(iniPath);   //the initial file
		if(!iniFile.exists()){
			System.out.println("����·��" + iniPath + "����");
			return false;			
		}else{
			if(iniFile.isDirectory()){  //initial path reffers to a folder
				String[] fileList = iniFile.list();
				
				for(int i = 0; i<fileList.length; i++){
					File nextFile = new File(iniFile + File.separator + fileList[i]);
					if (nextFile.isDirectory()){
						getFiles(iniFile + File.separator + fileList[i],fileType,txtFiles);
					}else{
						fileName = nextFile.getName();
						if(fileName.endsWith(fileType)){    //decide is txt file or not
							try{
								txtFiles.add(nextFile.getAbsoluteFile());
							}catch(SecurityException e){
								e.printStackTrace();
							}							
						}
					}
				}
			}else{  //initial path reffers to a file
				if(iniFile.getName().endsWith(fileType)){
					
				}else{
					System.out.println("�ļ�·����ָ�ļ�Ϊ���ı��ĵ���");
				}
			}
		}
		return true;
	}
}
