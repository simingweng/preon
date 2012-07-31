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

package nl.flotsam.pecia.builder.html;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import nl.flotsam.pecia.ItemizedList;
import nl.flotsam.pecia.ListItem;
import nl.flotsam.pecia.Para;
import nl.flotsam.pecia.Section;
import nl.flotsam.pecia.Table2Cols;
import nl.flotsam.pecia.Table3Cols;
import nl.flotsam.pecia.Table4Cols;
import nl.flotsam.pecia.Table5Cols;
import nl.flotsam.pecia.Verbatim;
import nl.flotsam.pecia.builder.ArticleBuilder;
import nl.flotsam.pecia.builder.DocumentBuilder;
import nl.flotsam.pecia.builder.EntryBuilder;
import nl.flotsam.pecia.builder.FootnoteBuilder;
import nl.flotsam.pecia.builder.LastEntryBuilder;
import nl.flotsam.pecia.builder.ParaBuilder;
import nl.flotsam.pecia.builder.RowBuilder;
import nl.flotsam.pecia.builder.SectionBuilder;
import nl.flotsam.pecia.builder.base.DefaultDocumentBuilder;
import nl.flotsam.pecia.builder.base.DefaultEntryBuilder;
import nl.flotsam.pecia.builder.base.DefaultLastEntryBuilder;
import nl.flotsam.pecia.builder.base.DefaultParaBuilder;
import nl.flotsam.pecia.builder.base.DefaultRowBuilder;
import nl.flotsam.pecia.builder.base.LifecycleListener;
import nl.flotsam.pecia.builder.xml.DefaultTagWriterCatalog;
import nl.flotsam.pecia.builder.xml.XmlDocumentBuilder;
import nl.flotsam.pecia.builder.xml.XmlWriter;

import org.apache.commons.io.IOUtils;


public class HtmlDocumentBuilder extends XmlDocumentBuilder {

    private static ObjectIdentifier IDENTIFIER = new SimpleObjectIdentifier();

    public static DefaultTagWriterCatalog createCatalog(URL stylesheet) {
        DefaultTagWriterCatalog catalog = new DefaultTagWriterCatalog();
        catalog.register(new OneToOneTagWriter("p", Para.class));
        catalog.register(new OneToOneTagWriter("ul", ItemizedList.class));
        catalog.register(new OneToOneTagWriter("li", ListItem.class));
        catalog.register(new OneToOneTagWriter("pre", Verbatim.class));
        catalog.register(new ManyToOneTagWriter("table", new Class[] {
                Table2Cols.class, Table3Cols.class, Table4Cols.class,
                Table5Cols.class }));
        catalog.register(new SectionTagWriter(false));
        catalog.register(new ArticleTagWriter(stylesheet));
        return catalog;
    }

    public HtmlDocumentBuilder(XmlWriter writer) {
        super(writer, createCatalog(null));
    }

    public HtmlDocumentBuilder(XmlWriter writer, URL stylesheet) {
        super(writer, createCatalog(stylesheet));
    }

    @Override
    public <T> EntryBuilder<T> createEntry(T parent, boolean header,
            DocumentBuilder builder) {
        return new HtmlColBuilder<T>(builder, parent, header,
                getLifecycleListener());
    }

    @Override
    public <T> LastEntryBuilder<T> createLastEntry(T parent, boolean header,
            DocumentBuilder builder) {
        return new HtmlLastColBuilder<T>(builder, parent, header,
                getLifecycleListener());
    }

    @Override
    public <T> RowBuilder<T> createRow(T parent, boolean header, boolean first,
            DocumentBuilder builder) {
        return new HtmlRowBuilder<T>(builder, parent, header, first,
                getLifecycleListener());
    }

    @Override
    public void end() {
        getWriter().writeEndElement();
        getWriter().writeEndDocument();
    }

    @Override
    public void start() {
        getWriter().writeStartDocument();
        getWriter().writeStartElement("html");
        getWriter().writeDefaultNamespace("http://www.w3.org/1999/xhtml");
    }

    @Override
    public <T> ParaBuilder<T> createPara(T parent, DocumentBuilder builder) {
        return new HtmlParaBuilder<T>(builder, parent, getLifecycleListener(), getWriter());
    }

    @Override
    public <T> FootnoteBuilder<T> createFootnote(T parent,
            DocumentBuilder builder) {
        // Make sure footnotes are ignored for now.
        return super.createFootnote(parent, new DefaultDocumentBuilder());
    }

    public class HtmlRowBuilder<T> extends DefaultRowBuilder<T> {

        public HtmlRowBuilder(DocumentBuilder builder, T parent,
                boolean header, boolean first, LifecycleListener listener) {
            super(builder, parent, header, first, listener);
        }

        @Override
        public T entry() {
            getWriter().writeStartElement("tr");
            if (isHeader()) {
                getWriter().writeStartElement("th");
            } else {
                getWriter().writeStartElement("td");
            }
            return super.entry();
        }

    }

    public class HtmlColBuilder<T> extends DefaultEntryBuilder<T> {

        public HtmlColBuilder(DocumentBuilder builder, T parent,
                boolean header, LifecycleListener listener) {
            super(builder, parent, header, listener);
        }

        @Override
        public T entry() {
            getWriter().writeEndElement();
            if (isHeader()) {
                getWriter().writeStartElement("th");
            } else {
                getWriter().writeStartElement("td");
            }
            return super.entry();
        }

    }

    public class HtmlLastColBuilder<T> extends DefaultLastEntryBuilder<T> {

        public HtmlLastColBuilder(DocumentBuilder builder, T parent,
                boolean header, LifecycleListener listener) {
            super(builder, parent, header, listener);
        }

        @Override
        public T end() {
            getWriter().writeEndElement();
            getWriter().writeEndElement();
            return super.end();
        }

    }

    public static class HtmlParaBuilder<T> extends DefaultParaBuilder<T> {

        private XmlWriter xmlWriter;
        
        public HtmlParaBuilder(DocumentBuilder builder, T parent,
                LifecycleListener listener, XmlWriter xmlWriter) {
            super(builder, parent, listener);
            this.xmlWriter = xmlWriter;
        }

        private XmlWriter getWriter() {
            return xmlWriter;
        }
        
        @Override
        public Para<T> code(String text) {
            writeInline("code", text, xmlWriter);
            return HtmlParaBuilder.this;
        }

        @Override
        public Para<T> link(Object id, String text) {
            getWriter().writeStartElement("a");
            getWriter().writeAttribute("href", "#" + IDENTIFIER.create(id));
            getWriter().writeCharacters(text);
            getWriter().writeEndElement();
            return HtmlParaBuilder.this;
        }

        @Override
        public Para<T> email(String email) {
            getWriter().writeStartElement("a");
            getWriter().writeAttribute("href", "mailto:" + email);
            getWriter().writeCharacters(email);
            getWriter().writeEndElement();
            return HtmlParaBuilder.this;
        }

        @Override
        public Para<T> emphasis(String text) {
            writeInline("em", text, xmlWriter);
            return HtmlParaBuilder.this;
        }

        @Override
        public Para<T> text(String text) {
            getWriter().writeCharacters(text);
            return HtmlParaBuilder.this;
        }

        @Override
        public Para<T> xref(String id) {
            getWriter().writeStartElement("a");
            getWriter().writeAttribute("href", "#" + IDENTIFIER.create(id));
            getWriter().writeCharacters("[click]");
            getWriter().writeEndElement();
            return this;
        }

        @Override
        public Para<T> term(Object id, String text) {
            getWriter().writeEmptyElement("a");
            getWriter().writeAttribute("name", IDENTIFIER.create(id));
            getWriter().writeCharacters(text);
            return HtmlParaBuilder.this;
        }

    };

    private static class SectionTagWriter implements TagWriter {

        public boolean comments;

        public SectionTagWriter(boolean comments) {
            this.comments = comments;
        }

        public void end(Object documentElement, XmlWriter writer) {
            if (comments) {
                SectionBuilder builder = (SectionBuilder) documentElement;
                writer.writeComment("End of section \"" + builder.getTitle()
                        + "\"");
            }
        }

        public void start(Object documentElement, XmlWriter writer) {
            SectionBuilder builder = (SectionBuilder) documentElement;
            if (builder.getId() != null) {
                writer.writeEmptyElement("a");
                writer.writeAttribute("name", IDENTIFIER
                        .create(builder.getId()));
            }
            writer.writeStartElement("h2");
            writer.writeCharacters(builder.getTitle());
            writer.writeEndElement();
        }

        public boolean supports(Object documentElement) {
            return documentElement instanceof Section;
        }

    }

    public static class ArticleTagWriter implements TagWriter {

        private URL stylesheet;

        public ArticleTagWriter(URL stylesheet) {
            this.stylesheet = stylesheet;
        }

        public void end(Object documentElement, XmlWriter writer) {
            writer.writeEndElement();
            writer.writeEndElement();
        }

        public void start(Object documentElement, XmlWriter writer) {
            ArticleBuilder builder = (ArticleBuilder) documentElement;
            writer.writeStartElement("head");
            writer.writeStartElement("style");
            writer.writeAttribute("type", "text/css");
            writer
                    .writeCharacters("\nbody { font-family: Arial, sans; font-size: 10pt; }");
            writer
                    .writeCharacters("\ntd { font-family: Arial, sans; font-size: 10pt; }");
            if (stylesheet != null) {
                InputStream in = null;
                try {
                    in = stylesheet.openStream();
                    List<String> lines = IOUtils.readLines(in);
                    for (String line : lines) {
                        writer.writeCharacters(line);
                        writer.writeCharacters("\n");
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                    // TODO: Fix this, but let's ignore this for now.
                } finally {
                    IOUtils.closeQuietly(in);
                }
            }
            writer.writeEndElement();
            writer.writeStartElement("title");
            writer.writeCharacters(builder.getTitle());
            writer.writeEndElement();
            if (builder.getAuthorFirstname() != null
                    || builder.getAuthorSurname() != null) {
                writer.writeEmptyElement("meta");
                StringBuilder author = new StringBuilder();
                String firstname = builder.getAuthorFirstname();
                String surname = builder.getAuthorSurname();
                if (firstname != null)
                    author.append(firstname);
                if (firstname != null && surname != null)
                    author.append(' ');
                if (surname != null)
                    author.append(surname);
                writer.writeAttribute("name", "author");
                writer.writeAttribute("content", author.toString());
            }
            writer.writeEndElement();
            writer.writeStartElement("body");
            writer.writeStartElement("div");
            writer.writeStartElement("h1");
            writer.writeCharacters(((ArticleBuilder) documentElement)
                    .getTitle());
            writer.writeEndElement();
        }

        public boolean supports(Object documentElement) {
            return documentElement instanceof ArticleBuilder;
        }

    }

}
