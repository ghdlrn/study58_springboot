package com.membertest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "member")
@Setter @Getter @ToString
@Builder @AllArgsConstructor @NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    private int age;

    private String phone;

    private String address;
}
