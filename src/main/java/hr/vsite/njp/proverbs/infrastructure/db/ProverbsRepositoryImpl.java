package hr.vsite.njp.proverbs.infrastructure.db;

import com.querydsl.jpa.impl.JPAQuery;
import hr.vsite.njp.proverbs.domain.CustomProverbsRepository;
import hr.vsite.njp.proverbs.domain.Proverb;
import hr.vsite.njp.proverbs.domain.QProverb;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProverbsRepositoryImpl implements CustomProverbsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final EntityManager em;
    private final DataSource dataSource;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final BeanPropertyRowMapper<Proverb> proverbMapper;

    public ProverbsRepositoryImpl(JdbcTemplate jdbcTemplate, EntityManager em, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.em = em;
        this.dataSource = dataSource;
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        proverbMapper = new BeanPropertyRowMapper<>();
        proverbMapper.setMappedClass(Proverb.class);
    }

    @Override
    public Long randomId() {
        final String SQL = "SELECT id FROM proverb " +
                "OFFSET floor(random()*(select count(*) from proverb)) LIMIT 1";
        return jdbcTemplate.queryForObject(SQL, Long.class);

    }

    @Override
    public Optional<Proverb> randomProverb(Integer i) {
        final String SQL = "SELECT p.id as id, p.proverb as proverb FROM proverb p " +
                "OFFSET floor(random()*(select count(*) from proverb)) LIMIT 1";
        try {
            switch (i) {
                case 1:
                    return Optional.of(jdbcTemplate.queryForObject(SQL, new ProverbRowMapper()));
                case 2:
                    for (Field f : Proverb.class.getDeclaredFields()) {
                        System.out.println(f.getName() + " - " + f.getType());
                    }
                    return Optional.of(jdbcTemplate.queryForObject(SQL, proverbMapper));
            }
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }


    @Override
    public List<Proverb> findCustomBy(String text, Integer type) {
        // select * from proverb where proverb like 'text'
        TypedQuery<Proverb> typedQuery;
        Query nativeQuery;
        Session session;
        switch (type) {
            case 0:
                //Ovo je puno bolje nego JPQL case 1 i 2
                JPAQuery<Proverb> queryDSL = new JPAQuery<>(em);
                return queryDSL.from(QProverb.proverb1)
                        .where(QProverb.proverb1.proverb.contains(text))
                        .fetch();
            case 1:
                //JPQL koji radi sa classama a ne tablicama
                typedQuery =
                        em.createQuery("select b from Proverb b " +
                                "where b.proverb like ?1", Proverb.class);
                typedQuery.setParameter(1, text);
                return typedQuery.getResultList();
            case 2:
                //JPQL koji radi sa classama a ne tablicama
                typedQuery =
                        em.createQuery("select b from Proverb b " +
                                "where b.proverb like :name", Proverb.class);
                typedQuery.setParameter("name", text);
                return typedQuery.getResultList();
            case 3:
                //nativni SQL sa entity managerom i tu koristimo nazive tablica
                nativeQuery = em.createNativeQuery("select * from proverb " +
                        " where proverb like ?", Proverb.class);
                nativeQuery.setParameter(1, text);
                return nativeQuery.getResultList();
            case 4:
                //nativni SQL sa entity managerom i tu koristimo nazive tablica
                nativeQuery = em.createNativeQuery("select * from proverb " +
                        " where proverb like :name", Proverb.class);
                nativeQuery.setParameter("name", text);
                return nativeQuery.getResultList();
            case 5:
                session = em.unwrap(Session.class);
                NativeQuery<Proverb> hnq = session.createNativeQuery("select p.* from proverb p " +
                        " where proverb like ?", Proverb.class);
                hnq.setParameter(1, text);
                return hnq.getResultList();
            case 6:
                session = em.unwrap(Session.class);
                NativeQuery<Proverb> sqlQuerry =
                        session.createSQLQuery("select {a.*} from proverb a where proverb like ?")
                                .addEntity("a", Proverb.class).setParameter(1, text);
                return sqlQuerry.getResultList();
            case 7:
                return jdbcTemplate.query("select * from proverb where proverb like ?", new ProverbRowMapper(), text);
            case 8:
                Map<String, Object> params = new HashMap<>();
                params.put("name", text);
                return namedParameterJdbcTemplate.query("select * from proverb where proverb in (:name)",
                        params,
                        new ProverbRowMapper());

        }
        return null;
    }

    private static class ProverbRowMapper implements RowMapper<Proverb> {

        @Override
        public Proverb mapRow(ResultSet rs, int rowNum) throws SQLException {
            Proverb proverb = new Proverb();
            proverb.setId(rs.getLong(1));
            proverb.setProverb(rs.getString("proverb"));
            return proverb;
        }
    }
}
