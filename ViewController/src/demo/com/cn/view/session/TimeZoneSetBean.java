package demo.com.cn.view.session;

import java.io.Serializable;

import java.util.Locale;
import java.util.TimeZone;

import javax.el.ELContext;
import javax.el.ExpressionFactory;


import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

public class TimeZoneSetBean implements Serializable {
    @SuppressWarnings("compatibility:-6864447233766830112")
    private static final long serialVersionUID = 1L;

    TimeZone userTimeZone;
    //TimeZone defaultTimeZone = TimeZone.getTimeZone("America/Denver");
    //Asia/Tokyo   GMT+8
    //TimeZone defaultTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
    TimeZone defaultTimeZone = TimeZone.getTimeZone("GMT+8");

    public TimeZoneSetBean() {
        super();
    }

    public void setUserTimeZone(TimeZone userTimeZone) {
        this.userTimeZone = userTimeZone;
    }

    public TimeZone getUserTimeZone() {
        return (userTimeZone == null) ? findTimeZone() : userTimeZone;
    }

    TimeZone findTimeZone() {
        String locale = FacesContext.getCurrentInstance().getViewRoot().getLocale().toString();
        System.out.println("-----------------locale:  " + locale);
        if ("zh_CN".equalsIgnoreCase(locale)) {
            return TimeZone.getTimeZone("GMT+8");
        } else if ("en_US".equalsIgnoreCase(locale)) {
            return TimeZone.getTimeZone("GMT-5");
        }
        return defaultTimeZone;
    }
}
