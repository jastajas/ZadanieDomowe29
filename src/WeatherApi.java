import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherApi {
    private static final String TEMP_PATTERN = "<p class=\"today-temp\">\\+(.*?)&deg;C</p>";
    private static final String DESC_PATTERN = "<img width=\"188\" height=\"150\" src=\"(.*?)\" alt=\"(.*?)\"/>";
    private static final String URL_PATTERN = "https://sinoptik.pl/pogoda-";
    
    /**
     * @param city - miasto, dla którego pogodę chcemy pobrać
     * musi zawierać polskie znaki, czyli np. "wrocław", a nie "wroclaw"
     * @return - temperatura w stopniach Celsjusza
     * @throws IOException - jeśli nie można pobrać informacji o pogodzie dla danego miasta,
     * np. wpisano miasto z błędem
     */
    public int getTemperature(String city) throws IOException {
        String pageSource = getPageSource(URL_PATTERN + city);
        int temperature = extractTempFromSource(pageSource);
        return temperature;
    }
    
    /**
     * 
     * @param city - miasto, dla którego pogodę chcemy pobrać,
     * musi zawierać polskie znaki, czyli np. "wrocław", a nie "wroclaw"
     * @return - opis aktualnej pogody, np "zachmurzenie zmienne", "Zachmurzenie z rozpogodzeniami",
     * np. wpisano miasto z błędem
     * @throws IOException -jeśli nie można pobrać informacji o pogodzie dla danego miasta
     */
    public String getDescription(String city) throws IOException {
        String pageSource = getPageSource(URL_PATTERN + city);
        String description = extractDescriptionFromSource(pageSource);
        return description;
    }
    
    private String getPageSource(String pageUrl) throws IOException {
        URL url = new URL(pageUrl);
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String nextLine;
        StringBuilder source = new StringBuilder();
        while ((nextLine = in.readLine()) != null) {
            source.append(nextLine);
        }
        in.close();
        return source.toString();
    }
    
    private int extractTempFromSource(String source){
        Pattern pattern = Pattern.compile(TEMP_PATTERN);
        Matcher matcher = pattern.matcher(source);
        if(!matcher.find()) {
            throw new NoSuchElementException("Nie znaleziono temperatury");
        }
        return Integer.valueOf(matcher.group(1));
    }
    
    private String extractDescriptionFromSource(String source) {
        Pattern pattern = Pattern.compile(DESC_PATTERN);
        Matcher matcher = pattern.matcher(source);
        if(!matcher.find()) {
            throw new NoSuchElementException("Nie znaleziono temperatury");
        }
        return matcher.group(2);
    }
}
