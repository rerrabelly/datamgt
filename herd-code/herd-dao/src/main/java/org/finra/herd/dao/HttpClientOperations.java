/*
* Copyright 2015 herd contributors
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.finra.herd.dao;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * Interface that wraps calls to HttpClient.
 */
public interface HttpClientOperations
{
    /**
     * Executes an HTTP request on an HTTP client.
     *
     * @param httpClient the HTTP client that executes the request.
     * @param request the request to execute.
     *
     * @return the response of the request.
     * @throws IOException if an I/O error occurred.
     * @throws JAXBException if a JAXB parsing error occurred.
     */
    CloseableHttpResponse execute(CloseableHttpClient httpClient, HttpUriRequest request) throws IOException, JAXBException;
}
