package xyz.myfur.students.WebSite.Authorization;

public class Data{
    public String mouth;
    public String data;
    public String day;
    public String time;


    public Data(String mouth, String data, String day, String time) {
        this.mouth = mouth;
        this.data = data;
        this.day = day;
        this.time = time;
    }

    public String getMouth() {
        return mouth;
    }

    public String getData() {
        return data;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }
}
