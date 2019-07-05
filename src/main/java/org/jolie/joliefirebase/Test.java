/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jolie.joliefirebase;

import com.google.firebase.auth.FirebaseAuthException;
import java.io.FileNotFoundException;
import java.io.IOException;
import jolie.runtime.Value;

/**
 *
 * @author maschio
 */
public class Test
{

	/**
	 * @param args the command line arguments
	 */
	public static void main( String[] args ) throws FileNotFoundException, IOException, FirebaseAuthException
	{   
            JolieFireBase jolieFireBase = new JolieFireBase();
            Value v= Value.create();
            v.getFirstChild("filename").setValue("./joliefirebase-firebase-adminsdk-a0zir-6c5a95ce70.json");
            v.getFirstChild("url").setValue("https://joliefirebase.firebaseio.com/");
            jolieFireBase.connect(v);
            
          /*  Value insertValue = Value.create();
            insertValue.getFirstChild("collection").setValue("/userBasicInfo");
            insertValue.getFirstChild("firebaseId").setValue("myId");
            insertValue.getFirstChild("payload").getFirstChild("email").setValue("pincopallo@pincopallo.com");
            insertValue.getFirstChild("payload").getFirstChild("firstName").setValue("pinco");
            insertValue.getFirstChild("payload").getFirstChild("lastName").setValue("pallo");
            jolieFireBase.insertDBRecord(insertValue);*/
            
           /* Value updateValue = Value.create();
            updateValue.getFirstChild("collection").setValue("/userBasicInfo");
            updateValue.getFirstChild("firebaseId").setValue("myId");
            updateValue.getFirstChild("payload").getFirstChild("email").setValue("pincopallo@pincopallo.com");
            updateValue.getFirstChild("payload").getFirstChild("firstName").setValue("pinco");
            updateValue.getFirstChild("payload").getFirstChild("lastName").setValue("pluto");
            jolieFireBase.updateDBRecord(updateValue);*/
            
            Value vAddListener = Value.create();
            vAddListener.getFirstChild("collection").setValue("/test");
            vAddListener.getFirstChild("serviceAddress").setValue("socket://localhost:4000");
            vAddListener.getFirstChild("valueEvent").setValue(Boolean.FALSE);
            vAddListener.getFirstChild("childEvent").setValue(Boolean.TRUE);
            jolieFireBase.addListener(vAddListener);
            

            
            while(true){
                boolean a = true;
		}
		
            /*	FileInputStream refreshToken = new FileInputStream( "./src/blushuptrial-firebase-adminsdk-kjw6d-2f8bed832f.json" );
		FirebaseOptions options = new FirebaseOptions.Builder()
		.setCredentials( GoogleCredentials.fromStream( refreshToken ) )
		.setDatabaseUrl( "https://blushuptrial.firebaseio.com/" )
		.build();
		FirebaseApp defautApp = FirebaseApp.initializeApp( options );
		FirebaseDatabase db = FirebaseDatabase.getInstance();
		DatabaseReference ref = db.getReference( "/userBasicInfo");//3Elm3CeDvqUV9nh0jIX4u1mXcst1" );
            ref.addValueEventListener(new ValueEventListener()
		{
		@Override
		public void onDataChange( DataSnapshot arg0 )
		{
		userBasicInfo userDataInfo = arg0.getValue(userBasicInfo.class);
		System.out.println(userDataInfo.firstName);
		}
		@Override
		public void onCancelled( DatabaseError arg0 )
		{
		String res = arg0.toString();
		System.out.println( res );
		}
		});*/
/*
		ref.addChildEventListener( new ChildEventListener()
		{
		@Override
		public void onChildAdded( DataSnapshot arg0, String arg1 )
		{
		throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
		}
		@Override
		public void onChildChanged( DataSnapshot arg0, String arg1 )
		{
		System.out.println(arg1); //To change body of generated methods, choose Tools | Templates.
		}
		@Override
		public void onChildRemoved( DataSnapshot arg0 )
		{
		throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
		}
		@Override
		public void onChildMoved( DataSnapshot arg0, String arg1 )
		{
		throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
		}
		@Override
		public void onCancelled( DatabaseError arg0 )
		{
		throw new UnsupportedOperationException( "Not supported yet." ); //To change body of generated methods, choose Tools | Templates.
		}
		});
		Map<String, Object> users = new HashMap<>();
		users.put("bulliniid", new UserBasicInfo("bmaschio@bmachio", "balint", "maschio" ));
		ref.updateChildrenAsync(users);
		UserRecord userRecord = FirebaseAuth.getInstance().getUser("xnvRU9sh40SAqvFOa9t3cwdYxJP2");
		System.out.println("Successfully fetched user data: " + userRecord.getUid());
		CreateRequest request = new CreateRequest()
		.setEmail("user@example.com")
		.setEmailVerified(false)
		.setPassword("secretPassword")
		.setPhoneNumber("+11234567890")
		.setDisplayName("John Doe")
		.setPhotoUrl("http://www.example.com/12345678/photo.png")
		.setDisabled(false);
		userRecord = FirebaseAuth.getInstance().createUser(request);
		System.out.println("Successfully created new user: " + userRecord.getUid());
		while(true){
		System.out.println();
		}
		 */

		
		
		
	}
public static  class UserBasicInfo{
   public String email;
   public String firstName;
   public String lastName;   
   public UserBasicInfo(String email , String firstName , String lastName){
      this.email = email;
	  this.firstName =firstName;
	  this.lastName = lastName;
   }
	}
}


