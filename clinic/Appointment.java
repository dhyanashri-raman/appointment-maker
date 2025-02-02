package clinic;

/**
 * This class represents an appointment for a clinic. It includes details such as date, timeslot, patient, and provider.
 * It provides methods to compare appointments based on various criteria, such as date, location, and patient information.
 */
public class Appointment implements Comparable<Appointment> {
    private Date date;
    private Timeslot timeslot;
    private Profile patient;
    private Provider provider;

    /**
     * Constructor for creating an Appointment object.
     *
     * @param date      The date of the appointment.
     * @param timeslot  The timeslot of the appointment.
     * @param patient   The profile of the patient.
     * @param provider  The provider assigned to the appointment.
     */
    public Appointment(Date date, Timeslot timeslot, Profile patient, Provider provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    /**
     * Compares this appointment with another appointment based on the date and timeslot.
     *
     * @param appt The appointment to compare with.
     * @return     An integer representing the comparison result. Returns 0 if both date and timeslot are equal.
     */
    @Override
    public int compareTo(Appointment appt) {
        int dateComparison = this.date.compareTo(appt.date);

        // If the date is the same, compare the timeslot.
        if (dateComparison == 0) {
            int timeslotComparison = this.timeslot.compareTo(appt.timeslot);
            return timeslotComparison;
        }
        return dateComparison; // If the dates are not the same, return the difference in dates.
    }

    /**
     * Compares this appointment with another appointment based on the location (county), date, and timeslot.
     *
     * @param appt The appointment to compare with.
     * @return     An integer representing the comparison result based on location, date, and timeslot.
     */
    public int compareByLocation(Appointment appt) {
        int locationComparison = this.provider.getLocation().getCounty().compareTo(appt.provider.getLocation().getCounty());
        if (locationComparison != 0) {
            return locationComparison;
        }
        int dateComparison = this.date.compareTo(appt.date);
        if (dateComparison != 0) {
            return dateComparison;
        }
        return this.timeslot.compareTo(appt.timeslot);
    }

    /**
     * Compares this appointment with another appointment based on patient information,
     * including last name, first name, date of birth, and appointment date and time.
     *
     * @param appt The appointment to compare with.
     * @return     An integer representing the comparison result based on patient information.
     */
    public int compareByPatient(Appointment appt) {
        // Compare by last name.
        int lastNameComparison = this.patient.getLastName().compareTo(appt.patient.getLastName());
        // If last names are equal, compare by first name.
        if (lastNameComparison == 0) {
            int firstNameComparison = this.patient.getFirstName().compareTo(appt.patient.getFirstName());
            // If first names are equal, compare by date of birth.
            if (firstNameComparison == 0) {
                int dobComparison = this.patient.getDob().compareTo(appt.patient.getDob());
                // If date of birth is also equal, compare by appointment date and time.
                if (dobComparison == 0) {
                    int dateComparison = this.date.compareTo(appt.date);

                    // If dates are equal, compare by timeslot.
                    if (dateComparison == 0) {
                        return this.timeslot.compareTo(appt.timeslot);
                    }
                    return dateComparison;
                }
                return dobComparison;
            }
            return firstNameComparison;
        }
        return lastNameComparison;
    }

    /**
     * Compares this appointment with another appointment based on the date and provider information.
     *
     * @param appt The appointment to compare with.
     * @return     An integer representing the comparison result based on the date and provider information.
     */
    public int compareByAppointment(Appointment appt) {
        int timeslotComparison = this.date.compareTo(appt.date);
        // If the timeslot and date are both equal, that means they are the same appointment.
        if (timeslotComparison == 0) {
            int providerComparison = this.provider.compareTo(appt.provider);
            return providerComparison;
        }
        return timeslotComparison;
    }

    /**
     * Checks if this appointment is equal to another object.
     *
     * @param object The object to compare with.
     * @return       True if the object is an Appointment and all fields (date, timeslot, patient, and provider) are equal.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Appointment) { // Checks if the object is of type Appointment.
            Appointment appt = (Appointment) object;
            return (this.date != null ? this.date.equals(appt.date) : appt.date == null)
                    && (this.timeslot != null ? this.timeslot.equals(appt.timeslot) : appt.timeslot == null)
                    && (this.patient != null ? this.patient.equals(appt.patient) : appt.patient == null)
                    && (this.provider != null ? this.provider.equals(appt.provider) : appt.provider == null);
        }
        return false;
    }

    /**
     * Returns a string representation of the appointment details.
     *
     * @return A formatted string with date, timeslot, patient information, and provider details.
     */
    @Override
    public String toString() {
        return this.date.toString() + " " + this.timeslot.toString() + " " + this.patient.toString() + " [" + this.provider.getProvider().toUpperCase() + ", " + this.provider.getLocation() + ", " + this.getLocationByProvider().getCounty() + " " + this.getLocationByProvider().getZip() + ", " + this.getSpecialtyByProvider() + "]";
    }

    /**
     * Sets the timeslot for this appointment.
     *
     * @param timeslot The timeslot to set.
     */
    public void setTimeslot(Timeslot timeslot) {
        this.timeslot = timeslot;
    }

    /**
     * Gets the date of the appointment.
     *
     * @return The date of the appointment.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Gets the timeslot of the appointment.
     *
     * @return The timeslot of the appointment.
     */
    public Timeslot getTimeslot() {
        return this.timeslot;
    }

    /**
     * Gets the profile of the patient.
     *
     * @return The patient profile.
     */
    public Profile getProfile() {
        return this.patient;
    }

    /**
     * Gets the provider of the appointment.
     *
     * @return The provider assigned to the appointment.
     */
    public Provider getProvider() {
        return this.provider;
    }

    /**
     * Gets the location of the provider.
     *
     * @return The location of the provider.
     */
    public Location getLocationByProvider() {
        return this.provider.getLocation();
    }

    /**
     * Gets the specialty of the provider.
     *
     * @return The specialty of the provider.
     */
    public Specialty getSpecialtyByProvider() {
        return this.provider.getSpecialty();
    }
}