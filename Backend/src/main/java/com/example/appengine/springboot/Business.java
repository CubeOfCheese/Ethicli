public class Business {
    String name;
    boolean certified;
    String bcorpProfile;
    String website;
    int year;
    double overallScore;

    public Business() {
        this.name = null;
        this.certified = false;
        this.bcorpProfile = null;
        this.website = null;
        this.year = 0;
        this.overallScore = 0;
    }

    public Business(String name, boolean certified,
                    String bcorpProfile, String website,
                    int year, double overallScore) {
        this.name = name;
        this.certified = certified;
        this.bcorpProfile = bcorpProfile;
        this.website = website;
        this.year = year;
        this.overallScore = overallScore;
    }

    public String getName() {
        return name;
    }

    public boolean isCertified() {
        return certified;
    }

    public String getBcorpProfile() {
        return bcorpProfile;
    }

    public String getWebsite() {
        return website;
    }

    public int getYear() {
        return year;
    }

    public double getOverallScore() {
        return overallScore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCertified(boolean certified) {
        this.certified = certified;
    }

    public void setBcorpProfile(String bcorpProfile) {
        this.bcorpProfile = bcorpProfile;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setOverallScore(double overallScore) {
        this.overallScore = overallScore;
    }

    public void display() {
        System.out.println("Name:      " + this.name);
        System.out.println("Certified: " + this.certified);
        System.out.println("BCProfile: " + this.bcorpProfile);
        System.out.println("Website:   " + this.website);
        System.out.println("Year:      " + this.year);
        System.out.println("Score:     " + this.overallScore);
    }
}

