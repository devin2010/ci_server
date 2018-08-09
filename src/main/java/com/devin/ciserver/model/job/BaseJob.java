package com.devin.ciserver.model.job;
import java.util.List;

/**
 * Created by devin on 2018/8/8.
 */
public class BaseJob extends Job {
    private List<StringParm> stringParms;
    private List<String> shellCmd;

    public List<StringParm> getStringParms() {
        return stringParms;
    }

    public void setStringParms(List<StringParm> stringParms) {
        this.stringParms = stringParms;
    }

    public List<String> getShellCmd() {
        return shellCmd;
    }

    public void setShellCmd(List<String> shellCmd) {
        this.shellCmd = shellCmd;
    }
}
