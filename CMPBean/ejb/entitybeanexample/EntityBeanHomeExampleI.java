/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.entitybeanexample;

/**
 *
 * @author Alex
 */
import java.util.Collection;
import java.rmi.*;
import javax.ejb.*;

public interface EntityBeanHomeExampleI extends EJBHome{
    public EntityBeanExampleI create(Integer id, String name) throws CreateException, RemoteException;
    public EntityBeanExampleI findByPrimaryKey(Integer id) throws FinderException, RemoteException;
    public Collection findByNameLike(String s) throws FinderException, RemoteException;;
}
