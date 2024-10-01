package clinic;

/**
 * Represents the time slots available for scheduling appointments.
 */
public enum Timeslot {
    SLOT1 (9, 0),
    SLOT2 (10, 45),
    SLOT3 (11, 15),
    SLOT4 (13, 30),
    SLOT5 (15, 0),
    SLOT6 (16, 15);

    private final int hour;
    private final int minute;

    /**
     * Constructor for the Timeslot enum.
     * @param hour   The hour of the time slot.
     * @param minute The minute of the time slot.
     */
    Timeslot (int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Returns a string representation of the time slot in a 12-hour format.
     * @return A formatted string representing the time of the slot.
     */
    @Override
    public String toString() {
        String minuteString = String.format("%02d", this.minute);
        if (hour == 12) {
            return "12:" + minuteString + " PM";
        } else if (hour > 12) {
            return (hour - 12) + ":" + minuteString + " PM";
        } else {
            return hour + ":" + minuteString + " AM";
        }
    }
}