package com.devin.ciserver.util;

import com.devin.ciserver.model.job.BuildDetail;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.BuildWithDetails;
import org.apache.commons.lang.StringUtils;
import java.util.List;
import java.util.ArrayList;


/**
 * Created by devin on 2018/8/9.
 */
public class ConvertUtil {
    public static BuildDetail buildWithDetailToBuildDetail(BuildWithDetails bwd)throws Exception{
        if(bwd==null){
            return null;
        }
        String consoleOutputText="";
        String consoleOutputHtml="";
        try {
            if(null!=bwd.getConsoleOutputText()&&!bwd.getConsoleOutputText().equals("")){
                consoleOutputText=bwd.getConsoleOutputText();
            }
        }catch (Exception e){
        }

        try {
            if(null!=bwd.getConsoleOutputHtml()&&!bwd.getConsoleOutputHtml().equals("")){
                consoleOutputHtml=bwd.getConsoleOutputHtml();
            }
        }catch (Exception e){
        }

        BuildDetail bd=new BuildDetail(
                bwd.getDisplayName(),
                bwd.getFullDisplayName(),
                bwd.getId(),
                bwd.getResult().toString(),
                consoleOutputText,
                consoleOutputHtml,
                bwd.getNumber(),
                bwd.getDuration(),
                bwd.getTimestamp());
        return bd;
    }
    public static List<BuildDetail> buildWithDetailsToBuildDetails(List<BuildWithDetails> bwds)throws Exception{
        if(bwds==null){
            return null;
        }
        List<BuildDetail> bds=new ArrayList<BuildDetail>();
        for(BuildWithDetails bwd:bwds){
            bds.add(buildWithDetailToBuildDetail(bwd));
        }
        return bds;
    }
    public static BuildDetail buildToBuildDetail(Build build)throws Exception{
        if(build==null){
            return null;
        }
        BuildWithDetails bwd = build.details();
        if(bwd==null){
            return null;
        }
        return buildWithDetailToBuildDetail(bwd);
    }

    public static List<BuildDetail> buildsToBuildDetails(List<Build> builds)throws Exception{
        if(builds==null){
            return null;
        }
        List<BuildDetail> buildDetails=new ArrayList<BuildDetail>();
        for(Build build:builds){
            buildDetails.add(buildToBuildDetail(build));
        }
        return buildDetails;
    }

}
