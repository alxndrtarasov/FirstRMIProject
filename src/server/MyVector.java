/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexander
 */
public class MyVector extends Vector {

   static public MyVector fromResultSet(ResultSet rs) {
        MyVector result = new MyVector();
        try {
            while (rs.next()) {
                StringBuilder fromRS = new StringBuilder();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    fromRS.append(rs.getObject(i)).append(" ");
                }
                result.add(fromRS);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MyVector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
