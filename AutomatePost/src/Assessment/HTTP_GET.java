package Assessment;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HTTP_GET {

public HTTP_GET(){
		
	}
	
	public HttpResponse GetOperation(String sURL){
		
		HttpResponse response = new HttpResponse();		
		
		try {
			URL url = new URL(sURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			int iHttpResponseCode = con.getResponseCode();
			
			switch (iHttpResponseCode) {
			case 200:
				BufferedReader reader = new BufferedReader(
				        new InputStreamReader(con.getInputStream()));
				String sInputLine;
				StringBuffer sResponseBody = new StringBuffer();

				while ((sInputLine = reader.readLine()) != null) {
					sResponseBody.append(sInputLine);
				}
				reader.close();	
				response.ResponseCode = HttpResponseEnum.OK;
				response.Response = sResponseBody.toString();
				break;
			case 404:
				response.ResponseCode = HttpResponseEnum.NOTFOUND;
				
			default:				
				response.ResponseCode = HttpResponseEnum.INTERNALERROR;
				break;
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return response;
	}
	
	
	
}
