package com.gcm.pusher.servlets;

import com.gcm.pusher.device.Device;
import com.gcm.pusher.device.Device_;
import com.gcm.pusher.log.Log;
import it.algos.webbase.web.entity.BaseEntity;
import it.algos.webbase.web.query.AQuery;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet che riceve le richieste di registrazione dai devices
 */
@WebServlet(urlPatterns = {RegistrationServlet.GCM_REG}, asyncSupported = true)
public class RegistrationServlet extends HttpServlet {

    private static List<RegistrationListener> regListeners = new ArrayList<>();

    public static final String GCM_REG = "/gcm-reg";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); //"R=register, U=unregister"
        String name = req.getParameter("name");
        String model = req.getParameter("model");
        String android_id = req.getParameter("android_id");
        String gcm_token = req.getParameter("gcm_token");

        if (action == null || action.isEmpty()) {
            Log.e("REG", "missing parameter: action");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (!(action.equalsIgnoreCase("R") || action.equalsIgnoreCase("U"))) {
            Log.e("REG", "wrong parameter: action=" + action);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (android_id == null || android_id.isEmpty()) {
            Log.e("REG", "missing parameter: android_id");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (action.equalsIgnoreCase("R")) {
            if (gcm_token == null || gcm_token.isEmpty()) {
                Log.e("REG", "missing parameter: gcm_token");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

        }

        if (action.equalsIgnoreCase("R")) {
            register(android_id, gcm_token, name, model);
        } else {
            unregister(android_id);
        }

        resp.setStatus(HttpServletResponse.SC_OK);

    }


    /**
     * Register new device or update existing device
     */
    private void register(String android_id, String gcm_token, String name, String model) {
        BaseEntity entity = AQuery.queryOne(Device.class, Device_.deviceId, android_id);
        Device device;
        boolean created = false;
        if (entity != null) {
            device = (Device) entity;
            device.setToken(gcm_token);
            if (name != null && !name.isEmpty()) {
                device.setName(name);
            }
            if (model != null && !model.isEmpty()) {
                device.setModel(model);
            }
            Log.d("REG", "device updated: " + android_id);
        } else {
            device = new Device();
            device.setDeviceId(android_id);
            device.setToken(gcm_token);
            device.setName(name);
            device.setModel(model);
            Log.d("REG", "device registered: " + android_id);
            created = true;
        }
        device.save();

        if (created) {
            fireListeners(device, "C");
        } else {
            fireListeners(device, "U");
        }

    }

    /**
     * Unregister (delete) existing device
     */
    private void unregister(String android_id) {
        BaseEntity entity = AQuery.queryOne(Device.class, Device_.deviceId, android_id);
        if (entity != null) {
            Device device = (Device) entity;
            AQuery.delete(Device.class, Device_.deviceId, android_id);
            fireListeners(device, "D");
            Log.d("REG", "device unregistered: " + android_id);
        } else {
            Log.e("REG", "unregistering device: android_id not found: id=" + android_id);
        }
    }


    public static void addRegistrationListener(RegistrationListener l) {
        regListeners.add(l);
    }

    public static void removeRegistrationListener(RegistrationListener l) {
        regListeners.remove(l);
    }


    public static void removeAllRegistrationListeners() {
        regListeners.clear();
    }

    public interface RegistrationListener {
        /**
         * E' stata servita una richiesta di registrazione.
         *
         * @param device  il device
         * @param regType il tipo di richiesta ('C'=create, 'U'=update, 'D'=delete)
         */
        void requestServed(Device device, String regType);

    }

    private void fireListeners(Device device, String regType) {
        for (RegistrationListener l : regListeners) {
            l.requestServed(device, regType);
        }
    }




}
