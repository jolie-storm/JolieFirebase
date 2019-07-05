type CustomerType:void{
  .email?:string
  .firstName:string
  .brandId?:string
  .shopId?:string
  .lastName?:string
  .phoneNumber?:string
  .customerExtId?:string
  .blushupAdmin?:bool  //trash
  .brandAdmin?:undefined //trash
  .isAdmin?:undefined //trash
  .customerId?:string
  .bookingHistory*:void{
      .bookingId:string
      .shopName:string
      .dateName:string
      .fromTime:string
      .toTime:string
  }

  .incommigBooking*:void{
      .bookingId:string
      .shopName:string
      .dateName:string
      .fromTime:string
      .toTime:string
  }
}

type AddCustomerRequest: CustomerType

type AddCustomerResponse:void{
  .error?:undefined
  .customerId?: string
}



type GetCustomerInfoByIdRequest:void{
  .brandId:string
  .shopId:string
  .customerId:string
}

type GetCustomerInfoByIdResponse:void{
  .customer:CustomerType
  .error?:undefined
}




type GetCustomersRequest:void{
  .customerExtId?:string
  .email?:string
  .shopId?:string
  .brandId?:string
  .lastName?:string
}

type GetCustomersResponse:void{
 .error?:undefined
 .customer*:CustomerType
}

interface CustomerServiceInterface {
RequestResponse:
 addCustomer(AddCustomerRequest)(AddCustomerResponse),
 getCustomerInfoById (GetCustomerInfoByIdRequest) (GetCustomerInfoByIdResponse),
 getCustomers(GetCustomersRequest)(GetCustomersResponse),
}
