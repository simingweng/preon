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

package nl.flotsam.pecia.builder.xml;

import java.util.LinkedList;
import java.util.List;

import nl.flotsam.pecia.builder.xml.XmlDocumentBuilder.TagWriter;
import nl.flotsam.pecia.builder.xml.XmlDocumentBuilder.TagWriterCatalog;


public class DefaultTagWriterCatalog implements TagWriterCatalog {

    private List<TagWriter> writers = new LinkedList<TagWriter>();

    public DefaultTagWriterCatalog register(TagWriter writer) {
        writers.add(writer);
        return this;
    }

    @SuppressWarnings("unchecked")
    public TagWriter getTagWriter(Object documentElement) {
        for (TagWriter writer : writers) {
            if (writer.supports(documentElement)) {
                return writer;
            }
        }
        return null;
    }

}
