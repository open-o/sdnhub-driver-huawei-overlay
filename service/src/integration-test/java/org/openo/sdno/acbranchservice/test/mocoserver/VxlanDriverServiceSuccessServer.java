package org.openo.sdno.acbranchservice.test.mocoserver;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.openo.sdno.acbranchservice.login.AcBranchResponse;
import org.openo.sdno.acbranchservice.model.vxlan.adapter.NetVxLanDeviceModel;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.model.port.WanSubInterface;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.testframework.http.model.HttpRequest;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.moco.MocoHttpServer;
import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VxlanDriverServiceSuccessServer extends MocoHttpServer{
	private static final Logger LOGGER = LoggerFactory.getLogger(VxlanDriverServiceSuccessServer.class);

    public VxlanDriverServiceSuccessServer() {
        super();
    }

    public VxlanDriverServiceSuccessServer(String configFile) {
        super(configFile);
    }
    
    @Override
    public void addRequestResponsePairs() {

        this.addRequestResponsePair("src/integration-test/resources/ACBranchDriver/moco/vxlanCreateSuccess.json",
                new VxLanSuccessResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/ACBranchDriver/moco/vxlanDeletesuccess.json",
                new VxLanSuccessResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/ACBranchDriver/moco/queryVtepSuccess.json",
                new VxLanQuerySuccessResponseHandler());
    }
    
    private class VxLanSuccessResponseHandler extends MocoResponseHandler {

        @Override
        public void processRequestandResponse(HttpRquestResponse httpObject) {

            HttpRequest httpRequest = httpObject.getRequest();
            HttpResponse httpResponse = httpObject.getResponse();
            AcBranchResponse<List<NetVxLanDeviceModel>> inputInstanceList = JsonUtil.fromJson(httpResponse.getData(), new TypeReference<AcBranchResponse<List<NetVxLanDeviceModel>>>() {});

            ResultRsp<AcBranchResponse<List<NetVxLanDeviceModel>>> newResult = new ResultRsp<AcBranchResponse<List<NetVxLanDeviceModel>>>(ErrorCode.OVERLAYVPN_SUCCESS);

            httpResponse.setStatus(200);
            newResult.setData(inputInstanceList);
            httpResponse.setData(JsonUtil.toJson(inputInstanceList));
        }
    }
    
    private class VxLanQuerySuccessResponseHandler extends MocoResponseHandler {

        @Override
        public void processRequestandResponse(HttpRquestResponse httpObject) {

            HttpRequest httpRequest = httpObject.getRequest();
            HttpResponse httpResponse = httpObject.getResponse();
            AcBranchResponse<List<WanSubInterface>> inputInstanceList = JsonUtil.fromJson(httpResponse.getData(), new TypeReference<AcBranchResponse<List<WanSubInterface>>>() {});

            ResultRsp<AcBranchResponse<List<WanSubInterface>>> newResult = new ResultRsp<AcBranchResponse<List<WanSubInterface>>>(ErrorCode.OVERLAYVPN_SUCCESS);

            httpResponse.setStatus(200);
            newResult.setData(inputInstanceList);
            httpResponse.setData(JsonUtil.toJson(inputInstanceList));
        }
    }

}