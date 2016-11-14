import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class detect {
	public static int[] Character_counter(String str){
		  
	    char[] chars=str.toCharArray(); 
	    int Chinese_counter = 0;
	    int English_counter = 0;
	    int Other_counter = 0;
	    int FirstByte;
	    String a;
	    //int num = Integer.parseInt("C", 16); // num == 12
	    try{
	    for(int i=0;i<chars.length;i++)
	    {
	    	//if(i>=chars.length) break;
	        byte[] bytes=(" "+chars[i]).getBytes("UTF-8");
	        
	        String hex = Integer.toHexString((int) bytes[1] & 0xff);
	        FirstByte = Integer.parseInt(hex,16) ;
	        if((65 <= FirstByte && FirstByte <= 90 )||( 97 <= FirstByte&&FirstByte <= 122)){
	        	English_counter++;
	        }
	       else if(224 < FirstByte && FirstByte < 240){
	        	Chinese_counter++;
	        	//i = i+2;
	        }
	        else{
	        	Other_counter++;
	        	//i++;
	        }
	      
	       
	        // Finish these part to detect the number of characters in different languages
	    } }
	    catch (Exception e) {e.printStackTrace();} 
	    int[] counter = {Chinese_counter,English_counter,Other_counter};
	    return counter; 
	}

	public static boolean isUTF8(FileInputStream fs) throws Exception 
	{
	    if (fs.read() == 0xEF && fs.read() == 0xBB && fs.read() == 0xBF)
	    {
	    	System.out.println("The file is encoded with UTF-8.");
	        return true;
	    }
	    System.out.println("File encoding is not correct");
	    return false;
	}

	public static void main(String args[])
	{
	    int[] sumcounter = {0,0,0};
	    int[] counter = new int[3];
	    File file = new File("D:\\Program Files (x86)\\Eclipse\\CCLab1-detect\\src\\dialogue_ex2.txt");
	    try 
	    { 
	        FileInputStream fis = new FileInputStream(file);
	        String s;
	        if(isUTF8(fis)){
	            InputStreamReader isr = new InputStreamReader(fis,"UTF-8"); 
	            BufferedReader br = new BufferedReader(isr); 
	            while ((s = br.readLine()) != null) {// read a line of data
	                counter = Character_counter(s);
	                for(int j=0;j<3;j++)
	                	sumcounter[j]+=counter[j];
	            }
	            br.close();
	            isr.close();
	        }
	        
	        	System.out.println("Chinese charater: "+ sumcounter[0]);
	        	System.out.println("English charater: "+ sumcounter[1]);
	        	System.out.println("Other charater: "+ sumcounter[2]);
	        	
	    }	    
	    catch (Exception e) {e.printStackTrace();} 
	}


	
}

