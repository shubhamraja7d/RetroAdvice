package com.sr7d.retroadvice.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.http.GET;

public class AdviceSlip {

    @SerializedName("advice")
    @Expose
    private String advice;

    @SerializedName("slip_id")
    @Expose
    private String slipId;

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getSlipId() {
        return slipId;
    }

    public void setSlipId(String slipId) {
        this.slipId = slipId;
    }
}
