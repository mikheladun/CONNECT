package gov.hhs.fha.nhinc.hiem.dte;

import org.oasis_open.docs.wsn.b_2.Notify;
import org.oasis_open.docs.wsn.b_2.Subscribe;
import org.w3c.dom.Element;

/**
 *
 *
 * @author Neil Webb
 */
public class WsntSubscribeMarshaller {

    private static final String ContextPath = "org.oasis_open.docs.wsn.b_2";

    public Element marshalSubscribeRequest(Subscribe object) {
        return new MarshallerHelper().marshal(object, ContextPath);
    }

    public Subscribe unmarshalUnsubscribeRequest(Element element) {
        return (Subscribe) new MarshallerHelper().unmarshal(element, ContextPath);
    }

    public Element marshalNotifyRequest(Notify object) {
        return new MarshallerHelper().marshal(object, ContextPath);
    }

    public Notify unmarshalNotifyRequest(Element element) {
        return (Notify) new MarshallerHelper().unmarshal(element, ContextPath);
    }

}