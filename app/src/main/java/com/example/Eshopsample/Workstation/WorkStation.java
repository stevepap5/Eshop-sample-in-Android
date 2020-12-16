package com.example.Eshopsample.Workstation;

import com.example.Eshopsample.PersonalComputer.PersonalComputer;

public class WorkStation {

    private int id;
    private int memoryGb;
    private double cpuFrequency;
    private int screenSizeInches;
    private int hardDiskGB;
    private String operatingSystem;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemoryGb() {
        return memoryGb;
    }

    public void setMemoryGb(int memoryGb) {
        this.memoryGb = memoryGb;
    }

    public double getCpuFrequency() {
        return cpuFrequency;
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

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
}
