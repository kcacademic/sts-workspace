package com.sapient.learning;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.springframework.security.kerberos.test.MiniKdc;

public class Application {

	private static final String KRB_WORK_DIR = "C:\\Users\\kumchand0\\Apps\\sts-workspace\\Spring Projects\\spring-minikdc\\kdc";

	public static void main(String[] args) throws IOException, Exception {

		Properties conf = MiniKdc.createConf();
		conf.put(MiniKdc.DEBUG, true);

		MiniKdc kdc = new MiniKdc(conf, prepareWorkDir());

		kdc.start();

		File keyTab = new File(KRB_WORK_DIR + "\\example.keytab");
		kdc.createPrincipal(keyTab, "HTTP/localhost");
		//kdc.createPrincipal(keyTab, "client/localhost");
		//kdc.createPrincipal("HTTP/localhost", "password");
		kdc.createPrincipal("client/localhost", "password");
		
	}

	private static File prepareWorkDir() throws IOException {
		Path dir = Paths.get(KRB_WORK_DIR);
		File directory = dir.normalize().toFile();

		FileUtils.deleteQuietly(directory);
		FileUtils.forceMkdir(directory);
		return directory;
	}

}
