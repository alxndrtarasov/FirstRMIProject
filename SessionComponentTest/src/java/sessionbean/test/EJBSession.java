/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean.test;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

/**
 *
 * @author Alexander
 */
public interface EJBSession extends EJBObject{
    public String sayHello(String s) throws RemoteException;
}
