package com.example.Eshopsample.PersonalComputer;

public class PersonalComputer {

    private int id;
    private int memoryGb;
    private double cpuFrequency;
    private int screenSizeInches;
    private int hardDiskGB;

    public int getMemoryGb() {
        return memoryGb;
    }

    public void setMemoryGb(int memoryGb) {
        this.memoryGb = memoryGb;
    }

    public double getCpuFrequency() {
        return cpuFrequency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCpuFrequency(double cpuFrequency) {
        this.cpuFrequency = cpuFrequency;
    }

    public int getScreenSizeInches() {
        return screenSizeInches;
    }

    public void setScreenSizeInches(int screenSizeInches) {
        this.screenSizeInches = screenSizeInches;
    }

    public int getHardDiskGB() {
        return hardDiskGB;
    }

    public void setHardDiskGB(int hardDiskGB) {
        this.hardDiskGB = hardDiskGB;
    }
}
