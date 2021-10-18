/*
 * Copyright 2015 Flipkart Internet Pvt. Ltd.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.flipkart.ranger.core.finder.shardselector;

import com.flipkart.ranger.core.finder.serviceregistry.ListBasedServiceRegistry;
import com.flipkart.ranger.core.model.Criteria;
import com.flipkart.ranger.core.model.ServiceNode;
import com.flipkart.ranger.core.units.TestNodeData;
import com.flipkart.ranger.core.utils.CriteriaUtils;
import com.flipkart.ranger.core.utils.RegistryTestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ListShardSelectorTest {

    @Test
    public void testNoOpShardSelector(){
        final ListBasedServiceRegistry<TestNodeData> serviceRegistry = RegistryTestUtils.getUnshardedRegistry();
        final ListShardSelector<TestNodeData, Criteria<TestNodeData>> shardSelector = new ListShardSelector<>();
        final List<ServiceNode<TestNodeData>> nodes = shardSelector.nodes(CriteriaUtils.getCriteria(1), serviceRegistry);
        Assert.assertFalse(nodes.isEmpty());
        Assert.assertEquals("localhost-1", nodes.get(0).getHost());
    }
}
