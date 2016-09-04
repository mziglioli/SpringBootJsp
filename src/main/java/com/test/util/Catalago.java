package com.test.util;

public class Catalago {

	// server configuration
	public static final String SERVER = "http://localhost";

	// DB configuration
	public static final String DB_NAME = "test";
	public static final String DB_USERNAME = "root";
	public static final String DB_PASSWORD = "marcelo12";
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME + "?useUnicode=true";

	// hibernate confi
	public static final String DB_DDL = "hibernate.hbm2ddl.auto";
	public static final String DB_DDL_VALUE = "update";
	public static final String DB_DIALECT = "hibernate.dialect";
	public static final String DB_DIALECT_VALUE = "org.hibernate.dialect.MySQL5Dialect";
	public static final String DB_SHOW_SQL = "show_sql";
	public static final String DB_SHOW_SQL_VALUE = "true";

	// ROLES
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";
	public static final String HAS_ROLE_USER = "hasRole('ROLE_USER')";

	// LOGIN USER PARAMETER
	public static final String LOGIN_PARAM_USERNAME = "username";
	public static final String LOGIN_PARAM_PASSWORD = "password";

	// URL main pages
	public static final String URL_BASE = "/";
	public static final String URL_PUBLIC_BASE = "/public/";
	public static final String URL_PUBLIC_ALL_BASE = "/public/**";
	public static final String URL_LOGIN = "/public/login";
	public static final String URL_HOME = "/public/home";
	public static final String URL_DENIED = "/public/denied";

	public static final String URL_ID = "/{id}";
	public static final String URL_NEW = "/new";
	public static final String URL_EDIT = "/edit";

	public static final String URL_USER = "/user";

}