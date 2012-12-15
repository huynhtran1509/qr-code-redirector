package com.willowtreeapps.qrcoderedirector.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.RootPanel;
import com.willowtreeapps.qrcoderedirector.client.ui.QrApplication;

/**
 * Created with IntelliJ IDEA.
 * User: mlake
 * Date: 12/14/12
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class IndexPage implements EntryPoint {


    @Override
    public void onModuleLoad() {
        RootPanel.getBodyElement().removeChild(
                DOM.getElementById("Loading-Message"));

        QrApplication qrApplication = new QrApplication();
        // createOrUpdate greetings message
        RootPanel.get().add(qrApplication);

    }
}
