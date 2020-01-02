package com.zixuan.factory;

import com.zixuan.factory.i18n.I18N;
import com.zixuan.factory.i18n.I18NFactory;

public class Software {
    public static void main(String[] args) {
        I18N i18N1 = I18NFactory.getI18NObject("China");
        System.out.println(i18N1.getTitle());

        I18N i18N2 = I18NFactory.getI18NObject("Italy");
        System.out.println(i18N2.getTitle());

        I18N i18N3 = I18NFactory.getI18NObject("Spain");
        System.out.println(i18N3.getTitle());
    }
}
