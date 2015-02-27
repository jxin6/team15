import java.io.IOException;

import org.json.JSONException;


public class Main {
	public static void main(String[] args) throws IOException, JSONException {
		
		WeatherBuilder c = new WeatherBuilder("London,ca");
		
		/* Current weather */
		Weather londonCurrentWeather = new Weather();
		londonCurrentWeather = c.buildCurrent();
		
		System.out.println("Current Weather:");
		System.out.println("Temperature: "+londonCurrentWeather.temperature[0]);
		System.out.println("Wind speed: "+londonCurrentWeather.windSpeed[0]);
		System.out.println("Wind direction: "+londonCurrentWeather.windDirection[0]);
		System.out.println("Air pressure: "+londonCurrentWeather.airPressure[0]);
		System.out.println("Humidity: "+londonCurrentWeather.humidity[0]);
		System.out.println("Sky condition: "+londonCurrentWeather.skyCondition[0]);
		System.out.println("Min temp: "+londonCurrentWeather.minTemp[0]);
		System.out.println("Max temp: "+londonCurrentWeather.maxTemp[0]);
		System.out.println("Sunrise: "+londonCurrentWeather.sunrise[0]);
		System.out.println("Sunset: "+londonCurrentWeather.sunset[0]);
		System.out.println("time: "+londonCurrentWeather.time[0]);
		System.out.println("icon: "+londonCurrentWeather.icon[0]+"\n");
	
		/* Short Term */
		Weather londonShortTerm = new Weather();
		londonShortTerm = c.buildShortTerm();
		
		System.out.println("Short Term:");
		System.out.println("Temperature: "+londonShortTerm.temperature[0]+"->"+londonShortTerm.temperature[1]+"->"+londonShortTerm.temperature[2]+"...");
		System.out.println("Sky condition: "+londonShortTerm.skyCondition[0]+"->"+londonShortTerm.skyCondition[1]+"->"+londonShortTerm.skyCondition[2]+"...");
		System.out.println("time: "+londonShortTerm.time[0]);
		System.out.println("icon: "+londonShortTerm.icon[0]+"->"+londonShortTerm.icon[1]+"->"+londonShortTerm.icon[2]+"..."+"\n");
		
		/* Long Term */
		Weather londonLongTerm = new Weather();
		londonLongTerm = c.buildLongTerm();
		
		System.out.println("Long Term:");
		System.out.println("Temperature: "+londonLongTerm.temperature[0]+"->"+londonLongTerm.temperature[1]+"->"+londonLongTerm.temperature[2]+"...");
		System.out.println("Min Temp: "+londonLongTerm.minTemp[0]+"->"+londonLongTerm.minTemp[1]+"->"+londonLongTerm.minTemp[2]+"...");
		System.out.println("Max Temp: "+londonLongTerm.maxTemp[0]+"->"+londonLongTerm.maxTemp[1]+"->"+londonLongTerm.maxTemp[2]+"...");
		System.out.println("Sky Condition: "+londonLongTerm.skyCondition[0]+"->"+londonLongTerm.skyCondition[1]+"->"+londonLongTerm.skyCondition[2]+"...");
		System.out.println("Icon: "+londonLongTerm.icon[0]+"->"+londonLongTerm.icon[1]+"->"+londonLongTerm.icon[2]+"..."+"\n");

		/* Mars */
		Weather marsCurrentWeather = new Weather();
		marsCurrentWeather = c.buildMarsWeather();
		
		System.out.println("Mars Current Weather:");
		System.out.println("Temperature: "+marsCurrentWeather.temperature[0]);
		System.out.println("Wind speed: "+marsCurrentWeather.windSpeed[0]);
		System.out.println("Wind direction: "+marsCurrentWeather.windDirection[0]);
		System.out.println("Air pressure: "+marsCurrentWeather.airPressure[0]);
		System.out.println("Humidity: "+marsCurrentWeather.humidity[0]);
		System.out.println("Sky condition: "+marsCurrentWeather.skyCondition[0]);
		System.out.println("time: "+marsCurrentWeather.time[0]);
		System.out.println("icon: "+marsCurrentWeather.icon[0]+"\n");
	}
}
