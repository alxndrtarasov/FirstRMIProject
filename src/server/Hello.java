package server;

import java.rmi.*;
import java.util.Vector;

public interface Hello extends Remote {
	public String sayHello(String who) throws RemoteException;
	public Vector getObjects(String id) throws RemoteException;
}