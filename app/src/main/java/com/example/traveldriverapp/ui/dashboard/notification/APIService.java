package com.example.traveldriverapp.ui.dashboard.notification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAuH_3QPY:APA91bHRHJI2IXbu2wfhGlyXjg_CHriF4hhMD_1BXo-G1krHzA0SWpxXhJD1XNTeoc0wgB4VmFDdgW6ubLp5v8CpG_BUzu9Vc66kC7FV1QX9INa9Z2V6G1Tcbz3NlRHWDCxPvRLOFb-8"
    })

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
