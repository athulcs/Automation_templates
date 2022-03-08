import java.io.*;
import java.util.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.*;

public class PinCodeParser{
    public static void main(String[] args){
        Scanner sc;
        String pin;
        try{
            sc = new Scanner(System.in);
            while(true){
                //System.out.print("\nEnter pincode:");
                pin = sc.nextLine();
                try {

                    URL url = new URL("http://www.postalpincode.in/api/pincode/"+pin);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("GET");
                    int status = conn.getResponseCode();
                    //System.out.println("stat="+status);
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer content = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();
                    //System.out.println(content);
                    //parse json here
                    JsonObject convertedObject = new Gson().fromJson(String.valueOf(content), JsonObject.class);
                    JsonArray postOffice = convertedObject.getAsJsonArray("PostOffice");
                    JsonObject fPostOffice =(JsonObject) postOffice.get(0);
                    System.out.println(pin+"-"+fPostOffice.get("Name"));
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }

    }
}	