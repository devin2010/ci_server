package com.devin.ciserver.model.xml;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
/**
 * Created by devin on 2018/8/8.
 */
public class Builders {
    private List<HudsonTasksShell> hudsonTasksShells;

    @XmlElement(name="hudson.tasks.Shell")
    public List<HudsonTasksShell> getHudsonTasksShells() {
        return hudsonTasksShells;
    }

    public void setHudsonTasksShells(List<HudsonTasksShell> hudsonTasksShells) {
        this.hudsonTasksShells = hudsonTasksShells;
    }
}
