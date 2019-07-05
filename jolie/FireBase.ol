include "/public/interfaces/JolieFireBase.iol"
include "string_utils.iol"
include "console.iol"


inputPort CallBackPort {
  Location: "socket://localhost:4000"
  Protocol: sodep
  Interfaces: CallBackInterface
}


init{
  request.filename = "joliefirebase-firebase-adminsdk-a0zir-a4d6df5e84.json";
  request.url = "https://joliefirebase.firebaseio.com/";
  connect@FireBasePort(request)();
  requestAddListener.collection="/history";
  requestAddListener.serviceAddress = "socket://localhost:4000";
  requestAddListener.valueEvent = false;
  requestAddListener.childEvent = true;
  addListener@FireBasePort(requestAddListener)();
  requestAddObject.collection="/history";
  requestAddObject.firebaseId="mykey";
  requestAddObject.payload.twitterMessage="Jolie power";
  insertDBRecord@FireBasePort(requestAddObject)()
}
execution{ concurrent }
main{
  [onChildAdded(request)]{
     nullProcess
    }
  [onChildChanged(request)]{
    nullProcess
  }
  [onChildRemoved(request)]{
    nullProcess
  }
  [onChildMoved(request)]{
    nullProcess
  }

  [onCancelled(request)]{
    nullProcess
  }

  [onDataChange(request)]{
    nullProcess
  }

}
