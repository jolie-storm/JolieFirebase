include "/public/interfaces/JolieFireBase.iol"
include "/public/config/ShopServiceConfig.iol"
include "./public/config/CustomerServiceConfig.iol"
include "./public/interfaces/CustomerServiceInterface.iol"
include "string_utils.iol"
include "./public/interfaces/ShopServiceInterface.iol"
include "console.iol"


inputPort CallBackPort {
  Location: "socket://localhost:4000"
  Protocol: sodep
  Interfaces: CallBackInterface
}

outputPort ShopSodep {
    Location: ShopService_SodepIP_location
    Protocol: sodep
    Interfaces:  ShopServiceInterface , ShopServiceInterfaceBrandAdmin ,  ShopServiceInterfaceTechAdmin
}

outputPort CustomerSodep {
    Location: CustomerService_SodepIP_location
    Protocol: sodep
    Interfaces: CustomerServiceInterface
}

init{
  request.filename = "joliefirebase-firebase-adminsdk-a0zir-6c5a95ce70.json";
  request.url = "https://joliefirebase.firebaseio.com/";
  //connect@FireBasePort(request)();
  requestAddListener.collection="/test";
  requestAddListener.serviceAddress = "socket://localhost:4000";
  requestAddListener.valueEvent = false;
  requestAddListener.childEvent = true
  //addListener@FireBasePort(requestAddListener)()
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
