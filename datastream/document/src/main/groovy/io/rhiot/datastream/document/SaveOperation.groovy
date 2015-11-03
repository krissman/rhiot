/**
 * Licensed to the Rhiot under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.rhiot.datastream.document

import static Pojos.collectionName
import static io.rhiot.datastream.document.Pojos.pojoToMap;

public class SaveOperation {

    private final String collection

    private final Map<String, Object> pojo

    SaveOperation(String collection, Map<String, Object> pojo) {
        this.collection = collection;
        this.pojo = pojo;
    }

    SaveOperation(Object pojo) {
        this(collectionName(pojo.getClass()), pojoToMap(pojo))
    }

    String collection() {
        return collection;
    }

    Map<String, Object> pojo() {
        return pojo;
    }

}