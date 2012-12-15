package com.willowtreeapps.qrcoderedirector.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.willowtreeapps.qrcoderedirector.client.QrCodesServiceAsync;
import com.willowtreeapps.qrcoderedirector.model.QrCode;

/**
 * Created with IntelliJ IDEA.
 * User: mlake
 * Date: 12/14/12
 * Time: 7:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class QrCodeForm extends HorizontalPanel {

    private QrCodesServiceAsync qrCodesService;

    private final Panel qrCodePanel;

    private QrCode mQrCode;

    private final Image image;

    private final TextBox appNameInput;

    private final Button addOrUpdateButton;

    private final Button deleteButton;

    private QrCodeListBox qrCodeListBox;

    private final TextBox appleStoreUrlInput;

    private final TextBox googleStoreUrlInput;


    public void setQrCodesService(QrCodesServiceAsync qrCodesService) {
        this.qrCodesService = qrCodesService;
    }

    public void setQrCodeListBox(QrCodeListBox qrCodeListBox) {
        this.qrCodeListBox = qrCodeListBox;
    }

    public QrCodeForm(QrCode qrCode) {
        this.mQrCode = qrCode;

        setStyleName("newQrCode");

        String targetLandingUrl = GWT.getHostPageBaseURL() + "r/" + mQrCode.getId();


        String qrCodeUrl = "http://chart.apis.google.com/chart?cht=qr&chs=200x200&chl=" + targetLandingUrl;

        image = new Image(qrCodeUrl);
        add(image);

        VerticalPanel vPanel = new VerticalPanel();

        vPanel.add(new Label("App Name:"));
        appNameInput = new TextBox();
        appNameInput.setVisibleLength(50);
        vPanel.add(appNameInput);

        vPanel.add(new Label("Apple Store URL:"));
        appleStoreUrlInput = new TextBox();
        appleStoreUrlInput.setVisibleLength(100);
        vPanel.add(appleStoreUrlInput);

        vPanel.add(new Label("Google Store URL:"));
        googleStoreUrlInput = new TextBox();
        googleStoreUrlInput.setVisibleLength(100);
        vPanel.add(googleStoreUrlInput);

        qrCodePanel = new VerticalPanel();

        qrCodePanel.add(new Label("QR Code target URL:"));
        qrCodePanel.add(new Anchor(targetLandingUrl, targetLandingUrl, "_blank"));
        vPanel.add(qrCodePanel);

        add(vPanel);

        addOrUpdateButton = new Button();
        addOrUpdateButton.addClickHandler(new AddOrUpdateQrCodeHandler());

        add(addOrUpdateButton);

        deleteButton = new Button("Delete");
        deleteButton.addClickHandler(new DeleteClickHandler());
        add(deleteButton);

        populateDisplay();

    }

    private void populateDisplay() {
        appNameInput.setText(mQrCode.getAppName());

        appleStoreUrlInput.setText(mQrCode.getAppleStoreUrl());

        googleStoreUrlInput.setText(mQrCode.getGoogleStoreUrl());

        if (mQrCode.isNew()) {
            addOrUpdateButton.setText("Create");
            deleteButton.setVisible(false);
            image.setVisible(false);
            qrCodePanel.setVisible(false);
            //projectLabelInput.setEnabled(true);

        } else {
            addOrUpdateButton.setText("Update");
            deleteButton.setVisible(true);
            image.setVisible(true);
            qrCodePanel.setVisible(true);
            //projectLabelInput.setEnabled(false);
        }
    }


    protected class AddOrUpdateQrCodeHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {

            collectInput();

            AsyncCallback<Void> callback = new AsyncCallback<Void>() {

                @Override
                public void onFailure(Throwable caught) {
                    qrCodeListBox.update();
                }

                @Override
                public void onSuccess(Void result) {
                    qrCodeListBox.update();
                }
            };
            qrCodesService.createOrUpdate(mQrCode, callback);
            if (mQrCode.isNew()) {
                mQrCode = new QrCode();
            }
            QrCodeForm.this.setStyleName("updating", true);
            populateDisplay();
        }
    }

    protected class DeleteClickHandler implements ClickHandler {

        public DeleteClickHandler() {

        }

        @Override
        public void onClick(ClickEvent event) {

            if (Window.confirm("SURE you want to delete this?")) {

                AsyncCallback<Void> callback = new AsyncCallback<Void>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        qrCodeListBox.update();
                    }

                    @Override
                    public void onSuccess(Void result) {
                        qrCodeListBox.update();
                    }
                };

                qrCodesService.deleteById(mQrCode.getId(), callback);
            }
        }
    }

    private void collectInput() {

        mQrCode.setAppName(appNameInput.getText());
        mQrCode.setAppleStoreUrl(appleStoreUrlInput.getText());
        mQrCode.setGoogleStoreUrl(googleStoreUrlInput.getText());
    }

}
