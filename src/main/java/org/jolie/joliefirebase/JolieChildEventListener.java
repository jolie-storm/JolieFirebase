/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jolie.joliefirebase;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import jolie.Jolie;
import jolie.net.CommChannel;
import jolie.net.CommCore;
import jolie.net.CommMessage;
import jolie.net.SocketCommChannel;
import jolie.net.SocketCommChannelFactory;
import jolie.net.ext.CommChannelFactory;
import jolie.net.ports.OutputPort;
import jolie.process.RequestResponseProcess;
import jolie.runtime.FaultException;
import jolie.runtime.InvalidIdException;
import jolie.runtime.JavaService;
import jolie.runtime.OneWayOperation;
import jolie.runtime.Value;
import jolie.runtime.typing.Type;
import joliex.java.Callback;
import joliex.java.Service;
import joliex.java.ServiceFactory;
import joliex.java.impl.SocketSodepService;

/**
 *
 * @author bmasc
 */
public class JolieChildEventListener implements ChildEventListener {

    private URI location;
    private Service service;
    private String collection;

    public JolieChildEventListener(String serviceAddress, String collection) {
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
    public void onChildAdded(DataSnapshot ds, String string) {

        Value valueToBeSent = parseFireBaseValue(ds);

        service.callOneWay("onChildAdded", valueToBeSent, new Callback() {
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
    public void onChildChanged(DataSnapshot ds, String string) {

        Value valueToBeSent = parseFireBaseValue(ds);

        service.callOneWay("onChildChanged", valueToBeSent, new Callback() {
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
    public void onChildRemoved(DataSnapshot ds) {
        Value valueToBeSent = parseFireBaseValue(ds);

        service.callOneWay("onChildRemoved", valueToBeSent, new Callback() {
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
    public void onChildMoved(DataSnapshot ds, String string) {

        Value valueToBeSent = parseFireBaseValue(ds);
        service.callOneWay("onChildMoved", valueToBeSent, new Callback() {
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

        Value valueToBeSent = Value.create();

        valueToBeSent.getFirstChild("error").setValue(de.getCode());
        valueToBeSent.getFirstChild("detail").setValue(de.getDetails());
        valueToBeSent.getFirstChild("message").setValue(de.getMessage());

        service.callOneWay("onCancelled", valueToBeSent, new Callback() {
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

    private Value parseFireBaseValue(DataSnapshot ds) {
        Value v = Value.create();

        Iterable<DataSnapshot> interactor = ds.getChildren();
        v.getFirstChild("collection").setValue(this.collection);
        interactor.forEach((t) -> {
            if (t.getValue() instanceof HashMap) {
                v.getFirstChild("payload").getFirstChild(ds.getKey()).getFirstChild(t.getKey()).deepCopy(parseHashMap((HashMap<String, Object>)t.getValue()));
            }else{
               v.getFirstChild("payload").getFirstChild(ds.getKey()).getFirstChild(t.getKey()).setValue(t.getValue());
      
            }

        });
        return v;
    }

    private Value parseHashMap(HashMap<String, Object> hashMap) {
        Value v1 = Value.create();
        hashMap.forEach((t, u) -> {
            if (u instanceof HashMap) {
                v1.getFirstChild(t).deepCopy(parseHashMap((HashMap<String, Object>) u));
            } else {
                v1.getFirstChild(t).setValue(u);
            }
        });

        return v1;
    }
}
