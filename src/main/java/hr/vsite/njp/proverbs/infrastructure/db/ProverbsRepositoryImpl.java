package hr.vsite.njp.proverbs.infrastructure.db;

import hr.vsite.njp.proverbs.domain.CustomProverbsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProverbsRepositoryImpl implements CustomProverbsRepository {
    private final JdbcTemplate jdbcTemplate;


    public ProverbsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Long randomId() {
        final String SQL = "SELECT id FROM proverb " +
                "OFFSET floor(random()*(select count(*) from proverb)) LIMIT 1";
        return jdbcTemplate.queryForObject(SQL, Long.class);
    }
}
