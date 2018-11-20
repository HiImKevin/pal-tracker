package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private String port;
    private String memoryLimit;
    private String instanceIndex;
    private String instanceAddress;

    public EnvController(@Value("${port:NOT SET}") String port,
                         @Value("${memory.limit:NOT SET}") String memoryLimit,
                         @Value("${cf.instance.index:NOT SET}") String instanceIndex,
                         @Value("${cf.instance.addr:NOT SET}") String instanceAddress) {
        this.instanceAddress = instanceAddress;
        this.instanceIndex = instanceIndex;
        this.memoryLimit = memoryLimit;
        this.port = port;
    }

    @GetMapping("/env")
    public Map getEnv() {
        Map<String, String> map = new HashMap<>();
        map.put("PORT", port);
        map.put("MEMORY_LIMIT", memoryLimit);
        map.put("CF_INSTANCE_INDEX", instanceIndex);
        map.put("CF_INSTANCE_ADDR", instanceAddress);
        return map;
    }
}
