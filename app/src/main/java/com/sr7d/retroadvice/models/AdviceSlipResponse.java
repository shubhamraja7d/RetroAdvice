package com.sr7d.retroadvice.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdviceSlipResponse {

    @SerializedName("slip")
    @Expose
    private AdviceSlip adviceSlip;

    public AdviceSlipResponse(AdviceSlip adviceSlip) {
        this.adviceSlip = adviceSlip;
    }

    public AdviceSlip getAdviceSlip() {
        return adviceSlip;
    }

    public void setAdviceSlip(AdviceSlip adviceSlip) {
        this.adviceSlip = adviceSlip;
    }
}
