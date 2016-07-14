package bmpapp;
 
import java.rmi.*;
import java.util.*;
import javax.ejb.*;
 
public interface EmployeeHome extends EJBHome {
 
    public Employee create(Integer empNo, String empName, Float salary)
        throws CreateException, RemoteException;
 
    public Employee findByPrimaryKey(EmployeePK pk)
        throws FinderException, RemoteException;
 
    public Collection findByName(String empName)
        throws FinderException, RemoteException;
 
    public Collection findAll()
        throws FinderException, RemoteException;
}