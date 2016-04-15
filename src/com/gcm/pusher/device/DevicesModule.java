package com.gcm.pusher.device;


import com.vaadin.data.Item;
import com.vaadin.server.FontAwesome;
import it.algos.webbase.web.form.ModuleForm;
import it.algos.webbase.web.module.ModulePop;
import it.algos.webbase.web.table.TablePortal;

import javax.persistence.metamodel.Attribute;

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

