package in.fourbits.foodie.service;

import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Optional<String> checkDBConnectivity() {
        try {
        String curTime = jdbcTemplate.queryForObject("select CURRENT_TIMESTAMP as curtime from dummy", new RowMapper<String>() {
            @Override
            public String mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("curtime");
            }
        });
        return Optional.of(curTime);
    } catch (Exception e) {
        e.printStackTrace();
        return Optional.of(null);
    }
        
    }
}
