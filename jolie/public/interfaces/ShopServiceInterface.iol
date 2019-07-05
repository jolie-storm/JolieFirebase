type ShopType:void{
  .shopName?:string
  .shopId?:string
  .shopIdExt?:string
  .isActive?:int
  .brandId:string
  .address:string
  .city:string
  .state:string
  .zip:int |string
  .online_booking?:string |void
  .storetimes:void{
    .fri? : string
    .mon? : string
    .sat? : string
    .sun?: string
    .thu? : string
    .tue? : string
    .wed? : string
    .orgSat?:string
    .orgSun?:string
  }
}

type GetShopInfoRequest:void{
  .brandId:string
  .shopId:string

}



type GetShopInfoResponse:void{
  .shop?: ShopType
  .error?: undefined
}


type GetShopsRequest: void{
  .brandId?:string
  .shopIdExt?:string
  .shopName?:string
  .address?:string
}

type GetShopsResponse:void{
  .shop*: ShopType
  .error?: undefined
}

type AddShopRequest:ShopType

type AddShopResponse:void{
  .error?:undefined
}


type ModifyShopRequest:void{
  .shopId:string
  .brandId:string
  .shopData:void{
    .shopName?:string
    .address?:string
    .city?:string
    .state?:string
    .zip?:string
    .online_booking?:string
    .storetimes?:void{
      .fri? : string
      .mon? : string
      .sat? : string
      .sun?: string
      .thu? : string
      .tue? : string
      .wed? : string
      .orgSat?:string
      .orgSun?:string
    }
  }
}

type ModifyShopResponse:void{
  .error?:undefined
}

type GetBrandInfoRequest:void{
  .brandId:string
}

type GetBrandInfoResponse:void{
  .brandId?:string
  .quote?:string
  .about?:string
  .error?:undefined
}

interface ShopServiceInterface {
RequestResponse:
  getShopInfo(GetShopInfoRequest)(GetShopInfoResponse),
  getShops(GetShopsRequest)(GetShopsResponse)
}

interface ShopServiceInterfaceBrandAdmin {
RequestResponse:
  addShop(AddShopRequest)(AddShopResponse),
  modifyShop(ModifyShopRequest) (ModifyShopResponse),
  getBrandInfo(GetBrandInfoRequest)(GetBrandInfoResponse),

}



type  GetBrandsRequest:void


type  GetBrandsResponse :void{
  .brandId*:string
  .error?:undefined
}

type AddBrandRequest:void{
  .brandId:string
  .quote:string
  .about:string
}

type AddBrandResponse:void{
  .error?:undefined
}


interface ShopServiceInterfaceTechAdmin {
RequestResponse:
  getBrands(GetBrandsRequest)(GetBrandsResponse),
  addBrand(AddBrandRequest)(AddBrandResponse)
}
