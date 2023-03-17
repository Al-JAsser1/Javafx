package Students;


public  class Student  {
    private String id;
    private String name;
    private String major;
    private double grade;

    public Student(String id, String name,String major,double grade) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.grade = grade;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }


    protected void setGrade(double grade) {
        this.grade = grade;
    }

    public  String toString(){
        return  "Id: "+ this.getId()+
                "\tName: "+ this.getName()
                +"\tMajor: "+ this.getMajor()
                +"\tGrade: "+ this.getGrade();
    }




}
