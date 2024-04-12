package com.example.demo.util.security;

import com.iceteasoftwarebe.constant.SecurityConstants;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Implementation of {@link AuditorAware} based on Spring Security.
 *
 * @author duongduc
 * @version 1.0
 * @since 2024-04-08
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtils.getCurrentUserLogin().orElse(SecurityConstants.Account.SUPER_AMDIN));
    }
}
