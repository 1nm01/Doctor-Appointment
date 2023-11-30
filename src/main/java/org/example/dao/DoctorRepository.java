package org.example.dao;

import org.example.entity.Doctor;
import org.example.entity.Speciality;
import org.example.entity.TimeSlot;
import org.example.search.SortDoctors;
import org.example.search.SortDoctorsByStartTime;

import java.util.*;

public class DoctorRepository {
    private static final Map<Integer, Doctor> doctorData = new HashMap<>();

    public Doctor getDoctor(int id){
        if(doctorData.containsKey(id))
            return doctorData.get(id);
        return null;
    }
    public void addDoctor(Doctor doctor){
        if(doctor.getId() != null){
            if(!doctorData.containsKey(doctor.getId())){
                doctorData.put(doctor.getId(), doctor);
            }
            else{
                System.out.println("Doctor Already present");
            }
        }
        else{
            System.out.println("Id is Missing");
        }

    }

    public void addTimeSlot(Integer id, TimeSlot timeSlot){
        if(doctorData.containsKey(id)){
            Doctor doctor = doctorData.get(id);
            TreeMap<TimeSlot, Boolean> slots = doctor.getSlots();
            boolean putOrNot = true;
            for(TimeSlot slot: slots.keySet()){
                if(slot.equals(timeSlot)){
                    System.out.println("Duplicate Timeslot");
                    putOrNot = false;
                }
            }
            if(putOrNot){
                slots.put(timeSlot, true);
//                doctor.setSlots(slots);
//                doctorData.put(id, doctor);
            }
        }
        else{
            System.out.println("Doctor not present");
        }
    }

    public boolean bookDoctor(Doctor doctor, TimeSlot timeSlot) {
        for(TimeSlot slot : doctor.getSlots().keySet()) {
            if(slot.equals(timeSlot)){
                if(doctor.getSlots().get(slot)) {
                    doctor.getSlots().put(slot, false);
                    return true;
                } else {
                    System.out.println("Not Available");
                    return false;
                }

            }
        }
        System.out.println("No such slot for doctor");
        return false;
    }
    public List<Doctor> searchBySpecialization(Speciality speciality){
        List<Doctor> doctors = new ArrayList<>();
        for(Map.Entry<Integer, Doctor> entry: doctorData.entrySet()){
            if(entry.getValue().getSpeciality().equals(speciality) && !entry.getValue().getSlots().isEmpty()){
                doctors.add(entry.getValue());
            }
        }
        SortDoctors sortDoctors = new SortDoctorsByStartTime();
        return sortDoctors.sort(doctors);
    }

    public void unBookDoctor(Doctor doctor, TimeSlot timeSlot) {
        for(TimeSlot slot : doctor.getSlots().keySet()) {
            if(slot.equals(timeSlot)){
                if(doctor.getSlots().get(slot)) {
                    doctor.getSlots().put(slot, true);
                }
            }
        }

    }
}
