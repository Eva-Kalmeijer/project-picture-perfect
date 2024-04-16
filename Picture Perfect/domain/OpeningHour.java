package domain;
import java.time.LocalTime;
import java.time.DayOfWeek;

public class OpeningHour {
    private int openingId;
    private DayOfWeek openingDay;
    private LocalTime openingTime;
    private LocalTime closingTime;


    public OpeningHour(int openingId, DayOfWeek openingDay, LocalTime openingTime, LocalTime closingTime) { //constructor
        this.openingId = openingId;
        this.openingDay = openingDay;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public OpeningHour(OpeningHour source) { //constructor for modifying
        this.openingId = source.openingId;
        this.openingDay = source.openingDay;
        this.openingTime = source.openingTime;
        this.closingTime = source.closingTime;
    }


    public int getOpeningId() { //getter opening ID
        return this.openingId;
    }

    public void setOpeningId(int openingId) { //setter opening ID
        this.openingId = openingId;
    }

    public DayOfWeek getOpeningDay() { //getter opening day
        return this.openingDay;
    }

    public void setOpeningDay(DayOfWeek openingDay) { //setter opening day
        this.openingDay = openingDay;
    }

    public LocalTime getOpeningTime() { //getter opening time
        return this.openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) { //setter opening time
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() { //getter closing time
        return this.closingTime;
    }

    public void setClosingTime(LocalTime closingTime) { //setter closing time
        this.closingTime = closingTime;
    }

    public String toString() { //to string
        return this.openingId + "      " + this.openingDay + "    " + this.openingTime + "    " + this.closingTime + " ";
    }  

    

}
