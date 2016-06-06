package Assessment;

import java.net.HttpURLConnection;

import java.net.URL;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class HttpPost {
	
public HttpResponse PostOperation(String sURL, String sBody){
		
		URL obj;
		HttpResponse resp;
		resp = new HttpResponse();
		try {
			obj = new URL(sURL);
		
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	
			//add reuqest header
			con.setRequestMethod("POST");
			
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(sBody);
			wr.flush();
			wr.close();
	
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + sURL);
			System.out.println("Post parameters : " + sBody);
			System.out.println("Response Code : " + responseCode);
	
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();			
			
			resp.Response = response.toString();
			resp.ResponseCode = HttpResponseEnum.OK;
			
			return resp;
		}
		catch(Exception ex)
		{
			return resp;
		}
		
	
					
	}

	
	}
