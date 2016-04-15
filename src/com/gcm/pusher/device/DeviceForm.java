package com.gcm.pusher.device;

import com.vaadin.data.Item;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import it.algos.webbase.web.field.TextArea;
import it.algos.webbase.web.form.ModuleForm;
import it.algos.webbase.web.module.ModulePop;

import javax.persistence.metamodel.Attribute;

/**
 * Created by alex on 14/04/16.
 */
public class DeviceForm extends ModuleForm {

    public DeviceForm(Item item, ModulePop module) {
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

        if(attr.equals(Device_.token)) {
            TextArea ta = new TextArea();
            ta.setWidth("30em");
            field = ta;
            String caption = DefaultFieldFactory.createCaptionByPropertyId(attr.getName());
            field.setCaption(caption);
            doSuper=false;
        }

//        if(attr.equals(Device_.lastPushDate)) {
//            DateField df = new DateField();
////            df.set
////            ta.setWidth("30em");
////            field = ta;
////            String caption = DefaultFieldFactory.createCaptionByPropertyId(attr.getName());
////            field.setCaption(caption);
//            doSuper=false;
//        }



        if(doSuper){
            field = super.createField(attr);
        }

        return field;
    }
}
