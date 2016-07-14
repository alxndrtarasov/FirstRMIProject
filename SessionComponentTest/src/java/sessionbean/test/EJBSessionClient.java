/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean.test;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

/**
 *
 * @author Alexander
 */
public class EJBSessionClient {

    public static void main(String[] args) throws NamingException, RemoteException, CreateException {
        InitialContext ic = new InitialContext();
        Object ref = ic.lookup("java:global/SessionComponentTest/EJBSession!sessionbean.test.EJBSessionHome");
        EJBSessionHome ejbHome = (EJBSessionHome) PortableRemoteObject.narrow(ref, EJBSessionHome.class);
        EJBSession hello = ejbHome.create();
        String s =hello.sayHello("Vasya");
        System.out.println(s);
    }
}
