package com.devin.ciserver.Inter;
import com.devin.ciserver.model.job.BuildDetail;
import com.devin.ciserver.model.job.JobDetail;
import com.offbytwo.jenkins.model.BuildWithDetails;

import java.util.Map;
import java.util.List;

/**
 * Created by devin on 2018/8/8.
 */
public interface JobService {
    public void createJob(String jobName,String configXml) throws Exception;
    public void updateJob(String jobName,String configXml) throws Exception;
    public void deleteJob(String jobName) throws Exception;
    public void buildJob(String jobName) throws Exception;
    public void buildJob(String jobName,Map<String, String> params) throws Exception;
    public BuildWithDetails getBuildJobDetailByLastTime(String jobName) throws Exception;
    public BuildWithDetails getBuildJobDetailByNumber(String jobName,int Number) throws Exception;

    public JobDetail getJobDetail(String jobName) throws Exception;
    public List<JobDetail> getJobs(String queryKey) throws Exception;
    public List<BuildDetail> getJobBuilds(String jobName) throws Exception;
    public List<BuildDetail> getJobBuildsByRange(String jobName,Integer from,Integer to) throws Exception;

}
