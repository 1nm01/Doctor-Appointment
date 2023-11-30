package org.example.dao;

import junit.framework.Assert;
import org.example.entity.Doctor;
import org.example.entity.Speciality;
import org.example.entity.TimeSlot;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.TreeMap;

public class DoctorRepositoryTest {
    private DoctorRepository doctorRepository = new DoctorRepository();
    private TreeMap<TimeSlot, Boolean> initializeSLots(){
        return new TreeMap<>(new Comparator<TimeSlot>() {
            @Override
            public int compare(TimeSlot o1, TimeSlot o2) {
                if(o1.getStartHour() == o2.getStartHour()){
                    return o1.getStartMin() - o2.getStartMin();
                }
                return o1.getStartHour() - o2.getStartHour();
            }
        });
    }
    @Test
    public void testAddDoctor() {
        Doctor doctor = new Doctor( 1,"Anmol", Speciality.Cardio, initializeSLots());
        doctorRepository.addDoctor(doctor);
        Assert.assertEquals(doctor, doctorRepository.getDoctor(1));
    }
}
