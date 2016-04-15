package com.gcm.pusher.log;


import com.vaadin.data.Item;
import it.algos.webbase.web.form.ModuleForm;
import it.algos.webbase.web.module.ModulePop;
import it.algos.webbase.web.table.ATable;

/**
 * Modulo Log
 */
public class LogModule extends ModulePop {

    public LogModule() {
        super(Log.class);
    }

    @Override
    public ModuleForm createForm(Item item) {
        return new LogForm(item, this);
    }

//    @Override
//    protected Attribute<?, ?>[] creaFieldsList() {
//        return new Attribute[]{
//                Log_.timestamp,
//                Log_.level,
//                Log_.tag,
//                Log_.message};
//    }


    @Override
    public ATable createTable() {
        return new LogTable(this);
    }
}

