package in.fourbits.foodie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import com.sap.cloud.security.xsuaa.XsuaaServiceConfiguration;
import com.sap.cloud.security.xsuaa.token.TokenAuthenticationConverter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.Jwt;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Slf4j
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
	XsuaaServiceConfiguration xsuaaServiceConfiguration;

    @Override
	public void configure(HttpSecurity http) throws Exception {
        log.error("################### in configAdapter");
        http.
		authorizeRequests().
        //antMatchers("/**").authenticated().
        antMatchers("/protected/**").hasAuthority("share").
        and().authorizeRequests().
        anyRequest().authenticated().
		//.and().authorizeRequests().
        //anyRequest().authenticated()
        //and().csrf().disable().
        and()
			.oauth2ResourceServer()
			.jwt()
			.jwtAuthenticationConverter(getJwtAuthenticationConverter());
    }
    
    Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter(){
		TokenAuthenticationConverter converter = new TokenAuthenticationConverter(xsuaaServiceConfiguration);
		converter.setLocalScopeAsAuthorities(true);
		return converter;
	}
    
}
