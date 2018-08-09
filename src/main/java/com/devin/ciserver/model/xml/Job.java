package com.devin.ciserver.model.xml;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by devin on 2018/8/8.
 */
@XmlRootElement(name="project")
public class Job {
    private String description="";
    private boolean keepDependencies=false;
    private Properties properties=new Properties();
    private Scm scm=new Scm();
    private boolean canRoam=true;
    private boolean disabled=false;
    private boolean blockBuildWhenDownstreamBuilding=false;
    private boolean blockBuildWhenUpstreamBuilding=false;
    private Triggers triggers=new Triggers();
    private boolean concurrentBuild=false;
    private BuildWrappers buildWrappers=new BuildWrappers();
    private Builders builders=new Builders();
    private Publishers publishers=new Publishers();



    public String getDescription() {
        return description;
    }
    public boolean isKeepDependencies() {
        return keepDependencies;
    }
    public Scm getScm() {
        return scm;
    }
    public boolean isCanRoam() {
        return canRoam;
    }
    public boolean isDisabled() {
        return disabled;
    }
    public boolean isBlockBuildWhenDownstreamBuilding() {
        return blockBuildWhenDownstreamBuilding;
    }
    public boolean isBlockBuildWhenUpstreamBuilding() {
        return blockBuildWhenUpstreamBuilding;
    }
    public Triggers getTriggers() {
        return triggers;
    }
    public boolean isConcurrentBuild() {
        return concurrentBuild;
    }

    public BuildWrappers getBuildWrappers() {
        return buildWrappers;
    }

    public void setScm(Scm scm) {
        this.scm = scm;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setKeepDependencies(boolean keepDependencies) {
        this.keepDependencies = keepDependencies;
    }

    public void setCanRoam(boolean canRoam) {
        this.canRoam = canRoam;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setBlockBuildWhenDownstreamBuilding(boolean blockBuildWhenDownstreamBuilding) {
        this.blockBuildWhenDownstreamBuilding = blockBuildWhenDownstreamBuilding;
    }

    public void setBlockBuildWhenUpstreamBuilding(boolean blockBuildWhenUpstreamBuilding) {
        this.blockBuildWhenUpstreamBuilding = blockBuildWhenUpstreamBuilding;
    }

    public void setTriggers(Triggers triggers) {
        this.triggers = triggers;
    }

    public void setConcurrentBuild(boolean concurrentBuild) {
        this.concurrentBuild = concurrentBuild;
    }

    public void setBuildWrappers(BuildWrappers buildWrappers) {
        this.buildWrappers = buildWrappers;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Builders getBuilders() {
        return builders;
    }

    public void setBuilders(Builders builders) {
        this.builders = builders;
    }

    public Publishers getPublishers() {
        return publishers;
    }

    public void setPublishers(Publishers publishers) {
        this.publishers = publishers;
    }
}
