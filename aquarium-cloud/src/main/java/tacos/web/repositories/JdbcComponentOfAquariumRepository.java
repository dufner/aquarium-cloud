package tacos.web.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tacos.ComponentOFAquarium;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Repository
public class JdbcComponentOfAquariumRepository implements ComponentOfAquariumRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcComponentOfAquariumRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<ComponentOFAquarium> findAll() {
        return jdbcTemplate.query("select id, name, type from ComponentOFAquarium", this::mapRowToComponentOFAquarium);
    }

    @Override
    public Optional<ComponentOFAquarium> findById(String id) {
        List<ComponentOFAquarium> results = jdbcTemplate.query("select id, name, type from ComponentOFAquarium where id=?", this::mapRowToComponentOFAquarium, id);
        return results.size() == 0 ?
                Optional.empty() :
                Optional.of(results.get(0));
    }

    @Override
    public ComponentOFAquarium save(ComponentOFAquarium componentOFAquarium) {
        return null;
    }

    private ComponentOFAquarium mapRowToComponentOFAquarium(ResultSet row, int rowNum) throws SQLException {
        return new ComponentOFAquarium(
                row.getString("id"),
                row.getString("name"),
                ComponentOFAquarium.Type.valueOf(row.getString("type")));

    }
}