package org.openo.sdnhub.overlayvpndriver.test.mocoserver;

import org.openo.sdno.testframework.moco.MocoHttpsServer;
import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalSNatMockServerHttpsFail extends MocoHttpsServer{

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalSNatMockServerHttpsFail.class);

    public LocalSNatMockServerHttpsFail() {
        super();
    }

    @Override
    public void addRequestResponsePairs()  {

        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/localSnatCreateFail.json",
                new MocoResponseHandler());

        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/localSnatUpdateFail.json",
                new MocoResponseHandler());

        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/localSnatQueryFail.json",
                new MocoResponseHandler());

        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/localSnatDeleteFail.json",
                new MocoResponseHandler());

        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/aclCreateFail.json",
                new MocoResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/aclDeleteFail.json",
                new MocoResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/aclQueryFail.json",
                new MocoResponseHandler());
    }
}
