package es.uc3m.tiw.model.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import es.uc3m.tiw.model.Mensaje;
import es.uc3m.tiw.model.Usuario;

public class MensajesDao implements IMensajes {

	private EntityManager em;
	private UserTransaction ut;
	
	public MensajesDao(EntityManager em, UserTransaction ut) {
		super();
		this.em = em;
		this.ut = ut;
	}
	public MensajesDao() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<Mensaje> findAllMessagesByUsuario(Usuario usuario){
		Usuario u = em.find(Usuario.class, usuario.getId());
		return em.createQuery("select m from Mensaje m where m.to=:to", Mensaje.class)
				.setParameter("to", usuario)
				.getResultList();

	}
	@Override
	public void createMensaje(Mensaje mensaje) throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, NotSupportedException {
		ut.begin();
		em.persist(mensaje);
		ut.commit();
		
	}
	
	
}
