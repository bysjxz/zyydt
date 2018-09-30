package com.cdutcm.tcms.biz.DTO;

/**
 * @Auther: Mxq
 * @Date: 2018/8/8 15:25
 * @description: 病人状态数据传输对象
 */
public class StatusDTO {

    private String userId;
    private String status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
