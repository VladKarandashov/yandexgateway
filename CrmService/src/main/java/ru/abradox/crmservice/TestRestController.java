package ru.abradox.crmservice;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.util.UriEncoder;

@RestController
public class TestRestController {

    @GetMapping(value = "/whoami", produces = MediaType.APPLICATION_JSON_VALUE)
    public String whoami(@RequestHeader("user") String userInfo) {
        return UriEncoder.decode(userInfo);
    }

    @PostMapping(value = "/whoami", produces = MediaType.APPLICATION_JSON_VALUE)
    public String whoamiPOST(@RequestHeader("user") String userInfo) {
        return UriEncoder.decode(userInfo);
    }
}
