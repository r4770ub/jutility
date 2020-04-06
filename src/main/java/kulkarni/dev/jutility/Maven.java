package kulkarni.dev.jutility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Maven{


	public static final String COPY = "cp ";
	public static final String REMOVE = "rm -rf ";
	public static final String MKDIR = "mkdir ";
	public static final String LIB_DIRECTORY = "/home/r4770/dev/java/lib/";

	public static final String JAR_BASE = "/home/r4770/.m2/repository/dev/utility/ ";
	public static final String JBASE_JAR = "/home/r4770/.m2/repository/dev/utility/jbase/v1/jbase-v1.jar ";
	public static final String JFILE_JAR = "/home/r4770/.m2/repository/dev/utility/jfile/v1/jfile-v1.jar ";
	public static final String JIMAGE_JAR = "/home/r4770/.m2/repository/dev/utility/jimage/v1/jimage-v1.jar ";
	public static final String JSYSTEM_JAR = "/home/r4770/.m2/repository/dev/utility/jsystem/v1/jsystem-v1.jar ";
	public static final String JMEDIA_JAR = "/home/r4770/.m2/repository/dev/utility/jmedia/v1/jmedia-v1.jar ";
	public static final String JGUI_JAR = "/home/r4770/.m2/repository/dev/utility/jgui/v1/jgui-v1.jar ";
	public static final String PROJECT_BASE = "/home/r4770/dev/eclipsejprojects/java";
	
	private static BufferedReader stdError;
	private static BufferedReader stdInput;
	private static Process process;
	private static Runtime runtimeEnviroment;
	private static String cmd ="/home/r4770/dev/java/eclipsejprojects/jutility/ ";


	public static void main(String[] args) throws IOException, InterruptedException 
	{
		deploy();
		String type = "";
			if(args.length ==1)
			{
				 type = args[0];
			}
			else if(args.length == 2)
			{
 				type = args[0] + " " + args[1]; 
			}
			else if(args.length == 3)
			{
				type = args[0] +" "+ args[1] +" "+ args[2];
			}
 			System.out.println("Input argument is:" + type);
 			
			 if (type.equals("clean install deploy")) 
			 {
				 System.out.println("Calling to clean install and deploy");
				clean();
				install();
				deploy();
			} else if (type.equals("clean install")) 
			{
				 System.out.println("Calling to clean and install ");
				clean();
				install();
			} 
			else if (type.equals("clean")) 
			{
				 System.out.println("Calling to clean");
				 clean();
			} else if (type.equals("install")) {
				 System.out.println("Calling to  install");

				install();
			} else if (type.equals("deploy")) {
				
				 System.out.println("Calling to deploy");
				deploy();
			} else {
				System.out.println("invalid option");
				System.exit(1);
			}
		
	}

	public static void deploy() throws IOException, InterruptedException {
		System.out.println("mvn: Clean and deploy");

		System.out.println("Removing Utility Jre Library");
		exec(REMOVE + LIB_DIRECTORY);
		System.out.println("Creating Utility Library");
		exec(MKDIR + LIB_DIRECTORY);
		System.out.println("Adding JBASE");
		exec(COPY + JBASE_JAR + LIB_DIRECTORY);
		System.out.println("Adding JFILE");
		exec(COPY + JFILE_JAR + LIB_DIRECTORY);
		System.out.println("Adding JIMAGE");
		exec(COPY + JIMAGE_JAR + LIB_DIRECTORY);
		System.out.println("Adding JGUI");
		exec(COPY + JGUI_JAR + LIB_DIRECTORY);
		System.out.println("Adding JSYSTEM");
		exec(COPY + JSYSTEM_JAR + LIB_DIRECTORY);
		System.out.println("Adding MEDIA");
		exec(COPY + JMEDIA_JAR + LIB_DIRECTORY);
		System.out.println("Adding JBASE");
		System.out.println("Library Generating refresh projects");

	}

	public static void install() throws IOException {
		exec("mvn -f install" + cmd);
	}

	public static void clean() throws IOException {
		exec("mvn -f  clean " +cmd);
	}
	
	private static  void printLog(final String log) 
	{
		Thread thread = new Thread(new Runnable() 
		{
			public void run() 
			{
				System.out.println(log);

			} 
     
		});
		thread.start();

	}

	protected static void exec(String cmd) throws IOException 
	{
    
		runtimeEnviroment = Runtime.getRuntime();

		try 
		{
			process = runtimeEnviroment.exec(cmd);

			stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
			stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

	
			String s = null;
			while ((s = stdInput.readLine()) != null) 
			{
 
				printLog("\t" + s);

			}
			// read any errors from the attempted command
			while ((s = stdError.readLine()) != null) 
			{

				printLog(s);
			}


		}

		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}}

