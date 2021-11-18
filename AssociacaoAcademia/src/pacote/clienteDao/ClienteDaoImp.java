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
import java.sql.Date;
import java.sql.Statement;

/**
 *
 * @author arthu
 */
public class ClienteDaoImp implements ClienteDao {

    private Connection conexao;
    private PreparedStatement preparaInstrucao;
    private ResultSet resultado;

    @Override
    public void salvarCliente(EntidadeCliente entidadeCliente) throws SQLException {

        preparaInstrucao = null;
        resultado = null;
        Integer matricula = null;

        String instrucao = "INSERT INTO public.cliente(\n"
                + "	nome, sobrenome, sexo, peso, altura, endereco, cidade_estado, data_treino)\n"
                + "	VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(instrucao, Statement.RETURN_GENERATED_KEYS);
            preparaInstrucao.setString(1, entidadeCliente.getNomeCliente());
            preparaInstrucao.setString(2, entidadeCliente.getSobreNomeCliente());
            preparaInstrucao.setString(3, entidadeCliente.getSexoCliente());
            preparaInstrucao.setString(4, entidadeCliente.getAlturaCliente());
            preparaInstrucao.setString(5, entidadeCliente.getPesoCliente());
            preparaInstrucao.setString(6, entidadeCliente.getEndereçoCliente());
            preparaInstrucao.setString(7, entidadeCliente.getCidadeEstadoCliente());
            preparaInstrucao.setDate(8, new Date(entidadeCliente.getDataTreino().getTime()));

            preparaInstrucao.executeUpdate();

            //código para pegar a chave primaria do BD
            resultado = preparaInstrucao.getGeneratedKeys();
            resultado.next();
            entidadeCliente.setMatricula(resultado.getInt(1));

        } catch (Exception e) {

            System.out.println("Erro ao salvar perfil! " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
            resultado.close();
            /*erro aqui na real*/
        }

    }

    @Override

    public void alterar(EntidadeCliente entidadeCliente) throws Exception {
        String instrucao = "UPDATE public.cliente SET nome=?, sobrenome=?, sexo=?, peso=?, altura=?, "
                + "endereco=?, cidade_estado=?, data_treino=? WHERE matricula=?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaInstrucao = conexao.prepareStatement(instrucao);
            preparaInstrucao.setString(1, entidadeCliente.getNomeCliente());
            preparaInstrucao.setString(2, entidadeCliente.getSobreNomeCliente());
            preparaInstrucao.setString(3, entidadeCliente.getSexoCliente());
            preparaInstrucao.setString(4, entidadeCliente.getPesoCliente());
            preparaInstrucao.setString(5, entidadeCliente.getAlturaCliente());
            preparaInstrucao.setString(6, entidadeCliente.getEndereçoCliente());
            preparaInstrucao.setString(7, entidadeCliente.getCidadeEstadoCliente());
            preparaInstrucao.setDate(8, new Date(entidadeCliente.getDataTreino().getTime()));
            preparaInstrucao.setInt(9, entidadeCliente.getMatricula());

            preparaInstrucao.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erro ao alterar perfil! " + e.getMessage());
        } finally {
            conexao.close();
            preparaInstrucao.close();
            resultado.close();
        }

    }

}
