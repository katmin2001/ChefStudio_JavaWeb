package com.devpro.javaweb.enumType;

public enum OrderStatus {
    SHIPPING("Đang vận chuyển"),
    SUCCESS("Thành công"),
    CANCELED("Đã huỷ");

    public final String label;
    private OrderStatus(String label) {
        this.label = label;
    }
}
