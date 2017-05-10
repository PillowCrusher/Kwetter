import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by robvangastel on 9-5-2017.
 */
class TweetSender {

    // Set up all the default values
    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_DESTINATION = "jms/queue/kwetter";
    private static final String DEFAULT_MESSAGE_COUNT = "5";
    private static final String DEFAULT_USERNAME = "quickstartUser";
    private static final String DEFAULT_PASSWORD = "quickstartPwd1!";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://127.0.0.1:9080";

    void sendTweet(String message) {

        Context namingContext = null;

        try {
            String userName = System.getProperty("username", DEFAULT_USERNAME);
            String password = System.getProperty("password", DEFAULT_PASSWORD);

            // Set up the namingContext for the JNDI lookup
            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
            env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
            env.put(Context.SECURITY_PRINCIPAL, userName);
            env.put(Context.SECURITY_CREDENTIALS, password);
            namingContext = new InitialContext(env);

            // Perform the JNDI lookups
            String connectionFactoryString = System.getProperty("connection.factory", DEFAULT_CONNECTION_FACTORY);

            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(connectionFactoryString);

            String destinationString = System.getProperty("destination", DEFAULT_DESTINATION);
            Destination destination = (Destination) namingContext.lookup(destinationString);

            int count = Integer.parseInt(System.getProperty("message.count", DEFAULT_MESSAGE_COUNT));
            String content = System.getProperty("message.content", message);

            try (JMSContext context = connectionFactory.createContext(userName, password)) {
                    context.createProducer().send(destination, content);
            }
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        } finally {
            if (namingContext != null) {
                try {
                    namingContext.close();
                } catch (NamingException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
