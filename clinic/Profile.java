package clinic;

public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    Profile(String fname, String lname, Date dob)
    {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    public boolean dobValid() {
        if (!this.dob.isValidDate()) {
            return false;
        }
        else if (this.dob.isToday()) {
            return false;
        }
        else if (this.dob.isFutureDate()) {
            return false;
        }
        return true;
    }

    public String getFirstName() {
        return this.fname;
    }

    public String getLastName() {
        return this.lname;
    }

    public Date getDob() {
        return this.dob;
    }

    @Override
    public boolean equals(Object patientProfile)
    {
        if (patientProfile instanceof Profile)
        {
            Profile profile = (Profile) patientProfile;
            return (profile.fname.equals(this.fname)
                    && profile.lname.equals(this.lname)
                    && profile.dob.equals(this.dob));
        }
        return false;
    }

    @Override
    public int compareTo(Profile patientProfile)
    {
        // compare last names
        if(patientProfile.lname.compareTo(this.lname) != 0) {
            return patientProfile.lname.compareTo(this.lname);
        }
        // if they are equal, compare first names
        else if(patientProfile.fname.compareTo(this.fname) != 0){
            return patientProfile.fname.compareTo(this.fname);
        }
        // if first names are equal compare dob
        else if(patientProfile.dob.compareTo(this.dob) != 0)
        {
            return patientProfile.dob.compareTo(this.dob);
        }
        // if everything is the same return 0
        return 0;
    }

    @Override
    public String toString()
    {
        return fname + " " + lname + " " + dob.toString();
    }
    //testbed main method for testing compareTo
    public static void main(String[] args) {
        Date date1 = new Date(2003, 11, 20);
        Profile profile1 = new Profile("John", "Doe", date1);
    }


}
