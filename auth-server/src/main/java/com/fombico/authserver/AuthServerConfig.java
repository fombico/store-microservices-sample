package com.fombico.authserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
public class AuthServerConfig implements AuthorizationServerConfigurer {

    @Value("${clientApi.id}")
    private String clientApiId;

    @Value("${clientApi.secret}")
    private String clientApiSecret;

    @Value("${clientApi.redirectUrl}")
    private String clientApiRedirectUrl;

    @Value("${clientBatch.id}")
    private String clientBatchId;

    @Value("${clientBatch.secret}")
    private String clientBatchSecret;

    private PasswordEncoder passwordEncoder;
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    private UserDetailsService userDetailsService;

    public AuthServerConfig(PasswordEncoder passwordEncoder,
                            JwtAccessTokenConverter jwtAccessTokenConverter,
                            UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientApiId)
                    .secret(passwordEncoder.encode(clientApiSecret))
                    .redirectUris(clientApiRedirectUrl)
                    .authorizedGrantTypes("authorization_code", "refresh_token")
                    .scopes("myscope")
                    .autoApprove(true)
                    .accessTokenValiditySeconds(5)
                    .refreshTokenValiditySeconds(60)
                .and()
                .withClient(clientBatchId)
                    .secret((passwordEncoder.encode(clientBatchSecret)))
                    .authorizedGrantTypes("client_credentials")
                    .scopes("myscope")
                    .autoApprove(true)
        ;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
        endpoints
                .authenticationManager(authenticationManager)
                // set user details service for verifying users via refresh tokens
                .userDetailsService(userDetailsService)
                .accessTokenConverter(jwtAccessTokenConverter);
    }

}
