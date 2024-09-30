package clinic;

public class Appointment implements Comparable <Appointment> {
    private Date date;
    private Timeslot timeslot; 
    private Profile patient;
    private Provider provider;

    // Constructor
    public Appointment(Date date, Timeslot timeslot, Profile patient, Provider provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    @Override
    public int compareTo(Appointment o) { // ASK ABOUT THIS
        int dateComparison = this.date.compareTo(o.date);

        // if the timeslot and date and both equal, that means they are the same appointment!
        if (dateComparison == 0) {
            int timeslotComparison = this.timeslot.compareTo(o.timeslot); 
            return timeslotComparison;
        }

        return dateComparison; // if the timeslots are not the same, it'll return int difference in dates
    }

    public int compareByLocation(Appointment o) {
        // comparing the counties first
        int locationComparison = this.provider.getLocation().getCounty().compareTo(o.provider.getLocation().getCounty());

        // if the timeslot and date and both equal, that means they are the same appointment!
        if (locationComparison == 0) {
            int timeslotComparison = this.timeslot.compareTo(o.timeslot); 
            return timeslotComparison;
        }

        return locationComparison; // if the timeslots are not the same, it'll return int difference in dates
    }

    public int compareByAppointment(Appointment o) { 
        int timeslotComparison = this.date.compareTo(o.date);

        // if the timeslot and date and both equal, that means they are the same appointment!
        if (timeslotComparison == 0) {
            int providerComparison = this.provider.compareTo(o.provider); // is this because i didnt make Timeslot class yet?
            return providerComparison;
        }

        return timeslotComparison; // if the timeslots are not the same, it'll return int difference in dates
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
        return this.date.toString() + " " + this.timeslot.toString() + " " + this.patient.toString() + " [" + this.provider.getProvider().toUpperCase() + ", " + this.provider.getLocation() + ", " + this.getLocationByProvider().getCounty() + " " + this.getLocationByProvider().getZip() + ", " + this.getSpecialtyByProvider() + "]";
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }


    public Date getDate() {
        return this.date;
    }

    public Timeslot getTimeslot() {
        return this.timeslot;
    }

    public Profile getProfile() {
        return this.patient;
    }

    public Provider getProvider() {
        return this.provider;
    }

    public Location getLocationByProvider() {
        return this.provider.getLocation();
    }

    public Specialty getSpecialtyByProvider() {
        return this.provider.getSpecialty();
    }

}