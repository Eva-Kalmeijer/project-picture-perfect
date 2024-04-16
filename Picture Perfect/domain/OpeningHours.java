package domain;
import java.util.ArrayList;
import java.util.List;

public class OpeningHours {
    private List<OpeningHour> openingHourList;

    public OpeningHours() { //constructor
        this.openingHourList = new ArrayList<>();
    }

    public OpeningHour getOpeningHour(int index) { //getter for opening hour
        return new OpeningHour(openingHourList.get(index));
    }

    public void setOpeningHour(int index, OpeningHour openingHour) { //setter for opening hour
        this.openingHourList.set(index, new OpeningHour(openingHour));
    }

    public void addOpeningHour(OpeningHour openingHour) { 
        this.openingHourList.add(new OpeningHour(openingHour));
    }

    public String toString() { //to string function
        String temp = "";
        for (OpeningHour openingHour : openingHourList) {
            temp += openingHour.toString() + "\n";
        }
        return temp;

    }
}
