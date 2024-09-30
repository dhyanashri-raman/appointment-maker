package clinic;

public class MedicalRecord {
    private Patient[] patients;
    private int size; //number of patient objects in the array


    public Patient[] getPatientArray()
    {
        return patients;
    }
    public int getPatientArrSize()
    {
        return size;
    }
    public MedicalRecord()
    {
        patients = new Patient[4];
        size = 0;
    }
    private void grow()
    {
        if(size == patients.length)
        {
            patients = new Patient[size + 4];
        }
    }

    public Patient getPatient(int i)
    {
        if(size != 0)
        {
            return patients[i];
        }
        return null;
    }

    public void add(Patient newPatient)  {
        //make sure the array is not full yet
        if(size != patients.length) {
            patients[size] = newPatient;
            size++;
            return;
        }
        else
        {
            grow();
            add(newPatient);
        }
    }

    public int containsPatient (Profile patient) {
        for (int i = 0; i < size; i++)
        {
            if(patients[i].getProfileThroughPatient().equals(patient))
            {
                return i;
            }
        }
        return -1;
    }

}
