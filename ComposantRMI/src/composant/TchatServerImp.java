package composant;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author Fruteau
 */
public class TchatServerImp extends UnicastRemoteObject implements TchatServer{
    
    private ArrayList<TchatClient> tc;
    
    public TchatServerImp() throws RemoteException{
    };
    
    @Override
     public void register( TchatClient c){
         tc.add(c);
     };
    
    @Override
    public void dispatch(String msg) throws RemoteException{
        for(TchatClient client: tc){
            client.receive(msg);
        }
    };
    
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        TchatServerImp tchatS = new TchatServerImp();
        tchatS.tc= new ArrayList<TchatClient>();
        Naming.rebind("tchatS",tchatS); 
        System.out.println("Serveur actif");
    }   
}
