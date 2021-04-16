package com.ethereal.learning.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

	public Organization findByName(String name);

}