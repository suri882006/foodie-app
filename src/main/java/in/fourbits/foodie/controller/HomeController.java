package in.fourbits.foodie.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import in.fourbits.foodie.service.HealthCheckService;

@RestController
public class HomeController {

    @Autowired
    HealthCheckService healthCheckService; 

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value="/health", method=RequestMethod.GET)
    public String test() {
        Optional<String> dbStatus = healthCheckService.checkDBConnectivity();
        if(dbStatus.isPresent())
            return "API and DB health check status OK. Timestamp returned from DB - "+dbStatus.get();
        else 
            return "Problem connecting to DB";
    }

    @RequestMapping(value="/protected/share", method=RequestMethod.GET)
    public String share() {
        return "This is a protected resource with share role";
    }

     @GetMapping("/tokens")
    public void getTokens(final HttpServletRequest request, final HttpServletResponse response ) throws IOException {
        // https://jwt.io/
        // Decode the bearer token
        response.setContentType("text/plain");
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);

            response.getOutputStream().println(key+" : "+value);
        }
    }


}
