package composant;

import java.rmi.*;

/**
 *
 * @author Fruteau
 */
public interface TchatClient extends Remote{
    
    public void connect( String srv) throws RemoteException;
    public void send( String msg) throws RemoteException;
    public void receive(String msg) throws RemoteException;
    //public void  main() throws RemoteException;
}
