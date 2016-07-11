package server;

import java.rmi.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Vector;

public class HelloImpl implements Hello {

    @Override
    public String sayHello(String who) {
        return "hello, dear " + who;
    }

    @Override
    public Vector getObjects(String id) {
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Locale.setDefault(Locale.US);
            System.out.println("Class loaded");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@edu-netcracker.com:1520:xe", "unc16_user17", "unc16_user17");
            System.out.println("Connection established");
            PreparedStatement stat = con.prepareStatement("SELECT * FROM pers WHERE pers_id = ?");
            stat.setString(1, "1");
            rs = stat.executeQuery();            
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        if (rs == null) {
            return null;
        } else {
            return MyVector.fromResultSet(rs);
        }
    }
}
