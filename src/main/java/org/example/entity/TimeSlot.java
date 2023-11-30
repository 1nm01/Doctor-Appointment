package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Objects;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TimeSlot {
    int startHour;
    int startMin;
    int endHour;
    int endMin;
}
