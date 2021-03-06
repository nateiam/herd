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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;

import org.finra.dm.model.ObjectNotFoundException;
import org.finra.dm.model.jpa.StoragePlatformEntity;
import org.finra.dm.model.api.xml.StoragePlatform;
import org.finra.dm.model.api.xml.StoragePlatforms;

/**
 * This class tests various functionality within the storage platform REST controller.
 */
public class StoragePlatformServiceTest extends AbstractServiceTest
{
    private static Logger logger = Logger.getLogger(StoragePlatformServiceTest.class);

    @Test
    public void testGetStoragePlatforms() throws Exception
    {
        StoragePlatforms storagePlatforms = storagePlatformService.getStoragePlatforms();
        assertNotNull(storagePlatforms);
        logger.info("Total number of storage platforms: " + storagePlatforms.getStoragePlatforms().size());
        assertTrue(storagePlatforms.getStoragePlatforms().size() >= 1); // We should have at least 1 row of reference data present (i.e. S3).
    }

    @Test
    public void testGetStoragePlatform() throws Exception
    {
        StoragePlatform storagePlatform = storagePlatformService.getStoragePlatform(StoragePlatformEntity.S3);
        assertNotNull(storagePlatform);
        assertTrue(StoragePlatformEntity.S3.equals(storagePlatform.getName()));
        logger.info("Storage platform name: " + storagePlatform.getName());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testGetStoragePlatformInvalidName() throws Exception
    {
        storagePlatformService.getStoragePlatform("invalid" + getRandomSuffix());
    }
}
