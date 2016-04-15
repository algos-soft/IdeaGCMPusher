package com.gcm.pusher.log;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import it.algos.webbase.web.lib.DateConvertUtils;
import it.algos.webbase.web.module.ModulePop;
import it.algos.webbase.web.table.ModuleTable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by alex on 08/04/16.
 */
public class LogTable extends ModuleTable {

    // id della colonna generata "timestamp"
    protected static final String COL_TIMESTAMP = "logtime";


    public LogTable(ModulePop module) {
        super(module);
    }

    @Override
    protected Object[] getDisplayColumns() {
        return new Object[]{
                COL_TIMESTAMP,
                Log_.level,
                Log_.tag,
                Log_.message
        };
    }



    @Override
    protected void createAdditionalColumns() {
        addGeneratedColumn(COL_TIMESTAMP, new TimestampColumnGenerator());
    }


    /**
     * Colonna generata: time.
     */
    private class TimestampColumnGenerator implements ColumnGenerator {

        private DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy kk:mm:ss");

        /**
         * Genera la cella del timestamp.
         */
        public Component generateCell(Table source, Object itemId, Object columnId) {
            Item item = source.getItem(itemId);
            Property prop = item.getItemProperty(Log_.timestamp.getName());
            Date date = (Date)prop.getValue();
            LocalDateTime ldate = DateConvertUtils.asLocalDateTime(date);
            String text = ldate.format(formatter);
            return new Label(text);
        }
    }

}
