package com.devin.ciserver.model.xml;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Administrator on 2018/8/8.
 */
public class Properties {
    private HudsonModelParametersDefinitionProperty hmpd;

    @XmlElement(name="hudson.model.ParametersDefinitionProperty")
    public HudsonModelParametersDefinitionProperty getHmpd() {
        return hmpd;
    }

    public void setHmpd(HudsonModelParametersDefinitionProperty hmpd) {
        this.hmpd = hmpd;
    }
}
