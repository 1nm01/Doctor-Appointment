package org.example.entity;

import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Doctor {
    private Integer id;
    private String name;
    private Speciality speciality;
    private TreeMap<TimeSlot, Boolean> slots;
    private Map<TimeSlot, List<Integer>> queue;

}
