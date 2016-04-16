package com.gcm.pusher.device;


import com.gcm.pusher.servlets.RegistrationServlet;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ClientConnector;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import it.algos.webbase.web.form.ModuleForm;
import it.algos.webbase.web.module.ModulePop;
import it.algos.webbase.web.table.TablePortal;

import javax.persistence.metamodel.Attribute;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Modulo Devices
 */
public class DevicesModule extends ModulePop {

    public DevicesModule() {
        super(Device.class, "Devices", FontAwesome.CHECK_SQUARE_O);
    }

    @Override
    public ModuleForm createForm(Item item) {
        return new DeviceForm(item, this);
    }

    @Override
    public TablePortal createTablePortal() {
        return new DeviceTablePortal(this);
    }


    @Override
    protected Attribute<?, ?>[] creaFieldsList() {
        return new Attribute[]{
                Device_.deviceId,
                Device_.name,
                Device_.model};
    }


}

