package com.gcm.pusher;

import com.gcm.pusher.device.DevicesModule;
import com.gcm.pusher.log.LogModule;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

@Theme("algos")
public class GCMUI extends UI {

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
        NavComponent nc = new NavComponent(this);

        // aggiungo le view - la menubar viene riempita automaticamente
        nc.addView(DevicesModule.class, true, "Devices", FontAwesome.TABLET);
        nc.addView(LogModule.class, true, "Log", FontAwesome.LIST);

        // da chiamare dopo che ho aggiunto tutti i MenuItems,
        // configura il Navigator in base alla MenuBar
        nc.setup();

        return nc;
    }

}
