package picture.dao;

import picture.connection.ConnectionManager;
import picture.connection.ConnectionManagerFactory;

public class PictDaoFactory {
    private static ConnectionManager h2ConnectionManager
            = ConnectionManagerFactory.getH2ConnectionManager();

    public static PictDao getUserDao(){
        return new PictDaoImpl(h2ConnectionManager);
    }
}
