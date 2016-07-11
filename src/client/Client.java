/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import server.Hello;

public class Client {

    public static void main(String[] args) {
        System.setSecurityManager(new RMISecurityManager());
        try {
            Registry registry = LocateRegistry.getRegistry();
            Hello stub = (Hello) registry.lookup("Hello");
            String response = stub.sayHello("Stefan");
            System.out.println("response: " + response);
            for (Object each : stub.getObjects("1")) {
                System.out.println(each);
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
