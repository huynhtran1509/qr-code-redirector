package com.willowtreeapps.qrcoderedirector.client.ui;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.willowtreeapps.qrcoderedirector.client.QrCodesServiceAsync;
import com.willowtreeapps.qrcoderedirector.model.QrCode;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mlake
 * Date: 12/14/12
 * Time: 8:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class QrCodeListBox extends VerticalPanel {

    private QrCodesServiceAsync qrCodesServiceAsync;

    public void setQrCodesService(QrCodesServiceAsync qrCodesServiceAsync) {
        this.qrCodesServiceAsync = qrCodesServiceAsync;
    }

    public void update() {
        AsyncCallback<List<QrCode>> callback = new AsyncCallback<List<QrCode>>() {

            @Override
            public void onFailure(Throwable caught) {
                Window.alert("failure: " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<QrCode> result) {
                update(result);
            }
        };

        qrCodesServiceAsync.getAll(callback);
    }

    protected void update(List<QrCode> qrCodes) {
        clear();

        for (QrCode qrCode : qrCodes) {
            QrCodeForm qrCodeForm = new QrCodeForm(qrCode);
            qrCodeForm.setQrCodesService(qrCodesServiceAsync);
            qrCodeForm.setQrCodeListBox(this);
            qrCodeForm.setStyleName("qrCodeListItem");
            add(qrCodeForm);
        }
    }
}
