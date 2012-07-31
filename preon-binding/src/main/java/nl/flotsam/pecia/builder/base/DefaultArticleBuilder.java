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

import nl.flotsam.pecia.AnnotatedArticle;
import nl.flotsam.pecia.AnnotatedSection;
import nl.flotsam.pecia.Article;
import nl.flotsam.pecia.Author;
import nl.flotsam.pecia.Documenter;
import nl.flotsam.pecia.ItemizedList;
import nl.flotsam.pecia.Para;
import nl.flotsam.pecia.SimpleContents;
import nl.flotsam.pecia.Table2Cols;
import nl.flotsam.pecia.Table3Cols;
import nl.flotsam.pecia.Table4Cols;
import nl.flotsam.pecia.Table5Cols;
import nl.flotsam.pecia.Verbatim;
import nl.flotsam.pecia.builder.ArticleBuilder;
import nl.flotsam.pecia.builder.DocumentBuilder;

public class DefaultArticleBuilder<T> extends AbstractBuilder<T> implements
        ArticleBuilder<T> {

    private String title;

    private boolean started = false;

    private String authorFirstname;

    private String authorSurname;

    private String authorEmail;

    private String copyrightHolder;

    private int[] copyRightYears;

    public DefaultArticleBuilder(DocumentBuilder builder, T parent,
            String title, LifecycleListener listener) {
        super(builder, parent, listener);
        this.title = title;
    }

    public ItemizedList<? extends Article<T>> itemizedList() {
        considerStart();
        return getBuilder().createItemizedList(this, getBuilder()).start();
    }

    public ItemizedList<? extends Article<T>> orderedList() {
        considerStart();
        return getBuilder().createItemizedList(this, getBuilder()).start();
    }

    public Para<? extends Article<T>> para() {
        considerStart();
        return getBuilder().createPara(this, getBuilder()).start();
    }

    public AnnotatedSection<? extends Article<T>> section(String title) {
        considerStart();
        return getBuilder().createSection(this, title, getBuilder()).start();
    }

    public Table2Cols<? extends Article<T>> table2Cols() {
        considerStart();
        return getBuilder().createTable2Cols(this, getBuilder()).start();
    }

    public Table3Cols<? extends Article<T>> table3Cols() {
        considerStart();
        return getBuilder().createTable3Cols(this, getBuilder()).start();
    }

    public Table4Cols<? extends Article<T>> table4Cols() {
        considerStart();
        return getBuilder().createTable4Cols(this, getBuilder()).start();
    }

    public Table5Cols<? extends Article<T>> table5Cols() {
        considerStart();
        return getBuilder().createTable5Cols(this, getBuilder()).start();
    }

    public Verbatim<? extends Article<T>> verbatim() {
        considerStart();
        return getBuilder().createVerbatim(this, getBuilder()).start();
    }

    public AnnotatedArticle<T> start() {
        return this;
    }

    public String getTitle() {
        return title;
    }

    public AnnotatedArticle<T> copyright(String owner, int... year) {
        this.copyrightHolder = owner;
        this.copyRightYears = year;
        return this;
    }

    private void considerStart() {
        if (!started) {
            getListener().started(this);
            started = true;
        }
    }

    public String getCopyright() {
        StringBuilder builder = new StringBuilder().append("Copyright ").append(
                copyrightHolder);
        for (int i = 0 ; i < copyRightYears.length; i++) {
            if (i > 0) {
                builder.append(",");
            }
            builder.append(copyRightYears[i]);
        }
        return builder.toString();
    }

    public Author<AnnotatedArticle<T>> author() {
        return new Author<AnnotatedArticle<T>>() {

            public Author<AnnotatedArticle<T>> email(String email) {
                DefaultArticleBuilder.this.authorEmail = email;
                return this;
            }

            public AnnotatedArticle<T> end() {
                return DefaultArticleBuilder.this;
            }

            public Author<AnnotatedArticle<T>> firstname(String firstname) {
                DefaultArticleBuilder.this.authorFirstname = firstname;
                return this;
            }

            public Author<AnnotatedArticle<T>> surname(String surname) {
                DefaultArticleBuilder.this.authorSurname = surname;
                return this;
            }

        };
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public String getAuthorFirstname() {
        return authorFirstname;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public Article<T> para(String text) {
        getBuilder().createPara(this, getBuilder()).start().text(text).end();
        return this;
    }

    public Article<T> document(Documenter<SimpleContents<?>> target) {
        target.document(this);
        return this;
    }
}
