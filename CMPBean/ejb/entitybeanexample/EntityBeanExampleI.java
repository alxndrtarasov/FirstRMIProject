/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.entitybeanexample;

import java.rmi.*;
import javax.ejb.*;
 
public interface EntityBeanExampleI extends EJBObject{
     
     public Integer getId() throws RemoteException;
     public void  setId(Integer id) throws RemoteException;
 
     public String getName() throws RemoteException;
     public void  setName(String name) throws RemoteException;
}
