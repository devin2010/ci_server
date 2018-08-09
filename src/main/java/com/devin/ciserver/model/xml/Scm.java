package com.devin.ciserver.model.xml;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by devin on 2018/8/8.
 */
public class Scm {
    private String scmClassName="hudson.scm.NullSCM";

    @XmlAttribute(name="class")
    public String getScmClassName() {
        return scmClassName;
    }

    public void setScmClassName(String scmClassName) {
        this.scmClassName = scmClassName;
    }
}
