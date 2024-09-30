package clinic;

public enum Provider {
    Patel (Location.Bridgewater, Specialty.Family),
    Lim (Location.Bridgewater, Specialty.Pediatrician),
    Zimnes (Location.Clark, Specialty.Family),
    Harper (Location.Clark, Specialty.Family),
    Kaur (Location.Princeton, Specialty.Allergist),
    Taylor (Location.Piscataway, Specialty.Pediatrician),
    Ramesh (Location.Morristown, Specialty.Allergist),
    Ceravolo (Location.Edison, Specialty.Pediatrician);

    private final Location location;
    private final Specialty specialty;

    Provider (Location location, Specialty specialty) {
        this.location = location;
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "[" + this.name().toUpperCase() + ", " + this.location.toString().toUpperCase() + ", " + this.location.getCounty() + " " + this.location.getZip() + ", " + this.specialty + "]";
    }

    public Location getLocation() {
        return location;
    }
    public Specialty getSpecialty(){
        return specialty;
    }

    public String getProvider() {
        return this.name();
    }
}
