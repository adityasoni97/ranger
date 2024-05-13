/*
 * Copyright 2015 Flipkart Internet Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.appform.ranger.client;

import io.appform.ranger.client.utils.RangerHubTestUtils;
import io.appform.ranger.core.model.Service;
import io.appform.ranger.core.model.ServiceNode;
import io.appform.ranger.core.units.TestNodeData;
import io.appform.ranger.core.utils.RangerTestUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class AbstractRangerHubClientTest {

    private static final Service service = RangerTestUtils.getService("test-ns", "test-s");

    @Test
    void testAbstractHubClient() {
        val testAbstractHub = RangerHubTestUtils.getTestHub();
        testAbstractHub.start();
        ServiceNode<TestNodeData> node = testAbstractHub.getNode(service).orElse(null);
        Assertions.assertNotNull(node);
        Assertions.assertTrue(node.getHost().equalsIgnoreCase("localhost"));
        Assertions.assertEquals(9200, node.getPort());
        Assertions.assertEquals(1, node.getNodeData().getShardId());
        Assertions.assertFalse(testAbstractHub.getNode(RangerTestUtils.getService("test", "test")).isPresent());
        Assertions.assertFalse(testAbstractHub.getNode(service, nodeData -> nodeData.getShardId() == 2).isPresent());
        Assertions.assertFalse(testAbstractHub.getNode(RangerTestUtils.getService("test", "test"), nodeData -> nodeData.getShardId() == 1).isPresent());
        testAbstractHub.stop();
    }

    @Test
    void testAbstractHubClientWithDataSource() {
        val testAbstractHub = RangerHubTestUtils.getTestHubWithDataSource();
        testAbstractHub.start();
        val node = testAbstractHub.getNode(service).orElse(null);
        Assertions.assertNotNull(node);
        Assertions.assertTrue(node.getHost().equalsIgnoreCase("localhost"));
        Assertions.assertEquals(9200, node.getPort());
        Assertions.assertEquals(1, node.getNodeData().getShardId());
        Assertions.assertFalse(testAbstractHub.getNode(RangerTestUtils.getService("test", "test")).isPresent());
        Assertions.assertFalse(testAbstractHub.getNode(service, nodeData -> nodeData.getShardId() == 2).isPresent());
        Assertions.assertFalse(testAbstractHub.getNode(RangerTestUtils.getService("test", "test"), nodeData -> nodeData.getShardId() == 1).isPresent());
        testAbstractHub.stop();
    }
}
