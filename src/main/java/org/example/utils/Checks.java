package org.example.utils;

import org.example.entity.TimeSlot;

public class Checks {
    public static boolean checkHalfHour(TimeSlot timeSlot) {
        if(timeSlot.getEndHour() == timeSlot.getStartHour()){
            if(timeSlot.getEndMin() - timeSlot.getStartMin() == 30){
                return true;
            }
            else return false;
        }
        else {
            if(timeSlot.getEndHour() - timeSlot.getStartHour() == 1){
                if(timeSlot.getEndMin() + 60 - timeSlot.getStartMin() == 30) {
                    return true;
                }
                else{
                    return false;
                }
            }
            else return false;
        }
    }

    public static boolean validateMin(int min) {
        if(min < 60 && min >= 0){
            return true;
        }
        return false;
    }

    public static boolean validateHour(int hour) {
        if(hour>=0 && hour<24){
            return true;
        }
        return false;
    }
}
