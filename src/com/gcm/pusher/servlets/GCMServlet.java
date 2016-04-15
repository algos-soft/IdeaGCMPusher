package com.gcm.pusher.servlets;

import com.gcm.pusher.GCMUI;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import it.algos.webbase.web.servlet.AlgosServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Theme("valo")
@WebServlet(urlPatterns = { "/gcm/*" , "/VAADIN/*" }, asyncSupported = true, displayName = "Gcm")
@VaadinServletConfiguration(productionMode = false, ui = GCMUI.class)
public class GCMServlet extends AlgosServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
    }

    /**
     * Invoked when a new Vaadin service session is initialized for that service.
     * <p>
     * Because of the way different service instances share the same session, the listener is not necessarily notified immediately
     * when the session is created but only when the first request for that session is handled by a specific service.
     *
     * @param event the initialization event
     *
     * @throws ServiceException a problem occurs when processing the event
     */
    @Override
    public void sessionInit(SessionInitEvent event) throws ServiceException {
        super.sessionInit(event);
    }// end of method

}// end of servlet class
