/*
 * Copyright 2017 Huawei Technologies Co., Ltd.
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

package org.openo.sdnhub.overlayvpndriver.service.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BaseModelTest {

    BaseModel baseModel = new BaseModel();

    /**
     * <br/>
     *
     * @throws Exception setup exception
     * @since SDNHUB 0.5
     */
    @Before
    public void setup() {
        baseModel.setTenantId("tenant1");
        baseModel.setName("basemodel1");
        baseModel.setDescription("Base Model 1");
        baseModel.setDeployStatus("deployed1");
        baseModel.setOperationStatus("sucess1");
        baseModel.setActiveStatus("active");
        baseModel.setRunningStatus("running");
        baseModel.setCreatetime((long)280);
        baseModel.setUpdatetime((long)180);
        baseModel.setAdditionalInfo("additional1");
    }

    @Test
    public void testEqualTenantId() {

        BaseModel baseModel1 = new BaseModel();
        baseModel1.setTenantId("tenant2");
        baseModel1.setName("basemodel1");
        baseModel1.setDescription("Base Model 1");
        baseModel1.setDeployStatus("deployed1");
        baseModel1.setOperationStatus("sucess1");
        baseModel1.setActiveStatus("active");
        baseModel1.setRunningStatus("running");
        baseModel1.setCreatetime((long)280);
        baseModel1.setUpdatetime((long)180);
        baseModel1.setAdditionalInfo("additional1");
        assertFalse(baseModel.equals(baseModel1));
    }

    @Test
    public void testEqualName() {
        BaseModel baseModel1 = new BaseModel();
        baseModel1.setTenantId("tenant1");
        baseModel1.setName("basemodel2");
        baseModel1.setDescription("Base Model 1");
        baseModel1.setDeployStatus("deployed1");
        baseModel1.setOperationStatus("sucess1");
        baseModel1.setActiveStatus("active");
        baseModel1.setRunningStatus("running");
        baseModel1.setCreatetime((long)280);
        baseModel1.setUpdatetime((long)180);
        baseModel1.setAdditionalInfo("additional1");
        assertFalse(baseModel.equals(baseModel1));
    }

    @Test
    public void testEqualDescription() {
        BaseModel baseModel1 = new BaseModel();
        baseModel1.setTenantId("tenant1");
        baseModel1.setName("basemodel1");
        baseModel1.setDescription("Base Model 2");
        baseModel1.setDeployStatus("deployed1");
        baseModel1.setOperationStatus("sucess1");
        baseModel1.setActiveStatus("active");
        baseModel1.setRunningStatus("running");
        baseModel1.setCreatetime((long)280);
        baseModel1.setUpdatetime((long)180);
        baseModel1.setAdditionalInfo("additional1");
        assertFalse(baseModel.equals(baseModel1));
    }

    @Test
    public void testEqualDeployStatus() {
        BaseModel baseModel1 = new BaseModel();
        baseModel1.setTenantId("tenant1");
        baseModel1.setName("basemodel1");
        baseModel1.setDescription("Base Model 1");
        baseModel1.setDeployStatus("deployed2");
        baseModel1.setOperationStatus("sucess1");
        baseModel1.setActiveStatus("active");
        baseModel1.setRunningStatus("running");
        baseModel1.setCreatetime((long)280);
        baseModel1.setUpdatetime((long)180);
        baseModel1.setAdditionalInfo("additional1");
        assertFalse(baseModel.equals(baseModel1));
    }

    @Test
    public void testEqualOperationStatus() {
        BaseModel baseModel1 = new BaseModel();
        baseModel1.setTenantId("tenant1");
        baseModel1.setName("basemodel1");
        baseModel1.setDescription("Base Model 1");
        baseModel1.setDeployStatus("deployed1");
        baseModel1.setOperationStatus("sucess2");
        baseModel1.setActiveStatus("active");
        baseModel1.setRunningStatus("running");
        baseModel1.setCreatetime((long)280);
        baseModel1.setUpdatetime((long)180);
        baseModel1.setAdditionalInfo("additional1");
        assertFalse(baseModel.equals(baseModel1));
    }

    @Test
    public void testEqualActiveStatus() {
        BaseModel baseModel1 = new BaseModel();
        baseModel1.setTenantId("tenant1");
        baseModel1.setName("basemodel1");
        baseModel1.setDescription("Base Model 1");
        baseModel1.setDeployStatus("deployed1");
        baseModel1.setOperationStatus("sucess1");
        baseModel1.setActiveStatus("Notactive");
        baseModel1.setRunningStatus("running");
        baseModel1.setCreatetime((long)280);
        baseModel1.setUpdatetime((long)180);
        baseModel1.setAdditionalInfo("additional1");
        assertFalse(baseModel.equals(baseModel1));
    }

    @Test
    public void testEqualRunningStatus() {
        BaseModel baseModel1 = new BaseModel();
        baseModel1.setTenantId("tenant1");
        baseModel1.setName("basemodel1");
        baseModel1.setDescription("Base Model 1");
        baseModel1.setDeployStatus("deployed1");
        baseModel1.setOperationStatus("sucess1");
        baseModel1.setActiveStatus("active");
        baseModel1.setRunningStatus("Notrunning");
        baseModel1.setCreatetime((long)280);
        baseModel1.setUpdatetime((long)180);
        baseModel1.setAdditionalInfo("additional1");
        assertFalse(baseModel.equals(baseModel1));
    }

    @Test
    public void testCreatetime() {
        BaseModel baseModel1 = new BaseModel();
        baseModel1.setTenantId("tenant1");
        baseModel1.setName("basemodel1");
        baseModel1.setDescription("Base Model 1");
        baseModel1.setDeployStatus("deployed1");
        baseModel1.setOperationStatus("sucess1");
        baseModel1.setActiveStatus("active");
        baseModel1.setRunningStatus("running");
        baseModel1.setCreatetime((long)380);
        baseModel1.setUpdatetime((long)180);
        baseModel1.setAdditionalInfo("additional1");
        assertFalse(baseModel.equals(baseModel1));
    }

    @Test
    public void testEqualUpdatetime() {
        BaseModel baseModel1 = new BaseModel();
        baseModel1.setTenantId("tenant1");
        baseModel1.setName("basemodel1");
        baseModel1.setDescription("Base Model 1");
        baseModel1.setDeployStatus("deployed1");
        baseModel1.setOperationStatus("sucess1");
        baseModel1.setActiveStatus("active");
        baseModel1.setRunningStatus("running");
        baseModel1.setCreatetime((long)280);
        baseModel1.setUpdatetime((long)280);
        baseModel1.setAdditionalInfo("additional1");
        assertFalse(baseModel.equals(baseModel1));
    }

    @Test
    public void testEqualAdditionalInfo() {
        BaseModel baseModel1 = new BaseModel();
        baseModel1.setTenantId("tenant1");
        baseModel1.setName("basemodel1");
        baseModel1.setDescription("Base Model 1");
        baseModel1.setDeployStatus("deployed1");
        baseModel1.setOperationStatus("sucess1");
        baseModel1.setActiveStatus("active");
        baseModel1.setRunningStatus("running");
        baseModel1.setCreatetime((long)280);
        baseModel1.setUpdatetime((long)180);
        baseModel1.setAdditionalInfo("additional2");
        assertFalse(baseModel.equals(baseModel1));
    }

   
    @Test
    public void testEqual() {
        
        BaseModel baseModel1 = new BaseModel();
        baseModel1.setTenantId("tenant1");
        baseModel1.setName("basemodel1");
        baseModel1.setDescription("Base Model 1");
        baseModel1.setDeployStatus("deployed1");
        baseModel1.setOperationStatus("sucess1");
        baseModel1.setActiveStatus("active");
        baseModel1.setRunningStatus("running");
        baseModel1.setCreatetime((long)280);
        baseModel1.setUpdatetime((long)180);
        baseModel1.setAdditionalInfo("additional1");
        
        assertTrue(baseModel.equals(baseModel1));
        assertTrue(baseModel.equals(baseModel));
    }

    @Test
    public void testEqualAllNull() {
        BaseModel baseModel1 = new BaseModel();
        baseModel1.setTenantId(null);
        baseModel1.setName(null);
        baseModel1.setDescription(null);
        baseModel1.setDeployStatus(null);
        baseModel1.setOperationStatus(null);
        baseModel1.setActiveStatus(null);
        baseModel1.setRunningStatus(null);
        baseModel1.setCreatetime(null);
        baseModel1.setUpdatetime(null);
        baseModel1.setAdditionalInfo(null);

        baseModel1.hashCode();
        baseModel.hashCode();
        assertFalse(baseModel.equals(baseModel1));
        assertFalse(baseModel.equals(null));
        assertFalse(baseModel.equals(new Object()));
    }
}
