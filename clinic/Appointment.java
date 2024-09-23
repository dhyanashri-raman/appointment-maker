package clinic;

public class Appointment implements Comparable <Appointment> {
    private Date date;
    private Timeslot timeslot; // will the timeslot be in military time because there is no mention of AM or PM?
    private Profile patient;
    private Provider provider;

    @Override
    public int compareTo(Appointment o) { // which instance variable should we compare for this method?
        int dateComparison = this.date.compareTo(o.date);

        // if the timeslot and date and both equal, that means they are the same appointment!
        if (dateComparison == 0) {
            int timeslotComparison = this.timeslot.compareTo(o.timeslot); // why is that warning there?
            return timeslotComparison;
        }

        return dateComparison; // if the timeslots are not the same, it'll return int difference in dates
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Appointment) { // checks to make sure the object o is of type Appointment
            Appointment appt = (Appointment) o;

            // making sure that each instance variable is not null and has the same value as the comparing Appointment object
            return (this.date != null ? this.date.equals(appt.date) : appt.date == null)
                    && (this.timeslot != null ? this.timeslot.equals(appt.timeslot) : appt.timeslot == null)
                    && (this.patient != null ? this.patient.equals(appt.patient) : appt.patient == null)
                    && (this.provider != null ? this.provider.equals(appt.provider) : appt.provider == null);

        }
        return false;
    }

    @Override
    public String toString() {
        return this.date + " " + this.timeslot + " " + this.patient + " " + this.provider;
    }

}