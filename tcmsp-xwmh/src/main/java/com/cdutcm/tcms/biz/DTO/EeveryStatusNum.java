package com.cdutcm.tcms.biz.DTO;

/**
 * @Auther: Mxq
 * @Date: 2018/8/7 13:46
 * @description:  疾病类型数值
 */
public class EeveryStatusNum {

    int booking;
    int waiting;
    int doing;

    public int getBooking() {
        return booking;
    }

    public void setBooking(int booking) {
        this.booking = booking;
    }

    public int getWaiting() {
        return waiting;
    }

    public void setWaiting(int waiting) {
        this.waiting = waiting;
    }

    public int getDoing() {
        return doing;
    }

    public void setDoing(int doing) {
        this.doing = doing;
    }

    public EeveryStatusNum(int booking, int waiting, int doing) {
        this.booking = booking;
        this.waiting = waiting;
        this.doing = doing;
    }
}
