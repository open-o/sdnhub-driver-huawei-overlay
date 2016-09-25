
package org.openo.sdno.overlayvpndriver.test.mocoserver;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpndriver.login.OverlayVpnDriverResponse;
import org.openo.sdno.overlayvpndriver.model.ipsec.adapter.NetIpSecModel;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.moco.MocoHttpsServer;
import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;

public class IpSecDriverHttpsServiceServer extends MocoHttpsServer {

    public IpSecDriverHttpsServiceServer() {
        super();
    }

    @Override
    public void addRequestResponsePairs() {

        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/ipsecCreateSuccess.json",
                new IpSecSuccessResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/ipsecCreatePutSuccess.json",
                new IpSecSuccessResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/ESRGetController.json",
                new MocoResponseHandler());
    }

    private class IpSecSuccessResponseHandler extends MocoResponseHandler {

        @Override
        public void processRequestandResponse(HttpRquestResponse httpObject) {

            // HttpRequest httpRequest = httpObject.getRequest();
            HttpResponse httpResponse = httpObject.getResponse();

            OverlayVpnDriverResponse<List<NetIpSecModel>> inputInstanceList = JsonUtil.fromJson(httpResponse.getData(),
                    new TypeReference<OverlayVpnDriverResponse<List<NetIpSecModel>>>() {});
            ResultRsp<OverlayVpnDriverResponse<List<NetIpSecModel>>> newResult =
                    new ResultRsp<OverlayVpnDriverResponse<List<NetIpSecModel>>>(ErrorCode.OVERLAYVPN_SUCCESS);
            httpResponse.setStatus(200);
            newResult.setData(inputInstanceList);
            httpResponse.setData(JsonUtil.toJson(inputInstanceList));
        }
    }
}
