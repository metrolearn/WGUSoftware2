package wguSoftware2.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class GeoIP {

  private String ip;
  private String country_code;
  private String country_name;
  private String region_code;
  private String region_name;
  private String city;
  private String zip_code;
  private String time_zone;
  private String latitude;
  private String longitude;
  private String metro_code;

  public GeoIP() throws IOException {

    URL url = new URL("https://freegeoip.app/csv");
    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
    String s = br.lines().collect(Collectors.joining());
    String[] as = s.split(",");

    this.ip = as[0];
    this.country_code = as[1];
    this.country_name = as[2];
    this.region_code = as[3];
    this.region_name = as[4];
    this.city = as[5];
    this.zip_code = as[6];
    this.time_zone = as[7];
    this.latitude = as[8];
    this.longitude = as[9];
    this.metro_code = as[10];

  }

  @Override
  public String toString() {
    return "GeoIP{" +
        "ip='" + ip + '\'' +
        ", country_code='" + country_code + '\'' +
        ", country_name='" + country_name + '\'' +
        ", region_code='" + region_code + '\'' +
        ", region_name='" + region_name + '\'' +
        ", city='" + city + '\'' +
        ", zip_code='" + zip_code + '\'' +
        ", time_zone='" + time_zone + '\'' +
        ", latitude='" + latitude + '\'' +
        ", longitude='" + longitude + '\'' +
        ", metro_code='" + metro_code + '\'' +
        '}';
  }

    public String getLoginLocationString(){
      return "Current Location: "+ this.city+", "+this.region_name +" IP: "+this.ip;
    }

  public String getLoginLocationStringSpanish(){
    return "Ubicación actual: "+ this.city+", "+this.region_name +" IP: "+this.ip;


  }
  public String getIp() {
    return ip;
  }

  public String getCountry_code() {
    return country_code;
  }

  public String getCountry_name() {
    return country_name;
  }

  public String getRegion_code() {
    return region_code;
  }

  public String getRegion_name() {
    return region_name;
  }

  public String getCity() {
    return city;
  }

  public String getZip_code() {
    return zip_code;
  }

  public String getTime_zone() {
    return time_zone;
  }

  public String getLatitude() {
    return latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public String getMetro_code() {
    return metro_code;
  }
}





