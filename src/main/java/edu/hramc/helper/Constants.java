package edu.hramc.helper;

public interface Constants {
	
	// Get the snowflake connection URL - <hostname>.snowflakecomputing.com
	String SNOWFLAKE_URL = "<Snowflake URL>";
	
	//Blob Path
	String AZURE_BLOB_PATH="<Azure Blob Folder Path>";
	
	// Blob URL - wasbs://<containerName>@<accountName>.blob.core.windows.net
	String AZURE_BLOB_URL = "<Azure Blob URL>";
	
	// Azure Blob Account Name - fs.azure.account.key.<accountName>.blob.core.windows.net
	String AZURE_ACCOUNT_NAME ="<Account Name>";
	
	// Account Key
	String AZURE_ACCOUNT_KEY="<Account Key>";
	
	
	// Snowflake Format
	String SNOWFLAKE_FORMAT="net.snowflake.spark.snowflake";
	
	/**
	 *  Please create a table in Snowflake 
	 *  Table Structure is Body (Variant) and CurrentTime (Timestamp)
	 */
	
	// Table Name
	String SNOWFLAKE_TABLENAME = "<table Name>";
	
	// Query to insert the JSON data into snowflake
	String INSERT_DATA_INTO_SNOWFLAKE = "insert into <tableName> "
					 +"select parse_json('%s'),CURRENT_TIMESTAMP::timestamp_ntz";
	
	
	String SNOWFLAKE_JDBC_DRIVER_NAME="net.snowflake.client.jdbc.SnowflakeDriver";
	
	String SNOWFLAKE_SPARK_FORMAT="net.snowflake.spark.snowflake";
	
	// Snowflake credentails
	
	String SNOWFLAKE_USER="<userName>";
	String SNOWFLAKE_PASSWORD="<Password>";
	String SNOWFLAKE_WAREHOUSE="<wareHouse>";
	String SNOWFLAKE_DATABASE="<Database Name>";
	String SNOWFLAKE_SCHEMA="<schema Name>";
	String SNOWFLAKE_ACCOUNT="<accountName>";

}
