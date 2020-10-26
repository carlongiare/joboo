package com.expert.mpesa;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {

   public static final String BASE_URL ="/api/v1";


   @POST(BASE_URL+"/mpesapay/{bussinessId}/{phone}/{amount}/{userId}")
   Call<LNMResult> payWithMpesa(@Path("bussinessId") String bussinessId, @Path("phone") String phone, @Path("amount") String amount, @Path("userId") String userId);

}
