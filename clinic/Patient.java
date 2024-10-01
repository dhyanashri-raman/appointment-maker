package clinic;

/**
 * The Patient class represents a patient with a profile and a list of visits (completed appointments).
 * It implements Comparable to allow for ordering based on the patient's profile.
 */
public class Patient implements Comparable<Patient> {
    private Profile profile; // The Profile object representing the patient's information
    private Visit visits; // A linked list of visits (completed appointments) for the patient

    /**
     * Constructor to create a Patient object with a specified profile and list of visits.
     *
     * @param profile The Profile object for the patient
     * @param visits The linked list of visits for the patient
     */
    public Patient (Profile profile, Visit visits) {
        this.profile = profile; // Initializes the profile of the patient
        this.visits = visits; // Initializes the visits of the patient
    }

    /**
     * Compares this Patient object with another Patient object based on their profiles.
     *
     * @param patient The Patient object to compare with
     * @return 0 if profiles are equal; otherwise, returns the comparison result of profiles
     */
    @Override
    public int compareTo(Patient patient) {
        if (this.profile.compareTo(patient.profile) == 0) {
            return 0;
        } else {
            return this.profile.compareTo(patient.profile);
        }
    }

    /**
     * Checks if this Patient object is equal to another object.
     *
     * @param patient The object to compare
     * @return true if the object is a Patient and has the same profile; otherwise, false
     */
    @Override
    public boolean equals(Object patient) {
        if(patient instanceof Profile) {
            Patient newPatient = (Patient) patient;
            return this.profile.equals(newPatient.profile);
        }
        return false;
    }

    /**
     * Returns a string representation of all appointments in the patient's visit list.
     *
     * @return A string listing all the appointments for this patient
     */
    @Override
    public String toString() {
        Visit currVisitNode = this.visits;
        String appts = "";
        while (currVisitNode != null) {
            appts += currVisitNode.getAppointment().toString() + "\n";
            currVisitNode = currVisitNode.getVisit();
        }
        return appts;
    }

    /**
     * Adds a new visit to the patient's list of visits.
     *
     * @param newVisit The Visit object to be added to the patient's visit list
     */
    public void addVisit(Visit newVisit) {
        if (visits == null) {
            visits = newVisit;
        } else {
            Visit current = visits;
            while (current.getVisit() != null) {
                current = current.getVisit();
            }
            current.add(newVisit);
        }
    }

    /**
     * Retrieves the profile associated with this Patient object.
     *
     * @return The Profile object for this patient
     */
    public Profile getProfileThroughPatient() {
        return profile;
    }

    /**
     * Computes the total charge for all visits associated with this patient.
     *
     * @return The total charge calculated from all appointments
     */
    public int getTotalCharge() {
        int totalCharge = 0;
        Visit todaysVisit = this.visits;

        while (todaysVisit != null) {
            totalCharge += todaysVisit.getAppointment().getSpecialtyByProvider().getCharge();
            todaysVisit = todaysVisit.getVisit();
        }
        return totalCharge;
    }
}
