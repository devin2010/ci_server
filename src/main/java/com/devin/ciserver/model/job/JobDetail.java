package com.devin.ciserver.model.job;

import java.util.List;

/**
 * Created by devin on 2018/8/9.
 */
public class JobDetail {
    private String jobName;
    private String description;
    private String displayName;
    private boolean buildable;
    private List<BuildDetail> builds;
    private BuildDetail firstBuild;
    private BuildDetail lastBuild;
    private BuildDetail lastCompletedBuild;
    private BuildDetail lastFailedBuild;
    private BuildDetail lastStableBuild;
    private BuildDetail lastSuccessfulBuild;
    private BuildDetail lastUnstableBuild;
    private BuildDetail lastUnsuccessfulBuild;
    private int nextBuildNumber;
    private boolean inQueue;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isBuildable() {
        return buildable;
    }

    public void setBuildable(boolean buildable) {
        this.buildable = buildable;
    }

    public List<BuildDetail> getBuilds() {
        return builds;
    }

    public void setBuilds(List<BuildDetail> builds) {
        this.builds = builds;
    }

    public BuildDetail getFirstBuild() {
        return firstBuild;
    }

    public void setFirstBuild(BuildDetail firstBuild) {
        this.firstBuild = firstBuild;
    }

    public BuildDetail getLastBuild() {
        return lastBuild;
    }

    public void setLastBuild(BuildDetail lastBuild) {
        this.lastBuild = lastBuild;
    }

    public BuildDetail getLastCompletedBuild() {
        return lastCompletedBuild;
    }

    public void setLastCompletedBuild(BuildDetail lastCompletedBuild) {
        this.lastCompletedBuild = lastCompletedBuild;
    }

    public BuildDetail getLastFailedBuild() {
        return lastFailedBuild;
    }

    public void setLastFailedBuild(BuildDetail lastFailedBuild) {
        this.lastFailedBuild = lastFailedBuild;
    }

    public BuildDetail getLastStableBuild() {
        return lastStableBuild;
    }

    public void setLastStableBuild(BuildDetail lastStableBuild) {
        this.lastStableBuild = lastStableBuild;
    }

    public BuildDetail getLastSuccessfulBuild() {
        return lastSuccessfulBuild;
    }

    public void setLastSuccessfulBuild(BuildDetail lastSuccessfulBuild) {
        this.lastSuccessfulBuild = lastSuccessfulBuild;
    }

    public BuildDetail getLastUnstableBuild() {
        return lastUnstableBuild;
    }

    public void setLastUnstableBuild(BuildDetail lastUnstableBuild) {
        this.lastUnstableBuild = lastUnstableBuild;
    }

    public BuildDetail getLastUnsuccessfulBuild() {
        return lastUnsuccessfulBuild;
    }

    public void setLastUnsuccessfulBuild(BuildDetail lastUnsuccessfulBuild) {
        this.lastUnsuccessfulBuild = lastUnsuccessfulBuild;
    }

    public int getNextBuildNumber() {
        return nextBuildNumber;
    }

    public void setNextBuildNumber(int nextBuildNumber) {
        this.nextBuildNumber = nextBuildNumber;
    }

    public boolean isInQueue() {
        return inQueue;
    }

    public void setInQueue(boolean inQueue) {
        this.inQueue = inQueue;
    }
}
