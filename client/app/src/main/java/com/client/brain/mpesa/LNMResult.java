package com.client.brain.mpesa;

import com.google.gson.annotations.SerializedName;

public class LNMResult {

    @SerializedName("MerchantRequestID")
    private String merchantRequestID;

    @SerializedName("CheckoutRequestID")
    private String checkoutRequestID;

    @SerializedName("ResponseCode")
    private String responseCode;

    @SerializedName("ResponseDescription")
    private String responseDescription;

    @SerializedName("CustomerMessage")
    private String customerMessage;

    public LNMResult() {
    }

    public String getMerchantRequestID() {
        return merchantRequestID;
    }

    public void setMerchantRequestID(String merchantRequestID) {
        this.merchantRequestID = merchantRequestID;
    }

    public String getCheckoutRequestID() {
        return checkoutRequestID;
    }

    public void setCheckoutRequestID(String checkoutRequestID) {
        this.checkoutRequestID = checkoutRequestID;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getCustomerMessage() {
        return customerMessage;
    }

    public void setCustomerMessage(String customerMessage) {
        this.customerMessage = customerMessage;
    }

    @Override
    public String toString() {
        return "LNMResult{" +
                "merchantRequestID='" + merchantRequestID + '\'' +
                ", checkoutRequestID='" + checkoutRequestID + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", responseDescription='" + responseDescription + '\'' +
                ", customerMessage='" + customerMessage + '\'' +
                '}';
    }
}

