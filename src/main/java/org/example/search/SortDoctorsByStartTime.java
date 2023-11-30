package org.example.search;

import org.example.entity.Doctor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortDoctorsByStartTime implements SortDoctors{

    @Override
    public List<Doctor> sort(List<Doctor> doctors) {
        Collections.sort(doctors, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor o1, Doctor o2) {
                if(o1.getSlots().firstKey().getStartHour() == o2.getSlots().firstKey().getStartHour()){
                    return o1.getSlots().firstKey().getStartMin() - o2.getSlots().firstKey().getStartMin();
                }
                return o1.getSlots().firstKey().getStartHour() - o2.getSlots().firstKey().getStartHour();
            }
        });
        return doctors;
    }
}
