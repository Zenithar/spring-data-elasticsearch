package org.springframework.data.elasticsearch.core.mapping;

import org.springframework.data.util.TypeInformation;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * @author Thibault Normand
 */
public class ConfigurableElasticsearchMappingContext extends SimpleElasticsearchMappingContext {

    private String indexName = null;
    private String indexType = null;
    private short shards = 0;
    private short replicas = 0;
    private String refreshInterval = null;
    private String indexStoreType = null;

    @Override
    protected <T> SimpleElasticsearchPersistentEntity<?> createPersistentEntity(TypeInformation<T> typeInformation) {
        return overrideValues(new ConfigurableElasticsearchPersistentEntity<T>(typeInformation));
    }

    private SimpleElasticsearchPersistentEntity<?> overrideValues(ConfigurableElasticsearchPersistentEntity entity) {
        if(isNotBlank(indexName)) { entity.setIndexName(indexName); }
        if(isNotBlank(indexType)) { entity.setIndexType(indexType); }
        if(shards > 0) { entity.setShards(shards); }
        if(replicas > 0) { entity.setReplicas(replicas); }
        if(isNotBlank(refreshInterval)) { entity.setRefreshInterval(refreshInterval); }
        if(isNotBlank(indexStoreType)) { entity.setIndexStoreType(indexStoreType); }
        return entity;
    }

    /**
     * Mutators
     */

    public void setIndexName(String indexName) {
        this.indexName = isNotBlank(indexName) ? indexName : null;
    }

    public void setIndexType(String indexType) {
        this.indexType = isNotBlank(indexType) ? indexType : null;
    }

    public void setShards(short shards) {
        this.shards = shards > 0 ? shards : 0;
    }

    public void setReplicas(short replicas) {
        this.replicas = replicas > 0 ? replicas : 0;
    }

    public void setRefreshInterval(String refreshInterval) {
        this.refreshInterval = isNotBlank(refreshInterval) ? refreshInterval : null;
    }

    public void setIndexStoreType(String indexStoreType) {
        this.indexStoreType = isNotBlank(indexStoreType) ? indexStoreType : null;
    }
}
