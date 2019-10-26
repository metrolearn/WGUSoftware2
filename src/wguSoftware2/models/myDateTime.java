package wguSoftware2.models;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class myDateTime {

    private ZonedDateTime UTCZonedDateTime = null;
    private ZonedDateTime zonedLocalDateTime = null;
    private ZonedDateTime menuZonedDateTime = null;
    private String local_date_str = null;
    private String local_time_str = null;

    private Integer month = null;
    private Integer day = null;
    private Integer year = null;
    private Integer local_hour = null;
    private Integer local_min = null;
    private String local_am_pm = null;

    private Active_User ac = null;
    private DateTimeFormatter formatter = null;




    public myDateTime(String input,Active_User ac) {
        // input should be MM/DD/YYYY hh:mm:AM/PM
        // ZonedDateTime myTime = utcTime.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));

        parseinput(input);
        this.ac = ac;
        setLocalZonedDateTime(ac);
        setUTCZondedDateTime();




    }

    public void setMenuZonedDateTime(ZoneId zid) {
        this.menuZonedDateTime = UTCZonedDateTime.withZoneSameInstant(zid);
    }

    public String getSimpleDate(ZonedDateTime zdt) {
        return DateTimeFormatter.ofPattern("MM/d/yyyy").format(zdt);
    }


    public String getSimpleTime(ZonedDateTime zdt) {
        return DateTimeFormatter.ofPattern("hh:mm a").format(zdt);
    }

    private void setUTCZondedDateTime() {
        this.UTCZonedDateTime = this.zonedLocalDateTime.withZoneSameInstant(ZoneOffset.UTC);
    }

    private void setLocalZonedDateTime(Active_User ac) {
        this.formatter = DateTimeFormatter.ofPattern("MM/d/yyyy h:mm a");
        this.zonedLocalDateTime = ZonedDateTime.parse(this.get_zdt_str(),
                this.formatter.withZone(ZoneId.of(ac.getTz().toZoneId().toString())));
    }

    private String get_zdt_str(){

        return this.local_date_str+" "+local_time_str+" "+local_am_pm;


    }

    private void parseinput(String input) {
        init_parse_input_str(input);
        parse_month_str(this.local_date_str);
        parse_time_str(this.local_time_str);
    }

    private void parse_time_str(String local_time_str) {
        String re1="(\\d+)";	// Integer Number 1
        String re2=".*?";	// Non-greedy match on filler
        String re3="(\\d+)";	// Integer Number 2
        String re4=".*?";	// Non-greedy match on filler
        String re5="((?:[a-z][a-z0-9_]*))";	// Variable Name 1

        Pattern p = Pattern.compile(re1+re2+re3+re4+re5,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(local_time_str);
        if (m.find())
        {
            this.local_hour= Integer.valueOf(m.group(1));
            this.local_min= Integer.valueOf(m.group(2));
            this.local_am_pm=m.group(3);
        }

    }

    private void parse_month_str(String local_date_str) {
        String re1="(\\d+)";	// Integer Number 1
        String re2=".*?";	// Non-greedy match on filler
        String re3="(\\d+)";	// Integer Number 2
        String re4=".*?";	// Non-greedy match on filler
        String re5="(\\d+)";	// Integer Number 3

        Pattern p = Pattern.compile(re1+re2+re3+re4+re5,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(local_date_str);
        if (m.find())
        {
            this.month= Integer.valueOf(m.group(1));
            this.day= Integer.valueOf(m.group(2));
            this.year= Integer.valueOf(m.group(3));
        }
    }



    private void init_parse_input_str(String input) {
        String re1="((?:[0]?[1-9]|[1][012])[-:\\/.](?:(?:[0-2]?\\d{1})|(?:[3][01]{1}))" +
                "[-:\\/.](?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3})))(?![\\d])";	// MMDDYYYY
        String re2=".*?";	// Non-greedy match on filler
        String re3="((?:(?:[0-1][0-9])|(?:[2][0-3])|(?:[0-9])):" +
                "(?:[0-5][0-9])(?::[0-5][0-9])?(?:\\s?(?:am|AM|pm|PM))?)";	// HourMinuteSec
        String re4=".*?";	// Non-greedy match on filler
        String re5="((?:[a-z][a-z]+))";	//

        Pattern p = Pattern.compile(re1+re2+re3+re4+re5,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(input);
        try {
            if (m.find())
            {
                this.local_date_str=m.group(1);
                this.local_time_str=m.group(2);
                this.local_am_pm=m.group(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ZonedDateTime getUTCZonedDateTime() {
        return UTCZonedDateTime;
    }

    public ZonedDateTime getZonedLocalDateTime() {
        return zonedLocalDateTime;
    }

    public ZonedDateTime getMenuZonedDateTime() {
        return menuZonedDateTime;
    }
}
