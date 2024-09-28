package clinic;

public class MedicalRecord {
    private Patient[] patients;
    private int size; //number of patient objects in the array


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

}
