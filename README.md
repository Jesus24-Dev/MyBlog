# MyBlog

MyBlog is a web application developed with **Java Server Pages (JSP)** that allows users to create, view, and comment on posts. This project was created to practice the use of **Servlets** and **JPA (Java Persistence API)**. The application includes basic blog functionalities such as article creation, comments, and user profiles.

## Technologies Used

- **NetBeans**: Version 23 as the integrated development environment (IDE).
- **Java**: JDK version 23.
- **MySQL**: Database management system, using the `com.mysql:mysql-connector-j` connector.
- **Tomcat**: Application server version 10.1.34.
- **Jakarta EE**: Version 8, the successor to Java EE, for handling HTTP requests and data persistence.
- **JPA**: Implemented with EclipseLink 4.0.4.

## Persistence Model

### User

- **Email** (primary key)
- **Profile Id** (foreign key)
- **Nickname** (unique)
- **First Name**
- **Last Name**
- **Date of Birth**
- **Gender**

### Post

- **Post Id** (primary key)
- **Profile Id** (foreign key)
- **Description**
- **Publication Date**

### Comment

- **Comment Id** (primary key)
- **Post Id** (foreign key)
- **Profile Id** (foreign key)
- **Description**
- **Publication Date**

### Profile

- **Profile Id** (primary key)
- **Biography**
- **Photo**

## Pages and Functionalities

- **Sign Up or Log In**: Allows users to register or log in to the application.
- **Home**: Main page displaying posts from other users.
- **Search**: Enables users to search for specific profiles or posts.
- **Comments**: Displays comments made on a post.
- **Profile**: Shows the list of posts made by a user.
- **Error Page**: Displays common errors such as 404 (page not found) or 403 (forbidden access).

## Important Notes

- All necessary dependencies are managed in the project's `pom.xml` file.
- The database must be pre-configured, but it does not require manual table creation, as tables are generated automatically.

## Environment Setup

### 1. Database

- Create an empty schema in your MySQL database.

### 2. Database Connection

- Configure the database connection in NetBeans:
  1. Go to the **Services** tab in NetBeans.
  2. Create a new connection with your database credentials.

### 3. Tomcat Server

- Download Tomcat 10 from the [official site](https://tomcat.apache.org/download-10.cgi).
- Configure it as the server in NetBeans.

### 4. Dependencies

- Build the project and download all necessary dependencies:
  1. Right-click on the project in NetBeans.
  2. Select **"Build With Dependencies"**.

## Project Configuration

### persistence.xml

Configure the following properties in the file:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.2" xmlns="http://xmlns.jcp.org/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd">
  <persistence-unit name="MyBlogJPU" transaction-type="RESOURCE_LOCAL">
    <provider>org.eclipse.persistence.jpa.PersistenceProvider</provider>
    <class>com.jesus24dev.myblog.persistence.models.Comment</class>
    <class>com.jesus24dev.myblog.persistence.models.Post</class>
    <class>com.jesus24dev.myblog.persistence.models.Profile</class>
    <class>com.jesus24dev.myblog.persistence.models.User</class>
    <exclude-unlisted-classes>false</exclude-unlisted-classes>
    <properties>
      <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/DATABASENAME?serverTimezone=UTC"/>
      <property name="jakarta.persistence.jdbc.user" value="INSERT YOUR USER"/>
      <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
      <property name="jakarta.persistence.jdbc.password" value="INSERT YOUR PASSWORD"/>
      <property name="jakarta.persistence.schema-generation.database.action" value="create"/>
    </properties>
  </persistence-unit>
</persistence>
```

Ensure that the schema name, user, and password match your MySQL configuration.

### persistence/controllers/SeekerController.java File

```java

private String url = "jdbc:mysql://localhost:3306/DATABASENAME?serverTimezone=UTC";
private Properties props;

public SeekerController() {
    props = new Properties();
    props.setProperty("user", "YOUR USER");
    props.setProperty("password", "YOUR PASSWORD");
}
```

Ensure that the schema name, user, and password match your MySQL configuration.
