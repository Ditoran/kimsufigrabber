

package im.jahnke.kimsuficrawler;
 
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;
 
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JOptionPane;
 
/**
 * KimsufiCrawler is a tool for checking the availability of Kimsufi servers 
 * 
 */
 
public class KimsufiCrawler {
     
    public static void main(String[] args) throws Exception {
         
        Scanner sc = new Scanner(System.in);
         
        System.out.println("Kimsufi Server Scanner");
        System.out.println("1 \t KS-1");
        System.out.println("2A \t KS-2A");
        System.out.println("2B \t KS-2B");
        System.out.println("2C \t KS-2C");
        System.out.println("2D \t KS-2D");
        System.out.println("3A \t KS-3A");
        System.out.println("3B \t KS-3B");
        System.out.println("3C \t KS-3C");
        System.out.println("3D \t KS-3D");
        System.out.println("4A \t KS-4A");
        System.out.println("4B \t KS-4B");
        System.out.println("4C \t KS-4C");
        System.out.println("4D \t KS-4D");
        System.out.println("5 \t KS-5");
        System.out.print("Choose Server: ");
        
        String chosenServer = "";
        switch (sc.nextLine()) {
        case "1":
            chosenServer = "160sk1";
            break;
        case "2A":
            chosenServer = "160sk2";
            break;
        case "2B":
            chosenServer = "160sk21";
            break;
        case "2C":
            chosenServer = "160sk22";
            break;
        case "2D":
            chosenServer = "160sk23";
            break;
        case "3A":
            chosenServer = "160sk3";
            break;
        case "3B":
            chosenServer = "160sk31";
            break;
        case "3C":
            chosenServer = "160sk32";
            break;
        case "3D":
            chosenServer = "160sk33";
            break;
        case "4A":
            chosenServer = "160sk4";
            break;
        case "4B":
            chosenServer = "160sk41";
            break;
        case "4C":
            chosenServer = "160sk42";
            break;
        case "4D":
            chosenServer = "160sk43";
            break;
        case "5":
            chosenServer = "160sk5";
            break;
        default:
            chosenServer = "";
            break;
        }
         
        System.out.print("Sleeping time in seconds: ");
        int sleepingTime = sc.nextInt()*1000;
         
        sc.close();
         
        while(true){
            System.out.println("Scanning for available server...");
            String pageContent = getPageContent("https://www.kimsufi.com/en/order/kimsufi.cgi?hard=" + chosenServer + "&dedicatedQuantity=1");
            if(!pageContent.contains("invalide") && !pageContent.contains("rapprovisionnement")){                
                Desktop.getDesktop().browse(new URI("https://www.kimsufi.com/en/order/kimsufi.cgi?hard=" + chosenServer + "&dedicatedQuantity=1"));
                JOptionPane.showMessageDialog(null, "Alarm");
                return;
            } else {
                System.out.println("No free servers found...");
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
