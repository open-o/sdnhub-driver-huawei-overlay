
package org.openo.sdno.overlayvpndriver.test.mocoserver;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;
import org.openo.sdno.framework.container.util.JsonUtil;
import org.openo.sdno.overlayvpn.errorcode.ErrorCode;
import org.openo.sdno.overlayvpn.model.port.WanSubInterface;
import org.openo.sdno.overlayvpn.result.ResultRsp;
import org.openo.sdno.overlayvpndriver.login.OverlayVpnDriverResponse;
import org.openo.sdno.testframework.http.model.HttpRequest;
import org.openo.sdno.testframework.http.model.HttpResponse;
import org.openo.sdno.testframework.http.model.HttpRquestResponse;
import org.openo.sdno.testframework.moco.MocoHttpServer;
import org.openo.sdno.testframework.moco.responsehandler.MocoResponseHandler;

public class WanInterfaceDriverSuccessServer extends MocoHttpServer {

    public WanInterfaceDriverSuccessServer(){
        super();
    }
    @Override
    public void addRequestResponsePairs() {

        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/queryWanInterfaceSuccess.json",
                new wanInterfaceResponseHandler());
        this.addRequestResponsePair("src/integration-test/resources/overlayvpndriver/moco/ESRGetController.json",
                new MocoResponseHandler());
    }

    private class wanInterfaceResponseHandler extends MocoResponseHandler {

        @Override
        public void processRequestandResponse(HttpRquestResponse httpObject) {

            HttpRequest httpRequest = httpObject.getRequest();
            HttpResponse httpResponse = httpObject.getResponse();
            OverlayVpnDriverResponse<List<WanSubInterface>> inputInstanceList = JsonUtil.fromJson(httpResponse.getData(),
                    new TypeReference<OverlayVpnDriverResponse<List<WanSubInterface>>>() {});
            ResultRsp<OverlayVpnDriverResponse<List<WanSubInterface>>> newResult =
                    new ResultRsp<OverlayVpnDriverResponse<List<WanSubInterface>>>(ErrorCode.OVERLAYVPN_SUCCESS);
            httpResponse.setStatus(200);
            newResult.setData(inputInstanceList);
            httpResponse.setData(JsonUtil.toJson(inputInstanceList));
        }
    }
}
