import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public static ArrayList<WeatherInfo> getCities() throws IOException {

        FileReader fr = new FileReader("cities.txt");
        BufferedReader bfr = new BufferedReader(fr);
        ArrayList<WeatherInfo> wi = new ArrayList<>();
        String line = null;
        int i = 0;

        while((line = bfr.readLine()) != null){
            wi.add(new WeatherInfo(line));
            i++;
        }
        bfr.close();
        //System.out.println(wi.toString());
        return wi;
    }

    public static ArrayList<WeatherInfo> getWeatherInfo() throws IOException {
        FileReader fr = new FileReader("WeatherInfo.csv");
        BufferedReader bfr = new BufferedReader(fr);
        ArrayList<WeatherInfo> wi = new ArrayList<>();
        String line = null;
        int i = 0;

        while((line = bfr.readLine()) != null){
            String[] splitLine = line.split(";");
            wi.add(new WeatherInfo(splitLine[0],splitLine[1],Integer.valueOf(splitLine[2])));
            i++;
        }

        return wi;
    }

    public static void wrightWeatherInfo(ArrayList<WeatherInfo> wi) throws IOException {
        FileWriter fw = new FileWriter("WeatherInfo.csv", false);
        BufferedWriter bfw = new BufferedWriter(fw);
        for(int i = 0; i < wi.size(); i++){
            System.out.println();
            bfw.write(wi.get(i).getCity() + ";" + wi.get(i).getDescription() + ";" + wi.get(i).getTemperature());
                    bfw.newLine();
        }
        bfw.close();
    }
}
