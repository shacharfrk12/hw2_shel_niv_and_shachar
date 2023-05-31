public class DateTime extends Date{
    private int hour;
    private int minute;

    private static int minHour = 0, maxHour = 23;
    private static int minMinute = 0, maxMinute = 59;
    public DateTime(int year, int month, int day, int hour, int minute){
        super(year, month, day);
        if(minute <= maxMinute && minute >= minMinute)
            this.minute = minute;
        else this.minute = 0 ;
        if(hour <= maxHour && hour >= minHour)
            this.hour = hour ;
        else this.hour = 0 ;
    }
    @Override
    public String toString(){
       String date = super.toString();
       return (date + " " + (hour / 10) + "" + (hour % 10) + ":" + (minute / 10) + (minute % 10));
    }
    @Override
    public boolean equals(Object other){
        if(!(other instanceof DateTime))
            return false;
        else if(this.day == ((DateTime) other).day && this.month == ((DateTime) other).month &&
                this.year == ((DateTime) other).year && this.hour == ((DateTime) other).hour &&
                this.minute == ((DateTime) other).minute){
            return true;
        }
        else return false;
    }
    @Override
    public int hashCode(){
        int date = super.hashCode();
        int sign = 1 ;
        if(date < 0){
            sign = -1;
            date = (-1) * date;
        }
        return sign*((date * 10000) + (hour * 100) + minute);
    }
    public void setHour(int hour){
        if(hour <= maxHour && hour >= minHour)
            this.hour = hour;
    }
    public void setMinute(int minute){
        if(minute <= maxMinute && minute >= minMinute)
            this.minute = minute;
    }
}
