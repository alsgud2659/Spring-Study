package com.example.ex02.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ReservationVO {
    private String name;
    private String date;
    private String time;
    private String people;
}
