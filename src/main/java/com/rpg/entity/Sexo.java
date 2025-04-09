package com.rpg.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sexo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSexo;

    private String nome;
}