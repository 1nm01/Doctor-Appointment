package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.example.utils.Pair;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Patient {
    private Integer id;
    private String name;
    private List<Pair<Doctor, TimeSlot>> appointments;

}
