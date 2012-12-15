package com.willowtreeapps.qrcoderedirector.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.willowtreeapps.qrcoderedirector.model.QrCodes;

/**
 * Created with IntelliJ IDEA.
 * User: mlake
 * Date: 12/14/12
 * Time: 7:10 PM
 * To change this template use File | Settings | File Templates.
 */

@RemoteServiceRelativePath("qrcodes")
public interface QrCodesService extends RemoteService, QrCodes {


}
