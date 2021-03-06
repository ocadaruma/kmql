package kmql;

import static java.util.Collections.emptyList;

import java.sql.Connection;
import java.util.Collection;

import org.apache.kafka.clients.admin.AdminClient;

/**
 * An interface of a kmql table implementation.
 */
public interface Table {
    /**
     * Name of the table.
     * @return name of the table.
     */
    String name();

    /**
     * Optionally declared list of table names that this table is depending on to construct the table.
     * The returned tables are prepared before the {@link #prepare(Connection, AdminClient)} method of this
     * table is called.
     * @return list of table names to depend on.
     */
    default Collection<String> dependencyTables() {
        return emptyList();
    }

    /**
     * Prepare this table on the given {@link Connection}. This process includes both of creating a table and
     * feeding in its data by obtaining it from the given {@link AdminClient}.
     * At the time of this method call, absence of the target table is guaranteed.
     * @param connection a JDBC {@link Connection}.
     * @param adminClient a Kafka {@link AdminClient}.
     * @throws Exception at any errors.
     */
    void prepare(Connection connection, AdminClient adminClient) throws Exception;
}
