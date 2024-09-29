package clinic;

public enum Timeslot {
    SLOT1 (9, 0),
    SLOT2 (10, 45),
    SLOT3 (11, 15),
    SLOT4 (13, 30),
    SLOT5 (15, 0),
    SLOT6 (16, 15);

    private final int hour;
    private final int minute;

    Timeslot (int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    @Override
    public String toString() {
        if (hour>12) { //PM
            return this.hour-12 + ":" + this.minute + "PM";
        }
        else { //AM
            return this.hour + ":" + this.minute + "AM";
        }
    }

}
