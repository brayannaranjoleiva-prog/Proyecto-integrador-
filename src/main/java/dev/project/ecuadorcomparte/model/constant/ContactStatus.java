package dev.project.ecuadorcomparte.model.constant;

public enum ContactStatus {

    PENDING("Pendiente"),
    REVIEWED("Revisado"),
    RESOLVED("Resuelto");

    private final String displayName;

    ContactStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
