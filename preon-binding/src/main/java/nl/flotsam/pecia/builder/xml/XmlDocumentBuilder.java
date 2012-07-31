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

import nl.flotsam.pecia.builder.base.DefaultDocumentBuilder;
import nl.flotsam.pecia.builder.base.LifecycleListener;


public class XmlDocumentBuilder extends DefaultDocumentBuilder {

    private XmlWriter writer;

    public XmlDocumentBuilder(XmlWriter writer, TagWriterCatalog catalog) {
        super(new XmlLifecycleListener(writer, catalog));
        this.writer = writer;
    }

    protected final XmlWriter getWriter() {
        return writer;
    }

    public final void writeInline(String localName, String text) throws XmlWriterException {
        getWriter().writeStartElement(localName);
        getWriter().writeCharacters(text);
        getWriter().writeEndElement();
    }

    public final static void writeInline(String localName, String text, XmlWriter xmlWriter) throws XmlWriterException {
        xmlWriter.writeStartElement(localName);
        xmlWriter.writeCharacters(text);
        xmlWriter.writeEndElement();
    }

    private static class XmlLifecycleListener implements LifecycleListener {

        private TagWriterCatalog catalog;

        private XmlWriter writer;

        public XmlLifecycleListener(XmlWriter writer, TagWriterCatalog catalog) {
            this.writer = writer;
            this.catalog = catalog;
        }

        public void ended(Object documentElement) {
            TagWriter tagWriter = catalog.getTagWriter(documentElement);
            if (tagWriter != null) {
                tagWriter.end(documentElement, writer);
            }
        }

        public void started(Object documentElement) {
            TagWriter tagWriter = catalog.getTagWriter(documentElement);
            if (tagWriter != null) {
                tagWriter.start(documentElement, writer);
            }
        }

    }

    public interface TagWriterCatalog {

        TagWriter getTagWriter(Object documentElement);

    }

    public interface TagWriter {

        boolean supports(Object documentElement);

        void start(Object documentElement, XmlWriter writer);

        void end(Object documentElement, XmlWriter writer);

    }

    public static abstract class AbstractSingleTagWriter implements TagWriter {

        private String tag;

        public AbstractSingleTagWriter(String tag) {
            this.tag = tag;
        }

        public void end(Object documentElement, XmlWriter writer) {
            writer.writeEndElement();
            writer.flush();
        }

        public void start(Object documentElement, XmlWriter writer) {
            writer.writeStartElement(tag);
            writer.flush();
        }

    }

    public static class OneToOneTagWriter extends AbstractSingleTagWriter {

        private Class cl;

        public OneToOneTagWriter(String tag, Class cl) {
            super(tag);
            this.cl = cl;
        }

        @SuppressWarnings("unchecked")
        public boolean supports(Object documentElement) {
            return cl.isAssignableFrom(documentElement.getClass());
        }

    }

    public static class ManyToOneTagWriter extends AbstractSingleTagWriter {

        private Class[] classes;

        public ManyToOneTagWriter(String tag, Class[] classes) {
            super(tag);
            this.classes = classes;
        }

        @SuppressWarnings("unchecked")
        public boolean supports(Object documentElement) {
            for (Class cl : classes) {
                if (cl.isAssignableFrom(documentElement.getClass())) {
                    return true;
                }
            }
            return false;
        }

    }

}
