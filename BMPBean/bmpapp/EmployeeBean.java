package bmpapp;

import java.util.*;
import java.rmi.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.*;
import javax.naming.*;
import javax.ejb.*;
import oracle.jdbc.pool.*;

public class EmployeeBean implements EntityBean {

    public Integer empNo;

    public EntityContext ctx;
    private Connection conn = null;
    private PreparedStatement ps = null;
    private EmployeePK pk;
    private static final String dsName = "jdbc/OracleSample";

    private static final String insertStatement
            = "INSERT INTO EMP (EMPNO, ENAME, SAL) VALUES (?, ?, ?)";
    private static final String updateStatement
            = "UPDATE EMP SET ENAME=?, SAL=? WHERE EMPNO=?";
    private static final String deleteStatement
            = "DELETE FROM EMP WHERE EMPNO=?";
    private static final String findAllStatement
            = "SELECT EMPNO, ENAME, SAL FROM EMP";
    private static final String findByPKStatement
            = "SELECT EMPNO, ENAME, SAL FROM EMP WHERE EMPNO = ?";
    private static final String findByNameStatement
            = "SELECT EMPNO, ENAME, SAL FROM EMP WHERE ENAME = ?";
    // or you can define a variable specific to orion to implement finder-method:
    // or use <finder-method/> in orion-ejb-jar.xml
    public static final String findByNameQuery = "full: "
            + "SELECT EMPNO, ENAME, SAL FROM EMP WHERE ENAME = $1";

    public EmployeeBean() {
        // Empty constructor, don't initialize here but in the create().
        // passivate() may destroy these attributes in the case of pooling
    }

    public EmployeePK ejbCreate(Integer empNo, String empName, Float salary)
            throws CreateException {
        try {
            pk = new EmployeePK(empNo, empName, salary);
            conn = getConnection(dsName);
            ps = conn.prepareStatement(insertStatement);
            ps.setInt(1, empNo.intValue());
            ps.setString(2, empName);
            ps.setFloat(3, salary.floatValue());
            ps.executeUpdate();
            return pk;
        } catch (SQLException e) {
            System.out.println("Caught an exception 1 " + e.getMessage());
            throw new CreateException(e.getMessage());
        } catch (NamingException e) {
            System.out.println("Caught an exception 1 " + e.getMessage());
            throw new EJBException(e.getMessage());
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                throw new EJBException(e.getMessage());
            }
        }
    }

    public void ejbPostCreate(Integer empNo, String empName, Float salary)
            throws CreateException {
    }

    public EmployeePK ejbFindByPrimaryKey(EmployeePK pk)
            throws FinderException {
        if (pk == null || pk.empNo == null) {
            throw new FinderException("Primary key cannot be null");
        }
        try {
            conn = getConnection(dsName);
            ps = conn.prepareStatement(findByPKStatement);
            ps.setInt(1, pk.empNo.intValue());
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            if (rs.next()) {
                pk.empNo = new Integer(rs.getInt(1));
                pk.empName = new String(rs.getString(2));
                pk.salary = new Float(rs.getFloat(3));
            } else {
                throw new FinderException("Failed to select this PK");
            }
        } catch (SQLException e) {
            throw new FinderException(e.getMessage());
        } catch (NamingException e) {
            System.out.println("Caught an exception 1 " + e.getMessage());
            throw new EJBException(e.getMessage());
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                throw new EJBException(e.getMessage());
            }
        }
        return pk;
    }

    public Collection ejbFindAll() throws FinderException {
        //System.out.println("EmployeeBean.ejbFindAll(): begin");
        Vector recs = new Vector();
        try {
            conn = getConnection(dsName);
            ps = conn.prepareStatement(findAllStatement);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            int i = 0;
            while (rs.next()) {
                pk = new EmployeePK();
                pk.empNo = new Integer(rs.getInt(1));
                pk.empName = new String(rs.getString(2));
                pk.salary = new Float(rs.getFloat(3));
                recs.add(pk);
            }
        } catch (SQLException e) {
            throw new FinderException(e.getMessage());
        } catch (NamingException e) {
            System.out.println("Caught an exception 1 " + e.getMessage());
            throw new EJBException(e.getMessage());
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                throw new EJBException(e.getMessage());
            }
        }
        return recs;
    }

    public Collection ejbFindByName(String empName)
            throws FinderException {
        //System.out.println("EmployeeBean.ejbFindByName(): begin");
        if (empName == null) {
            throw new FinderException("Name cannot be null");
        }
        Vector recs = new Vector();
        try {
            conn = getConnection(dsName);
            ps = conn.prepareStatement(findByNameStatement);
            ps.setString(1, empName);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            int i = 0;
            while (rs.next()) {
                pk = new EmployeePK();
                pk.empNo = new Integer(rs.getInt(1));
                pk.empName = new String(rs.getString(2));
                pk.salary = new Float(rs.getFloat(3));
                recs.add(pk);
            }
        } catch (SQLException e) {
            throw new FinderException(e.getMessage());
        } catch (NamingException e) {
            System.out.println("Caught an exception 1 " + e.getMessage());
            throw new EJBException(e.getMessage());
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                throw new EJBException(e.getMessage());
            }
        }
        return recs;
    }

    public void ejbLoad() throws EJBException {
        //Container invokes this method to instruct the instance to
        //synchronize its state by loading it from the underlying database
        //System.out.println("EmployeeBean.ejbLoad(): begin");
        try {
            pk = (EmployeePK) ctx.getPrimaryKey();
            ejbFindByPrimaryKey(pk);
        } catch (FinderException e) {
            throw new EJBException(e.getMessage());
        }
    }

    public void ejbStore() throws EJBException {
        //Container invokes this method to instruct the instance to
        //synchronize its state by storing it to the underlying database
        //System.out.println("EmployeeBean.ejbStore(): begin");
        try {
            pk = (EmployeePK) ctx.getPrimaryKey();
            conn = getConnection(dsName);
            ps = conn.prepareStatement(updateStatement);
            ps.setString(1, pk.empName);
            ps.setFloat(2, pk.salary.floatValue());
            ps.setInt(3, pk.empNo.intValue());
            if (ps.executeUpdate() != 1) {
                throw new EJBException("Failed to update record");
            }
        } catch (SQLException e) {
            throw new EJBException(e.getMessage());
        } catch (NamingException e) {
            System.out.println("Caught an exception 1 " + e.getMessage());
            throw new EJBException(e.getMessage());
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                throw new EJBException(e.getMessage());
            }
        }
    }

    public void ejbRemove() throws RemoveException {
        //Container invokes this method befor it removes the EJB object
        //that is currently associated with the instance
        //System.out.println("EmployeeBean.ejbRemove(): begin");
        try {
            pk = (EmployeePK) ctx.getPrimaryKey();
            conn = getConnection(dsName);
            ps = conn.prepareStatement(deleteStatement);
            ps.setInt(1, pk.empNo.intValue());
            if (ps.executeUpdate() != 1) {
                throw new RemoveException("Failed to delete record");
            }
        } catch (SQLException e) {
            throw new RemoveException(e.getMessage());
        } catch (NamingException e) {
            System.out.println("Caught an exception 1 " + e.getMessage());
            throw new EJBException(e.getMessage());
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException e) {
                throw new EJBException(e.getMessage());
            }
        }
    }

    public void ejbActivate() {
        // Container invokes this method when the instance is taken out
        // of the pool of available instances to become associated with
        // a specific EJB object
        //System.out.println("EmployeeBean.ejbActivate(): begin");
    }

    public void ejbPassivate() {
        // Container invokes this method on an instance before the instance
        // becomes disassociated with a specific EJB object
        //System.out.println("EmployeeBean.ejbPassivate(): begin");
    }

    public void setEntityContext(EntityContext ctx) {
        //Set the associated entity context
        //System.out.println("EmployeeBean.setEntityContext(): begin");
        this.ctx = ctx;
    }

    public void unsetEntityContext() {
        //Unset the associated entity context
        //System.out.println("EmployeeBean.unsetEntityContext(): begin");
        this.ctx = null;
    }

    /**
     * methods inherited from EJBObject
     */
    public Integer getEmpNo() {
        pk = (EmployeePK) ctx.getPrimaryKey();
        return pk.empNo;
    }

    public String getEmpName() {
        pk = (EmployeePK) ctx.getPrimaryKey();
        return pk.empName;
    }

    public Float getSalary() {
        pk = (EmployeePK) ctx.getPrimaryKey();
        return pk.salary;
    }

    public void setEmpNo(Integer empNo) {
        pk = (EmployeePK) ctx.getPrimaryKey();
        pk.empNo = empNo;
    }

    public void setEmpName(String empName) {
        pk = (EmployeePK) ctx.getPrimaryKey();
        pk.empName = empName;
    }

    public void setSalary(Float salary) {
        pk = (EmployeePK) ctx.getPrimaryKey();
        pk.salary = salary;
    }

    public EJBHome getEJBHome() {
        return ctx.getEJBHome();
    }

    public Handle getHandle() throws RemoteException {
        return ctx.getEJBObject().getHandle();
    }

    public Object getPrimaryKey() throws RemoteException {
        return ctx.getEJBObject().getPrimaryKey();
    }

    public boolean isIdentical(EJBObject remote) throws RemoteException {
        return ctx.getEJBObject().isIdentical(remote);
    }

    public void remove() throws RemoveException, RemoteException {
        ctx.getEJBObject().remove();
    }

    /**
     * Private methods
     */
    private Connection getConnection(String dsName)
            throws SQLException, NamingException {
        DataSource ds = getDataSource(dsName);
        return ds.getConnection();
    }

    private DataSource getDataSource(String dsName) throws NamingException {

        DataSource ds = null;
        Context ic = new InitialContext();
        
        Object ref = ic.lookup(dsName);
        if (ref != null) {
            ds = (DataSource) ref;
        } else {
            try {
                //////////////////////////////////////
                OracleDataSource _ds = new OracleDataSource();
                _ds.setDriverType("thin");
                _ds.setServerName("win-student1");
                _ds.setPortNumber(1521);
                _ds.setDatabaseName("orcl");
                _ds.setUser("nb");
                _ds.setPassword("nb");
                //Connection conn = ds.getConnection();
                ic.bind(dsName, ds);
                System.out.println("!!!! new OracleDataSource");
                //////////////////////////////////////
                ds = _ds;
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return ds;
    }
}
