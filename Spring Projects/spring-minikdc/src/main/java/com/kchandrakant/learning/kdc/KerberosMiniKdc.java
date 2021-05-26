package com.kchandrakant.learning.kdc;

import org.apache.commons.io.FileUtils;
import org.springframework.security.kerberos.test.MiniKdc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

class KerberosMiniKdc {

	private static final String KRB_WORK_DIR = "C:\\Users\\kumchand0\\Apps\\sts-workspace\\Spring Projects\\spring-minikdc\\kdc";

	public static void main(String[] args) throws Exception {

		String[] config = MiniKdcConfigBuilder
				.builder()
				.workDir(prepareWorkDir())
				.confDir("minikdc-krb5.conf")
				.keytabName("example.keytab")
				.principals("client/localhost", "HTTP/localhost")
				.build();

		MiniKdc.main(config);
	}

	private static String prepareWorkDir() throws IOException {
		Path dir = Paths.get(KRB_WORK_DIR);
		File directory = dir.normalize().toFile();

		FileUtils.deleteQuietly(directory);
		FileUtils.forceMkdir(directory);
		return dir.toString();
	}
}