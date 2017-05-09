package com.robvangastel.kwettermonitor;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.ConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Enterprise Application Client main class.
 *
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        MainForm mf = new MainForm();
//        mf.setVisible(true);
        
        Context context=null;  
        try {  
            Properties props = new Properties();  
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");  
            props.put(Context.PROVIDER_URL, "http-remoting://localhost:11010");   // NOTICE: "http-remoting" and port "8080"  
            props.put(Context.SECURITY_PRINCIPAL, "admin");  
            props.put(Context.SECURITY_CREDENTIALS, "admin");  
            props.put("jboss.naming.client.ejb.context", true);  

            context = new InitialContext(props);
            System.out.println("\n\tGot initial Context: "+context);  
            ConnectionFactory factory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");  // NOTICE  
        } catch (NamingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}