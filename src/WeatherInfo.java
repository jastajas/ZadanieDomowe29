public class WeatherInfo {
    private String city;
    private String description;
    private int temperature;

    public WeatherInfo(String city){
        this.city = city;
    }

    public WeatherInfo(String city, String description, int temperature){
        this.city = city;
        this.description = description;
        this.temperature = temperature;
    }

    public String getCity(){
        return city;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public int getTemperature(){
        return temperature;
    }
    public void setTemperature(int temperature){
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "city='" + city + '\'' +
                ", description='" + description + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}
