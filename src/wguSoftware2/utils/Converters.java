package wguSoftware2.utils;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Converters {


    public String local_date_time_to_mysql_date_time_string(LocalDateTime ldt){
        /*
         * Mysql example of datetime : "2019-08-06 23:45:23"
         */
        String format_string = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_string);
        String ldt_str =  ldt.format(formatter);

        return ldt_str;
    }

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
