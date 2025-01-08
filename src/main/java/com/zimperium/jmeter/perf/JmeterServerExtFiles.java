package com.zimperium.jmeter.perf;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Hello world!
 *
 */
public class JmeterServerExtFiles 
{
    public static void main( String[] args ) throws IOException
    {
    	
    	
    	File jmeter_home_txt= new File(args[0] + "/jmeter_home.txt");
    	String jmeter_path = FileUtils.readFileToString(jmeter_home_txt, "UTF-8");    	
    	// Remove trailing line feed (newline) characters
        jmeter_path = StringUtils.stripEnd(jmeter_path, null);
        
     // Define the source and destination directories
        File sourceDir = new File(jmeter_path + "/lib/ext");
        File destinationDir = new File(args[1]);

        // Ensure the destination directory exists
        if (!destinationDir.exists()) {
            destinationDir.mkdirs();
        }

        // Copy files from the source directory to the destination directory
        FileUtils.copyDirectory(sourceDir, destinationDir);

        System.out.println("Files copied successfully from " + sourceDir + " to " + destinationDir);
    	
    }
}
