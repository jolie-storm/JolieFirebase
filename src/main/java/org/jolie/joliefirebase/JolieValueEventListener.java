/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jolie.joliefirebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import jolie.runtime.FaultException;
import jolie.runtime.Value;
import joliex.java.Callback;
import joliex.java.Service;
import joliex.java.ServiceFactory;

/**
 *
 * @author bmasc
 */
public class JolieValueEventListener implements ValueEventListener {

    private URI location;
    private Service service;
    private String collection;

    public JolieValueEventListener(String serviceAddress, String collection) {
        this.collection = collection;
        ServiceFactory serviceFactory = new ServiceFactory();
        location = URI.create(serviceAddress);
        try {
            service = serviceFactory.create(location, "sodep", Value.create());
        } catch (IOException ex) {
            Logger.getLogger(JolieChildEventListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onDataChange(DataSnapshot ds) {
        Value valueToBeSent = parseFireBaseValue(ds);

        service.callOneWay("onDataChange", valueToBeSent, new Callback() {
            @Override
            public void onSuccess(Value value) {

            }

            @Override
            public void onFault(FaultException fe) {
                System.out.println("Fault");
            }

            @Override
            public void onError(IOException ioe) {
                System.out.println("Fault");
            }
        });
    }

    @Override
    public void onCancelled(DatabaseError de) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Value parseFireBaseValue(DataSnapshot ds) {
        Value v = Value.create();
        Iterable<DataSnapshot> interactor = ds.getChildren();
        v.getFirstChild("collection").setValue(this.collection);
        interactor.forEach(new Consumer<DataSnapshot>() {
            @Override
            public void accept(DataSnapshot t) {
         
              v.getFirstChild(t.getKey()).deepCopy(parseHashMap((HashMap<String, Object>)t.getValue()));
        
            }

        });
        return v;
    }
    
    private Value parseHashMap (HashMap<String, Object> hashMap){
      Value v1 = Value.create();
      hashMap.forEach((k, u) -> {
                    if (u instanceof String){
                       v1.getFirstChild(k).setValue((String)u);
                    }
                    
                    if (u instanceof HashMap){
                       
                       v1.getFirstChild(k).deepCopy(parseHashMap((HashMap<String, Object>)u));
                    }
                    
                });
       return v1;
    }
    
}
