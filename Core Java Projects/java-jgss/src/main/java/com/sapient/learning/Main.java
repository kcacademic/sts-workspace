package com.sapient.learning;

import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;

public class Main {

	public static void main(String[] args) throws GSSException {
		
		byte[] clientToken = new byte[0];
		
		clientToken = clientContext().initSecContext(clientToken, 0, clientToken.length);
		
		
		byte[] serverToken = clientToken;
		
		serverToken = serverContext().acceptSecContext(serverToken, 0, serverToken.length);


	}
	
	private static GSSContext serverContext() throws GSSException {
		
		GSSManager manager = GSSManager.getInstance();
		
		GSSContext context = manager.createContext((GSSCredential)null);
		
		return context;
	}
	
	private static GSSContext clientContext() throws GSSException {
		
		GSSManager manager = GSSManager.getInstance();
		
		GSSName serverName = manager.createName("myServer", null);
		
		Oid krb5Oid = new Oid("1.2.840.113554.1.2.2");
		
		GSSContext context = 
			    manager.createContext(serverName,
			                          krb5Oid,
			                          null,
			                          GSSContext.DEFAULT_LIFETIME);
		context.requestMutualAuth(true);  // Mutual authentication
		context.requestConf(true);  // Will use encryption later
		context.requestInteg(true); // Will use integrity later
		
		return context;
		
		
	}

}