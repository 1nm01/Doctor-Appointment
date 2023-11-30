package org.example.service;

import org.example.dao.DoctorRepository;
import org.example.entity.Doctor;
import org.example.entity.Speciality;
import org.example.entity.TimeSlot;
import org.example.utils.Checks;

import java.util.List;

public class DoctorService {
    private DoctorRepository doctorRepository = new DoctorRepository();

    public void registerDoctor(Doctor doctor){
        doctorRepository.addDoctor(doctor);
    }

    public void addSlot(Integer id, String slot){
        String[] slots = slot.split("-");
        String[] start = slots[0].split(":");
        String[] end = slots[1].split(":");

        TimeSlot timeSlot = new TimeSlot(Integer.parseInt(start[0]), Integer.parseInt(start[1]), Integer.parseInt(end[0]), Integer.parseInt(end[1]));

        if(Checks.validateHour(timeSlot.getStartHour()) && Checks.validateHour(timeSlot.getEndHour()) && Checks.validateMin(timeSlot.getStartMin()) && Checks.validateMin(timeSlot.getEndMin())){
            if(Checks.checkHalfHour(timeSlot)){
                doctorRepository.addTimeSlot(id, timeSlot);
            }
            else {
                System.out.println("Time Slot format matched");
            }
        }
        else{
            System.out.println("Wrong format");
        }
    }

    public void searchDoctor(Speciality speciality){
        List<Doctor> doctors = doctorRepository.searchBySpecialization(speciality);
        for(Doctor doctor: doctors){
            System.out.println(doctor.getName());
        }
    }

    public boolean bookDoctor(Doctor doctor, TimeSlot timeSlot) {
        return doctorRepository.bookDoctor(doctor, timeSlot);
    }

    public void unBookDoctor(Doctor doctor, TimeSlot timeSlot) {
        doctorRepository.unBookDoctor(doctor, timeSlot);
    }

    public Doctor getDoctor(int id){
        return doctorRepository.getDoctor(id);
    }
}
