class Service {
    String name;
    int limit; // Limit for the service
    boolean emergencyStatus; // Emergency status

    public Service(String name, int limit, boolean emergencyStatus) {
        this.name = name;
        this.limit = limit;
        this.emergencyStatus = emergencyStatus;
    }

    public String getName() {
        return name;
    }

    public int getLimit() {
        return limit;
    }

    public boolean isEmergencyStatus() {
        return emergencyStatus;
    }
}
