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
 * A specialization of {@link Section}, allowing you to add some
 * {@link Section} metadata. Once you start adding content to the
 * {@link Section}, there is no way turning back to the {@link Section} itself.
 * 
 * @author Wilfred Springer
 * 
 * @param <T>
 *            The type of content.
 */
public interface AnnotatedSection<T> extends Section<T> {

    /**
     * Marks the section with a certain unique identifier. (Relevant for cross
     * references.)
     * 
     * @param id
     *            The identifier of that section.
     * @return The start of the {@link Section}, allowing you to potentially
     *         add more metadata.)
     */
    AnnotatedSection<T> mark(String id);

}
