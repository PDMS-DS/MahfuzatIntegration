package com.dataserve.mahfuzatintegration.util;

import javax.security.auth.Subject;

import com.dataserve.mahfuzatintegration.exception.ConnectionException;
import com.filenet.api.core.Connection;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.util.UserContext;

public class FileNetConnection implements AutoCloseable {


    public ObjectStore connect(String userName, String password) {
        try {
            Connection conn = Factory.Connection.getConnection(ConfigManager.getFileNetURI());
            Subject sub = UserContext.createSubject(conn, userName, password, ConfigManager.getFileNetStanza());
            UserContext uc = UserContext.get();
            uc.pushSubject(sub);
            Domain domain = Factory.Domain.fetchInstance(conn, null, null);
            ObjectStore os = Factory.ObjectStore.fetchInstance(domain, ConfigManager.getFileNetObjectStoreName(), null);
            return os;
        } catch (Exception e) {
            throw new ConnectionException("Failed to access Object Store", e);
        }
    }

    @Override
    public void close() {
        try {
            UserContext.get().popSubject();
        } catch (Exception e) {
            LogUtil.warn("Conntection to FileNet was not closed successfully", e);
        }
    }


}
