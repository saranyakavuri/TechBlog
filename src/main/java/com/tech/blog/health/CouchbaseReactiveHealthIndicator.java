package com.tech.blog.health;

import com.couchbase.client.core.diagnostics.DiagnosticsResult;
import com.couchbase.client.java.Cluster;
import org.springframework.boot.actuate.health.AbstractReactiveHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CouchbaseReactiveHealthIndicator extends AbstractReactiveHealthIndicator {

    private final Cluster cluster;

    /**
     * Create a new {@link org.springframework.boot.actuate.couchbase.CouchbaseReactiveHealthIndicator} instance.
     * @param cluster the Couchbase cluster
     */
    public CouchbaseReactiveHealthIndicator(Cluster cluster) {
        super("Couchbase health check failed");
        this.cluster = cluster;
    }

    @Override
    protected Mono<Health> doHealthCheck(Health.Builder builder) {
        DiagnosticsResult  diagnostics = this.cluster.diagnostics();
        new CouchbaseHealth(diagnostics).applyTo(builder);
        return Mono.just(builder.build());
    }
}
