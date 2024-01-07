package ru.abradox.yandexgateway;

import net.minidev.json.JSONObject;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.util.UriEncoder;

@Component
public class AddUserHeaderFilter extends AbstractGatewayFilterFactory<AddUserHeaderFilter.Config> {

    public AddUserHeaderFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> exchange.getPrincipal()
                .flatMap(authentication -> {
                    // достаём данные пользователя из контекста (они подтянулись из yandex)
                    var user = ((OAuth2AuthenticationToken) authentication).getPrincipal();
                    var attributes = user.getAttributes();
                    var userInfo = new JSONObject(attributes).toString();
                    // кодируем с помощью UriEncoder для нормальной работы с русскими символами
                    var userInfoEncoded = UriEncoder.encode(userInfo);

                    // засовываем данные пользователя в хедер, чтобы микросервис знал от кого запрос
                    var request = exchange.getRequest();
                    var httpHeaders = HttpHeaders.writableHttpHeaders(request.getHeaders());
                    httpHeaders.add("user", userInfoEncoded);
                    return chain.filter(exchange);
                });
    }

    public static class Config {
        // Здесь можно добавить конфигурационные параметры, если необходимо
    }
}