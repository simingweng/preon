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

package nl.flotsam.pecia.builder.base;

import nl.flotsam.pecia.builder.ArticleBuilder;
import nl.flotsam.pecia.builder.DocumentBuilder;
import nl.flotsam.pecia.builder.EntryBuilder;
import nl.flotsam.pecia.builder.FootnoteBuilder;
import nl.flotsam.pecia.builder.ItemizedListBuilder;
import nl.flotsam.pecia.builder.LastEntryBuilder;
import nl.flotsam.pecia.builder.ListItemBuilder;
import nl.flotsam.pecia.builder.ParaBuilder;
import nl.flotsam.pecia.builder.RowBuilder;
import nl.flotsam.pecia.builder.SectionBuilder;
import nl.flotsam.pecia.builder.Table2ColsBuilder;
import nl.flotsam.pecia.builder.Table3ColsBuilder;
import nl.flotsam.pecia.builder.Table4ColsBuilder;
import nl.flotsam.pecia.builder.Table5ColsBuilder;
import nl.flotsam.pecia.builder.Table6ColsBuilder;
import nl.flotsam.pecia.builder.VerbatimBuilder;

public class DefaultDocumentBuilder implements DocumentBuilder {

    private LifecycleListener listener;

    public DefaultDocumentBuilder() {
        this(new NullLifecycleListener());
    }

    public DefaultDocumentBuilder(LifecycleListener listener) {
        this.listener = listener;
    }

    public <T> FootnoteBuilder<T> createFootnote(T parent,
            DocumentBuilder builder) {
        return new DefaultFootnoteBuilder<T>(builder, parent, listener);
    }

    public <T> ItemizedListBuilder<T> createItemizedList(T parent,
            DocumentBuilder builder) {
        return new DefaultItemizedListBuilder<T>(builder, parent, listener);
    }

    public <T> LastEntryBuilder<T> createLastEntry(T parent, boolean header,
            DocumentBuilder builder) {
        return new DefaultLastEntryBuilder<T>(builder, parent, header, listener);
    }

    public <T> ListItemBuilder<T> createListItem(T parent,
            DocumentBuilder builder) {
        return new DefaultListItemBuilder<T>(builder, parent, listener);
    }

    public <T> ItemizedListBuilder<T> createOrderedList(T parent,
            DocumentBuilder builder) {
        return new DefaultItemizedListBuilder<T>(builder, parent, listener);
    }

    public <T> ParaBuilder<T> createPara(T parent, DocumentBuilder builder) {
        return new DefaultParaBuilder<T>(builder, parent, listener);
    }

    public <T> SectionBuilder<T> createSection(T parent, String title,
            DocumentBuilder builder) {
        return new DefaultSectionBuilder<T>(builder, title, parent, listener);
    }

    public <T> Table2ColsBuilder<T> createTable2Cols(T parent,
            DocumentBuilder builder) {
        return new DefaultTable2ColsBuilder<T>(builder, parent, listener);
    }

    public <T> Table3ColsBuilder<T> createTable3Cols(T parent,
            DocumentBuilder builder) {
        return new DefaultTable3ColsBuilder<T>(builder, parent, listener);
    }

    public <T> Table4ColsBuilder<T> createTable4Cols(T parent,
            DocumentBuilder builder) {
        return new DefaultTable4ColsBuilder<T>(builder, parent, listener);
    }

    public <T> Table5ColsBuilder<T> createTable5Cols(T parent,
            DocumentBuilder builder) {
        return new DefaultTable5ColsBuilder<T>(builder, parent, listener);
    }

    public <T> Table6ColsBuilder<T> createTable6Cols(T parent,
            DocumentBuilder builder) {
        return new DefaultTable6ColsBuilder<T>(builder, parent, listener);
    }

    public <T> VerbatimBuilder<T> createVerbatim(T parent,
            DocumentBuilder builder) {
        return new DefaultVerbatimBuilder<T>(builder, parent, listener);
    }

    public <T> EntryBuilder<T> createEntry(T parent, boolean header,
            DocumentBuilder builder) {
        return new DefaultEntryBuilder<T>(builder, parent, header, listener);
    }

    public <T> RowBuilder<T> createRow(T parent, boolean header, boolean first,
            DocumentBuilder builder) {
        return new DefaultRowBuilder<T>(builder, parent, header, first,
                listener);
    }

    public <T> ArticleBuilder<T> createArticle(T parent, String title,
            DocumentBuilder builder) {
        return new DefaultArticleBuilder<T>(builder, parent, title, listener);
    }

    public void end() {
    }

    public void start() {
    }

    protected LifecycleListener getLifecycleListener() {
        return listener;
    }

}
