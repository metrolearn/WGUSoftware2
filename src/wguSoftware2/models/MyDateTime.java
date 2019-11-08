package wguSoftware2.models;


import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.zone.ZoneRules;
import java.util.TimeZone;
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
    private ZoneId menuZone = null;

    /**
     * Instantiates a new My date time.
     *
     * @param input the input
     * @param ac    the ac
     */
    public MyDateTime(String input, Active_User ac) {
        // input should be MM/DD/YYYY hh:mm:AM/PM
        // ZonedDateTime myTime = utcTime.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));

        parseinput(input);
        this.ac = ac;
        setLocalZonedDateTime(ac);
        setUTCZondedDateTime();

    }

    /**
     * Instantiates a new My date time.
     *
     * @param ts the ts
     * @param ac the ac
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

    /**
     * Set menu zoned date time by zone id.
     *
     * @param zid the zid
     */
    public void setMenuZonedDateTimeByZoneID(ZoneId zid){
        menuZonedDateTime = menuZonedDateTime.toInstant().atZone(zid);
    }

    /**
     * Get now utcts timestamp.
     *
     * @return the timestamp
     */
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

    /**
     * Get utc time stamp timestamp.
     *
     * @return the timestamp
     */
    public Timestamp getUTCTimeStamp(){
        return Timestamp.valueOf(uTCZonedDateTime.toLocalDateTime());
    }

    /**
     * Get local time stamp timestamp.
     *
     * @return the timestamp
     */
    public Timestamp getLocalTimeStamp(){
        return Timestamp.valueOf(zonedLocalDateTime.toLocalDateTime());
    }

    /**
     * Get menu time stamp timestamp.
     *
     * @return the timestamp
     */
    public Timestamp getMenuTimeStamp(){
        return Timestamp.valueOf(menuZonedDateTime.toLocalDateTime());

    }

    /**
     * Gets local date str.
     *
     * @return the local date str
     */
    public String getLocal_date_str() {
        return local_date_str;
    }

    /**
     * Gets local time str.
     *
     * @return the local time str
     */
    public String getLocal_time_str() {
        return local_time_str;
    }

    /**
     * Gets month.
     *
     * @return the month
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * Gets day.
     *
     * @return the day
     */
    public Integer getDay() {
        return day;
    }

    /**
     * Gets year.
     *
     * @return the year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Gets local hour.
     *
     * @return the local hour
     */
    public Integer getLocal_hour() {
        return local_hour;
    }

    /**
     * Gets local 24 hour.
     *
     * @return the local 24 hour
     */
    public Integer getLocal24hour() {
        return zonedLocalDateTime.getHour();
    }

    /**
     * Gets local min.
     *
     * @return the local min
     */
    public Integer getLocal_min() {
        return local_min;
    }

    /**
     * Gets local am pm.
     *
     * @return the local am pm
     */
    public String getLocal_am_pm() {
        return local_am_pm;
    }

    /**
     * Gets ac.
     *
     * @return the ac
     */
    public Active_User getAc() {
        return ac;
    }

    /**
     * Gets formatter.
     *
     * @return the formatter
     */
    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    /**
     * Sets menu zoned date time.
     *
     * @param zid the zid
     */
    public void setMenuZonedDateTime(ZoneId zid) {
        this.menuZonedDateTime = uTCZonedDateTime.withZoneSameInstant(zid);
    }

    /**
     * Gets simple date.
     *
     * @param zdt the zdt
     * @return the simple date
     */
    public String getSimpleDate(ZonedDateTime zdt) {
        return DateTimeFormatter.ofPattern("MM/d/yyyy").format(zdt);
    }

    /**
     * Gets simple date local str.
     *
     * @return the simple date local str
     */
    public String getSimpleDateLocalStr() {
        return DateTimeFormatter.ofPattern("MM/d/yyyy").format(this.zonedLocalDateTime);
    }

    /**
     * Gets simple date my sql utc ts.
     *
     * @return the simple date my sql utc ts
     */
// 2019-10-25 12:55:00
    public String getSimpleDateMySqlUTCTs() {
        return DateTimeFormatter.ofPattern("yyyy-MM-d HH:mm").format(this.uTCZonedDateTime);
    }

    /**
     * Gets simple time.
     *
     * @param zdt the zdt
     * @return the simple time
     */
    public String getSimpleTime(ZonedDateTime zdt) {
        return DateTimeFormatter.ofPattern("hh:mm a").format(zdt);
    }

    /**
     * Gets simple time local.
     *
     * @return the simple time local
     */
    public String getSimpleTimeLocal() {
        return DateTimeFormatter.ofPattern("hh:mm a").format(this.zonedLocalDateTime);
    }

    /**
     * Gets simple time hour local.
     *
     * @return the simple time hour local
     */
    public String getSimpleTimeHourLocal() {
        return DateTimeFormatter.ofPattern("hh").format(this.zonedLocalDateTime);
    }

    /**
     * Gets simple time min local.
     *
     * @return the simple time min local
     */
    public String getSimpleTimeMinLocal() {
        return DateTimeFormatter.ofPattern("mm").format(this.zonedLocalDateTime);
    }

    /**
     * Gets tc zoned date time.
     *
     * @return the tc zoned date time
     */
    public ZonedDateTime getuTCZonedDateTime() {
        return uTCZonedDateTime;
    }

    /**
     * Gets zoned local date time.
     *
     * @return the zoned local date time
     */
    public ZonedDateTime getZonedLocalDateTime() {
        return zonedLocalDateTime;
    }

    /**
     * Gets menu zoned date time.
     *
     * @return the menu zoned date time
     */
    public ZonedDateTime getMenuZonedDateTime() {
        return menuZonedDateTime;
    }

    /**
     * Gets start month.
     *
     * @return the start month
     */
    public String getStartMonth() {

      return   DateTimeFormatter.ofPattern("MMMM").format(zonedLocalDateTime);

    }

    /**
     * Get start day of week string.
     *
     * @return the string
     */
    public String getStartDayOfWeek(){
        return   DateTimeFormatter.ofPattern("EEE").format(zonedLocalDateTime);

    }

    /**
     * Get simple date menu string zdt string.
     *
     * @return the string
     */
    public String getSimpleDateMenuStringZDT(){
        return DateTimeFormatter.ofPattern("MM/d/yyyy").format(menuZonedDateTime);

    }

    /**
     * Sets utc date time zone by time stamp.
     *
     * @param ts the ts
     */
    public void setUTCDateTimeZoneByTimeStamp(Timestamp ts) {

        this.uTCZonedDateTime = ts.toLocalDateTime().atZone(UTC);

    }

    /**
     * Gets simple time hour min.
     *
     * @return the simple time hour min
     */
    public String getSimpleTimeHourMin() {

        return DateTimeFormatter.ofPattern("hh:mm").format(this.zonedLocalDateTime);

    }

    /**
     * Gets simple am pm.
     *
     * @return the simple am pm
     */
    public String getSimpleAmPm() {

        return DateTimeFormatter.ofPattern("a").format(this.zonedLocalDateTime);

    }

    /**
     * Sets menu zoned date time.
     *
     * @param zonedDateTime the zoned date time
     * @return the menu zoned date time
     */
    public String setMenuZonedDateTime(ZonedDateTime zonedDateTime) {
        this.menuZonedDateTime = zonedDateTime;
        return DateTimeFormatter.ofPattern("hh:mm").format(this.menuZonedDateTime);
    }


    public Long getDst(){

        TimeZone tz = this.ac.getTz();
        ZoneId zoneId = tz.toZoneId();
        ZoneRules rules = zoneId.getRules();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
        LocalDate date = LocalDate.parse("11/01/2019", dateTimeFormatter);

        ZonedDateTime result = date.atStartOfDay(zoneId);
        return rules.getDaylightSavings(result.toInstant()).getSeconds();
    }

    public String addDST(){

       return DateTimeFormatter.ofPattern("hh:mm a").
                format(this.menuZonedDateTime.plus(3600, ChronoUnit.SECONDS));

    }

    public String minusDST(){

        return DateTimeFormatter.ofPattern("hh:mm a").
                format(this.menuZonedDateTime.minus(3600, ChronoUnit.SECONDS));

    }




    /**
     * Gets simple time menu zdt minus dst.
     *
     * @return the simple time menu zdt minus dst
     */
    public String getSimpleTimeMenuZDTMinusDST() {

            TimeZone tz = this.ac.getTz();
            ZoneId zoneId = tz.toZoneId();
            ZoneRules rules = zoneId.getRules();
            ZonedDateTime avm_zdt_start = menuZonedDateTime;
            Instant avm_zdt_instant = avm_zdt_start.toInstant();
            Duration start_duration = rules.getDaylightSavings(avm_zdt_instant);
            long diff_in_seconds = start_duration.getSeconds();
            ZonedDateTime zonedDateTime = avm_zdt_start;
            return DateTimeFormatter.ofPattern("hh:mm a").
                    format(this.menuZonedDateTime.minus(diff_in_seconds, ChronoUnit.SECONDS));


    }

    /**
     * Gets simple time menu zdt no dst.
     *
     * @return the simple time menu zdt no dst
     */
    public String getSimpleTimeMenuZDTNoDST() {

        return DateTimeFormatter.ofPattern("hh:mm a").
                format(this.menuZonedDateTime);


    }

    /**
     * Gets simple date menu string by tz.
     *
     * @return the simple date menu string by tz
     */
    public String getSimpleDateMenuStringByTZ() {


        return DateTimeFormatter.ofPattern("hh:mm a").
                format(this.menuZonedDateTime.withZoneSameInstant(this.menuZone));


    }

    /**
     * Gets menu zone.
     *
     * @return the menu zone
     */
    public ZoneId getMenuZone() {
        return menuZone;
    }

    /**
     * Sets menu zone.
     *
     * @param menuZone the menu zone
     */
    public void setMenuZone(ZoneId menuZone) {
        this.menuZone = menuZone;
    }


    @Override
    public String toString() {
        return "MyDateTime{" +
                "uTCZonedDateTime=" + uTCZonedDateTime +
                ", zonedLocalDateTime=" + zonedLocalDateTime +
                ", menuZonedDateTime=" + menuZonedDateTime +
                ", local_date_str='" + local_date_str + '\'' +
                ", local_time_str='" + local_time_str + '\'' +
                ", month=" + month +
                ", day=" + day +
                ", year=" + year +
                ", local_hour=" + local_hour +
                ", local_min=" + local_min +
                ", local_am_pm='" + local_am_pm + '\'' +
                ", ac=" + ac +
                ", formatter=" + formatter +
                ", menuZone=" + menuZone +
                '}';
    }
}
