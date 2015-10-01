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
package org.finra.dm.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.finra.dm.model.api.xml.BusinessObjectDataAvailabilityCollectionRequest;
import org.finra.dm.model.api.xml.BusinessObjectDataAvailabilityRequest;
import org.finra.dm.model.api.xml.BusinessObjectDataCreateRequest;
import org.finra.dm.model.api.xml.BusinessObjectDataDdlCollectionRequest;
import org.finra.dm.model.api.xml.BusinessObjectDataDdlRequest;
import org.finra.dm.model.api.xml.BusinessObjectDataInvalidateUnregisteredRequest;
import org.finra.dm.model.api.xml.BusinessObjectDataKey;

public class BusinessObjectDataServiceTest extends AbstractServiceTest
{
    @Autowired
    @Qualifier(value = "businessObjectDataServiceImpl")
    private BusinessObjectDataService businessObjectDataServiceImpl;

    /**
     * This method is to get the coverage for the business object data service method that starts the new transaction.
     */
    @Test
    public void testBusinessObjectDataServiceMethodsNewTx() throws Exception
    {
        BusinessObjectDataAvailabilityRequest businessObjectDataAvailabilityRequest = new BusinessObjectDataAvailabilityRequest();
        try
        {
            businessObjectDataServiceImpl.checkBusinessObjectDataAvailability(businessObjectDataAvailabilityRequest);
            fail("Should throw an IllegalArgumentException.");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals("A business object definition name must be specified.", e.getMessage());
        }

        BusinessObjectDataAvailabilityCollectionRequest businessObjectDataAvailabilityCollectionRequest = new BusinessObjectDataAvailabilityCollectionRequest();
        try
        {
            businessObjectDataServiceImpl.checkBusinessObjectDataAvailabilityCollection(businessObjectDataAvailabilityCollectionRequest);
            fail("Should throw an IllegalArgumentException.");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals("At least one business object data availability request must be specified.", e.getMessage());
        }

        BusinessObjectDataCreateRequest businessObjectDataCreateRequest = new BusinessObjectDataCreateRequest();
        try
        {
            businessObjectDataServiceImpl.createBusinessObjectData(businessObjectDataCreateRequest);
            fail("Should throw an IllegalArgumentException.");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals("A business object definition name must be specified.", e.getMessage());
        }

        try
        {
            businessObjectDataServiceImpl.getBusinessObjectData(new BusinessObjectDataKey(), null);
            fail("Should throw an IllegalArgumentException.");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals("A business object definition name must be specified.", e.getMessage());
        }

        try
        {
            businessObjectDataServiceImpl.getS3KeyPrefix(new BusinessObjectDataKey(), null, null);
            fail("Should throw an IllegalArgumentException.");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals("A business object definition name must be specified.", e.getMessage());
        }

        try
        {
            businessObjectDataServiceImpl.generateBusinessObjectDataDdl(new BusinessObjectDataDdlRequest());
            fail("Should throw an IllegalArgumentException.");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals("A business object definition name must be specified.", e.getMessage());
        }

        try
        {
            businessObjectDataServiceImpl.generateBusinessObjectDataDdlCollection(new BusinessObjectDataDdlCollectionRequest());
            fail("Should throw an IllegalArgumentException.");
        }
        catch (IllegalArgumentException e)
        {
            assertEquals("At least one business object data DDL request must be specified.", e.getMessage());
        }

        try
        {
            businessObjectDataServiceImpl.invalidateUnregisteredBusinessObjectData(new BusinessObjectDataInvalidateUnregisteredRequest());
        }
        catch (IllegalArgumentException e)
        {
            assertEquals("The namespace is required", e.getMessage());
        }
    }
}