package com.gcm.pusher.device;

import it.algos.webbase.web.module.ModulePop;
import it.algos.webbase.web.table.TablePortal;
import it.algos.webbase.web.toolbar.TableToolbar;

/**
 * Created by alex on 08/04/16.
 */
public class DeviceTablePortal extends TablePortal {
    public DeviceTablePortal(ModulePop modulo) {
        super(modulo);
    }

    public TableToolbar createToolbar() {
        return new DeviceTableToolbar(getTable());
    }

}
