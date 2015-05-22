package org.picketlink.identity.federation.bindings.tomcat.sp;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;


/**
 * Unified Service Provider Authenticator
 *
 * @author anil saldhana
 */
public class ServiceProviderAuthenticator extends AbstractSPFormAuthenticator {
    /*
     * (non-Javadoc)
     *
     * @see org.picketlink.identity.federation.bindings.tomcat.sp.BaseFormAuthenticator#start()
     */
    @Override
    public void start() throws LifecycleException {
        super.start();
        startPicketLink(); 
    }
    
    @Override
    protected String getContextPath() { 
        return getContext().getServletContext().getContextPath();
    }

    @Override
    public void invoke(Request arg0, Response arg1) throws IOException,
            ServletException {

        if (arg0.getHeader("x-skip-picketlink") == null) {
            logger.debug("Found x-skip-picketlink header, skipping saml authentication");
            getNext().invoke(arg0, arg1);
        } else {
            super.invoke(arg0, arg1);
        }
    }
}