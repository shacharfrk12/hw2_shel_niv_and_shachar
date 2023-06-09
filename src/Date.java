public class Date {
    protected int day;
    protected int month;
    protected int year;

    private static int minDay = 1, maxDay=31;
    private static int minMonth = 1, maxMonth=12;
    private static int minYear = -3999, maxYear = 3999;

    public Date(int year, int month, int day){
        if(day >= minDay && day <= maxDay)
            this.day = day;
        else this.day = 1;

        if(month >= minMonth && month <= maxMonth)
            this.month = month;
        else this.month = 1;

        if(year >= minYear && year <= maxYear)
            this.year = year;
        else this.year = 0;
    }

    @Override
    public String toString(){
        String dayStr = (this.day / 10) + "" + (this.day % 10);
        String monthStr = (this.month / 10) + "" + (this.month % 10);
        String yearStr = "";
        int year = this.year;
        if(year<0) {
            yearStr = "-";
            year = (-1)*year;
        }
        yearStr += (year / 1000) + "" + ((year % 1000) / 100) + "" + ((year % 100)/10) + "" + (year % 10);

        return (dayStr + "/" + monthStr + "/" + yearStr);
    }
    @Override
    public boolean equals(Object other){
        if(!(other instanceof Date))
            return false;
        else if(this.toString().equals(other.toString()) && this.day == ((Date) other).day &&
                this.month == ((Date) other).month && this.year == ((Date) other).year){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        int year = this.year;
        int sign = 1;
        if(year < 0) {
            year = (-1)*year;
            sign = -1;
        }

        return sign*(year * 10000 + month * 100 + day);
    }
    public void setDay(int day){
        if(day <= maxDay && day >= minDay)
            this.day = day;
    }
    public void setMonth(int month){
        if(month <= maxMonth && month >= minMonth)
            this.month = month;
    }
    public void setYear(int year){
        if(year <= maxYear && year >= minYear)
            this.year = year;
    }
}
