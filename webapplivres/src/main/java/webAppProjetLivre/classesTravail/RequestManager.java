package webAppProjetLivre.classesTravail;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RequestManager {

    //private static final String[] actionsAExlureDuTraçage = new String[]{"deconnexion","connexionUtilisateur"};


    public HttpServletRequest addLocationParameter(HttpServletRequest currentRequest){

        String page0=(String)currentRequest.getSession().getAttribute("page0");
        String page1=(String)currentRequest.getSession().getAttribute("page1");
        String strRequestParameters;
        List<String> mapActions = ActionsListener.getRuntimeActionConfigs();
        // exclusion des actions indiquées dans actionsAExlure
        /*for (String strAction:actionsAExlureDuTraçage) {
            mapActions.remove(strAction);
        }*/
        String actionPotentielle = currentRequest.getRequestURI().substring(1,currentRequest.getRequestURI().length());
        actionPotentielle=actionPotentielle.split("/")[0];
        if(!mapActions.contains(actionPotentielle) && !actionPotentielle.equals("")){
            return currentRequest;
        }
        // On recupère les paramètres
        strRequestParameters=httpGetQueryParameters(currentRequest);

        if(page0==null){
            currentRequest.getSession().setAttribute("page0",currentRequest.getRequestURI() +  strRequestParameters);
        }else if(page1==null){
            currentRequest.getSession().setAttribute("page1",currentRequest.getRequestURI() +  strRequestParameters);
        }else{
            currentRequest.getSession().setAttribute("page0",page1);
            currentRequest.getSession().setAttribute("page1",currentRequest.getRequestURI() +  strRequestParameters);
        }
        return currentRequest;
    }

    public static String httpGetQueryParameters(HttpServletRequest request){
        if(!request.getMethod().equals("GET")){
            return "";
        }
        String strRequestParameters="?" + request.getQueryString();
        if(strRequestParameters.equals("?") || strRequestParameters.equals("?null")){
            strRequestParameters="";
        }
        return strRequestParameters;
    }


    public static String pagePrecedenteParametrable(HttpServletRequest theCurrentRequest, String parametre){
        String commandPage;
        String pagePrecedente = (String)theCurrentRequest.getSession().getAttribute("page0");
        // si il n y a pas d'historique on redirige à l'acceuil
        if(pagePrecedente != null && !pagePrecedente.equals(theCurrentRequest.getRequestURI() + RequestManager.httpGetQueryParameters(theCurrentRequest))){
            commandPage=pagePrecedente + parametre;
        }else{
            // redirection page acceuil
            commandPage="/";
        }
        return commandPage;
    }


}
