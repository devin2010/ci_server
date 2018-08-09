package com.devin.ciserver.controller;

import com.devin.ciserver.Inter.JobService;
import com.devin.ciserver.model.CiResponse;
import com.devin.ciserver.model.Constants;
import com.devin.ciserver.model.job.BaseJob;
import com.devin.ciserver.model.job.BuildDetail;
import com.devin.ciserver.model.job.JobDetail;
import com.devin.ciserver.model.job.StringPair;
import com.devin.ciserver.util.XmlUtil;
import com.offbytwo.jenkins.model.BuildWithDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by devin on 2018/8/8.
 */
@Api(value = "集成服务-任务管理接口")
@RequestMapping("/api/job-admin/v1")
@RestController
public class JobV1Controller extends BaseController{
    @Autowired
    private JobService jobService;
    @ApiOperation(value = "获取系统job信息")
    @RequestMapping(value = "/job", method = RequestMethod.GET)
    public CiResponse<List<JobDetail>> getJobs(@RequestParam(value = "queryKey", required = false) String queryKey){
        try {
            List<JobDetail> jobDetails=jobService.getJobs(queryKey);
            return new CiResponse<List<JobDetail>>(Constants.RESPONSE_FLAG_SUCCESS,jobDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<List<JobDetail>>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation(value = "获取单个job明细")
    @RequestMapping(value = "/job/{jobName}/detail", method = RequestMethod.GET)
    public CiResponse<JobDetail> getJobDetail(@PathVariable(value = "jobName") String jobName){
        try {
            if(jobName==null||jobName.equals("")){
                return new CiResponse<JobDetail>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            JobDetail jobDetail=jobService.getJobDetail(jobName);
            return new CiResponse<JobDetail>(Constants.RESPONSE_FLAG_SUCCESS,jobDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<JobDetail>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation("创建job")
    @RequestMapping(value = "/job", method = RequestMethod.POST)
    public CiResponse<Object> jobAdd(@RequestBody BaseJob baseJob) {
        try {
            if(baseJob.getJobName()==null||baseJob.getJobName().equals("")){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            jobService.createJob(baseJob.getJobName(), XmlUtil.parseBaseJobToXml(baseJob));
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation("修改job")
    @RequestMapping(value = "/job", method = RequestMethod.PATCH)
    public CiResponse<Object> jobUpdate(@RequestBody BaseJob baseJob) {
        try {
            if(baseJob.getJobName()==null||baseJob.getJobName().equals("")){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            jobService.updateJob(baseJob.getJobName(),XmlUtil.parseBaseJobToXml(baseJob));
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation("删除job")
    @RequestMapping(value = "/job", method = RequestMethod.DELETE)
    public CiResponse<Object> jobDelete(@RequestParam(value = "jobName", required = true) String jobName) {
        try {
            if(jobName==null||jobName.equals("")){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            jobService.deleteJob(jobName);
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation("执行job")
    @RequestMapping(value = "/job/build/{jobName}", method = RequestMethod.POST)
    public CiResponse<Object> jobBuild(@PathVariable(value = "jobName") String jobName) {
        try {
            if(jobName==null||jobName.equals("")){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            jobService.buildJob(jobName);
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation("执行job(带参数)")
    @RequestMapping(value = "/job/build/{jobName}/param", method = RequestMethod.POST)
    public CiResponse<Object> jobBuild1(@PathVariable(value = "jobName") String jobName,@RequestBody List<StringPair> params) {
        try {
            if(jobName==null||jobName.equals("")){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            if(params==null||params.size()==0){
                return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,"参数不能为空!");
            }
            Map<String,String> paramMap=new HashMap<String, String>();
            for(StringPair sp:params){
                paramMap.put(sp.getParamName(),sp.getParamValue());
            }
            jobService.buildJob(jobName,paramMap);
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<Object>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation(value = "获取单个job的所有build信息")
    @RequestMapping(value = "/job/build/{jobName}", method = RequestMethod.GET)
    public CiResponse<List<BuildDetail>> getJobBuilds(@PathVariable(value = "jobName") String jobName){
        try {
            if(jobName==null||jobName.equals("")){
                return new CiResponse<List<BuildDetail>>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            List<BuildDetail> buildDetails=jobService.getJobBuilds(jobName);
            return new CiResponse<List<BuildDetail>>(Constants.RESPONSE_FLAG_SUCCESS,buildDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<List<BuildDetail>>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation(value = "根据范围获取单个job的build信息")
    @RequestMapping(value = "/job/build/{jobName}/range/{from}/{to}", method = RequestMethod.GET)
    public CiResponse<List<BuildDetail>> getJobBuildsByRange(@PathVariable(value = "jobName") String jobName,
                                                             @PathVariable(value = "from") Integer from,
                                                             @PathVariable(value = "to") Integer to){
        try {
            if(jobName==null||jobName.equals("")){
                return new CiResponse<List<BuildDetail>>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            List<BuildDetail> buildDetails=jobService.getJobBuildsByRange(jobName,from,to);
            return new CiResponse<List<BuildDetail>>(Constants.RESPONSE_FLAG_SUCCESS,buildDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<List<BuildDetail>>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation(value = "获取job最后一次执行的build明细信息")
    @RequestMapping(value = "/job/build/{jobName}/detail/last", method = RequestMethod.GET)
    public CiResponse<BuildDetail> jobBuildDetail(@PathVariable(value = "jobName") String jobName){
        try {
            if(jobName==null||jobName.equals("")){
                return new CiResponse<BuildDetail>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            BuildWithDetails bwd=jobService.getBuildJobDetailByLastTime(jobName);
            if(bwd.getId()==null){
                return new CiResponse<BuildDetail>(Constants.RESPONSE_FLAG_FAILURE,"job["+jobName+"]没有build记录!");
            }
            BuildDetail bd=new BuildDetail(
                    bwd.getDisplayName(),
                    bwd.getFullDisplayName(),
                    bwd.getId(),
                    bwd.getResult().toString(),
                    StringUtils.isEmpty(bwd.getConsoleOutputText())?"":bwd.getConsoleOutputText(),
                    StringUtils.isEmpty(bwd.getConsoleOutputHtml())?"":bwd.getConsoleOutputHtml(),
                    bwd.getNumber(),
                    bwd.getDuration(),
                    bwd.getTimestamp());
            return new CiResponse<BuildDetail>(Constants.RESPONSE_FLAG_SUCCESS,bd);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<BuildDetail>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }

    @ApiOperation(value = "获取job第number次执行的build明细信息")
    @RequestMapping(value = "/job/build/{jobName}/detail/{number}", method = RequestMethod.GET)
    public CiResponse<BuildDetail> jobBuildDetailByNumber(@PathVariable(value = "jobName") String jobName,@PathVariable(value = "number") int number){
        try {
            if(jobName==null||jobName.equals("")){
                return new CiResponse<BuildDetail>(Constants.RESPONSE_FLAG_FAILURE,"job名称不能为空!");
            }
            BuildWithDetails bwd=jobService.getBuildJobDetailByNumber(jobName,number);
            if(bwd.getId()==null){
                return new CiResponse<BuildDetail>(Constants.RESPONSE_FLAG_FAILURE,"job["+jobName+"]没有第["+number+"]的build记录!");
            }
            BuildDetail bd=new BuildDetail(
                    bwd.getDisplayName(),
                    bwd.getFullDisplayName(),
                    bwd.getId(),
                    bwd.getResult().toString(),
                    StringUtils.isEmpty(bwd.getConsoleOutputText())?"":bwd.getConsoleOutputText(),
                    StringUtils.isEmpty(bwd.getConsoleOutputHtml())?"":bwd.getConsoleOutputHtml(),
                    bwd.getNumber(),
                    bwd.getDuration(),
                    bwd.getTimestamp());
            return new CiResponse<BuildDetail>(Constants.RESPONSE_FLAG_SUCCESS,bd);
        } catch (Exception e) {
            e.printStackTrace();
            return new CiResponse<BuildDetail>(Constants.RESPONSE_FLAG_FAILURE,e.getMessage());
        }
    }
}
