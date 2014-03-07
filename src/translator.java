import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.LineNumberReader;
import java.io.FileReader;


public class translator
{
  public static void main (String[]args) throws Exception
  {
String source="",initlang="",targlang="",outfile="";


for(int count=0;count<args.length;count++)
  {
   if(!args[count].startsWith("-")) 
     {
System.out.println("Invalid Syntax");
System.out.println("Usage: java translator [-options]  ");     
System.out.println("	where options include:");
System.out.println("-s\t Source File name");
System.out.println("-i\t Source Language ");
System.out.println("-t\t Target Language");
System.out.println("-o\t Output File");
System.out.println("\tOutput file will be created in the same directory as input file.");
 System.out.println("\tIf -o option is not used output will be created as \"sourcefile_TargetLanguage\" in source file directory. ");
return ;


     }
   //if args[count]==any flag then args[count+1] contains the 
   //value for that flag
   if(args[count].equals("-s")) 
{
	//System.out.println(count+"--"+args.length);
	int l=count+1;
	if(l < args.length)
	source=args[++count];
	else	{
	System.out.println("Specify Input file name after -s");
System.out.println("Usage: java translator [-options]  ");     
System.out.println("	where options include:");
System.out.println("-s\t Source File name");
System.out.println("-i\t Source Language ");
System.out.println("-t\t Target Language");
System.out.println("-o\t Output File");
System.out.println("\tOutput file will be created in the same directory as input file.");
 System.out.println("\tIf -o option is not used output will be created as \"sourcefile_TargetLanguage\" in source file directory. ");
	return ;
	}
	}
   if(args[count].equals("-i")) 
{
	//System.out.println(count+"--"+args.length);
	int l=count+1;
	if(l < args.length)
	initlang=args[++count];
	else	{
	System.out.println("Specify Source Language after -i");
System.out.println("Usage: java translator [-options]  ");     
System.out.println("	where options include:");
System.out.println("-s\t Source File name");
System.out.println("-i\t Source Language ");
System.out.println("-t\t Target Language");
System.out.println("-o\t Output File");
System.out.println("\tOutput file will be created in the same directory as input file.");
 System.out.println("\tIf -o option is not used output will be created as \"sourcefile_TargetLanguage\" in source file directory. ");
	return ;
	}
	}
   if(args[count].equals("-t")) {
	//System.out.println(count+"--"+args.length);
	int l=count+1;
	if(l < args.length)
	{
	targlang=args[++count];
	//System.out.println(targlang);
	}	
	else	{
	System.out.println("Specify Target Language after -t");
System.out.println("Usage: java translator [-options]  ");     
System.out.println("	where options include:");
System.out.println("-s\t Source File name");
System.out.println("-i\t Source Language ");
System.out.println("-t\t Target Language");
System.out.println("-o\t Output File");
System.out.println("\tOutput file will be created in the same directory as input file.");
 System.out.println("\tIf -o option is not used output will be created as \"sourcefile_TargetLanguage\" in source file directory. ");
	return ;
	}
	}
   if(args[count].equals("-o")) 
	{
	//System.out.println(count+"--"+args.length);
	int l=count+1;
	if(l < args.length)
	outfile=args[++count];
	else	{
	System.out.println("Specify Output file name after -o");
System.out.println("Usage: java translator [-options]  ");     
System.out.println("	where options include:");
System.out.println("-s\t Source File name");
System.out.println("-i\t Source Language ");
System.out.println("-t\t Target Language");
System.out.println("-o\t Output File");
System.out.println("\tOutput file will be created in the same directory as input file.");
 System.out.println("\tIf -o option is not used output will be created as \"sourcefile_TargetLanguage\" in source file directory. ");
	return ;
	}
	}

  }


 translate_string check = new translate_string ();
//System.out.println(check.check_invalid(initlang.toUpperCase()));
if(source=="" ||initlang==""||targlang=="")
{
System.out.println("File : " + source +" to be Converted from " + initlang + " to " +targlang );
System.out.println("Invalid Number of Arguments");
System.out.println("Usage: java translator [-options]  ");     
System.out.println("	where options include:");
System.out.println("-s\t Source File name");
System.out.println("-i\t Source Language ");
System.out.println("-t\t Target Language");
System.out.println("-o\t Output File");
System.out.println("\tOutput file will be created in the same directory as input file.");
 System.out.println("\tIf -o option is not used output will be created as \"sourcefile_TargetLanguage\" in source file directory. ");
return ;
}
if(check.check_invalid(initlang.toUpperCase())==0)
{
System.out.println("Invalid Initial Language");
System.out.println("See Languges.txt for list of Supported Languages");
return ;
}
if(check.check_invalid(targlang.toUpperCase())==0)
{
System.out.println("Invalid Target Language");
System.out.println("See Languges.txt for list of Supported Languages");
return ;
}
initlang=initlang.toUpperCase();
targlang=targlang.toUpperCase();
if(outfile=="")
{

outfile=source+"_"+targlang;
}
else{
File file12 = new File(source);

File parentDir = file12.getParentFile(); // to get the parent dir 
String parentDirName = file12.getParent(); // to get the parent dir name

outfile=parentDirName+"/"+outfile;

}
System.out.println("File : " + source +" to be Converted from " + initlang + " to " +targlang );
    convert_localizablestring (source, initlang,targlang,outfile);



  }
  public static void convert_localizablestring (String fname, String lang,String dest,String outfile)
  {
    translate_string call = new translate_string ();
      try
    {

LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(fname)));
lnr.skip(Long.MAX_VALUE);
int filelinenumbers=lnr.getLineNumber();
System.out.println("Lines in input File : "+ lnr.getLineNumber());




      String filepath = outfile;
		System.out.println("Output will be written to File : " + filepath );
      File fileDir = new File (filepath);
      final File parent_directory = fileDir.getParentFile ();
      if (null != parent_directory)
	{
	  parent_directory.mkdirs ();
	}

      Writer out =
	new BufferedWriter (new
			    OutputStreamWriter (new
						FileOutputStream (fileDir),
						"UTF8"));
      try
      {
	FileInputStream fis = new FileInputStream (fname);
	Scanner scanner = new Scanner (fis);
	int count=1;

	while (scanner.hasNextLine ())
	  {

		System.out.print("Lines : "+ count +"/"+filelinenumbers +"\tProgress = "+count*100/filelinenumbers +"% \r\r" );

		count++;
	    String expression = scanner.nextLine ();
	    String[]tokens = expression.split (Pattern.quote ("="));
		
	    out.append (tokens[0]);
	    if (tokens.length >= 2)
	      {
		String rofl=call.trans_string (tokens[1], lang,dest);
				out.append ("=" +rofl).append ("\r\n");
	      }
	else
	{
	out.append("\r\n");
	}


	  }
	out.flush ();
	out.close ();
	scanner.close ();
      }
      catch (Exception e)
      {				//Catch exception if any
	System.err.println ("Error: " + e.getMessage ());
      }

    }
    catch (UnsupportedEncodingException e)
    {
      System.out.println (e.getMessage ());
    }
    catch (IOException e)
    {
      System.out.println (e.getMessage ());
    }
    catch (Exception e)
    {
      System.out.println (e.getMessage ());
    }


  }
}
