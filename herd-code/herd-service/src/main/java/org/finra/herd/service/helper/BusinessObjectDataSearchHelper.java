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
package org.finra.herd.service.helper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import org.finra.herd.model.api.xml.BusinessObjectDataSearchFilter;
import org.finra.herd.model.api.xml.BusinessObjectDataSearchKey;
import org.finra.herd.model.api.xml.BusinessObjectDataSearchRequest;

/*
 * a helper class Business Object Data Search 
 */
@Component
public class BusinessObjectDataSearchHelper
{
    @Autowired
    private AlternateKeyHelper alternateKeyHelper;

    /**
     * validate business object search request
     *
     * @param business object DATA search request
     *
     * @throws IllegalArgumentException when business object data search request is not valid
     */
    public void validateBusinesObjectDataSearchRequest(BusinessObjectDataSearchRequest request) throws IllegalArgumentException
    {
        Assert.notNull(request, "A BusinessObjectDataSearchRequest must be specified");
        List<BusinessObjectDataSearchFilter> businessObjectDataSearchFilters = request.getBusinessObjectDataSearchFilters();
        Assert.isTrue(businessObjectDataSearchFilters!= null, "BusinessObjectDataSearchFilters must be specified");
        Assert.isTrue(businessObjectDataSearchFilters.size() == 1, "BusinessObjectDataSearchFilters can only have one filter");
        List<BusinessObjectDataSearchKey> businessObjectDataSearchKeys = request.getBusinessObjectDataSearchFilters().get(0).getBusinessObjectDataSearchKeys();

        Assert.isTrue(businessObjectDataSearchKeys != null, "A BusinessObject Search Key must be specified");

        Assert.isTrue(businessObjectDataSearchKeys.size() == 1, "A BusinessObject Search Key can only have one");

        for (BusinessObjectDataSearchKey key : businessObjectDataSearchKeys)
        {
            validateBusinessObjectDataKey(key);
        }
    }

    /**
     * validate business search key
     *
     * @param key business object data search key
     *
     * @throws IllegalArgumentException when business object data search key is not valid
     */
    public void validateBusinessObjectDataKey(BusinessObjectDataSearchKey key) throws IllegalArgumentException
    {
        Assert.notNull(key, "A business object data key must be specified.");
        key.setNamespace(alternateKeyHelper.validateStringParameter("namespace", key.getNamespace()));
        key.setBusinessObjectDefinitionName(
            alternateKeyHelper.validateStringParameter("business object definition name", key.getBusinessObjectDefinitionName()));

        if (key.getBusinessObjectFormatUsage() != null)
        {
            key.setBusinessObjectFormatUsage(alternateKeyHelper.validateStringParameter("business object format usage", key.getBusinessObjectFormatUsage()));
        }

        if (key.getBusinessObjectFormatFileType() != null)
        {
            key.setBusinessObjectFormatFileType(
                alternateKeyHelper.validateStringParameter("business object format file type", key.getBusinessObjectFormatFileType()));
        }
    }
}
