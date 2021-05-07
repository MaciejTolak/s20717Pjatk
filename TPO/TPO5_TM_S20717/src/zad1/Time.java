package zad1;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Time {

    public static  String passed(String dataOd, String dataDo){

        LocalDate ld1;
        LocalDate ld2;
        LocalDateTime ldt1;
        LocalDateTime ldt2;
        String patt = "d MMMM yyyy (EEEE)";
        String pattTime = "hh:mm";
        Locale.setDefault(Locale.ENGLISH);
        try{
            if(dataOd.contains("T")){
                String result = "Od ";
                ldt1 = LocalDateTime.parse(dataOd);
                ldt2 =  LocalDateTime.parse(dataDo);

                result += ldt1.format(DateTimeFormatter.ofPattern(patt, new Locale("pl")))+" godz. "+ldt1.format(DateTimeFormatter.ofPattern(pattTime, new Locale("pl"))) + " do " + ldt2.format(DateTimeFormatter.ofPattern(patt, new Locale("pl")))+" godz. "+ldt2.format(DateTimeFormatter.ofPattern(pattTime, new Locale("pl"))) + "\n";
                result += "- mija: "+ DaysPassedString((int)getDaysPassed(ldt1.toLocalDate(),ldt2.toLocalDate())) + ", "+WeeksPassedString((int)getDaysPassed(ldt1.toLocalDate(),ldt2.toLocalDate()))+"\n";
                result += "- godzin: "+getHoursPassed(ldt1, ldt2)+", minut: "+getMinutesPassed(ldt1,ldt2)+"\n";
                result += "- kalendarzowo: "+YearsPassedCalendarString(getYearsPassedByPeriod(ldt1.toLocalDate(),ldt2.toLocalDate()))+MonthsPassedCalendarString(getMonthsPassedByPeriod(ldt1.toLocalDate(),ldt2.toLocalDate()))+ DaysPassedCalendarString(getDaysPassedByPeriod(ldt1.toLocalDate(),ldt2.toLocalDate()));

                result = result.substring(0,result.length()-2);
                return result;
            }else{
                String result = "Od ";
                ld1 = LocalDate.parse(dataOd);
                ld2 = LocalDate.parse(dataDo);

                result += ld1.format(DateTimeFormatter.ofPattern(patt, new Locale("pl"))) + " do " + ld2.format(DateTimeFormatter.ofPattern(patt, new Locale("pl")))+"\n";
                result+="- mija: "+DaysPassedString((int)getDaysPassed(ld1,ld2))+", "+WeeksPassedString((int)getDaysPassed(ld1,ld2))+"\n";
                result+= "- kalendarzowo: "+YearsPassedCalendarString(getYearsPassedByPeriod(ld1,ld2))+MonthsPassedCalendarString(getMonthsPassedByPeriod(ld1,ld2))+ DaysPassedCalendarString(getDaysPassedByPeriod(ld1,ld2));

                result = result.substring(0,result.length()-2);
                return result;
            }
        }catch (Exception ex){

            return "*** "+ ex.getClass().getName() + ex.getMessage();
        }






    }

    public static long getDaysPassed(LocalDate dataOd, LocalDate dataDo){
        return ChronoUnit.DAYS.between(dataOd, dataDo);
    }
    public static long getHoursPassed(LocalDateTime dataOd, LocalDateTime dataDo){
        ZonedDateTime zt1 = ZonedDateTime.of(dataOd, ZoneId.of("Europe/Warsaw"));
        ZonedDateTime zt2 = ZonedDateTime.of(dataDo, ZoneId.of("Europe/Warsaw"));
        return ChronoUnit.HOURS.between(zt1,zt2);
    }
    public static long getMinutesPassed(LocalDateTime dataOd, LocalDateTime dataDo){
        ZonedDateTime zt1 = ZonedDateTime.of(dataOd, ZoneId.of("Europe/Warsaw"));
        ZonedDateTime zt2 = ZonedDateTime.of(dataDo, ZoneId.of("Europe/Warsaw"));
        return ChronoUnit.MINUTES.between(zt1,zt2);
    }

    public static int getDaysPassedByPeriod(LocalDate dataOd, LocalDate dataDo){
        Period p = Period.between(dataOd,dataDo);
        return p.getDays();
    }
    public static int getMonthsPassedByPeriod(LocalDate dataOd, LocalDate dataDo){
        Period p = Period.between(dataOd,dataDo);
        return p.getMonths();
    }
    public static int getYearsPassedByPeriod(LocalDate dataOd, LocalDate dataDo){
        Period p = Period.between(dataOd,dataDo);
        return p.getYears();
    }

    public static String DaysPassedString(int days){
        if(days == 1){
            return "1 dzień";
        }else{
            return days + " dni";
        }
    }
    public static String WeeksPassedString(int days){
        double weeks = ((double)days/7);
        return "tygodni "+String.format("%.2f",weeks );
    }

    public static String DaysPassedCalendarString(int days){
        if(days == 0){
            return "";
        }else if(days == 1){
            return "1 dzień, ";
        }else if (days>1){
            return days +" dni, ";
        }
        return "";
    }

    public static String MonthsPassedCalendarString(int months){
        if(months == 0){
            return "";
        }else if(months == 1){
            return "1 miesiąc, ";
        }else if(months>=2 && months<=4){
            return months+" miesiące, ";
        }else if(months>=5){
            return months+"miesięcy, ";
        }
        return "";
    }

    public static String YearsPassedCalendarString(int years){
        if(years == 0){
            return "";
        }else if(years == 1){
            return "1 rok, ";
        }else if(years>=2 && years<=4){
            return years +" lata, ";
        }else{
            return years + " lat, ";
        }
    }


}

