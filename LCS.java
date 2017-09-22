import java.util.*;

import java.io.*;

interface longestcs
{
  public String Char(String s);
  public int strmatch(String s1,String s2);
}

class ValInp implements longestcs
{                            					
		public String Char(String str)
		{
			String st="";                         
			for (int i=0;i<str.length();i++ )     //Converts the string into lowercase
		    {					
				if(95<=str.charAt(i) && str.charAt(i)<=122 || str.charAt(i)==32)
				{            
					st=st+str.charAt(i);                               
				}
			}
			return st;                            
		}

		public int strmatch(String str1,String str2)
		{        
			int lcs=0;
			for (int i=0;i<str1.length();i++ )
			{
				for (int j=0;j<str2.length() ;j++ )
				{
					String st="";
					int y=0;
					while(i+y<str1.length() && j+y<str2.length() && str1.charAt(i+y)==str2.charAt(j+y))
					{                            
						st=st+str1.charAt(i);     
						y++;
					}
					if(st.length()>lcs)                       
					{
						lcs=st.length();           //Calculates the length of Longest common Substring
					}	
				}
			}
			return lcs;                  
		}
}
class LCS
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
		      str1=str1.replace("\n"," "); 					//replaces the  nextLine with spaces
		      count=count+1;
		    }
		    s.close();									//Closes the file
		}
		catch(Exception e)
		{
			e.printStackTrace();  //prints stack trace for the object
		}

		return str1;
        }
	public static void main(String[] args)  throws FileNotFoundException 
	{
		File folder=new File(args[0]);
		int fcount=0;

		File[] lf=folder.listFiles();  
												
		File[] f_name=new File[lf.length];
		for (int i=0;i<lf.length ;++i ){
		    File file=lf[i];
			if(file.getName().endsWith(".txt"))
			{			                                 //Files that ends with text are found
			 	f_name[fcount]=file;
				fcount++;								//Filecount is found
			}	
		}
		if(fcount==0){
			System.out.println("Empty Directory");  			 
		}

		for (int i=0;i<fcount;++i )
		{
		 	System.out.print("	        "+f_name[i].getName());		//Column printing of file names
		}
		System.out.println("\n");

		
		for (int i=0;i<fcount;++i)
		{
			System.out.print(f_name[i].getName());            //Row printing of file names
			for (int j=0;j<fcount;++j)
			{
				if(i==j)
				{
					System.out.printf("\t\t"+"%.2f",100.00);
				}
				else
				{
				ValInp l=new ValInp();
				String str3=Fileread(f_name[i].getName());        
				String str4=Fileread(f_name[j].getName());
				String str1=l.Char(str3);          
				String str2=l.Char(str4);
				
				int tlength = str1.length()+str2.length();
				int lstring = l.strmatch(str1,str2);   // Maximum length of the common substring is calculated
				if(lstring*2==tlength)
			    System.out.printf("\t\t"+"%.2f",100.00);
				else
				System.out.printf("\t\t"+"%.2f",(float)(lstring*200)/tlength); //plagiarism percentage is calculated
			  
			    } 
		    }                          
			System.out.println();
		}	
	}	
}
