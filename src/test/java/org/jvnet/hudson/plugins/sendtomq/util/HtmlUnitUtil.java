package org.jvnet.hudson.plugins.sendtomq.util;
import java.io.IOException;

import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;

public final class HtmlUnitUtil {
    
    private static HtmlElement component;
    private HtmlInput input;
    private HtmlTextArea textarea;
    private HtmlSelect select;
    private HtmlCheckBoxInput checkbox;
    private HtmlPage page;

    private static HtmlUnitUtil INSTANCE = new HtmlUnitUtil();

    private HtmlUnitUtil() {}

    public static HtmlUnitUtil getInstance(HtmlPage pagina, String componenteId) {
        INSTANCE.page = pagina;
        component = pagina.getElementById(componenteId);
        return INSTANCE;
    }

    public static HtmlUnitUtil getInstanceByXpath(HtmlPage pagina, String xpathExpr) {
        INSTANCE.page = pagina;
        component = (HtmlElement) pagina.getByXPath(xpathExpr).get(0);
        return INSTANCE;
    }
    
    public static void submit(final HtmlPage page, final String form, final String button) {
        try {
            HtmlForm frm = page.getFormByName(form);
            HtmlButton btn = (HtmlButton) page.getByXPath(button).get(0);
            frm.submit(btn);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void click() {
        try {
            component.click();
        }
        catch (final IOException e) {}
    }

    public void select(String valor) {
        select = (HtmlSelect) component;
        final HtmlOption option = select.getOptionByValue(valor);
        select.setSelectedAttribute(option, true);
    }

    public void insert(String valor) {
        textarea = (HtmlTextArea) component;
        textarea.setText(valor);
    }

    public void put(String valor) {
        try {
            textarea = (HtmlTextArea) component;
            textarea.type(valor);
        } catch (final IOException e) {}
    }

    public void type(String valor) {
        input = (HtmlInput) component;
        input.setValueAttribute(valor);
    }

    public String value() {
        return component.asText();
    }
    
    public String selectedValue() {
        select = (HtmlSelect) component;
        return select.getSelectedOptions().get(0).getValueAttribute();
    }

    public boolean hasOptionValue(String value) {
        select = (HtmlSelect) component;
        final HtmlOption option = select.getOptionByValue(value);
        return value.equals(option.getValueAttribute());
    }

    public boolean isChecked() {
        checkbox = (HtmlCheckBoxInput) component;
        return checkbox.isChecked();
    }
}