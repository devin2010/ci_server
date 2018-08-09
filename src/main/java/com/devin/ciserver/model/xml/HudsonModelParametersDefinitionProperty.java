package com.devin.ciserver.model.xml;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by devin on 2018/8/8.
 */
public class HudsonModelParametersDefinitionProperty {
    private  ParameterDefinitions parameterDefinitions;

    @XmlElement(name="parameterDefinitions")
    public ParameterDefinitions getParameterDefinitions() {
        return parameterDefinitions;
    }

    public void setParameterDefinitions(ParameterDefinitions parameterDefinitions) {
        this.parameterDefinitions = parameterDefinitions;
    }
}
