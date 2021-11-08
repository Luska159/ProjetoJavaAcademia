/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.clienteDao;

import br.entidade.academia.EntidadeCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lucas Denofrio
 */
public class ClienteDaoImpTest {

    private EntidadeCliente cliente;
    private ClienteDao clienteDao;

    public ClienteDaoImpTest() {
        clienteDao = new ClienteDaoImp();
    }

   // @Test
    public void testSalvarCliente() throws Exception {
        System.out.println("salvarCliente");
        cliente = new EntidadeCliente("Larissa", "Rodrigues", "F", "50kg", "1.57m", "blablabla", "Palhoça SC", new Date());
        clienteDao.salvarCliente(cliente);
        assertNotNull(cliente.getMatricula());

    }

    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        buscarClienteBD();
        cliente.setNomeCliente("Malaquias");
        clienteDao.alterar(cliente);
    }

    public EntidadeCliente buscarClienteBD() throws SQLException {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet resultado = null;

        try {
            conn = FabricaConexao.abrirConexao();
            psmt = conn.prepareStatement("SELECT matricula, nome, sobrenome, sexo, peso, altura, endereco, cidade_estado, data_treino\n" +
"	FROM public.cliente;");
            resultado = psmt.executeQuery();
            if (resultado.next()) {
                cliente = new EntidadeCliente();
                cliente.setMatricula(resultado.getInt("matricula"));
                cliente.setNomeCliente(resultado.getString("nome"));
                cliente.setSobreNomeCliente(resultado.getString("sobrenome"));
                cliente.setSexoCliente(resultado.getString("sexo"));
                cliente.setPesoCliente(resultado.getString("peso"));
                cliente.setAlturaCliente(resultado.getString("altura"));
                cliente.setEndereçoCliente(resultado.getString("endereco"));
                cliente.setCidadeEstadoCliente(resultado.getString("cidade_estado"));
                cliente.setDataTreino(resultado.getDate("data_treino"));
           } else {
                testSalvarCliente();
            }
        } catch (Exception e) {
            System.out.println("Errou ao pesquisar BD" + e.getMessage());
    } finally {
        conn.close();
        psmt.close();
    }

        return cliente;
    }
}
