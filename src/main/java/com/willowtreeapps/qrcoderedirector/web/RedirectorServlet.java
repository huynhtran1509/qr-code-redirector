package com.willowtreeapps.qrcoderedirector.web;

import com.willowtreeapps.qrcoderedirector.model.QrCode;
import com.willowtreeapps.qrcoderedirector.server.QrCodeRepository;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: mlake
 * Date: 12/14/12
 * Time: 9:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class RedirectorServlet extends HttpServlet {


    public static PersistenceManagerFactory pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            QrCodeRepository repository = new QrCodeRepository();

            QrCode qrCode = repository.getById(Long.parseLong(req.getPathInfo().split("/")[1]));

            String appleUrl = qrCode.getAppleStoreUrl();

            String appleIpadUrl = qrCode.getAppleIpadStoreUrl();

            String androidUrl = qrCode.getGoogleStoreUrl();

            String ua = req.getHeader("User-Agent");

            if ((ua.contains("iPhone") || ua.contains("iPad") || ua.contains("iPod")) && appleUrl != null && !"".equals(appleUrl)) {

                if (ua.contains("iPad") && appleIpadUrl != null && !"".equals(appleIpadUrl)){
                    resp.sendRedirect(resp.encodeRedirectURL(appleIpadUrl));
                } else {
                    resp.sendRedirect(resp.encodeRedirectURL(appleUrl));
                }
            } else if (ua.contains("Android") && androidUrl != null && !"".equals(androidUrl)) {
                resp.sendRedirect(resp.encodeRedirectURL(androidUrl));
            } else {
                resp.sendRedirect("/device_not_supported.html");
            }

            //resp.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw new ServletException(e);
        }
    }


}
