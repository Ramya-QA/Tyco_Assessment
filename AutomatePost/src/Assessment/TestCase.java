package Assessment;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TestCase {

	  @BeforeMethod
	  public void beforeMethod() {
	  }
	  
	  @Test
	  public void TestGet() throws Throwable{
		  HTTP_GET get = new HTTP_GET();
		  HttpResponse response = new HttpResponse(); 
		  response = get.GetOperation("http://httpbin.org/get");
		  Assert.assertEquals(response.ResponseCode, HttpResponseEnum.OK);
		  
		  JSONObject obj = new JSONObject(response.Response);
		  JSONObject obj2 = obj.getJSONObject("headers");
		  Assert.assertEquals(obj2.getString("Host"), "httpbin.org");
		  
	  }

	  @Test
	  public void TestPost() throws Throwable{
		  HttpPost post = new HttpPost();
		  HttpResponse response = new HttpResponse();
		  String sBody = "custname=Ram&custtel=4705545006&custemail=ramyab.qa%40gmail.com&size=small&topping=onion&delivery=12%3A00&comments=None";
		  response = post.PostOperation("http://httpbin.org/post", sBody);
		  Assert.assertEquals(response.ResponseCode, HttpResponseEnum.OK);	  
		  
		  JSONObject obj = new JSONObject(response.Response);
		  JSONObject obj2 = obj.getJSONObject("form");
		  Assert.assertEquals(obj2.getString("custemail"), "ramyab.qa@gmail.com");	
		  
	  }
	  
	  
	  @Test
	  public void TestHeaders() throws Throwable{
		  HTTP_GET get = new HTTP_GET();
		  HttpResponse response = new HttpResponse(); 
		  response = get.GetOperation("http://httpbin.org/headers");
		  Assert.assertEquals(response.ResponseCode, HttpResponseEnum.OK);
		  
		  JSONObject obj = new JSONObject(response.Response);
		  JSONObject obj2 = obj.getJSONObject("headers");
		  Assert.assertEquals(obj2.getString("Host"), "httpbin.org");
	  }
	
}
