package demo.com.cn.view.session;

import java.io.IOException;
import java.io.Serializable;

import java.util.Locale;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.controller.v2.lifecycle.PagePhaseEvent;
import oracle.adf.controller.v2.lifecycle.PagePhaseListener;

public class LocalSelect implements Serializable {
    public LocalSelect() {
        super();
    }

    String strLocale = "zh";
    Locale userLocale;
    Locale defaultLocale = Locale.SIMPLIFIED_CHINESE;

    public void setUserLocale(Locale userLocale) {
        this.userLocale = userLocale;
    }

    public Locale getUserLocale() {
        return (userLocale == null) ? getLocale() : defaultLocale;
    }

    public void setStrLocale(String strLocale) {
        this.strLocale = strLocale;
    }

    public String getStrLocale() {
        return strLocale;
    }


    public void changeLocale(ActionEvent actionEvent) {
        //this.getLocale();
        this.reloadPage();
    }

    Locale getLocale() {
        if ("zh".equals(strLocale)) {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.SIMPLIFIED_CHINESE);
            return Locale.SIMPLIFIED_CHINESE;
        } else if ("en".equals(strLocale)) {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.US);
            return Locale.US;
        }
        return defaultLocale;
    }

    public static void reloadPage() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String viewId = facesContext.getViewRoot().getViewId();
        String actionURL = facesContext.getApplication().getViewHandler().getActionURL(facesContext, viewId);
        try {
            ExternalContext eContext = facesContext.getExternalContext();
            String resourceURL = actionURL;
            eContext.redirect(resourceURL);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private Object resolveExpression(String expression) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp = elFactory.createValueExpression(elContext, expression, Object.class);
        return valueExp.getValue(elContext);
    }
}
