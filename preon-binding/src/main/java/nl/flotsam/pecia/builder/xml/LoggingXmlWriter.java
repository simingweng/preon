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

public class LoggingXmlWriter implements XmlWriter {

    private Logger logger = new Logger() {
        public void log(String text) {
        }
    };

    private XmlWriter writer;

    public LoggingXmlWriter(Logger logger, XmlWriter writer) {
        this.logger = logger;
        this.writer = writer;
    }

    public interface Logger {

        void log(String text);

    }

    private void log(String text) {
        logger.log(text);
    }

    public void close() throws XmlWriterException {
        log("Closing");
        writer.close();
    }

    public void flush() throws XmlWriterException {
        log("Flushing");
        writer.flush();
    }

    public NamespaceContext getNamespaceContext() {
        return writer.getNamespaceContext();
    }

    public String getPrefix(String uri) throws XmlWriterException {
        return writer.getPrefix(uri);
    }

    public Object getProperty(String name) throws IllegalArgumentException {
        return writer.getProperty(name);
    }

    public void setDefaultNamespace(String uri) throws XmlWriterException {
        writer.setDefaultNamespace(uri);
    }

    public void setNamespaceContext(NamespaceContext context)
            throws XmlWriterException {
        writer.setNamespaceContext(context);
    }

    public void setPrefix(String prefix, String uri) throws XmlWriterException {
        writer.setPrefix(prefix, uri);
    }

    public void writeAttribute(String localName, String value)
            throws XmlWriterException {
        writer.writeAttribute(localName, value);
        log("   attr " + localName + " \"" + value + "\"");
    }

    public void writeAttribute(String prefix, String namespaceURI,
            String localName, String value) throws XmlWriterException {
        writer.writeAttribute(prefix, namespaceURI, localName, value);
        log("   attr " + localName + " \"" + value + "\"");
    }

    public void writeAttribute(String namespaceURI, String localName,
            String value) throws XmlWriterException {
        writer.writeAttribute(namespaceURI, localName, value);
        log("   attr " + localName + " \"" + value + "\"");
    }

    public void writeCData(String data) throws XmlWriterException {
        writer.writeCData(data);
        log("  cdata " + data);
    }

    public void writeCharacters(String text) throws XmlWriterException {
        writer.writeCharacters(text);
        log("   text " + text);
    }

    public void writeCharacters(char[] text, int start, int len)
            throws XmlWriterException {
        writer.writeCharacters(text, start, len);
        log("   text " + new String(text, start, len));
    }

    public void writeComment(String data) throws XmlWriterException {
        writer.writeComment(data);
        log("comment " + data);
    }

    public void writeDTD(String dtd) throws XmlWriterException {
        writer.writeDTD(dtd);
    }

    public void writeDefaultNamespace(String namespaceURI)
            throws XmlWriterException {
        writer.writeDefaultNamespace(namespaceURI);
    }

    public void writeEmptyElement(String namespaceURI, String localName)
            throws XmlWriterException {
        writer.writeEmptyElement(namespaceURI, localName);
        log("element  <" + localName + "/>");
    }

    public void writeEmptyElement(String prefix, String localName,
            String namespaceURI) throws XmlWriterException {
        log("element  <" + localName + "/>");
        writer.writeEmptyElement(prefix, localName, namespaceURI);
    }

    public void writeEmptyElement(String localName) throws XmlWriterException {
        log("element  <" + localName + "/>");
        writer.writeEmptyElement(localName);
    }

    public void writeEndDocument() throws XmlWriterException {
        log("End of document");
        writer.writeEndDocument();
    }

    public void writeEndElement() throws XmlWriterException {
        log("        </>");
        writer.writeEndElement();
    }

    public void writeEntityRef(String name) throws XmlWriterException {
        writer.writeEntityRef(name);
    }

    public void writeNamespace(String prefix, String namespaceURI)
            throws XmlWriterException {
        writer.writeNamespace(prefix, namespaceURI);
    }

    public void writeProcessingInstruction(String target)
            throws XmlWriterException {
        writer.writeProcessingInstruction(target);
    }

    public void writeProcessingInstruction(String target, String data)
            throws XmlWriterException {
        writer.writeProcessingInstruction(target, data);
    }

    public void writeStartDocument() throws XmlWriterException {
        log("Start of document");
        writer.writeStartDocument();
    }

    public void writeStartDocument(String version) throws XmlWriterException {
        log("Start of document");
        writer.writeStartDocument(version);
    }

    public void writeStartDocument(String encoding, String version)
            throws XmlWriterException {
        log("Start of document");
        writer.writeStartDocument(encoding, version);
    }

    public void writeStartElement(String localName) throws XmlWriterException {
        log("element  <" + localName + ">");
        writer.writeStartElement(localName);
    }

    public void writeStartElement(String namespaceURI, String localName)
            throws XmlWriterException {
        log("element  <" + localName + ">");
        writer.writeStartElement(namespaceURI, localName);
    }

    public void writeStartElement(String prefix, String localName,
            String namespaceURI) throws XmlWriterException {
        log("element  <" + localName + ">");
        writer.writeStartElement(prefix, localName, namespaceURI);
    }

}
