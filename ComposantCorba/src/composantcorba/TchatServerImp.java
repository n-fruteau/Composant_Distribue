/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package composantcorba;

import java.util.ArrayList;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 *
 * @author Fruteau
 */
public class TchatServerImp extends TchatServerPOA{

    private ArrayList<TchatClientImp>  ltc;
    
    public void register(TchatClientImp c){
        ltc.add(c);
    }
    
    public void dispath(String msg){
        for(TchatClientImp tc : ltc)
            tc.send(msg);
    }
    
    public static void main(String[] args) throws InvalidName{
            ORB orb = ORB.init(args,null);
            POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPoa"));
            NamingContextExt naming = NamingContextExtHelper.narrow(orb.resolve_initial_references(("NameService")));
            TchatServerImp tsi = new TchatServerImp();
            
            org.omg.CORBA.Object bo = poa.servant_to_reference(tsi);
            TchatServer ts = TchatServerHelper.narrow(bo);
            naming.rebind(naming.to_name("TchatServer",ts));
            orb.run();
    }
    
}
