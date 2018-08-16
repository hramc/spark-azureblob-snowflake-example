package edu.hramc.application;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

import com.google.common.collect.Maps;

import edu.hramc.helper.Constants;
import edu.hramc.spark.blob.reader.SparkAzureBlobReader;

/**
 * 
 * This application used to read data from the Azure blob and insert into snowflake data warehouse
 * 
 * @author hramc
 *
 */
public class SparkApplication {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		// Create a local spark session
		SparkSession spark = SparkSession
                .builder()
                .appName("Spark Azure Blob Snwoflake Example")
                .master("local")
                .getOrCreate();
		
		// Set the Azure Blob credentials in the hadoop configuration
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", Constants.AZURE_BLOB_URL);
		conf.set(Constants.AZURE_ACCOUNT_NAME , Constants.AZURE_ACCOUNT_KEY);
		
		// Load the Azure blob in the hadoop fileSystem
		FileSystem blobFileSystem = FileSystem.get(conf);

	
		// Read data from Azure blob and insert into Snowflake
		Dataset<Row> blobData = SparkAzureBlobReader.readData(spark,blobFileSystem,Constants.AZURE_BLOB_PATH);
		
		// Insert into Snowflake table (we are adding createdtime column as a bookmark information)
		
		blobData.withColumn("createdtime", functions.lit(new Timestamp(Calendar.getInstance().getTime().getTime())))
        .write().format(Constants.SNOWFLAKE_FORMAT).options(getSfOptions()).option("dbtable", Constants.SNOWFLAKE_TABLENAME).mode(SaveMode.Append).save();

		
		spark.stop();
		spark.close();	

	}
	
	/**
	 * Method used to get all the snowflake credentials
	 * 
	 * @return Map
	 */
	public static Map<String, String> getSfOptions() {
        Map<String, String> sfOptions = Maps.newHashMap();
       
        sfOptions.put("sfURL", Constants.SNOWFLAKE_URL);
        sfOptions.put("sfUser", Constants.SNOWFLAKE_USER);
        sfOptions.put("sfPassword", Constants.SNOWFLAKE_PASSWORD);
        sfOptions.put("sfAccount",Constants.SNOWFLAKE_ACCOUNT);
        sfOptions.put("sfWarehouse", Constants.SNOWFLAKE_WAREHOUSE);
        sfOptions.put("sfDatabase", Constants.SNOWFLAKE_DATABASE);
        sfOptions.put("sfSchema", Constants.SNOWFLAKE_SCHEMA);
        
        return sfOptions;
    }

}
