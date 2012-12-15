package com.willowtreeapps.qrcoderedirector.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.willowtreeapps.qrcoderedirector.client.QrCodesService;
import com.willowtreeapps.qrcoderedirector.client.QrCodesServiceAsync;
import com.willowtreeapps.qrcoderedirector.model.QrCode;

/**
 * Created with IntelliJ IDEA.
 * User: mlake
 * Date: 12/14/12
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class QrApplication extends VerticalPanel {

    private final QrCodeListBox qrCodeListBox;

    private final QrCodeForm qrCodeForm;

    private final QrCodesServiceAsync qrCodesServiceAsync;

    public QrApplication() {

        this.qrCodesServiceAsync = GWT.create(QrCodesService.class);

        qrCodeListBox = new QrCodeListBox();
        qrCodeListBox.setQrCodesService(qrCodesServiceAsync);
        qrCodeListBox.update();

        add(qrCodeListBox);

        qrCodeForm = new QrCodeForm(new QrCode());

        qrCodeForm.setQrCodesService(qrCodesServiceAsync);
        qrCodeForm.setQrCodeListBox(qrCodeListBox);

        add(qrCodeForm);


    }
}
