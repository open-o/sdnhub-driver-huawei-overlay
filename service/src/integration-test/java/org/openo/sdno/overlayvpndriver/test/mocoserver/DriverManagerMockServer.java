
package org.openo.sdno.overlayvpndriver.test.mocoserver;

import org.openo.baseservice.remoteservice.exception.ServiceException;
import org.openo.sdno.testframework.moco.MocoHttpServer;
import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;

public class DriverManagerMockServer extends MocoHttpServer{
    
    public void addRequestResponsePairs() {

        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/DMregistration.json",
                new MocoResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/DMUnregistration.json",
                new MocoResponseHandler());
    }
    
    public static void main(String[] args) {
        DriverManagerMockServer dmMock = new DriverManagerMockServer();
        try {
            dmMock.start();
        } catch(ServiceException e) {
            e.printStackTrace();
        }
    }

}
