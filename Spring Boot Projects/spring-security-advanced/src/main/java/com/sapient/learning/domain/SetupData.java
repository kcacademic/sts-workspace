package com.sapient.learning.domain;

import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SetupData {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	private PasswordEncoder encoder;

	@PostConstruct
	public void init() {
		initOrganizations();
		initPrivileges();
		initUsers();
	}

	private void initUsers() {
        final Privilege privilege1 = privilegeRepository.findByName("ROLE_USER");
        final Privilege privilege2 = privilegeRepository.findByName("ROLE_ADMIN");
        
        final User user1 = new User();
        user1.setUsername("john");
        user1.setPassword(encoder.encode("123"));
        user1.setPrivileges(new HashSet<Privilege>(Arrays.asList(privilege1)));
        user1.setOrganization(organizationRepository.findByName("USER"));
        userRepository.save(user1);
        
        final User user2 = new User();
        user2.setUsername("tom");
        user2.setPassword(encoder.encode("111"));
        user2.setPrivileges(new HashSet<Privilege>(Arrays.asList(privilege1, privilege2)));
        user2.setOrganization(organizationRepository.findByName("ADMIN"));
        userRepository.save(user2);
	}

	private void initOrganizations() {
		final Organization org1 = new Organization("USER");
		organizationRepository.save(org1);
		
		final Organization org2 = new Organization("ADMIN");
		organizationRepository.save(org2);
	}

	private void initPrivileges() {
		final Privilege privilege1 = new Privilege("ROLE_USER");
		privilegeRepository.save(privilege1);
		
		final Privilege privilege2 = new Privilege("ROLE_ADMIN");
		privilegeRepository.save(privilege2);
	}
}