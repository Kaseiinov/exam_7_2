package kg.attractor.exam_7.dao;

import kg.attractor.exam_7.mapper.CurrencyMapper;
import kg.attractor.exam_7.model.Currency;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CurrencyDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<Currency> findById(String currency) {
        String sql = "select * from currencies where lower(currency) = lower(?)";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new CurrencyMapper(), currency));
    }
}
