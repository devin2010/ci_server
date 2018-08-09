package com.devin.ciserver.util;

import com.devin.ciserver.model.job.BaseJob;
import com.devin.ciserver.model.job.StringParm;
import com.devin.ciserver.model.xml.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by devin on 2018/8/8.
 */
public class XmlUtil {
    /**
     *
     * @param obj
     * @param load
     * @return
     * @throws JAXBException
     */
    public static String beanToXml(Object obj,Class<?> load) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(load);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        return writer.toString();
    }
    public static String parseBaseJobToXml(BaseJob baseJob){
        Job job=new Job();
        try {
            if(baseJob!=null){
                String desc=baseJob.getDesc();
                if(desc!=null){
                    job.setDescription(desc);
                }
                List<StringParm> stringParms=baseJob.getStringParms();
                List<String> shellCmds=baseJob.getShellCmd();
                if(stringParms!=null&&stringParms.size()>0){
                    List<StringParameterDefinition> spds=new ArrayList<StringParameterDefinition>();
                    for(StringParm sp:stringParms){
                        StringParameterDefinition spd=new StringParameterDefinition();
                        spd.setName(sp.getName());
                        spd.setDefaultValue(sp.getDefaultValue());
                        spd.setDescription(sp.getDescription());
                        spds.add(spd);
                    }
                    HudsonModelParametersDefinitionProperty hmpd=new HudsonModelParametersDefinitionProperty();
                    ParameterDefinitions pds=new ParameterDefinitions();
                    hmpd.setParameterDefinitions(pds);
                    pds.setSpds(spds);
                    job.getProperties().setHmpd(hmpd);
                }
                if(shellCmds!=null&&shellCmds.size()>0){
                    List<HudsonTasksShell> hudsonTasksShells =new ArrayList<HudsonTasksShell>();
                    for(String cmd:shellCmds){
                        HudsonTasksShell hudsonTasksShell=new HudsonTasksShell();
                        hudsonTasksShell.setCommand(cmd);
                        hudsonTasksShells.add(hudsonTasksShell);
                    }
                    job.getBuilders().setHudsonTasksShells(hudsonTasksShells);

                }
            }
            String xml=beanToXml(job,Job.class);
            return xml;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
//        Job job=new Job();
//        try {
//            String xml=beanToXml(job,Job.class);
//            System.out.println(xml);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }

            Map<String, String> map = System.getenv();
            for(Iterator<String> itr = map.keySet().iterator();itr.hasNext();){
                String key = itr.next();
                System.out.println(key + "=" + map.get(key));
            }
    }
}
