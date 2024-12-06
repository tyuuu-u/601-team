package com.shijiu.pojo;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;

@Data
public class TimeTest {
    int id;
    int uid;
    String createdAt;
    String updatedAt;
}
