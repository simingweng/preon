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
 * The base interface for (almost) all document elements, that are also
 * containers.
 *
 * @author Wilfred Springer
 *
 * @param <T>
 *            The type of the parent container element.
 */
public interface DocumentElement<T> {

    /**
     * Signals the end of the document element.
     *
     * @return The parent element.
     */
    T end();

    /**
     * Returns the parent element.
     *
     * @return The parent element.
     */
    T getParent();

}
