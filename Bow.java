import java.util.*;

import java.io.*;

interface wordblocks
{
	public int freq(String[] str3);
}

class Bag implements wordblocks
{
	HashMap<String,Integer> hm=new HashMap<String,Integer>();
	public int freq(String[] str3)            
	{
      for(String str:str3)                      			
	  {
	  	if(hm.containsKey(str))           					
	  	{
	  		hm.put(str,hm.get(str)+1);      						//Frequency of the words is calculated  				
	  	}
	  	else
	  	{
	  		hm.put(str,1);                        			
	  	}
	  }
	  int x=0;
	  for(Map.Entry<String,Integer> map:hm.entrySet())    
	  {
	  	String key=map.getKey();         						
	  	int value=map.getValue();          					
	  	x=x+(int)(Math.pow(value,2));       			//sum of squares of frequencies is calculated
	  }
	  return x;
	}
}
class Bow
{
		public static String Fileread(String str)throws FileNotFoundException
		{
		int count=0;
		File  file = new File(str);
		String str1="";
		try                                
		{
			Scanner s= new Scanner(file);
		    while(s.hasNextLine())
		    {
		      str1+=s.nextLine();
		      str1=str1.replace("\n"," "); 					
		      count=count+1;
		    }
		    s.close();
		}
		catch(Exception e)
		{
		    e.printStackTrace();  // prints stack trace for the object
		}

		return str1;
        }
	public static void main(String[] args)  throws FileNotFoundException
	{
		File folder=new File(args[0]);					// File path is created
		int fcount=0;

		File[] lf=folder.listFiles();  		
												
		File[] f_name=new File[lf.length];		
		for (int i=0;i<lf.length ;++i )
		 {                                   				
			File file=lf[i];
			if(file.getName().endsWith(".txt"))
			{                                     		//Searches for the files that ends with txt
				f_name[fcount]=file;
				fcount++;
			}	
		 }
		if(fcount==0)
		{
			System.out.println("Directory is Empty");    
		}

		for (int i=0;i<fcount;++i )
		 {
			System.out.print("	       "+f_name[i].getName());		//File names are printed coulmn wise
		 }
		System.out.println("\n");

		for (int j=0;j<fcount;++j ) 
		{
			System.out.print(f_name[j].getName());         //File names are printed row wise
			for (int k=0;k<fcount;++k) 
			{
			  Bag b=new Bag();       				
			  Bag b1=new Bag();
			  String str1=Fileread(f_name[j].getName());    
			  String str2=Fileread(f_name[k].getName());
			  String[] str3=str1.split(" ");                 
			  String[] str4=str2.split(" ");
			  int x1=b.freq(str3);       //Frequency is found for the string array    
			  int x2=b1.freq(str4);
			  int dot_product=0;
			  for(String key1: b.hm.keySet())
			  {
		        for(String key2: b1.hm.keySet())
		        {
			       if(key1.equals(key2))        
			       {
		                dot_product = dot_product+(b.hm.get(key1)*b1.hm.get(key2)); 
			       }
			     }
			   }
			   double result=dot_product/(Math.sqrt(x1)*Math.sqrt(x2)); //plagiarism percentage is calculated
			   System.out.printf("\t\t"+"%.2f",result*100);    
	        }
	        System.out.println();
        }
}


}