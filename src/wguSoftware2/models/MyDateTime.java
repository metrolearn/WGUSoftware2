package wguSoftware2.models;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.ZoneOffset.UTC;

/**
 * The type My date time.
 */
public class MyDateTime {

    private ZonedDateTime uTCZonedDateTime = null;
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

    public MyDateTime(String input, Active_User ac) {
        // input should be MM/DD/YYYY hh:mm:AM/PM
        // ZonedDateTime myTime = utcTime.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));

        parseinput(input);
        this.ac = ac;
        setLocalZonedDateTime(ac);
        setUTCZondedDateTime();

    }

    /**
     *
     * @param ts Timestamp
     * @param ac Active user
     * Constructor used while loading in appointments in MaineWindowC.java
     */
    public MyDateTime(Timestamp ts, Active_User ac) {
        this.ac = ac;
        this.uTCZonedDateTime = ts.toLocalDateTime().atZone(UTC);
        this.zonedLocalDateTime = uTCZonedDateTime.withZoneSameInstant(ac.getTz().toZoneId());
        this.menuZonedDateTime = zonedLocalDateTime;


    }

    private void parseinput(String input) {
        init_parse_input_str(input);
        parse_month_str(this.local_date_str);
        parse_time_str(this.local_time_str);
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

    public void setMenuZonedDateTimeByZoneID(ZoneId zid){
        menuZonedDateTime = menuZonedDateTime.toInstant().atZone(zid);
    }

    public Timestamp getNowUTCTS(){
        return Timestamp.valueOf(ZonedDateTime.now().withZoneSameInstant(UTC).toLocalDateTime());
    }


    private void setLocalZonedDateTime(Active_User ac) {
        this.formatter = DateTimeFormatter.ofPattern("MM/d/yyyy h:mm a");
        this.zonedLocalDateTime = ZonedDateTime.parse(this.get_zdt_str(),
                this.formatter.withZone(ZoneId.of(ac.getTz().toZoneId().toString())));
    }

    private String get_zdt_str(){

        return this.local_date_str+" "+local_time_str+" "+local_am_pm;

    }

    private void setUTCZondedDateTime() {
        this.uTCZonedDateTime = this.zonedLocalDateTime.withZoneSameInstant(ZoneOffset.UTC);
    }

    public Timestamp getUTCTimeStamp(){
        return Timestamp.valueOf(uTCZonedDateTime.toLocalDateTime());
    }

    public Timestamp getLocalTimeStamp(){
        return Timestamp.valueOf(zonedLocalDateTime.toLocalDateTime());
    }

    public Timestamp getMenuTimeStamp(){
        return Timestamp.valueOf(menuZonedDateTime.toLocalDateTime());

    }

    public String getLocal_date_str() {
        return local_date_str;
    }

    public String getLocal_time_str() {
        return local_time_str;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getLocal_hour() {
        return local_hour;
    }

    public Integer getLocal_min() {
        return local_min;
    }

    public String getLocal_am_pm() {
        return local_am_pm;
    }

    public Active_User getAc() {
        return ac;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setMenuZonedDateTime(ZoneId zid) {
        this.menuZonedDateTime = uTCZonedDateTime.withZoneSameInstant(zid);
    }

    public String getSimpleDate(ZonedDateTime zdt) {
        return DateTimeFormatter.ofPattern("MM/d/yyyy").format(zdt);
    }

    public String getSimpleDateLocalStr() {
        return DateTimeFormatter.ofPattern("MM/d/yyyy").format(this.zonedLocalDateTime);
    }

    public String getSimpleTime(ZonedDateTime zdt) {
        return DateTimeFormatter.ofPattern("hh:mm a").format(zdt);
    }

    public String getSimpleTimeLocal() {
        return DateTimeFormatter.ofPattern("hh:mm a").format(this.zonedLocalDateTime);
    }

    public String getSimpleTimeHourLocal() {
        return DateTimeFormatter.ofPattern("hh").format(this.zonedLocalDateTime);
    }

    public String getSimpleTimeMinLocal() {
        return DateTimeFormatter.ofPattern("mm").format(this.zonedLocalDateTime);
    }

    public ZonedDateTime getuTCZonedDateTime() {
        return uTCZonedDateTime;
    }

    public ZonedDateTime getZonedLocalDateTime() {
        return zonedLocalDateTime;
    }

    public ZonedDateTime getMenuZonedDateTime() {
        return menuZonedDateTime;
    }

    public String getStartMonth() {

      return   DateTimeFormatter.ofPattern("MMMM").format(zonedLocalDateTime);

    }

    public String getStartDayOfWeek(){
        return   DateTimeFormatter.ofPattern("dddd").format(zonedLocalDateTime);

    }

    public String getSimpleDateMenuStringZDT(){
        return DateTimeFormatter.ofPattern("MM/d/yyyy").format(menuZonedDateTime);

    }

    public void setUTCDateTimeZoneByTimeStamp(Timestamp ts) {

        this.uTCZonedDateTime = ts.toLocalDateTime().atZone(UTC);

    }

    public String getSimpleTimeHourMin() {

        return DateTimeFormatter.ofPattern("hh:mm").format(this.zonedLocalDateTime);

    }

    public String getSimpleAmPm() {

        return DateTimeFormatter.ofPattern("a").format(this.zonedLocalDateTime);

    }
}
