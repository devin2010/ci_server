package com.devin.ciserver.service;

import com.devin.ciserver.Inter.JobService;
import com.devin.ciserver.model.job.BuildDetail;
import com.devin.ciserver.model.job.JobDetail;
import com.devin.ciserver.util.ConvertUtil;
import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.helper.Range;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by devin on 2018/8/8.
 */
@Service
@ConfigurationProperties(prefix = "job")
public class JobServiceImpl implements JobService {
    private String jenkinsUrl;
    private String jenkinsUser;
    private String jenkinsPwd;

    private JenkinsServer jenkinsServer;
    private JenkinsServer getJenkinsServer() {
        if (jenkinsServer != null) {
            return jenkinsServer;
        }
        URI serverUri = null;
        try {
            serverUri = new URI(jenkinsUrl);
            jenkinsServer = new JenkinsServer(serverUri, jenkinsUser, jenkinsPwd);
            return jenkinsServer;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void createJob(String jobName, String configXml) throws Exception {
        Job job = getJenkinsServer().getJobs().get(jobName);
        if (job != null) {
            throw new Exception("job:[" + jobName + "]已经存在,job创建失败!");
        }
        getJenkinsServer().createJob(jobName, configXml, true);
    }

    @Override
    public void updateJob(String jobName, String configXml) throws Exception {
        Job job = getJenkinsServer().getJobs().get(jobName);
        if (job == null) {
            throw new Exception("job:[" + jobName + "]不存在,job修改失败!");
        }
        getJenkinsServer().updateJob(jobName, configXml, true);
    }

    @Override
    public void deleteJob(String jobName) throws Exception {
        Job job = getJenkinsServer().getJobs().get(jobName);
        if (job == null) {
            throw new Exception("job:[" + jobName + "]不存在,job删除失败!");
        }
        getJenkinsServer().deleteJob(jobName, true);
    }

    @Override
    public void buildJob(String jobName) throws Exception {
        Job job = getJenkinsServer().getJobs().get(jobName);
        if (job == null) {
            throw new Exception("job:[" + jobName + "]不存在,job执行失败!");
        }
        job.build(true);
    }

    @Override
    public void buildJob(String jobName, Map<String, String> params) throws Exception {
        Job job = getJenkinsServer().getJobs().get(jobName);
        if (job == null) {
            throw new Exception("job:[" + jobName + "]不存在,job执行失败!");
        }
        job.build(params, true);
    }

    @Override
    public BuildWithDetails getBuildJobDetailByLastTime(String jobName) throws Exception {
        Job job = getJenkinsServer().getJobs().get(jobName);
        if (job == null) {
            throw new Exception("job:[" + jobName + "]不存在,job执行失败!");
        }
        JobWithDetails jobWithDetails = getJenkinsServer().getJob(jobName);
        BuildWithDetails buildWithDetails = jobWithDetails.getLastBuild().details();
        return buildWithDetails;
    }

    @Override
    public BuildWithDetails getBuildJobDetailByNumber(String jobName, int number) throws Exception {
        Job job = getJenkinsServer().getJobs().get(jobName);
        if (job == null) {
            throw new Exception("job:[" + jobName + "]不存在,job执行失败!");
        }
        JobWithDetails jobWithDetails = getJenkinsServer().getJob(jobName);
        if (jobWithDetails.getBuildByNumber(number) == null) {
            throw new Exception("job[" + jobName + "]没有第[" + number + "]次的build记录!");
        }
        BuildWithDetails buildWithDetails = jobWithDetails.getBuildByNumber(number).details();
        return buildWithDetails;
    }

    @Override
    public JobDetail getJobDetail(String jobName) throws Exception {
        Job job = getJenkinsServer().getJobs().get(jobName);
        if (job == null) {
            throw new Exception("job:[" + jobName + "]不存在,job执行失败!");
        }
        JobWithDetails jwd = getJenkinsServer().getJob(jobName);
        JobDetail jd = new JobDetail();
        jd.setJobName(jobName);
        jd.setDescription(jwd.getDescription());
        jd.setDisplayName(jwd.getDisplayName());
        jd.setBuildable(jwd.isBuildable());
        jd.setBuilds(ConvertUtil.buildsToBuildDetails(jwd.getBuilds()));
        jd.setFirstBuild(ConvertUtil.buildToBuildDetail(jwd.getFirstBuild()));
        jd.setLastBuild(ConvertUtil.buildToBuildDetail(jwd.getLastBuild()));
        jd.setLastCompletedBuild(ConvertUtil.buildToBuildDetail(jwd.getLastCompletedBuild()));
        jd.setLastFailedBuild(ConvertUtil.buildToBuildDetail(jwd.getLastFailedBuild()));
        jd.setLastStableBuild(ConvertUtil.buildToBuildDetail(jwd.getLastStableBuild()));
        jd.setLastSuccessfulBuild(ConvertUtil.buildToBuildDetail(jwd.getLastSuccessfulBuild()));
        jd.setLastUnstableBuild(ConvertUtil.buildToBuildDetail(jwd.getLastUnstableBuild()));
        jd.setLastUnsuccessfulBuild(ConvertUtil.buildToBuildDetail(jwd.getLastUnsuccessfulBuild()));
        jd.setNextBuildNumber(jwd.getNextBuildNumber());
        jd.setInQueue(jwd.isInQueue());
        return jd;
    }

    @Override
    public List<JobDetail> getJobs(String queryKey) throws Exception {
        Map<String, Job> jobMap = getJenkinsServer().getJobs();
        Set<String> keys = jobMap.keySet();
        List<JobDetail> jobDetails = new ArrayList<JobDetail>();
        for (String key : keys) {
            Job job = jobMap.get(key);
            String jobName = job.getName();
            JobWithDetails jwd = job.details();
            String desc = jwd.getDescription();
            if (StringUtils.isEmpty(queryKey)
                    || jobName.indexOf(queryKey) != -1
                    || (!StringUtils.isEmpty(desc) && desc.indexOf(queryKey) != -1)) {
                JobDetail jd = new JobDetail();
                jd.setJobName(jobName);
                jd.setDescription(desc);
                jd.setDisplayName(jwd.getDisplayName());
                jd.setBuildable(jwd.isBuildable());
                jd.setInQueue(jwd.isInQueue());
                jd.setNextBuildNumber(jwd.getNextBuildNumber());
                jobDetails.add(jd);
            }
        }
        return jobDetails;
    }

    @Override
    public List<BuildDetail> getJobBuilds(String jobName) throws Exception {
        Job job = getJenkinsServer().getJobs().get(jobName);
        if (job == null) {
            throw new Exception("job:[" + jobName + "]不存在,job执行失败!");
        }
        JobWithDetails jobWithDetails = getJenkinsServer().getJob(jobName);
        List<Build> builds = jobWithDetails.getAllBuilds();
        List<BuildDetail> bds = new ArrayList<BuildDetail>();
        for (Build build : builds) {
            BuildWithDetails bwds = build.details();
            BuildDetail bd = new BuildDetail();
            bd.setDisplayName(bwds.getDisplayName());
            bd.setDuration(bwds.getDuration());
            bd.setFullDisplayName(bwds.getFullDisplayName());
            bd.setId(bwds.getId());
            bd.setNumber(bwds.getNumber());
            bd.setResult(bwds.getResult().toString());
            bd.setTimestamp(bwds.getTimestamp());
            bds.add(bd);
        }
        return bds;
    }

    @Override
    public List<BuildDetail> getJobBuildsByRange(String jobName, Integer from, Integer to) throws Exception {
        Job job = getJenkinsServer().getJobs().get(jobName);
        if (job == null) {
            throw new Exception("job:[" + jobName + "]不存在,job执行失败!");
        }
        JobWithDetails jobWithDetails = getJenkinsServer().getJob(jobName);
        Range range = Range.build().from(from).to(to);
        List<Build> builds = jobWithDetails.getAllBuilds(range);
        List<BuildDetail> bds = new ArrayList<BuildDetail>();
        for (Build build : builds) {
            BuildWithDetails bwds = build.details();
            BuildDetail bd = new BuildDetail();
            bd.setDisplayName(bwds.getDisplayName());
            bd.setDuration(bwds.getDuration());
            bd.setFullDisplayName(bwds.getFullDisplayName());
            bd.setId(bwds.getId());
            bd.setNumber(bwds.getNumber());
            bd.setResult(bwds.getResult().toString());
            bd.setTimestamp(bwds.getTimestamp());
            bds.add(bd);
        }
        return bds;
    }

    public String getJenkinsUrl() {
        return jenkinsUrl;
    }

    public void setJenkinsUrl(String jenkinsUrl) {
        this.jenkinsUrl = jenkinsUrl;
    }

    public String getJenkinsUser() {
        return jenkinsUser;
    }

    public void setJenkinsUser(String jenkinsUser) {
        this.jenkinsUser = jenkinsUser;
    }

    public String getJenkinsPwd() {
        return jenkinsPwd;
    }

    public void setJenkinsPwd(String jenkinsPwd) {
        this.jenkinsPwd = jenkinsPwd;
    }
}