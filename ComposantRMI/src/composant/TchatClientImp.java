/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package composant;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Fruteau
 */
public class TchatClientImp extends UnicastRemoteObject implements TchatClient{

    private TchatServer ts;
    private JTextArea receiveArea;
    private StringBuilder builder;
    
    public TchatClientImp() throws RemoteException{
    };
     
    @Override
    public void connect(String srv) throws RemoteException {
        try {
            this.ts = (TchatServer) Naming.lookup(srv);
            this.ts.register(this);
        } catch (NotBoundException ex) {
            Logger.getLogger(TchatClientImp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(TchatClientImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void send(String msg) throws RemoteException {
        ts.dispatch(msg); 
    }

    @Override
    public void receive(String msg) throws RemoteException {
      this.builder.append(msg + "\n");
      this.receiveArea.setText(this.builder.toString());
      this.receiveArea.setCaretPosition(this.builder.length());
    }
    
    public void addReceiveArea(JTextArea jta){
        this.receiveArea=jta;
        this.builder = new StringBuilder();
    };
    
   /*
    * public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        TchatClientImp tc = new TchatClientImp();
        tc.connect("rmi://10.57.110.16/tchatS");
        Scanner sc = new Scanner(System.in);
        while(true){
            String str = sc.nextLine();
            tc.send(str);
        }
    }
    */
    
    
}
