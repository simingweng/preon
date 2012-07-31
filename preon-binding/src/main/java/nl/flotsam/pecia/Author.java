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
 * The interface for producing the metadata of an author.
 *
 * @author Wilfred Springer
 *
 * @param <T> The type of the document element holding the author metadata.
 */
public interface Author<T> {

    /**
     * Passes the first name of the author.
     *
     * @param firstname The first name of the author.
     * @return The current author element.
     */
    Author<T> firstname(String firstname);

    /**
     * Passes the surname of the author.
     *
     * @param surname The surname of the author.
     * @return The current author element.
     */
    Author<T> surname(String surname);

    /**
     * Passes the email address of the author.
     *
     * @param email The email address of the author.
     * @return The current author element.
     */
    Author<T> email(String email);

    /**
     * Returns focus to the parent document element.
     *
     * @return
     */
    T end();

}
