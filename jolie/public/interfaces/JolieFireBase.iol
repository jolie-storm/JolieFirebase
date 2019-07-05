

type ConnectRequest:void{
  .filename:string
  .url:string
}
type AddListenerRequest:void{
  .valueEvent:bool
  .childEvent:bool
  .serviceAddress:string
  .collection:string
}

type UpdateDBRecord:void{
  .collection:string
  .firebaseId:string
  .payload:undefined
}

type InsertDBRecord:void{
  .collection:string
  .firebaseId:string
  .payload:undefined
}

interface JolieFireBaseInterface {
RequestResponse:
 connect(ConnectRequest)(void),
 addListener(AddListenerRequest)(void),
 updateDBRecord(UpdateDBRecord)(undefined),
 insertDBRecord(InsertDBRecord)(undefined),
 deleteDBRecord(undefined)(undefined)
}



interface CallBackInterface {
  OneWay:
  onChildAdded(undefined),
  onChildChanged(undefined),
  onChildRemoved(undefined),
  onChildMoved(undefined),
  onCancelled(undefined),
  onDataChange(undefined),
}


outputPort FireBasePort {
  Interfaces: JolieFireBaseInterface
}

embedded {
  Java:
    "org.jolie.joliefirebase.JolieFireBase" in FireBasePort
}
