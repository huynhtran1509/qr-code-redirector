package com.willowtreeapps.qrcoderedirector.server;


import com.willowtreeapps.qrcoderedirector.model.QrCode;
import com.willowtreeapps.qrcoderedirector.model.QrCodes;
import com.willowtreeapps.qrcoderedirector.web.RedirectorServlet;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QrCodeRepository implements QrCodes {


    public static PersistenceManagerFactory pmfInstance = RedirectorServlet.pmfInstance;

    public Collection<QrCode> getAll() {
        PersistenceManager pm = pmfInstance.getPersistenceManager();
        try {
            List<QrCode> QrCodes = new ArrayList<QrCode>();
            Extent<QrCode> extent = pm.getExtent(QrCode.class, false);
            for (QrCode qrCode : extent) {
                QrCodes.add(pm.detachCopy(qrCode));
            }
            extent.closeAll();

            return QrCodes;
        } finally {
            pm.close();
        }
    }

    public void createOrUpdate(QrCode qrCode) {
        PersistenceManager pm = pmfInstance.getPersistenceManager();
        try {
            pm.makePersistent(qrCode);
        } finally {
            pm.close();
        }
    }

    public QrCode getById(Long id) {
        PersistenceManager pm = pmfInstance.getPersistenceManager();
        try {
            return pm.getObjectById(QrCode.class, id);
        } finally {
            pm.close();
        }
    }

    public void deleteById(Long id) {
        PersistenceManager pm = pmfInstance.getPersistenceManager();
        try {
            pm.deletePersistent(pm.getObjectById(QrCode.class, id));
        } finally {
            pm.close();
        }
    }
}
