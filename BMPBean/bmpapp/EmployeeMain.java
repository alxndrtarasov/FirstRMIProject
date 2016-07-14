/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bmpapp;

import java.util.Collection;
import java.util.Iterator;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

/**
 *
 * @author Alex
 */
public class EmployeeMain {
    
    public static void main(String argv[]) throws Exception{
      Employee employee;
      InitialContext ic = new InitialContext();
      Object objRef = ic.lookup("java:global/EntBean/EmployeeBean");
      System.out.println(objRef);
      Object obj = PortableRemoteObject.narrow(objRef,EmployeeHome.class);
      
       System.out.println(obj);
       EmployeeHome home = (EmployeeHome)obj;
      //EmployeeHome home = (EmployeeHome)PortableRemoteObject.narrow(objRef,Employee.class);
      try{
        employee = home.create(4,"test",new Float(15.0));
      }catch(Exception e) {}
      try{
        employee = home.create(5,"test_3",new Float(50.0));
      }catch(Exception e) {}
      try{
        employee = home.create(6,"test_4",new Float(70.0));
      }catch(Exception e) {}
      Collection c = home.findAll();
      Iterator i = c.iterator();
      while(i.hasNext()) {
          Employee e = (Employee)i.next();
          System.out.println(e.getEmpNo()+" "+e.getEmpName()+" "+e.getSalary());
      }
    
    }
            
    
}
