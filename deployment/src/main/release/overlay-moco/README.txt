Introduction
============

This guide helps to setup the moco server for AC-B Controller to learn the REST API supported by overlay-vpn driver service.

Configuration of the Mocked Overlay Controller environment
=========================================================

1. Install the moco standalone server by following the steps given at https://github.com/dreamhead/moco/blob/master/moco-doc/usage.md#shell

2. Run the moco server using following command to mock the AC-B Controller:

             java -jar moco-runner-0.11.0-standalone.jar https -p 18008 -c mockoservice-overlayvpndriver.json -https cert.jks -cert mocohttps --keystore mocohttps

    mockoservice-overlayvpndriver.json file can be found in sample-api-usage: 
    cert.jks can be downloaded from here : https://github.com/dreamhead/moco/tree/master/moco-runner/src/test/resources/certificate

    Please note down the shutdown port reported in console when moco server started. And use that port to shutdown the server as below:

         moco shutdown -p <shutdown port number>

    Now controller is up. Following is the example to access the api:

    https://<Simulated Conroller IP>:18008/controller/cloud/v2/northbound/config/[a-zA-Z0-9\\-\\_]{1,36}/device/arvpnsipsec
    Method: GET
    HEADER: Content-Type: application/json

    Response:
    Status code: 200
    Body:
        {
            "id": "ca8d7ca7-1df0-4dcf-af2e-60f18b81100e",
            "name": "ipsec connection",
            "tenantId": "7f8e77d3-c9df-4209-aa88-0877d2e80488",
            "adminStatus": "active",
            "controllerId": "31f52b35-1514-453e-a317-44f7b37dca94",
            "connectionServiceId": "b6a74453-3ce9-48fd-b6b7-e3c0504d7660",
            "neId": "7dce73f3-d013-9871-b158-0899d2e80498",
            "sourceIfId": "GigabitEthernet0/0/0.1",
            "sourceAddress": "172.24.4.230/24",
            "topoRole": "spoke",
            "peerAddress": "172.24.4.233",
            "authMode": "PSK",
            "psk": "123456",
            "ipsecConnection": [{
                "ipSecConnectionId": "123",
                "seqNumber": "2"
            }],
            "ikePolicy": {
                "id": "af44dfd7-cf91-4451-be57-cd4fdd96b5dc",
                "name": "ike policy",
                "tenantId": "7f8e77d3-c9df-4209-aa88-0877d2e80488",
                "authAlgorithm": "sha1",
                "encryptionAlgorithm": "3des",
                "pfs": "group2",
                "lifeTime": "120",
                "ikeVersion": "v1"
            },
            "ipSecPolicy": {
                "id": "1f44d897-c691-8851-be97-cc4fdd8695d1",
                "name": "ipsec policy",
                "tenantId": "7f8e77d3-c9df-4209-aa88-0877d2e80488",
                "authAlgorithm": "sha1",
                "encryptionAlgorithm": "3des",
                "pfs": "group2",
                "lifeTime": "120",
                "transformProtocol": "esp",
                "encapsulationMode": "tunnel"
            }
        }

    3. Add overlay (e,g AC-B) controller to ESR service by using below sample json (please refer http://wiki.open-o.org , for how to register AC-B controller using ESR)

        Request:

            http://192.168.4.39:80/api/extsys/v1/sdncontrollers
            Header:
                Method: Post
                Content-Type: application/json

            Body:
                {
                  "name": <controller name>,
                  "vendor": <vendor name>,
                  "version": <version>,
                  "description": <description of controller>,
                  "type": <type of controller>,
                  "createTime": <create time>,
                  "category": <category of controller>,
                  "url": <url of controller e.g. https://192.168.2.228:18008>,
                  "userName": <username of controller e.g. admin>,
                  "password": <password of controller e.g. admin>,
                  "productName": <product name of controller>,
                  "protocol": <supported protocol e.g. restconf>
                }

        Response
            Status Code: 201
            Body:
                {
                  "name": <controller name>,
                  "vendor": <vendor name>,
                  "version": <version>,
                  "description": <description of controller>,
                  "type": <type of controller>,
                  "createTime": "2016-10-20 07:06:37",
                  "sdnControllerId": <Controller ID to be used in NBI to create vxlan e.g. "aff3b20d-66f5-4188-adcf-9fb5613f98cf">,
                  "url": <url of controller>,
                  "userName": <username of controller>,
                  "password": <password of controller>,
                  "productName": <product name of controller>,
                  "protocol": <supported protocol>
                }
 
 4. For each REST API supported by overlay-vpn driver services, corresponding samples request and response details are provided under the folder 'sample-api-usage'.

Ensure that 'driver.json' file under folder 'generalconfig/driver.json' is configured with correct IP address for overlay-vpn driver. This information will be used for
driver registeration with driver manager.

Tutorial
=========

To test the REST API, either postman or curl command could be used. Before testing,
update the header "X-Driver-Parameter": "extSysID=<Conroller ID generated by ESR>" and
use the JSON body as it is.

Bringup the docker container for MSB, ESR, Driver-Manager and overlay-vpn driver service as per wiki (https://wiki.open-o.org/view/Installation_Instructions).
Bringup the mocked/simuated controller as per the previous section.

NBI to create VxLAN

http://<overlay driver IP>:8536/openoapi/sbi-vxlan/v1/vxlan/batch-create-vxlan
HEADER:
 Content-Type: application/json
 X-Driver-Parameter: extSysID=aff3b20d-66f5-4188-adcf-9fb5613f98cf(controller id from the previous rest query. This is identifier for controller in ESR.)
Body:

{
    "name": "instance1",
    "tenantId": "375c84172bd846ce957497e761a23b9c",
    "description": "vxlan instance 1",
    "adminStatus": "none",
    "controllerId": "2ace53bdd02f481ea8ae9ffd2211c845",
    "connectionServiceId": "9cb278ad-8c91-46db-a8d0-b0f2dc53a3e8",
    "neId": "c6f420cd80d1442593b59126fe03c94a",
    "externalId": "null",
    "modifyMask": "null",
    "vni": 123,
    "arpProxy": "false",
    "arpBroadcastSuppress": "false",
    "vxlanTunnelList": [{
        "name": "tunnel1",
        "tenantId": "375c84172bd846ce957497e761a23b9c",
        "description": "tunnel 1",
        "controllerId": "2ace53bdd02f481ea8ae9ffd2211c845",
        "connectionServiceId": "9cb278ad-8c91-46db-a8d0-b0f2dc53a3e8",
        "neId": "c6f420cd80d1442593b59126fe03c94a",
        "externalId": "null",
        "modifyMask": "NOMODIFY",
        "vxlanInstances": [],
        "vnis": [],
        "sourceIfId": "GigabitEthernet0/0/0.1",
        "sourceAddress": "192.168.101.1/24",
        "destAddress": "192.168.101.2/24",
        "tunnelIfId": "null",
        "peerNeId": "null"
    }],
    "vxlanInterfaceList": [{
            "name": "interface1",
            "tenantId": "375c84172bd846ce957497e761a23b9c",
            "description": "null",
            "adminStatus": "none",
            "controllerId": "2ace53bdd02f481ea8ae9ffd2211c845",
            "connectionServiceId": "9cb278ad-8c91-46db-a8d0-b0f2dc53a3e8",
            "externalId": "null",
            "modifyMask": "NOMODIFY",
            "localName": "null",
            "vxlanInstanceId": "null",
            "accessType": "dot1q",
            "portId": "80",
            "dot1qVlanBitmap": "1"
        }

    ]
}

Response:
    Status Code: 200
    {
  "errorCode": "overlayvpn.operation.success",
  "data": [
    {
      "tenantId": "375c84172bd846ce957497e761a23b9c",
      "name": "instance1",
      "description": "vxlan instance 1",
      "modifyMask": "null",
      "additionalInfo": null,
      "operStatus": "none",
      "adminStatus": "none",
      "actionState": "Normal",
      "createTime": null,
      "controllerId": "2ace53bdd02f481ea8ae9ffd2211c845",
      "externalId": "null",
      "connectionServiceId": "9cb278ad-8c91-46db-a8d0-b0f2dc53a3e8",
      "vni": "123",
      "arpProxy": "false",
      "arpBroadcastSuppress": "false",
      "neId": "c6f420cd80d1442593b59126fe03c94a",
      "vxlanInterfaceList": [
        {
          "tenantId": "375c84172bd846ce957497e761a23b9c",
          "name": "interface1",
          "description": "null",
          "modifyMask": "NOMODIFY",
          "additionalInfo": null,
          "operStatus": "none",
          "adminStatus": "none",
          "actionState": "Normal",
          "createTime": null,
          "controllerId": "2ace53bdd02f481ea8ae9ffd2211c845",
          "externalId": "null",
          "connectionServiceId": "9cb278ad-8c91-46db-a8d0-b0f2dc53a3e8",
          "localName": "null",
          "vxlanInstanceId": "null",
          "accessType": "dot1q",
          "portId": "80",
          "dot1qVlanBitmap": "1",
          "id": null
        }
      ],
      "vxlanTunnelList": [
        {
          "tenantId": "375c84172bd846ce957497e761a23b9c",
          "name": "tunnel1",
          "description": "tunnel 1",
          "modifyMask": "NOMODIFY",
          "additionalInfo": null,
          "operStatus": "none",
          "adminStatus": "none",
          "actionState": "Normal",
          "createTime": null,
          "controllerId": "2ace53bdd02f481ea8ae9ffd2211c845",
          "externalId": "null",
          "connectionServiceId": "9cb278ad-8c91-46db-a8d0-b0f2dc53a3e8",
          "vxlanInstances": [],
          "vnis": [],
          "sourceIfId": "GigabitEthernet0/0/0.1",
          "sourceAddress": "192.168.101.1/24",
          "destAddress": "192.168.101.2/24",
          "tunnelIfId": "null",
          "neId": "c6f420cd80d1442593b59126fe03c94a",
          "peerNeId": "null",
          "id": null
        }
      ],
      "id": null
    }
  ],
  "descArg": "",
  "reasonArg": "",
  "detailArg": "",
  "adviceArg": "",
  "httpCode": 200,
  "message": "",
  "smallErrorCodeList": null,
  "successed": null,
  "fail": null
}