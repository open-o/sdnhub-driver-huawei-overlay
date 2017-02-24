package org.openo.sdnhub.overlayvpndriver.test.mocoserver;

import org.openo.sdno.testframework.moco.MocoHttpsServer;
import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VlanMockServerHttpsFail extends MocoHttpsServer{

    private static final Logger LOGGER = LoggerFactory.getLogger(VlanMockServerHttpsFail.class);

    public VlanMockServerHttpsFail() {
        super();
    }

    @Override
    public void addRequestResponsePairs()  {

        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/vlanCreateFail.json",
                new MocoResponseHandler());

        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/vlanUpdateFail.json",
                new MocoResponseHandler());

        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/vlanQueryFail.json",
                new MocoResponseHandler());
    }
}
