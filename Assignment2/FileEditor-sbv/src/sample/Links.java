package sample;

public enum Links {
    Student("https://github.com/salemAmassi"),
    Instructor("https://github.com/aashgar/PR320222");
    private String link;
     Links(String link){
        this.link = link;
    }
    public String getLink(){
         return link;
    }
}
