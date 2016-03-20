package demo.com.cn.view.session;

import java.io.Serializable;

import java.util.Currency;
import java.util.Locale;

import javax.faces.context.FacesContext;

import oracle.adf.view.rich.context.AdfFacesContext;

public class CustomCurrency implements Serializable {
    @SuppressWarnings("compatibility:4932410153575189142")
    private static final long serialVersionUID = -2233958407621647331L;

    Currency currency;
    private String currCode;
    private String currSymbol;


    public CustomCurrency() {
        super();
    }


    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        String locale = FacesContext.getCurrentInstance().getViewRoot().getLocale().toString();
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        if ("zh_CN".equals(locale)) {
            currency = Currency.getInstance(Locale.SIMPLIFIED_CHINESE);
        } else {
            currency = Currency.getInstance(Locale.US);
        }
        System.out.println("Curr: " + currency + "  Curr Code:" + currency.getCurrencyCode() + "   CurrSymoble: " +
                           currency.getSymbol());
        return currency;
    }

    public void setCurrCode(String currCode) {
        this.currCode = currCode;
    }

    public String getCurrCode() {
        currCode = currency.getCurrencyCode();
        return currCode;
    }

    public void setCurrSymbol(String currSymbol) {
        this.currSymbol = currSymbol;
    }

    public String getCurrSymbol() {
        currSymbol = currency.getSymbol();
        return currSymbol == "USD" ? "$" : currSymbol;
    }

    public static void main(String[] args) {
        Locale locale = Locale.US;
        Currency curr = Currency.getInstance(locale);
        System.out.println("Currency:  " + curr);
        String currCode = curr.getCurrencyCode();
        System.out.println("Locale's currency code is: " + currCode);
        String currSymbol = curr.getSymbol();
        System.out.println("Currency Symbol:  " + currSymbol);
    }
}
