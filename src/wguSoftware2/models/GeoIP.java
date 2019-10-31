package wguSoftware2.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

/**
 * The type Geo ip.
 */
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

    /**
     * Instantiates a new Geo ip.
     *
     * @throws IOException          the io exception
     * @throws InterruptedException the interrupted exception
     */
    public GeoIP() throws IOException, InterruptedException {

    String geoIPURL = "https://freegeoip.app/csv";
    URL url = new URL(geoIPURL);
    BufferedReader br = null;
    try {
      br = new BufferedReader(new InputStreamReader(url.openStream()));
    } catch (IOException e) {
      System.out.println("Getting location from "+geoIPURL);
      Thread.sleep(4000);

      try {
        br = new BufferedReader(new InputStreamReader(url.openStream()));
      } catch (IOException ex) {
        System.out.println("Cannot connect to "+geoIPURL+", waiting another 10 seconds then will try again!");
        Thread.sleep(10000);

        try {
          br = new BufferedReader(new InputStreamReader(url.openStream()));
        } catch (IOException exc) {
          System.out.println("Cannot connect to "+geoIPURL+", check your internet connection!");
          exc.printStackTrace();
        }

      }


    }
    assert br != null;
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

    /**
     * Get login location string string.
     *
     * @return the string
     */
    public String getLoginLocationString(){
      return "Current Location: "+ this.city+", "+this.region_name +" IP: "+this.ip;
    }

    /**
     * Get login location string spanish string.
     *
     * @return the string
     */
    public String getLoginLocationStringSpanish(){
    return "Ubicación actual: "+ this.city+", "+this.region_name +" IP: "+this.ip;


  }

    /**
     * Gets ip.
     *
     * @return the ip
     */
    public String getIp() {
    return ip;
  }

    /**
     * Gets country code.
     *
     * @return the country code
     */
    public String getCountry_code() {
    return country_code;
  }

    /**
     * Gets country name.
     *
     * @return the country name
     */
    public String getCountry_name() {
    return country_name;
  }

    /**
     * Gets region code.
     *
     * @return the region code
     */
    public String getRegion_code() {
    return region_code;
  }

    /**
     * Gets region name.
     *
     * @return the region name
     */
    public String getRegion_name() {
    return region_name;
  }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
    return city;
  }

    /**
     * Gets zip code.
     *
     * @return the zip code
     */
    public String getZip_code() {
    return zip_code;
  }

    /**
     * Gets time zone.
     *
     * @return the time zone
     */
    public String getTime_zone() {
    return time_zone;
  }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public String getLatitude() {
    return latitude;
  }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public String getLongitude() {
    return longitude;
  }

    /**
     * Gets metro code.
     *
     * @return the metro code
     */
    public String getMetro_code() {
    return metro_code;
  }
}





