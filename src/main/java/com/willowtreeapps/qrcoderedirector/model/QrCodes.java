package com.willowtreeapps.qrcoderedirector.model;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: mlake
 * Date: 12/14/12
 * Time: 7:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface QrCodes {

    public abstract Collection<QrCode> getAll();

    public abstract void createOrUpdate(QrCode qrCode);

    public abstract void deleteById(Long id);

}
