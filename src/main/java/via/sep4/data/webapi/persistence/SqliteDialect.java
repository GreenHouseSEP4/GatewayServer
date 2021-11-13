package via.sep4.data.webapi.persistence;

import org.hibernate.dialect.Dialect;
import org.springframework.stereotype.Repository;

import java.sql.Types;

@Repository
public class SqliteDialect extends Dialect {
    public SqliteDialect() {
        registerColumnType(Types.INTEGER, "id");
        registerColumnType(Types.VARCHAR, "value");
    }
}
