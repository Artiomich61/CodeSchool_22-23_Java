public class Worker {
    private String initials;
    private String job;
    private short SYear;

    public Worker(){
        this.initials = null;
        this.job = null;
        this.SYear = 0;
    };
    public Worker(String initials, String job, short SYear) {
        this.initials = initials;
        this.job = job;
        this.SYear = SYear;
    }

    public String getInitials() {
        return initials;
    }

    public String getJob() {
        return job;
    }

    public short getSYear() {
        return SYear;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setSYear(short SYear) {
        this.SYear = SYear;
    }

}
