package xyz.myfur.students.util;

public enum Day {
    SUNDAY(1){public String getName(Day day) {
        return "SUNDAY";
    }
    },
    MONDAY(2){
        public String getName(Day day) {
            return "MONDAY";
        }
    },
    TUESDAY(3){public String getName(Day day) {
        return "TUESDAY";
    }
    },
    WEDNESDAY(4){
        public String getName(Day day) {
            return "WEDNESDAY";
        }
    },
    THURSDAY(5){public String getName(Day day) {
        return "THURSDAY";
    }
    },
    FRIDAY(6){public String getName(Day day) {
        return "FRIDAY";
    }
    },
    SATURDAY(7){public String getName(Day day) {
        return "SATURDAY";
    }
    };

    private int dayId;

    Day(int dayId){
        this.dayId=dayId;
    }
    public abstract String getName(Day day);

    public int getId() {
        return dayId;
    }
}

