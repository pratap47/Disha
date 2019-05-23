package test.com.disha.tearcher;

public class ListItem {

    private String Name, purpose,studentphoneno;



    public ListItem(String Name, String purpose, String studentphoneno) {
        this.Name = Name;
        this.purpose = purpose;
        this.studentphoneno = studentphoneno;
    }

    public String getname() {
        return Name;
    }

    public void setname(String Name)
    {
        this.Name = Name;
    }

    public String getpupose() {
        return purpose;
    }

    public void setpurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getStudentphoneno() {
        return studentphoneno;
    }

    public void setStudentphoneno(String studentphoneno) {
        this.studentphoneno = studentphoneno;
    }


}
