package com.ethereal.learning.client;

import java.nio.file.Paths;

import org.springframework.security.kerberos.client.KerberosRestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class SampleClient {

	private static String endpoint = "http://localhost:8080/endpoint";
	private static String confLocation = "src//main//resources//krb5.conf";
	private static String keytabLocation = "src//main//resources//example.keytab";
	private static String principal = "client/localhost";

	
	public static void main(String[] args) {

		System.setProperty("java.security.krb5.conf", Paths.get(confLocation).normalize().toAbsolutePath().toString());
		System.setProperty("sun.security.krb5.debug", "true");
		// disable usage of local kerberos ticket cache
		// System.setProperty("http.use.global.creds", "false");

		RestTemplate restTemplate = new KerberosRestTemplate(keytabLocation, principal);

		restTemplate.getForObject(endpoint, String.class);
	}
}