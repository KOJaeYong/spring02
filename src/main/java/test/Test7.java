package test;

import java.io.File;

public class Test7 {
	public static void main(String[] args) {
		File dir=new File("d:\\");
		File[] fileList=dir.listFiles();
		for(File f : fileList){
			if( f.isDirectory() ) {
				System.out.println("디렉토리:"+ f.getName());
			}else if(f.isFile()){
				System.out.println("파일:"+
						f.getName());
			}
		}
	}
}
