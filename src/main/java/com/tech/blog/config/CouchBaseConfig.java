package com.tech.blog.config;

import com.couchbase.client.core.env.TimeoutConfig;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.ClusterOptions;
import com.couchbase.client.java.codec.JacksonJsonSerializer;
import com.couchbase.client.java.codec.JsonTranscoder;
import com.couchbase.client.java.env.ClusterEnvironment;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.ehcache.config.CacheConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.time.Duration;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.SECONDS;

@Configuration
@EnableCaching
public class CouchBaseConfig extends CachingConfigurerSupport {

    @Autowired
    private ObjectMapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(CouchBaseConfig.class);

    @Value("${cb.cluster.username}")
    private String clusterUserName;
    @Value("${cb.cluster.password}")
    private String clusterPassword;
    @Value("${cb.cluster.bucketname}")
    private String bucketName;
    @Value("#{'${cb.hosts}'.split(',')}")
    private Set<String> hosts;
    @Value("${cb.connectionTimeOut}")
    private int cbConnectionTimeOut;
    @Value("${cb.kvTimeOut}")
    private int cbKvTimeOut;

    private Cluster cluster;
    private ClusterEnvironment clusterEnvironment;

    public String getProfileBucket() {
        return bucketName;
    }

    public ClusterEnvironment buildClusterEnvironment() {
        clusterEnvironment = ClusterEnvironment.builder()
                .transcoder(JsonTranscoder.create(JacksonJsonSerializer.create(mapper)))
                .jsonSerializer(JacksonJsonSerializer.create(mapper))
                .timeoutConfig(TimeoutConfig.builder()
                        .connectTimeout(Duration.of(cbConnectionTimeOut, SECONDS))
                        .kvTimeout(Duration.of(cbKvTimeOut, SECONDS))).build();
        return clusterEnvironment;
    }

    public String getConnectionString() {
        return hosts.stream().collect(Collectors.joining(","));
    }

    @Bean
    public Cluster initCluster() {
        cluster =  Cluster.connect(getConnectionString(),
                ClusterOptions.clusterOptions(clusterUserName, clusterPassword)
                        .environment(buildClusterEnvironment()));
        cluster.waitUntilReady(Duration.ofMinutes(5));
        return cluster;
    }

    @Bean
    public net.sf.ehcache.CacheManager buildEhCacheManager() {
        CacheConfiguration profileCache = new CacheConfiguration();
        profileCache.setName("profile-cache");
        profileCache.setMemoryStoreEvictionPolicy("LRU");
        profileCache.setMaxEntriesLocalHeap(100);
        profileCache.setTimeToLiveSeconds(300);

        CacheConfiguration univsNamesCache = new CacheConfiguration();
        univsNamesCache.setName("univs-names-cache");
        univsNamesCache.setMemoryStoreEvictionPolicy("LRU");
        univsNamesCache.setMaxEntriesLocalHeap(100);
        univsNamesCache.setTimeToLiveSeconds(10000);

        net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();
        configuration.addCache(profileCache);
        configuration.addCache(univsNamesCache);

        return net.sf.ehcache.CacheManager.newInstance(configuration);
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(buildEhCacheManager());
    }

    @PreDestroy
    public void cleanup() {
        if (cluster != null) {
            LOGGER.info("shutting down the cluster");
            cluster.disconnect();
            clusterEnvironment.shutdown();
        }
    }
}
