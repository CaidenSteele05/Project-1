import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Convert {
	public static void convertFile(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName.substring(0,fileName.length()-3) + "csv"));
			
			String line;
			reader.readLine();
			reader.readLine();
			reader.readLine();
			writer.write("Time,Latitude,Longitude");
			
			int minutes = 0;
			while((line = reader.readLine()) != null) {
				line = line.replaceAll("\\?", "");
				line = line.replaceAll("\\s+", "");
				int latStart = line.indexOf("lat=\"");
				if(latStart == -1) {
					continue;
				}
				latStart += 5;
				int latEnd = line.indexOf("\"",latStart);
				int lonStart = line.indexOf("lon=\"") + 5;
				int lonEnd = line.indexOf("\"",lonStart);
				
				String lat = line.substring(latStart,latEnd);
				String lon = line.substring(lonStart,lonEnd);
				
				writer.append("\n" + minutes + "," + lat + "," + lon);
				minutes += 5;
			}
			reader.close();
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
