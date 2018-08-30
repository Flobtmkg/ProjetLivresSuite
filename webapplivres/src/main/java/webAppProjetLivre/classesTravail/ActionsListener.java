package webAppProjetLivre.classesTravail;

import com.opensymphony.xwork2.config.entities.ActionConfig;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.DispatcherListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActionsListener implements ServletContextListener,DispatcherListener {

    static List<String> runtimeActionConfigs;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Dispatcher.addDispatcherListener(this);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void dispatcherInitialized(Dispatcher dispatcher) {
        Map<String, Map<String, ActionConfig>> mapMapAction= dispatcher.getConfigurationManager().getConfiguration().getRuntimeConfiguration().getActionConfigs();
        List<String>  actionList = new ArrayList<>();
        actionList.addAll(mapMapAction.get("/").keySet());
        runtimeActionConfigs = actionList;

    }

    @Override
    public void dispatcherDestroyed(Dispatcher dispatcher) {

    }

    public static List<String> getRuntimeActionConfigs(){
        return runtimeActionConfigs;
    }
}
