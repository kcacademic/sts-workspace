package com.sapient.learning.domain;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class Car {
	private UUID id;
	private String name;
	private LocalDate releaseDate;
}