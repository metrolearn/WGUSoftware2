package wguSoftware2.utils;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The type Converters.
 */
public class Converters {


    /**
     * Ldt to mysql dt str string.
     *
     * @param ldt the ldt
     * @return the string
     */
    public String ldt_to_mysql_dt_str(ZonedDateTime ldt){
        /*
         * Mysql example of datetime : "2019-08-06 23:45:23"
         */
        String format_string = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_string);
        String ldt_str =  ldt.format(formatter);

        return ldt_str;
    }

    /**
     * Get fxml file name string.
     *
     * @param url the url
     * @return the string
     */
    public String get_fxml_file_name(URL url){
        String s = null;
        String r_val = null;
        try {
            s = url.toString();
            r_val = "Loading file: "+ s.substring(s.lastIndexOf('/') + 1).trim();
        } catch (NullPointerException e) {
            r_val = "Loading file: File not found";
            e.printStackTrace();
        }
        return r_val;

    }

}
