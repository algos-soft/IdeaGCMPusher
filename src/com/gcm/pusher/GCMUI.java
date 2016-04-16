package com.gcm.pusher;

import com.gcm.pusher.device.Device;
import com.gcm.pusher.device.DevicesModule;
import com.gcm.pusher.log.LogModule;
import com.gcm.pusher.servlets.RegistrationServlet;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@Theme("algos")
@Push
public class GCMUI extends UI {

    private NavComponent navigator;

    public GCMUI() {

        // la collezione dei listeners è statica, per sicurezza rimuovo tutti
        // i listeners prima di aggiungerli
        RegistrationServlet.removeAllRegistrationListeners();

        // aggiungo i listeners al servlet
        RegistrationServlet.addRegistrationListener(new RegistrationServlet.RegistrationListener() {
            @Override
            public void requestServed(Device device, String regType) {
                refreshDeviceList();
            }
        });

    }

    /**
     * @param request the Vaadin request that caused this UI to be created
     */
    @Override
    protected void init(VaadinRequest request) {
        this.setContent(creaCompUI());
        setPollInterval(1000);
    }

    /**
     * Crea il componente per la UI
     *
     * @return il componente creato
     */
    private Component creaCompUI() {

        /* creo un componente standard di navigazione */
        navigator = new NavComponent(this);

        // aggiungo le view - la menubar viene riempita automaticamente
        navigator.addView(DevicesModule.class, true, "Devices", FontAwesome.TABLET);
        navigator.addView(LogModule.class, true, "Log", FontAwesome.LIST);

        // da chiamare dopo che ho aggiunto tutti i MenuItems,
        // configura il Navigator in base alla MenuBar
        navigator.setup();

        return navigator;
    }


    /**
     * Aggiorna la lista dei device.
     * Solo se il modulo Devices è correntemente visualizzato.
     */
    private void refreshDeviceList() {


        if(isAttached()){
            // Init done, update the UI after doing locking
            access(new Runnable() {
                @Override
                public void run() {
                    View view=navigator.getNavigator().getCurrentView();
                    if(view != null && view instanceof DevicesModule){
                        DevicesModule module=(DevicesModule)view;
                        // Here the UI is locked and can be updated
                        module.getTable().refresh();
                    }
                }
            });
        }


    }



}
