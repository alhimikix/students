package xyz.myfur.students.util;

public enum Month {
    JANUARY(0){public String getName(){return "JANUARY";}},
    FEBRUARY(1){public String getName(){return "FEBRUARY";}},
    MARCH(2){public String getName(){return "MARCH";}},
    APRIL(3){public String getName(){return "APRIL";}},
    MAY(4){public String getName(){return "MAY";}},
    JUNE(5){public String getName(){return "JUNE";}},
    JULY(6){public String getName(){return "JULY";}},
    AUGUST(7){public String getName(){return "AUGUST";}},
    SEPTEMBER(8){public String getName(){return "SEPTEMBER";}},
    OCTOBER(9){public String getName(){return "OCTOBER";}},
    NOVEMBER(10){public String getName(){return "NOVEMBER";}},
    DECEMBER(11){public String getName(){return "DECEMBER";}};
    private int id;

    public int getId() {
        return id+1;
    }

    public abstract String getName();
    Month(int id){
        this.id=id;
    }

}
