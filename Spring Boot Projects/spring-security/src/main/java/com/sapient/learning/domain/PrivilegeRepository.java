package com.sapient.learning.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

	public Privilege findByName(String name);

}