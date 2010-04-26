package gov.hhs.fha.nhinc.docquery.proxy;

import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import gov.hhs.fha.nhinc.common.nhinccommon.AssertionType;
import gov.hhs.fha.nhinc.connectmgr.ConnectionManagerCache;
import gov.hhs.fha.nhinc.nhinclib.NhincConstants;
import gov.hhs.fha.nhinc.nhincproxydocquerysecured.NhincProxyDocQuerySecuredPortType;
import gov.hhs.fha.nhinc.nhincproxydocquerysecured.NhincProxyDocQuerySecured;
import gov.hhs.fha.nhinc.saml.extraction.SamlTokenCreator;
import java.util.Map;
import javax.xml.ws.BindingProvider;

/**
 *
 *
 * @author Neil Webb
 */
public class NhincProxyDocQueryImpl
{
    private static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(NhincProxyDocQueryImpl.class);
    private static NhincProxyDocQuerySecured service = new NhincProxyDocQuerySecured();

    public AdhocQueryResponse respondingGatewayCrossGatewayQuery(gov.hhs.fha.nhinc.common.nhinccommonproxy.RespondingGatewayCrossGatewayQueryRequestType respondingGatewayCrossGatewayQueryRequest)
    {
        AdhocQueryResponse response = null;

        try
        { 
            String url = ConnectionManagerCache.getLocalEndpointURLByServiceName(NhincConstants.NHINC_PROXY_DOC_QUERY_SECURED_SERVICE_NAME);

            NhincProxyDocQuerySecuredPortType port = getPort(url);

            AssertionType assertIn = respondingGatewayCrossGatewayQueryRequest.getAssertion();
            SamlTokenCreator tokenCreator = new SamlTokenCreator();
            Map requestContext = tokenCreator.CreateRequestContext(assertIn, url, NhincConstants.DOC_QUERY_ACTION);
            ((BindingProvider) port).getRequestContext().putAll(requestContext);
            
            gov.hhs.fha.nhinc.common.nhinccommonproxy.RespondingGatewayCrossGatewayQuerySecuredRequestType body = new gov.hhs.fha.nhinc.common.nhinccommonproxy.RespondingGatewayCrossGatewayQuerySecuredRequestType();
            body.setAdhocQueryRequest(respondingGatewayCrossGatewayQueryRequest.getAdhocQueryRequest());
            body.setNhinTargetSystem(respondingGatewayCrossGatewayQueryRequest.getNhinTargetSystem());

            response = port.respondingGatewayCrossGatewayQuery(body);
        }
        catch (Exception ex)
        {
            log.error("Failed to send entity doc query from proxy EJB to secure interface: " + ex.getMessage(), ex);
            response = new AdhocQueryResponse();
            // TODO: Populate registry error list?
        }

        return response;
    }
    
    private NhincProxyDocQuerySecuredPortType getPort(String url) {
        NhincProxyDocQuerySecuredPortType port = service.getNhincProxyDocQuerySecuredPortSoap();
        log.info("Setting endpoint address to NHIN Proxy Document Query Secured Service to " + url);
        //service.addPort((QName) service.getNhincProxyDocQuerySecuredPortSoap12(), javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING, url);
        ((BindingProvider) port).getRequestContext().put(javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
        //javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING
        return port;
    }

}
