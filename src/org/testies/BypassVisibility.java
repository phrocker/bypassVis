package org.testies;
import java.io.IOException;

import org.apache.accumulo.core.file.FileSKVIterator;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


public class BypassVisibility {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// this example could be extended to read the meta data table to get ranges
		// like the scanner does. i have code to do that somewhere around here. i'll add it later.
		
		Configuration conf = new Configuration();
		
		Path path = new Path( args[0] );
		FileSystem fs = path.getFileSystem(conf);
		FileSKVIterator rf = BypassOperations.openReader(args[0], true, fs, conf, null);
		while(rf.hasTop())
		{
			System.out.println( rf.getTopKey() );
			rf.next();
		}
	}

}
