package dev.kukim.issues;

import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class MysqlTestContainer {

	private static final String MYSQL_VERSION = "mysql:8";
	private static final String DATABASE_NAME = "testDB";

	@Container
	public static final MySQLContainer MYSQL_CONTAINER = new MySQLContainer(MYSQL_VERSION)
		.withDatabaseName(DATABASE_NAME);

}
