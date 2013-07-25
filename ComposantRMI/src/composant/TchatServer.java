/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package composant;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Fruteau
 */
public interface TchatServer extends Remote{
    
    public void register( TchatClient c) throws RemoteException;
    public void dispatch(String msg) throws RemoteException;
    
}
