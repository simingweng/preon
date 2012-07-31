/*
 * Copyright 2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.flotsam.pecia.builder.xml;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class StreamingXmlWriter implements XmlWriter {

    private XMLStreamWriter writer;

    public StreamingXmlWriter(XMLStreamWriter writer) {
        this.writer = writer;
    }

    public void close() throws XmlWriterException {
        try {
            writer.close();
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void flush() throws XmlWriterException {
        try {
            writer.flush();
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public NamespaceContext getNamespaceContext() {
        return writer.getNamespaceContext();
    }

    public String getPrefix(final String uri) throws XmlWriterException {
        try {
            return writer.getPrefix(uri);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public Object getProperty(String name) throws IllegalArgumentException {
        return writer.getProperty(name);
    }

    public void setDefaultNamespace(String uri) throws XmlWriterException {
        try {
            writer.setDefaultNamespace(uri);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void setNamespaceContext(NamespaceContext context)
            throws XmlWriterException {
        try {
            writer.setNamespaceContext(context);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void setPrefix(String prefix, String uri) throws XmlWriterException {
        try {
            writer.setPrefix(prefix, uri);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeAttribute(String localName, String value)
            throws XmlWriterException {
        try {
            writer.writeAttribute(localName, value);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeAttribute(String prefix, String namespaceURI,
            String localName, String value) throws XmlWriterException {
        try {
            writer.writeAttribute(prefix, namespaceURI, localName, value);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeAttribute(String namespaceURI, String localName,
            String value) throws XmlWriterException {
        try {
            writer.writeAttribute(namespaceURI, localName, value);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeCData(String data) throws XmlWriterException {
        try {
            writer.writeCData(data);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeCharacters(String text) throws XmlWriterException {
        try {
            writer.writeCharacters(text);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeCharacters(char[] text, int start, int len)
            throws XmlWriterException {
        try {
            writer.writeCharacters(text, start, len);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeComment(String data) throws XmlWriterException {
        try {
            writer.writeComment(data);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }

    }

    public void writeDTD(String dtd) throws XmlWriterException {
        try {
            writer.writeDTD(dtd);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeDefaultNamespace(String namespaceURI)
            throws XmlWriterException {
        try {
            writer.writeDefaultNamespace(namespaceURI);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeEmptyElement(String namespaceURI, String localName)
            throws XmlWriterException {
        try {
            writer.writeEmptyElement(namespaceURI, localName);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeEmptyElement(String prefix, String localName,
            String namespaceURI) throws XmlWriterException {
        try {
            writer.writeEmptyElement(prefix, localName, namespaceURI);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeEmptyElement(String localName) throws XmlWriterException {
        try {
            writer.writeEmptyElement(localName);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeEndDocument() throws XmlWriterException {
        try {
            writer.writeEndDocument();
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeEndElement() throws XmlWriterException {
        try {
            writer.writeEndElement();
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeEntityRef(String name) throws XmlWriterException {
        try {
            writer.writeEntityRef(name);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeNamespace(String prefix, String namespaceURI)
            throws XmlWriterException {
        try {
            writer.writeNamespace(prefix, namespaceURI);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeProcessingInstruction(String target)
            throws XmlWriterException {
        try {
            writer.writeProcessingInstruction(target);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeProcessingInstruction(String target, String data)
            throws XmlWriterException {
        try {
            writer.writeProcessingInstruction(target, data);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeStartDocument() throws XmlWriterException {
        try {
            writer.writeStartDocument();
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeStartDocument(String version) throws XmlWriterException {
        try {
            writer.writeStartDocument(version);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeStartDocument(String encoding, String version)
            throws XmlWriterException {
        try {
            writer.writeStartDocument(encoding, version);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeStartElement(String localName) throws XmlWriterException {
        try {
            writer.writeStartElement(localName);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeStartElement(String namespaceURI, String localName)
            throws XmlWriterException {
        try {
            writer.writeStartElement(namespaceURI, localName);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

    public void writeStartElement(String prefix, String localName,
            String namespaceURI) throws XmlWriterException {
        try {
            writer.writeStartElement(prefix, localName, namespaceURI);
        } catch (XMLStreamException e) {
            throw new XmlWriterException(e);
        }
    }

}
