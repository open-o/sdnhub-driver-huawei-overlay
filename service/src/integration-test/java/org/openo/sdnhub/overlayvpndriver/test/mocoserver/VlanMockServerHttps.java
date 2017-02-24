package org.openo.sdnhub.overlayvpndriver.test.mocoserver;

    import org.openo.sdno.testframework.moco.MocoHttpsServer;
    import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    public class VlanMockServerHttps extends MocoHttpsServer{

        private static final Logger LOGGER = LoggerFactory.getLogger(VlanMockServerHttps.class);

        public VlanMockServerHttps() {
            super();
        }
        @Override
        public void addRequestResponsePairs()  {

            this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/vlanCreateSuccess.json",
                    new MocoResponseHandler());
            this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/vlanUpdateSuccess.json",
                    new MocoResponseHandler());
            this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/vlanQuerySuccess.json",
                    new MocoResponseHandler());
        }
    }
