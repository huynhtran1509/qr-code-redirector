package com.willowtreeapps.qrcoderedirector.model;

import javax.jdo.annotations.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: mlake
 * Date: 12/14/12
 * Time: 7:12 PM
 * To change this template use File | Settings | File Templates.
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class QrCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    @Persistent
    private String appName;

    @Persistent
    private String appleStoreUrl;

    @Persistent
    private String googleStoreUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppleStoreUrl() {
        return appleStoreUrl;
    }

    public void setAppleStoreUrl(String appleStoreUrl) {
        this.appleStoreUrl = appleStoreUrl;
    }

    public String getGoogleStoreUrl() {
        return googleStoreUrl;
    }

    public void setGoogleStoreUrl(String googleStoreUrl) {
        this.googleStoreUrl = googleStoreUrl;
    }

    public boolean isNew(){
        return id == null;
    }
}
