package im.jahnke.kimsufigrabber;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;

public class Parser {
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Kimsufi Server Scanner");
		
		while(true){
			System.out.println("Scanning for available server. Refresh every 5 seconds...");
			if(!getPageContent("https://www.kimsufi.com/en/order/kimsufi.cgi?hard=150sk10&dedicatedQuantity=1").contains("invalide")){				
				Desktop.getDesktop().browse(new URI("https://www.kimsufi.com/en/order/kimsufi.cgi?hard=150sk10&dedicatedQuantity=1"));
				JOptionPane.showMessageDialog(null, "Alarm");
			} else {
				System.out.println("No server available...");
			}
			
			Thread.sleep(5000);
		}
		
	}
	
	private static String getPageContent(String url) throws Exception {

		URL obj = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();

		conn.setRequestMethod("GET");

		conn.setUseCaches(false);

		conn.setRequestProperty("User-Agent", "Mozilla/2.0");
		conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();

	}

}
