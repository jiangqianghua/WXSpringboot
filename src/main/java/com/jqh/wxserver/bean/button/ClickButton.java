package com.jqh.wxserver.bean.button;

/**
 * 点击按钮
 */
public class ClickButton extends AbstractButton {

    private String type = "click";
    private String key ;

    public ClickButton(String name, String key) {
        super(name);
        this.key = key;
    }
}
