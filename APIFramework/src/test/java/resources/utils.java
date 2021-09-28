package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class utils {
	
	public static RequestSpecification req;

	public RequestSpecification requestSppecification() throws IOException
	
{
		
		if(req==null)
		{
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt")); //new file is created at run time
		req= new RequestSpecBuilder().setBaseUri(getGlobalValues("baseurl"))
				
				//to log the details of the request and response
				.addFilter(RequestLoggingFilter.logRequestTo(log)) //create an object of printstream class to log into a file
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		return req;
		}
		return req;
		
	}
	
	public static String getGlobalValues(String key) throws IOException
	{
		Properties prop=new Properties();
		// read the file using below class
		
		FileInputStream fis=new FileInputStream("/Users/mrunaligaikwad/eclipse-workspace/APIFramework/src/test/java/resources/global.properties");
		prop.load(fis); //file will be added to the prop object
		
		return prop.getProperty(key); //access the base url from properties file
		
	}
	
	public String getJsonPath(Response res, String key) //key will be any key in the json
	{
		String resp=res.asString(); //response coming from addplace API (check step definition)
		JsonPath js=new JsonPath(resp);
		return js.get(key).toString();
		
	}
}
