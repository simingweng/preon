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

package nl.flotsam.pecia;

/**
 * A general interface, allowing you to produce several non-inline document
 * elements.
 * 
 * @author Wilfred Springer
 * 
 * @param <V>
 *            The type of the parent document element.
 */
public interface Contents<V> extends SimpleContents<V> {

    /**
     * Adds a new section.
     * 
     * @param title
     *            The title of the section.
     * @return The section just created.
     */
    AnnotatedSection<? extends V> section(String title);

}
