@XmlSchema(elementFormDefault = XmlNsForm.QUALIFIED, namespace = EmoRGNS.EMORG_NS, xmlns = {
    @XmlNs(namespaceURI = EmoRGNS.EMORG_NS, prefix = EmoRGNS.EMORG_PREFIX),
    @XmlNs(namespaceURI = EmoRGNS.XSI_NS, prefix = EmoRGNS.XSI_PREFIX)})
package pl.kiminoboku.emorg.domain.operation;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;
import pl.kiminoboku.emorg.domain.EmoRGNS;
