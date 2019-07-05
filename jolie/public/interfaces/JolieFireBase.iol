interface JolieFireBaseInterface {
RequestResponse:
 connect(undefined)(undefined),
 addListener(undefined)(undefined),
 updateDBRecord(undefined)(undefined),
 insertDBRecord(undefined)(undefined),
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
