package es.uc3m.tiw.model.daos;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import es.uc3m.tiw.model.Mensaje;
import es.uc3m.tiw.model.Usuario;

public interface IMensajes {

	public abstract List<Mensaje> findAllMessagesByUsuario(Usuario usuario);

	public abstract void createMensaje(Mensaje mensaje) throws SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException, NotSupportedException;

}