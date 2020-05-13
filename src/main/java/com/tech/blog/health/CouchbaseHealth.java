package com.tech.blog.health;

import com.couchbase.client.core.diagnostics.DiagnosticsResult;
import com.couchbase.client.core.diagnostics.EndpointDiagnostics;
import com.couchbase.client.core.endpoint.EndpointState;
import com.couchbase.client.core.service.ServiceType;
import org.springframework.boot.actuate.health.Health;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouchbaseHealth {
    private final DiagnosticsResult diagnostics;

    CouchbaseHealth(DiagnosticsResult diagnostics) {
        this.diagnostics = diagnostics;
    }

    void applyTo(Health.Builder builder) {
        builder = isCouchbaseUp(this.diagnostics) ? builder.up() : builder.down();
        builder.withDetail("cluster_state", this.diagnostics.state().name());
        builder.withDetail("sdk", this.diagnostics.sdk());
        for (Map.Entry<ServiceType, List<EndpointDiagnostics>> entry : this.diagnostics.endpoints().entrySet()) {
            builder.withDetail(entry.getKey().name(), entry.getValue().stream()
            .map(this::describe));
        }
    }

    private boolean isCouchbaseUp(DiagnosticsResult diagnostics) {
        return diagnostics.endpoints().entrySet().stream()
                .allMatch(entry -> entry.getValue()
                        .stream()
                        .allMatch(endpoint -> endpoint.state() == EndpointState.CONNECTED));
    }

    private Map<String, Object> describe(EndpointDiagnostics endpointHealth) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", endpointHealth.id());
        map.put("lastActivity", endpointHealth.lastActivity());
        map.put("local", endpointHealth.local());
        map.put("remote", endpointHealth.remote());
        map.put("state", endpointHealth.state());
        map.put("type", endpointHealth.type());
        return map;
    }

}
