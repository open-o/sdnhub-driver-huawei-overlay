package org.openo.sdnhub.overlayvpndriver.test.mocoserver;

    import org.openo.sdno.testframework.moco.MocoHttpsServer;
    import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    public class LocalSNatMockServerHttps extends MocoHttpsServer{

        private static final Logger LOGGER = LoggerFactory.getLogger(LocalSNatMockServerHttps.class);

        public LocalSNatMockServerHttps() {
            super();
        }
        @Override
        public void addRequestResponsePairs()  {

            this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/localSnatCreateSuccess.json",
                    new MocoResponseHandler());
            this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/localSnatDeleteSuccess.json",
                    new MocoResponseHandler());
            this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/localSnatUpdateSuccess.json",
                    new MocoResponseHandler());
            this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/localSnatQuerySuccess.json",
                    new MocoResponseHandler());
            this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/aclCreate.json",
                    new MocoResponseHandler());
            this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/aclDelete.json",
                    new MocoResponseHandler());
            this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/aclQuery.json",
                    new MocoResponseHandler());
        }
    }
