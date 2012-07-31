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

public interface XmlWriter {

    /**
     * Write the start of a tag.
     */
    void writeStartElement(String localName) throws XmlWriterException;

    /**
     * Write the start of a tag.
     */
    void writeStartElement(String namespaceURI, String localName)
            throws XmlWriterException;

    /**
     * Write the start of a tag.
     */
    void writeStartElement(String prefix, String localName, String namespaceURI)
            throws XmlWriterException;

    /**
     * Write an empty tag.
     */
    void writeEmptyElement(String namespaceURI, String localName)
            throws XmlWriterException;

    /**
     * Write an empty tag.
     */
    void writeEmptyElement(String prefix, String localName, String namespaceURI)
            throws XmlWriterException;

    /**
     * Write an empty tag.
     */
    void writeEmptyElement(String localName) throws XmlWriterException;

    /**
     * Closes the currently open tag.
     */
    void writeEndElement() throws XmlWriterException;

    /**
     * Closes any currently open tags.
     */
    void writeEndDocument() throws XmlWriterException;

    /**
     * Frees any resources used by this writer. This will not close the
     * underlying output sink.
     */
    void close() throws XmlWriterException;

    /**
     * Flushes any cached information to the underlying output sink.
     */
    void flush() throws XmlWriterException;

    /**
     * Write an attribute.
     */
    void writeAttribute(String localName, String value)
            throws XmlWriterException;

    /**
     * Write an attribute.
     */
    void writeAttribute(String prefix, String namespaceURI, String localName,
            String value) throws XmlWriterException;

    /**
     * Write an attribute.
     */
    void writeAttribute(String namespaceURI, String localName, String value)
            throws XmlWriterException;

    /**
     * Write a namespace declaration.
     */
    void writeNamespace(String prefix, String namespaceURI)
            throws XmlWriterException;

    /**
     * Write a default namespace declaration.
     */
    void writeDefaultNamespace(String namespaceURI) throws XmlWriterException;

    /**
     * Write a comment.
     */
    void writeComment(String data) throws XmlWriterException;

    /**
     * Write a processing instruction.
     */
    void writeProcessingInstruction(String target) throws XmlWriterException;

    /**
     * Write a processing instruction.
     */
    void writeProcessingInstruction(String target, String data)
            throws XmlWriterException;

    /**
     * Write a CDATA section.
     */
    void writeCData(String data) throws XmlWriterException;

    /**
     * Write a DOCTYPE declaration.
     */
    void writeDTD(String dtd) throws XmlWriterException;

    /**
     * Write an entity reference.
     */
    void writeEntityRef(String name) throws XmlWriterException;

    /**
     * Write an XML declaration.
     */
    void writeStartDocument() throws XmlWriterException;

    /**
     * Write an XML declaration with the specified XML version.
     */
    void writeStartDocument(String version) throws XmlWriterException;

    /**
     * Write an XML declaration with the specifed XML version and encoding.
     */
    void writeStartDocument(String encoding, String version)
            throws XmlWriterException;

    /**
     * Write the specified text.
     */
    void writeCharacters(String text) throws XmlWriterException;

    /**
     * Write the specified text.
     */
    void writeCharacters(char[] text, int start, int len)
            throws XmlWriterException;

    /**
     * Returns the prefix associated with the given namespace URI.
     */
    String getPrefix(String uri) throws XmlWriterException;

    /**
     * Sets the prefix for the given namespace URI.
     */
    void setPrefix(String prefix, String uri) throws XmlWriterException;

    /**
     * Sets the URI for the default namespace.
     */
    void setDefaultNamespace(String uri) throws XmlWriterException;

    /**
     * Sets the namespace context for namespace resolution.
     */
    void setNamespaceContext(NamespaceContext context)
            throws XmlWriterException;

    /**
     * Returns the current namespace context.
     */
    NamespaceContext getNamespaceContext();

    /**
     * Returns the implementation-specific feature or property of the given
     * name.
     *
     * @exception IllegalArgumentException
     *                if the property is not supported
     */
    Object getProperty(String name) throws IllegalArgumentException;

}