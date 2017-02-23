package org.openo.sdnhub.overlayvpndriver.test.mocoserver;

	import org.openo.sdno.testframework.moco.MocoHttpsServer;
	import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;

	public class SubnetBDIfMockServerHttps extends MocoHttpsServer{

		private static final Logger LOGGER = LoggerFactory.getLogger(SubnetBDIfMockServerHttps.class);

	    public SubnetBDIfMockServerHttps() {
	        super();
	    }
		@Override
	    public void addRequestResponsePairs()  {

	        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/subnetBDIfQuerySuccess.json",
	                new MocoResponseHandler());
	    }

	}


