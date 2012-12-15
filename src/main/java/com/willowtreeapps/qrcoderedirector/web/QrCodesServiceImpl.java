package com.willowtreeapps.qrcoderedirector.web;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.Collection;


import com.willowtreeapps.qrcoderedirector.client.QrCodesService;
import com.willowtreeapps.qrcoderedirector.model.QrCode;
import com.willowtreeapps.qrcoderedirector.model.QrCodes;
import com.willowtreeapps.qrcoderedirector.server.QrCodeRepository;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class QrCodesServiceImpl extends RemoteServiceServlet implements
        QrCodesService {

    @Override
    protected void checkPermutationStrongName() throws SecurityException {
        return;
    }

    private QrCodes qrCodeRepository = new QrCodeRepository();


    @Override
    public void createOrUpdate(QrCode qrCode) {
//        try {
//            if (project.isNew()) {
//                //GroupService mGroupService = new GroupService();
//                //mGroupService.createGroup("Project " + project.getLabel(), project.getEmail());
//            }
        qrCodeRepository.createOrUpdate(qrCode);
//        } catch (AuthenticationException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (ServiceException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        } catch (IOException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
    }

    @Override
    public void deleteById(Long id) {
        qrCodeRepository.deleteById(id);
    }

    @Override
    public Collection<QrCode> getAll() {
        return qrCodeRepository.getAll();
    }

}
