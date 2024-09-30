package clinic;

public class Patient implements Comparable<Patient> {
    private Profile profile;
    private Visit visits; //a linked list of visits (completed appt.)

    public Patient (Profile profile, Visit visits) {
        this.profile = profile;
        this.visits = visits;
    }

    @Override
    public int compareTo(Patient patient) {
        if (this.profile.compareTo(patient.profile) == 0)
        {
            return 0;
        }
        else
        {
            return this.profile.compareTo(patient.profile);
        }
    }

    @Override
    public boolean equals(Object patient) {
        if(patient instanceof Profile)
        {
            Patient newPatient = (Patient) patient;
            return this.profile.equals(newPatient.profile);

        }
        return false;
    }

    @Override
    public String toString() {
        //list out all the appointments in visit
        Visit currVisitNode = this.visits;
        String appts = "";
        while (currVisitNode != null)
        {
            appts += currVisitNode.getAppointment().toString() + "\n";
            currVisitNode = currVisitNode.getVisit();

        }
        return appts;
    }

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

    public Profile getProfileThroughPatient() {
        return profile;
    }


    //traverse the linked list to compute the charge
    public int getTotalCharge() {
        int totalCharge = 0;
        Visit todaysVisit = this.visits;

        while (todaysVisit!=null) {
            totalCharge += todaysVisit.getAppointment().getSpecialtyByProvider().getCharge();
            todaysVisit = todaysVisit.getVisit();
        }
        return totalCharge;
    }
    
}
