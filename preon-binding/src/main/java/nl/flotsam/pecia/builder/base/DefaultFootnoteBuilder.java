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

import nl.flotsam.pecia.Footnote;
import nl.flotsam.pecia.Para;
import nl.flotsam.pecia.builder.DocumentBuilder;
import nl.flotsam.pecia.builder.FootnoteBuilder;

public class DefaultFootnoteBuilder<T> extends AbstractBuilder<T> implements FootnoteBuilder<T> {

    public DefaultFootnoteBuilder(DocumentBuilder builder, T parent, LifecycleListener listener) {
        super(builder, parent, listener);
    }

    public Para<? extends Footnote<T>> para() {
        return getBuilder().createPara(this, getBuilder()).start();
    }

    public Footnote<T> start() {
        getListener().started(this);
        return this;
    }

}
