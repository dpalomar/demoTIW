package es.uc3m.tiw.web.jms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import es.uc3m.tiw.model.Mensaje;
import es.uc3m.tiw.model.Usuario;


/**
 * Esta clase muestra distintos ejemplos de codigo.
 * Por un lado y comentado esta el codigo clasico de acceso a recursos JMS via JNDI
 * Por otro se hace uso de CDI y se muestra como inyectar recursos {@link Queue} y {@link JMSContext}
 * que hacen mucho mas simple la distribucion del codigo
 * Para que funcione correctamente esta clase debe ser Inyectada en el {@link UsuarioServlet} y no cargada manualmente.
 * Tambien hay que incluir un fichero <em>beans.xml</em> vacio en el directorio <strong>WEB-INF</strong>
 * <strong> {@link JMSContext} es parte de la implementacion de JEE7, por lo que si se usa JJEE6 hay que hacerlo
 * inyectando el {@link ConnectionFactory}</strong>
 * @author David Palomar
 * @see UsuarioServlet
 */
public class EscribirEnQueue {

	// Usando CDI no es necesario inyectar el ConnectionFactory
	 @Resource(name = "jms_tiwConnectionFactory")	
	 private  ConnectionFactory connectionFactory;
	
	  @Resource(lookup = "jms_mensajes")
	  private  Queue queue;
	 // @Inject
	 // private JMSContext jmsContext;

	  public void enviar(Mensaje mensaje)
	  {
		  
		  try {
			MessageProducer messageProducer;
			  ObjectMessage message;
			  Connection connection = connectionFactory.createConnection();
			  Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			  

			  messageProducer = session.createProducer(queue);
			  message = session.createObjectMessage();
			  message.setObject(mensaje);


			  messageProducer.send(message);
			  messageProducer.close();
			  session.close();
			  connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		  
		  
//FIXME: Codigo legado  de acceso  tradicional via JNDI
		  
//	    MessageProducer messageProducer;
//	    ObjectMessage mensaje;
	    
//	    try
//	    {
////	        ConnectionFactory connectionFactory = null;
////	        
////	        //Get the JNDI Context
////	  
////	            Context jndiContext = new InitialContext();
////	             
////	            //Create the Connection Factory
////	            connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/_tiwConnectionFactory");
////	            Queue queue = (Queue) jndiContext.lookup("jms/mensajes"); 
//	
//	      Connection connection = connectionFactory.createConnection();
//	      Session session = connection.createSession(false,
//	        Session.AUTO_ACKNOWLEDGE);
//	      
//	
//	      messageProducer = session.createProducer(queue);
//	      mensaje = session.createObjectMessage();
//	      mensaje.setObject(direccion);
//
//	
//	      messageProducer.send(mensaje);
//	      messageProducer.close();
//	      session.close();
//	      connection.close();
//	    
//	    }
//	    catch (JMSException e)
//	    {
//	      e.printStackTrace();
//	    }
	   
		// ESTO SOLO CON JEE7	jmsContext.createProducer().send(queue, mensaje);
		
	  }

	}