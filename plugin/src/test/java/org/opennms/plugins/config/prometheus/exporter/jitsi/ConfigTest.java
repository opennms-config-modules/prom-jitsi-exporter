/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2020 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2020 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.plugins.config.prometheus.exporter.jitsi;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ConfigTest {

    @Test
    public void canLoadCollectionExtension() {
        CollectionExtensionImpl collectionExtension = new CollectionExtensionImpl();
        assertThat(collectionExtension.getSnmpDataCollectionGroups(), hasSize(3));
    }

    @Test
    public void canLoadGraphPropertiesExtension() {
        GraphPropertiesExtension graphPropertiesExtension = new GraphPropertiesExtension();
        assertThat(graphPropertiesExtension.getPrefabGraphs(), hasSize(24));
    }

    @Test
    public void canLoadResourceTypesExtension() {
        ResourceTypesExtension resourceTypesExtension = new ResourceTypesExtension();
        assertThat(resourceTypesExtension.getResourceTypes(), hasSize(2));
    }

    @Test
    public void testAliasRrdMax19CharLimit() {
        CollectionExtensionImpl snmpCollectionExtension = new CollectionExtensionImpl();
        snmpCollectionExtension.getSnmpDataCollectionGroups().forEach(collectionGroup -> collectionGroup.getGroups().forEach(group -> group.getMibObjs().forEach(mibObj -> assertThat(mibObj.getAlias().length(), lessThanOrEqualTo(19)))));
    }
}
