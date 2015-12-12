package im.jahnke.kimsufigrabber;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;

/**
 * KimsufiGrabber is a tool for checking the availability of Kimsufi servers 
 * 
 */

public class KimsufiGrabber {
	
	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Kimsufi Server Scanner");
		System.out.println("1 \t KS-1");
		System.out.println("2 \t KS-2");
		System.out.println("3 \t KS-2 SSD");
		System.out.println("4 \t KS-3");
		System.out.println("5 \t KS-4");
		System.out.println("6 \t KS-5");
		System.out.println("7 \t KS-6");		
		System.out.println("Choose Server: ");
		
		String chosenServer = "";
		switch (sc.nextLine()) {
		case "1":
			chosenServer = "150sk10";
			break;
		case "2":
			chosenServer = "150sk20";
			break;
		case "3":
			chosenServer = "150sk22";
			break;
		case "4":
			chosenServer = "150sk30";
			break;
		case "5":
			chosenServer = "150sk40";
			break;
		case "6":
			chosenServer = "150sk50";
			break;
		case "7":
			chosenServer = "150sk60";
			break;

		default:
			chosenServer = "150sk10";
			break;
		}
		
		System.out.println("Sleeping time in seconds: ");
		int sleepingTime = sc.nextInt()*1000;
		
		sc.close();
		
		System.out.println("Scanning for available server...");
		
		while(true){
			if(!getPageContent("https://www.kimsufi.com/en/order/kimsufi.cgi?hard=" + chosenServer + "&dedicatedQuantity=1").contains("invalide")){				
				Desktop.getDesktop().browse(new URI("https://www.kimsufi.com/en/order/kimsufi.cgi?hard=" + chosenServer + "&dedicatedQuantity=1"));
				JOptionPane.showMessageDialog(null, "Alarm");
			}
			
			Thread.sleep(sleepingTime);
			System.gc();
		}
	}
	
	private static String getPageContent(String url) throws Exception {

		String inputLine;
		StringBuffer response = new StringBuffer();
		
		URL obj = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();

		conn.setRequestMethod("GET");

		conn.setUseCaches(false);

		conn.setRequestProperty("User-Agent", "Mozilla/2.0");
		conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			conn.disconnect();
			conn = null;
		} catch (Exception e) {
			response.append("invalide");
		}

		return response.toString();

	}

}
