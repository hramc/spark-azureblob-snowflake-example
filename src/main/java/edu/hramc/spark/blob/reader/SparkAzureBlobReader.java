package edu.hramc.spark.blob.reader;

import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

/**
 * 
 * @author hramc
 * 
 * This class is used to read azure blob data 
 *
 */
public class SparkAzureBlobReader {

	public static Dataset<Row> readData(SparkSession spark,FileSystem blobFileSystem,String folderPath){
		
		Dataset<Row> blobData = null;
		try {
			
			// ListStatus method will list all the blobs inside the Path
			for(FileStatus f:blobFileSystem.listStatus(new Path(folderPath))){
				
				// Code used to add the batch the Dataset
				if(blobData==null)
					blobData = spark.read().text(f.getPath().toString());
				else	
					blobData = blobData.union(spark.read().text(f.getPath().toString()));
								
					break;
			}
			

			
		}catch(Exception e){
			e.printStackTrace();
		}
				
		return blobData;
	}
	
	
}
