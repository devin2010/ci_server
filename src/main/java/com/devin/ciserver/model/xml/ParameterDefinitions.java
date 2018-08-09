package com.devin.ciserver.model.xml;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by devin on 2018/8/8.
 */
public class ParameterDefinitions {
    private List<StringParameterDefinition> spds;

    @XmlElement(name = "hudson.model.StringParameterDefinition")
    public List<StringParameterDefinition> getSpds() {
        return spds;
    }

    public void setSpds(List<StringParameterDefinition> spds) {
        this.spds = spds;
    }
}
