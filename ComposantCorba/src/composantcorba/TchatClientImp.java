/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package composantcorba;

import org.omg.CORBA.ORBPackage.InvalidName;

/**
 *
 * @author Fruteau
 */
public class TchatClientImp extends TchatClientPOA{
    
    private TchatServerImp ts;
    private StringBuilder builder;
 

    public void connect(String srv){
        this.ts.register(this);
        
    }

    public void send(String msg){
        this.ts.dispath(msg);
    }

    public void receive(String msg){
      this.builder.append(msg + "\n");
      System.out.println(this.builder.toString());
    }
    
    public static void main(String[] args) throws InvalidName{
    
    }
    
}
