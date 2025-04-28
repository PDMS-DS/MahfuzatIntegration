package com.dataserve.mahfuzatintegration.util;
public enum IntegrationFileStatus {

    NEW(0, "جديد", "NEW"),
    SUCCESS(1, "تمت الارشفه بنجاح", "Success"),
    FAIL(2, "حدث خطأ أثناء الأرشفه", "Fail");

    private final int id;
    private final String statusAr;
    private final String statusEn;

    // Constructor
    IntegrationFileStatus(int id, String statusAr, String statusEn) {
        this.id = id;
        this.statusAr = statusAr;
        this.statusEn = statusEn;
    }

    public int getId() {
        return id;
    }

    public String getStatusAr() {
        return statusAr;
    }

    public String getStatusEn() {
        return statusEn;
    }

    // Method to get enum by id
    public static IntegrationFileStatus fromId(int id) {
        for (IntegrationFileStatus status : values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid ID: " + id);
    }
}
