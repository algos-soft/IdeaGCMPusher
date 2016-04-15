package com.gcm.pusher.device;

import com.gcm.pusher.servlets.RegisterServlet;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import it.algos.webbase.web.dialog.AlertDialog;
import it.algos.webbase.web.dialog.ConfirmDialog;
import it.algos.webbase.web.entity.BaseEntity;
import it.algos.webbase.web.field.TextField;
import it.algos.webbase.web.table.ATable;
import it.algos.webbase.web.toolbar.TableToolbar;

import java.net.URI;

/**
 * Created by alex on 08/04/16.
 */
public class DeviceTableToolbar extends TableToolbar {


    public DeviceTableToolbar(ATable table) {

        super();


        // bottone url
        addButton("Url", FontAwesome.LINK, new MenuBar.Command() {
            @Override
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                System.out.println(UI.getCurrent().getPage().getLocation().getHost());
                System.out.println(UI.getCurrent().getPage().getLocation().getPath());
                System.out.println(UI.getCurrent().getPage().getLocation().getPort());

                URI uri=UI.getCurrent().getPage().getLocation();
                int port = uri.getPort();

                String addr = "Url per la registrazione:<br><br><strong>http://XXX.XXX.XXX.XXX:"+port+
                        RegisterServlet.GCM_REG+"</strong><br><br>(sostituisci XXX.XXX.XXX.XXX con " +
                        "l'indirizzo IP del server)";

                AlertDialog dialog=new AlertDialog(addr);
                dialog.show();

            }
        });

        // bottone push
        addButton("Push", FontAwesome.ARROW_UP, new MenuBar.Command() {
            public void menuSelected(MenuBar.MenuItem selectedItem) {
                BaseEntity[] entities = table.getSelectedEntities();
                if(entities.length>0){
                    InputMsgDialog dialogo = new InputMsgDialog(new ConfirmDialog.Listener() {
                        @Override
                        public void onClose(ConfirmDialog confirmDialog, boolean confirmed) {
                            if(confirmed){

                                InputMsgDialog d =  (InputMsgDialog)confirmDialog;
                                String msg=d.getMessage();

                                for(BaseEntity entity : entities){
                                    Device device = (Device)entity;
                                    device.sendPush(msg);
                                }
                            }
                        }
                    });
                    dialogo.show();

                }
            }
        });



    }


    class InputMsgDialog extends ConfirmDialog{

        private TextField field;

        public InputMsgDialog(Listener closeListener) {
            super(closeListener);
            field = new TextField();
            field.setWidth("30em");
            field.setCaption("messaggio");
            addComponent(field);
        }


        public String getMessage(){
            return field.getValue();
        }
    }

}
