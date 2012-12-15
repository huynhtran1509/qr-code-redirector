package com.willowtreeapps.qrcoderedirector.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.willowtreeapps.qrcoderedirector.model.QrCode;
import com.willowtreeapps.qrcoderedirector.model.QrCodes;

import java.util.List;

public interface QrCodesServiceAsync {

    void getAll(AsyncCallback<List<QrCode>> callback);

    void createOrUpdate(QrCode qrCode, AsyncCallback<Void> callback);

    void deleteById(Long id, AsyncCallback<Void> callback);

}
