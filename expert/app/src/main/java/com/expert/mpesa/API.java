package com.expert.mpesa;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {

   String BASE_URL ="/api/v1";


   @POST(BASE_URL+"/mpesapay/arEbmj9GVNs2rNY/{phone}/{amount}/{userId}")
   Call<LNMResult> payWithMpesa(@Path("phone") String phone, @Path("amount") String amount, @Path("userId") String userId);

}
