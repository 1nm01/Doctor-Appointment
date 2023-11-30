package org.example.service;

import org.example.dao.PatientRepository;
import org.example.entity.Doctor;
import org.example.entity.Patient;
import org.example.entity.TimeSlot;
import org.example.utils.Checks;
import org.example.utils.Pair;

import java.util.List;

public class PatientService {
    private static final PatientRepository patientRepository = new PatientRepository();

    private DoctorService doctorService = new DoctorService();
    public void registerPatient(Patient patient) {
        patientRepository.registerPatient(patient);
    }

    public void bookAppointment(Integer id, Doctor doctor, String slot) {
        String[] slots = slot.split("-");
        String[] start = slots[0].split(":");
        String[] end = slots[1].split(":");

        TimeSlot timeSlot = new TimeSlot(Integer.parseInt(start[0]), Integer.parseInt(start[1]), Integer.parseInt(end[0]), Integer.parseInt(end[1]));

        if(Checks.validateHour(timeSlot.getStartHour()) && Checks.validateHour(timeSlot.getEndHour()) && Checks.validateMin(timeSlot.getStartMin()) && Checks.validateMin(timeSlot.getEndMin())) {
            if(Checks.checkHalfHour(timeSlot)) {
                if (doctorService.bookDoctor(doctor, timeSlot)) {
                    if (patientRepository.bookAppointment(id, doctor, timeSlot)) {
                        System.out.println("booked");
                    } else {
                        System.out.println("Booking failed");
                        doctorService.unBookDoctor(doctor, timeSlot);
                    }
                }
                else {
                    System.out.println("Booking failed");
                }
            }
            else{
                System.out.println("Time Slot format not matched");
            }
        }
        else {
            System.out.println("Wrong format");
        }
    }

    public void printAppointment(int id){
        List<Pair<Doctor, TimeSlot>> appointmentsByPatient = patientRepository.getAppointmentsByPatient(id);
        for(Pair<Doctor, TimeSlot> pair : appointmentsByPatient) {
            System.out.println(String.format("Appointment book for doctor %s at %s:%s-%s:%s", pair.first.getName(), pair.second.getStartHour(), pair.second.getStartMin(), pair.second.getEndHour(), pair.second.getEndMin()));
        }
    }


}
