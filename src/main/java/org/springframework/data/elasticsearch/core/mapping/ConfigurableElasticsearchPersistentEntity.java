package org.springframework.data.elasticsearch.core.mapping;

import org.springframework.data.util.TypeInformation;

/**
 * @author Thibault Normand
 */
public class ConfigurableElasticsearchPersistentEntity<T> extends SimpleElasticsearchPersistentEntity<T> {

    private String indexName;
    private String indexType;
    private short shards;
    private short replicas;
    private String refreshInterval;
    private String indexStoreType;

    public ConfigurableElasticsearchPersistentEntity(TypeInformation<T> typeInformation) {
        super(typeInformation);

        this.indexName = super.getIndexName();
        this.indexType = super.getIndexType();
        this.shards = super.getShards();
        this.replicas = super.getReplicas();
        this.refreshInterval = super.getRefreshInterval();
        this.indexStoreType = super.getIndexStoreType();
    }

    @Override
    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    @Override
    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    @Override
    public short getShards() {
        return shards;
    }

    public void setShards(short shards) {
        this.shards = shards;
    }

    @Override
    public short getReplicas() {
        return replicas;
    }

    public void setReplicas(short replicas) {
        this.replicas = replicas;
    }

    @Override
    public String getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(String refreshInterval) {
        this.refreshInterval = refreshInterval;
    }

    @Override
    public String getIndexStoreType() {
        return indexStoreType;
    }

    public void setIndexStoreType(String indexStoreType) {
        this.indexStoreType = indexStoreType;
    }
}
