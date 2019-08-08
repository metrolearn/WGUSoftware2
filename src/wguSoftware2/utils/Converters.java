package wguSoftware2.utils;

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

}
