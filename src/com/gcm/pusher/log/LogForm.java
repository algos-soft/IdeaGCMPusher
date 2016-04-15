package com.gcm.pusher.log;

import com.vaadin.data.Item;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import it.algos.webbase.web.field.DateField;
import it.algos.webbase.web.field.TextArea;
import it.algos.webbase.web.form.ModuleForm;
import it.algos.webbase.web.module.ModulePop;

import javax.persistence.metamodel.Attribute;

/**
 * Created by alex on 14/04/16.
 */
public class LogForm extends ModuleForm {

    public LogForm(Item item, ModulePop module) {
        super(item, module);
    }

    @Override
    public void createFields() {
        super.createFields();
    }

    @Override
    protected Field createField(Attribute attr) {
        Field field=null;

        boolean doSuper=true;

        if(attr.equals(Log_.message)) {
            TextArea ta = new TextArea();
            ta.setWidth("30em");
            field = ta;
            String caption = DefaultFieldFactory.createCaptionByPropertyId(attr.getName());
            field.setCaption(caption);
            doSuper=false;
        }

        if(attr.equals(Log_.timestamp)) {
            DateField df = new DateField();
            df.setResolution(Resolution.SECOND);
            df.setWidth("14em");
            field = df;
            String caption = DefaultFieldFactory.createCaptionByPropertyId(attr.getName());
            field.setCaption(caption);
            doSuper=false;
        }




        if(doSuper){
            field = super.createField(attr);
        }

        return field;
    }
}
