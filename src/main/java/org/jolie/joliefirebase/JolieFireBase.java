/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jolie.joliefirebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import jolie.runtime.CanUseJars;
import jolie.runtime.JavaService;
import jolie.runtime.Value;
import jolie.runtime.embedding.RequestResponse;

/**
 *
 * @author bmasc
 */
@CanUseJars( {
    "firebase-admin-6.8.1.jar",
    "jolie-java.jar",
    "libjolie.jar",
    "/extensions/sodep.jar"
         
} )
public class JolieFireBase extends JavaService {

    private static FirebaseDatabase db;
    private HashMap<String, DatabaseReference> dbReferences = new HashMap<>();

    @RequestResponse
    public Value connect(Value request) {
        FileInputStream refreshToken = null;
        Value response = Value.create();
        try {

            refreshToken = new FileInputStream(request.getFirstChild("filename").strValue());
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setDatabaseUrl(request.getFirstChild("url").strValue())
                    .build();
            FirebaseApp defautApp = FirebaseApp.initializeApp(options);
            db = FirebaseDatabase.getInstance();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(JolieFireBase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JolieFireBase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                refreshToken.close();
            } catch (IOException ex) {
                Logger.getLogger(JolieFireBase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return response;
    }

    @RequestResponse
    public Value addListener(Value request) {
        Value response = Value.create();

        DatabaseReference ref = db.getReference(request.getFirstChild("collection").strValue());
        if (request.getFirstChild("valueEvent").boolValue()) {
            ref.addValueEventListener(new JolieValueEventListener(request.getFirstChild("serviceAddress").strValue(), request.getFirstChild("collection").strValue()));
        };
        if (request.getFirstChild("childEvent").boolValue()) {
            ref.addChildEventListener(new JolieChildEventListener(request.getFirstChild("serviceAddress").strValue(), request.getFirstChild("collection").strValue()));
        }

        dbReferences.put(request.getFirstChild("collection").strValue(), ref);

        return response;
    }

    @RequestResponse
    public Value updateDBRecord(Value request) {
        Value response = Value.create();
        DatabaseReference ref = db.getReference(request.getFirstChild("collection").strValue());
        DatabaseReference objRef = ref.child(request.getFirstChild("firebaseId").strValue());
        objRef.updateChildrenAsync(parseJolieValue(request.getFirstChild("payload")));

        return response;
    }

    @RequestResponse
    public Value insertDBRecord(Value request) {
        Value response = Value.create();
        DatabaseReference ref = db.getReference(request.getFirstChild("collection").strValue());
        DatabaseReference objRef = ref.child(request.getFirstChild("firebaseId").strValue());
        objRef.setValueAsync(parseJolieValue(request.getFirstChild("payload")));

        return response;
    }

    @RequestResponse
    public Value deleteDBRecord(Value request) {
        Value response = Value.create();

        return response;
    }

    private HashMap<String, Object> parseJolieValue(Value v) {
        HashMap<String, Object> hashMap = new HashMap<>();
        v.children().forEach((t, u) -> {
            Value value = u.get(0);
            if (value.hasChildren()) {
                hashMap.put(t, parseJolieValue(value));
            } else {
                if (value.isString()) {
                    hashMap.put(t, value.strValue());
                };

                if (value.isInt()) {
                    hashMap.put(t, value.intValue());
                }

            }
        });
        return hashMap;
    }
}
