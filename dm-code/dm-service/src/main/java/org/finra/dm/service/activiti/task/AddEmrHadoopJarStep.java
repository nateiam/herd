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
package org.finra.dm.service.activiti.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.springframework.stereotype.Component;

import org.finra.dm.model.api.xml.EmrHadoopJarStepAddRequest;

/**
 * An Activiti task that adds hadoop jar step to EMR cluster
 * <p/>
 * 
 * <pre>
 * <extensionElements>
 *   <activiti:field name="namespaceCode" stringValue="" />
 *   <activiti:field name="emrClusterDefinitionName" stringValue="" />
 *   <activiti:field name="emrClusterName" stringValue="" />
 *   <activiti:field name="stepName" stringValue="" />
 *   <activiti:field name="jarLocation" stringValue="" />
 *   <activiti:field name="mainClass" stringValue="" />
 *   <activiti:field name="scriptArguments" stringValue="" />
 *   <activiti:field name="continueOnError" stringValue="" />
 * </extensionElements>
 * </pre>
 */
@Component
public class AddEmrHadoopJarStep extends BaseAddEmrStep
{
    protected Expression jarLocation;
    protected Expression mainClass;
    
    @Override
    public void executeImpl(DelegateExecution execution) throws Exception
    {
        // Create the request.
        EmrHadoopJarStepAddRequest request = new EmrHadoopJarStepAddRequest();
        
        populateCommonParams(request, execution);
        
        request.setJarLocation(getJarLocation(execution));
        request.setMainClass(getMainClass(execution));
        request.setScriptArguments(getScriptArguments(execution));

        addEmrStepAndSetWorkflowVariables(request, execution);
    }
    
    private String getJarLocation(DelegateExecution execution)
    {
        return activitiHelper.getExpressionVariableAsString(jarLocation, execution);
    }

    private String getMainClass(DelegateExecution execution)
    {
        return activitiHelper.getExpressionVariableAsString(mainClass, execution);
    }
}