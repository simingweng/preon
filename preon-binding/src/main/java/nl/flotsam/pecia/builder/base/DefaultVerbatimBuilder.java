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

import nl.flotsam.pecia.Verbatim;
import nl.flotsam.pecia.builder.DocumentBuilder;
import nl.flotsam.pecia.builder.VerbatimBuilder;

public class DefaultVerbatimBuilder<T> extends AbstractBuilder<T> implements VerbatimBuilder<T> {

    public DefaultVerbatimBuilder(DocumentBuilder builder, T parent, LifecycleListener listener) {
        super(builder, parent, listener);
    }

    public Verbatim<T> newline() {
        return this;
    }

    public Verbatim<T> text(String text) {
        return this;
    }

    public Verbatim<T> start() {
        getListener().started(this);
        return this;
    }

}
