package com.devin.ciserver.model.job;

/**
 * Created by devin on 2018/8/9.
 */
public class BuildDetail {
    private String displayName;
    private String fullDisplayName;
    private String id;
    private String result;
    private String consoleOutputText;
    private String consoleOutputHtml;
    private long number;
    private long duration;
    private long timestamp;

    public BuildDetail() {
    }

    public BuildDetail(String displayName, String fullDisplayName, String id, String result, String consoleOutputText, String consoleOutputHtml, long number, long duration, long timestamp) {
        this.displayName = displayName;
        this.fullDisplayName = fullDisplayName;
        this.id = id;
        this.result = result;
        this.consoleOutputText = consoleOutputText;
        this.consoleOutputHtml = consoleOutputHtml;
        this.number = number;
        this.duration = duration;
        this.timestamp = timestamp;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFullDisplayName() {
        return fullDisplayName;
    }

    public void setFullDisplayName(String fullDisplayName) {
        this.fullDisplayName = fullDisplayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getConsoleOutputText() {
        return consoleOutputText;
    }

    public void setConsoleOutputText(String consoleOutputText) {
        this.consoleOutputText = consoleOutputText;
    }

    public String getConsoleOutputHtml() {
        return consoleOutputHtml;
    }

    public void setConsoleOutputHtml(String consoleOutputHtml) {
        this.consoleOutputHtml = consoleOutputHtml;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
