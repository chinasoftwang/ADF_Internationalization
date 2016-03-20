package demo.com.cn.view.pagePhase;

import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;

import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.controller.v2.lifecycle.PagePhaseEvent;
import oracle.adf.controller.v2.lifecycle.PagePhaseListener;
import oracle.adf.share.ADFContext;

public class customPagePhase implements PagePhaseListener {
    public customPagePhase() {
        super();
    }


    @Override
    public void afterPhase(PagePhaseEvent pagePhaseEvent) {
        // TODO Implement this method
    }

    @Override
    public void beforePhase(PagePhaseEvent pagePhaseEvent) {
        // TODO Implement this method
        if (pagePhaseEvent.getPhaseId() == Lifecycle.PREPARE_MODEL_ID) {
            FacesContext fc = FacesContext.getCurrentInstance();
            String locale = this.getSessionAttByKey("userLanguage");
            System.out.println("Locale is:  " + locale);
            if (locale != null && !"".equals(locale)) {
                if ("zh_CN".equals(locale)) {
                    FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.SIMPLIFIED_CHINESE);
                } else if ("en_US".equals(locale)) {
                    FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.ENGLISH);
                }
            } else {
                FacesContext.getCurrentInstance().getViewRoot().setLocale(Locale.ENGLISH);
            }
        }
    }

    public String getSessionAttByKey(String strKey) {
        ADFContext ac = ADFContext.getCurrent();
        Map mp = ac.getSessionScope();
        return mp.get(strKey) == null ? "" : mp.get(strKey).toString();
    }
}
