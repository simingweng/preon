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

package nl.flotsam.pecia.builder.docbook;

import nl.flotsam.pecia.Entry;
import nl.flotsam.pecia.Footnote;
import nl.flotsam.pecia.ItemizedList;
import nl.flotsam.pecia.LastEntry;
import nl.flotsam.pecia.ListItem;
import nl.flotsam.pecia.Para;
import nl.flotsam.pecia.Section;
import nl.flotsam.pecia.Verbatim;
import nl.flotsam.pecia.builder.ArticleBuilder;
import nl.flotsam.pecia.builder.DocumentBuilder;
import nl.flotsam.pecia.builder.ParaBuilder;
import nl.flotsam.pecia.builder.RowBuilder;
import nl.flotsam.pecia.builder.SectionBuilder;
import nl.flotsam.pecia.builder.TableBuilder;
import nl.flotsam.pecia.builder.base.DefaultParaBuilder;
import nl.flotsam.pecia.builder.base.LifecycleListener;
import nl.flotsam.pecia.builder.xml.DefaultTagWriterCatalog;
import nl.flotsam.pecia.builder.xml.XmlDocumentBuilder;
import nl.flotsam.pecia.builder.xml.XmlWriter;

public class DocBookDocumentBuilder extends XmlDocumentBuilder {

    private static DefaultTagWriterCatalog CATALOG = new DefaultTagWriterCatalog();
    static {
        CATALOG.register(new OneToOneTagWriter("para", Para.class));
        CATALOG.register(new OneToOneTagWriter("itemizedlist",
                ItemizedList.class));
        CATALOG.register(new OneToOneTagWriter("listitem", ListItem.class));
        CATALOG.register(new OneToOneTagWriter("screen", Verbatim.class));
        CATALOG.register(new TableTagWriter());
        CATALOG.register(new RowTagWriter());
        CATALOG.register(new OneToOneTagWriter("entry", Entry.class));
        CATALOG.register(new OneToOneTagWriter("footnote", Footnote.class));
        CATALOG.register(new OneToOneTagWriter("entry", LastEntry.class));
        CATALOG.register(new ArticleTagWriter());
        CATALOG.register(new SectionTagWriter(true));
    }

    public DocBookDocumentBuilder(XmlWriter writer) {
        super(writer, CATALOG);
    }

    @Override
    public void end() {
        getWriter().writeEndDocument();
    }

    @Override
    public void start() {
        getWriter().writeStartDocument();
    }

    @Override
    public <T> ParaBuilder<T> createPara(T parent, DocumentBuilder builder) {
        return new DocBookParaBuilder<T>(builder, parent,
                getLifecycleListener());
    }

    private static class TableTagWriter implements TagWriter {

        public void end(Object documentElement, XmlWriter writer) {
            writer.writeEndElement();
            writer.writeEndElement();
            writer.writeEndElement();
        }

        public void start(Object documentElement, XmlWriter writer) {
            TableBuilder builder = (TableBuilder) documentElement;
            writer.writeStartElement("informaltable");
            writer.writeStartElement("tgroup");
            writer.writeAttribute("cols", Integer.toString(builder
                    .getColCount()));
        }

        public boolean supports(Object documentElement) {
            return (documentElement instanceof TableBuilder);
        }

    }

    private static class RowTagWriter implements TagWriter {

        public void end(Object documentElement, XmlWriter writer) {
            writer.writeEndElement();
        }

        public void start(Object documentElement, XmlWriter writer) {
            RowBuilder builder = (RowBuilder) documentElement;
            if (builder.isFirst() && builder.isHeader()) {
                writer.writeStartElement("thead");
            }
            if (builder.isFirst() && !builder.isHeader()) {
                writer.writeStartElement("tbody");
            }
            writer.writeStartElement("row");
        }

        public boolean supports(Object documentElement) {
            return documentElement instanceof RowBuilder;
        }

    }

    private static class ArticleTagWriter implements TagWriter {

        public void end(Object documentElement, XmlWriter writer) {
            writer.writeEndElement();
        }

        public void start(Object documentElement, XmlWriter writer) {
            ArticleBuilder builder = (ArticleBuilder) documentElement;
            writer.writeStartElement("article");
            if (builder.getAuthorEmail() != null || builder.getAuthorFirstname() != null || builder.getAuthorSurname() != null) {
                writer.writeStartElement("articleinfo");
                writer.writeStartElement("author");
                if (builder.getAuthorFirstname() != null) {
                    writer.writeStartElement("firstname");
                    writer.writeCharacters(builder.getAuthorFirstname());
                    writer.writeEndElement();
                }
                if (builder.getAuthorSurname() != null) {
                    writer.writeStartElement("surname");
                    writer.writeCharacters(builder.getAuthorSurname());
                    writer.writeEndElement();
                }
                if (builder.getAuthorEmail() != null) {
                    writer.writeStartElement("email");
                    writer.writeCharacters(builder.getAuthorEmail());
                    writer.writeEndElement();
                }
                writer.writeEndElement();
                writer.writeEndElement();
            }
            writer.writeStartElement("title");
            writer.writeCharacters(((ArticleBuilder)documentElement).getTitle());
            writer.writeEndElement();
        }

        public boolean supports(Object documentElement) {
            return documentElement instanceof ArticleBuilder;
        }

    }

    private class DocBookParaBuilder<T> extends DefaultParaBuilder<T> {

        public DocBookParaBuilder(DocumentBuilder builder, T parent,
                LifecycleListener listener) {
            super(builder, parent, listener);
        }

        @Override
        public Para<T> code(String text) {
            writeInline("command", text);
            return this;
        }

        @Override
        public Para<T> email(String email) {
            writeInline("email", email);
            return this;
        }

        @Override
        public Para<T> emphasis(String text) {
            writeInline("emphasis", text);
            return super.emphasis(text);
        }

        @Override
        public Para<T> text(String text) {
            getWriter().writeCharacters(text);
            return this;
        }

        @Override
        public Para<T> xref(String id) {
            getWriter().writeEmptyElement("xref");
            getWriter().writeAttribute("linkend", id);
            return this;
        }

    }

    private static class SectionTagWriter implements TagWriter {

        public boolean comments;

        public SectionTagWriter(boolean comments) {
            this.comments = comments;
        }

        public void end(Object documentElement, XmlWriter writer) {
            writer.writeEndElement();
            if (comments) {
                SectionBuilder builder = (SectionBuilder) documentElement;
                writer.writeComment("End of section \"" + builder.getTitle()
                        + "\"");
            }
        }

        public void start(Object documentElement, XmlWriter writer) {
            SectionBuilder builder = (SectionBuilder) documentElement;
            writer.writeStartElement("section");
            if (builder.getId() != null) {
                writer.writeAttribute("id", builder.getId());
            }
            writer.writeStartElement("title");
            writer.writeCharacters(builder.getTitle());
            writer.writeEndElement();
        }

        public boolean supports(Object documentElement) {
            return documentElement instanceof Section;
        }

    }


}
