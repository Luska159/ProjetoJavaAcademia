/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.clienteDao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author arthu
 */
public class FabricaConexao {
    public static Connection abrirConexao() throws Exception{
        Class.forName("org.postgresql.Driver");
        
        return DriverManager.getConnection("jdbc:postgresql://kesavan.db.elephantsql.com:5432/rquolwug","rquolwug","a3iKrxsbtj_QkfWnr3PutenBjMMIBDWJ");
        
    }
    
        
}
