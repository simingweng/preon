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

package nl.flotsam.pecia.builder;

import nl.flotsam.pecia.Article;

/**
 * The interface implemented by objects capable of building a certain type of
 * document. (This one definitely needs to change in the future.)
 * 
 * @author Wilfred Springer
 * 
 */
public interface DocumentBuilder {

    /**
     * Called when the document is started.
     */
    void start();

    /**
     * Called when the document is ended.
     */
    void end();

    <T> ArticleBuilder<T> createArticle(T parent, String title,
            DocumentBuilder builder);

    <T> ParaBuilder<T> createPara(T parent, DocumentBuilder builder);

    <T> FootnoteBuilder<T> createFootnote(T parent, DocumentBuilder builder);

    <T> ItemizedListBuilder<T> createItemizedList(T parent,
            DocumentBuilder builder);

    <T> ItemizedListBuilder<T> createOrderedList(T parent,
            DocumentBuilder builder);

    <T> SectionBuilder<T> createSection(T parent, String title,
            DocumentBuilder builder);

    <T> VerbatimBuilder<T> createVerbatim(T parent, DocumentBuilder builder);

    <T> ListItemBuilder<T> createListItem(T parent, DocumentBuilder builder);

    <T> RowBuilder<T> createRow(T parent, boolean header, boolean first,
            DocumentBuilder builder);

    <T> EntryBuilder<T> createEntry(T parent, boolean header,
            DocumentBuilder builder);

    <T> LastEntryBuilder<T> createLastEntry(T parent, boolean header,
            DocumentBuilder builder);

    <T> Table2ColsBuilder<T> createTable2Cols(T parent, DocumentBuilder builder);

    <T> Table3ColsBuilder<T> createTable3Cols(T parent, DocumentBuilder builder);

    <T> Table4ColsBuilder<T> createTable4Cols(T parent, DocumentBuilder builder);

    <T> Table5ColsBuilder<T> createTable5Cols(T parent, DocumentBuilder builder);

    <T> Table6ColsBuilder<T> createTable6Cols(T parent, DocumentBuilder builder);

}
