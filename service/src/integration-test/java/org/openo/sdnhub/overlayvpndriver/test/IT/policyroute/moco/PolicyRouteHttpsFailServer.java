package org.openo.sdnhub.overlayvpndriver.test.IT.policyroute.moco;

import org.openo.sdno.testframework.moco.MocoHttpsServer;
import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;

public class PolicyRouteHttpsFailServer  extends MocoHttpsServer{
	public PolicyRouteHttpsFailServer() {
        super();
    }
    
    @Override
    public void addRequestResponsePairs() {

        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/policyroute/moco/createfail.json",
                new MocoResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/policyroute/moco/queryfail.json",
                new MocoResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/policyroute/moco/deletefail.json",
                new MocoResponseHandler());
    }
}
