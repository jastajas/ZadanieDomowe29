import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class WeatherApp {
    public static void main(String[] args) {

        ArrayList<WeatherInfo> wi = null;


        try {
            wi = FileManager.getCities();
        } catch (IOException e) {
            System.out.println("Nie udało się pobrać miast.");
        }

        String city = null;

        for(int i = 0; i < wi.size(); i++) {
            if ((city = wi.get(i).getCity()) != null) {
                WeatherApi api = new WeatherApi();
                int temp = 0;
                try {
                    temp = api.getTemperature(city);
                    wi.get(i).setTemperature(temp);
                } catch (IOException e) {
                    System.err.println("Nie udało się pobrać informacji dla miasta:" + city);
                } catch (NoSuchElementException a) {
                    System.out.println("Nie udało się pobrać temperatury dla miasta:" + city);
                }

                String descript = null;
                try {
                    descript = api.getDescription(city);
                    wi.get(i).setDescription(descript);
                } catch (IOException e) {
                    System.err.println("Nie udało się pobrać informacji dla miasta:" + city);
                } catch (NoSuchElementException a) {
                    System.out.println("Nie udało się pobrać temperatury dla miasta:" + city);
                }

                if (descript != null || temp != 0) {
                    System.out.printf("Pogoda w mieście %s: %s\n", wi.get(i).getCity(), wi.get(i).getDescription());
                    System.out.printf("Aktualna temperatura: %d stopni\n", wi.get(i).getTemperature());
                } else {
                    System.out.println("nie pobrano właściwie danych.");
                }
            }

        }
        try {
            if (wi != null){
            FileManager.wrightWeatherInfo(wi);
            ArrayList<WeatherInfo> newOne = FileManager.getWeatherInfo();
            System.out.println(newOne.toString());}
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}