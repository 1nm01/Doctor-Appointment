package org.example.dao;
import org.example.entity.Doctor;
import org.example.entity.Patient;
import org.example.entity.TimeSlot;
import org.example.utils.Pair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientRepository {
    Map<Integer, Patient> patientData = new HashMap<>();
    public void registerPatient(Patient patient){
        if(patientData.containsKey(patient.getId())){
            System.out.println("Patient already registered!!");
        }
        else {
            patientData.put(patient.getId(), patient);
        }
    }

    public boolean bookAppointment(Integer id, Doctor doctor, TimeSlot timeSlot){
        if(patientData.containsKey(id)) {
            Patient patient = patientData.get(id);
            boolean isAllowed = true;
            for(Pair<Doctor, TimeSlot> booking: patient.getAppointments()) {
                if(booking.second.getStartHour() == timeSlot.getStartHour() && booking.second.getStartMin() == timeSlot.getStartMin()) {
                    System.out.println("Your slot is not available");

                    return false;
                }
            }

                List<Pair<Doctor, TimeSlot>> appointments = patient.getAppointments();
                appointments.add(new Pair<>(doctor ,timeSlot));
                return true;

        }
        else {
            System.out.println("No such Patient");
            return false;
        }
    }

    public List<Pair<Doctor, TimeSlot>> getAppointmentsByPatient(int id){
        return patientData.get(id).getAppointments();
    }
}
