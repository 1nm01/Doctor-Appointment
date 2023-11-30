package org.example;


import org.example.entity.Doctor;
import org.example.entity.Patient;
import org.example.entity.Speciality;
import org.example.entity.TimeSlot;
import org.example.service.DoctorService;
import org.example.service.PatientService;
import org.example.utils.Pair;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;


/**
 * Hello world!
 *
 */
public class App 
{
    private static TreeMap<TimeSlot, Boolean> initializeSLots(){
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
    public static void main( String[] args )
    {
        DoctorService doctorService = new DoctorService();

        doctorService.registerDoctor(new Doctor(1, "Anmol", Speciality.Cardio, initializeSLots()));
        doctorService.registerDoctor(new Doctor(2, "Gunjan", Speciality.Cardio, initializeSLots()));
        doctorService.addSlot(1, "09:50-10:20");
        doctorService.searchDoctor(Speciality.Cardio);

        doctorService.addSlot(2, "09:40-10:10");
        doctorService.addSlot(2, "11:00-11:30");
        doctorService.searchDoctor(Speciality.Cardio);
        Patient patient1 = new Patient(1, "p1", new ArrayList<Pair<Doctor, TimeSlot>>());
        PatientService patientService = new PatientService();
        patientService.registerPatient(patient1);
        patientService.bookAppointment(1, doctorService.getDoctor(2), "09:40-10:10");
        patientService.bookAppointment(1, doctorService.getDoctor(2), "11:00-11:30");
        patientService.printAppointment(1);


    }

}
