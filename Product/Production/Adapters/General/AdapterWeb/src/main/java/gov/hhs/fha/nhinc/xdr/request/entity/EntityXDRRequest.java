package gov.hhs.fha.nhinc.xdr.request.entity;

import javax.jws.WebService;
import javax.xml.ws.BindingType;

/**
 *
 * @author Neil Webb
 */
@WebService(serviceName = "EntityXDRRequest_Service", portName = "EntityXDRRequest_Port", endpointInterface = "gov.hhs.fha.nhinc.nhincentityxdr.async.request.EntityXDRRequestPortType", targetNamespace = "urn:gov:hhs:fha:nhinc:nhincentityxdr:async:request", wsdlLocation = "WEB-INF/wsdl/EntityXDRRequest/EntityXDRRequest.wsdl")
@BindingType(value = javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public class EntityXDRRequest
{

    public ihe.iti.xdr._2007.AcknowledgementType provideAndRegisterDocumentSetBRequest(gov.hhs.fha.nhinc.common.nhinccommonentity.RespondingGatewayProvideAndRegisterDocumentSetRequestType provideAndRegisterRequestRequest)
    {
        return new EntityXDRRequestImpl().provideAndRegisterDocumentSetBRequest(provideAndRegisterRequestRequest);
    }

}
