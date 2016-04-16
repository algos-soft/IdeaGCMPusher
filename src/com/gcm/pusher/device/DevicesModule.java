package com.gcm.pusher.device;


import com.gcm.pusher.servlets.RegistrationServlet;
import com.vaadin.data.Item;
import com.vaadin.server.ClientConnector;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.UI;
import it.algos.webbase.web.form.ModuleForm;
import it.algos.webbase.web.module.ModulePop;
import it.algos.webbase.web.table.TablePortal;

import javax.persistence.metamodel.Attribute;

/**
 * Modulo Devices
 */
public class DevicesModule extends ModulePop {

    private UI ui;

    public DevicesModule() {
        super(Device.class, "Devices", FontAwesome.CHECK_SQUARE_O);

        // la collezione dei listeners è statica, per sicurezza rimuovo tutti
        // i listeners prima di aggiungerli
        RegistrationServlet.removeAllRegistrationListeners();

        // aggiungo i listeners al servlet
        RegistrationServlet.addRegistrationListener(new RegistrationServlet.RegistrationListener() {
            @Override
            public void deviceCreated(Device device) {
                refreshTable();
            }

            @Override
            public void deviceUpdated(Device device) {
                refreshTable();
            }

            @Override
            public void deviceDeleted(Device device) {
                refreshTable();
            }
        });

        addAttachListener(new AttachListener() {
            @Override
            public void attach(AttachEvent event) {
                ui=UI.getCurrent();
                int a = 87;
            }
        });
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



    private void refreshTable() {

        getTable().getFilterableContainer().removeAllContainerFilters();
        getTable().refresh();

    }


}

