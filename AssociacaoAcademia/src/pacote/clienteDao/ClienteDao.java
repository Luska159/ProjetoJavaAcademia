
package pacote.clienteDao;

import br.entidade.academia.EntidadeCliente;
import java.sql.SQLException;


/**
 *
 * @author arthu
 */
public interface ClienteDao {
    
    public abstract void salvarCliente(EntidadeCliente entidadeCliente)throws SQLException;
    
    void alterar(EntidadeCliente entidadeCliente) throws Exception;
    
    

}
