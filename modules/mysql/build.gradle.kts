plugins {
    kotlin("jvm") version "2.0.0"
    id("app.cash.sqldelight") version "2.0.1"
}

dependencies {
    implementation("app.cash.sqldelight:jdbc-driver:2.0.1")
    implementation("mysql:mysql-connector-java:8.0.33")

    implementation("org.flywaydb:flyway-core:10.16.0")
    implementation("org.flywaydb:flyway-mysql:10.16.0")
}

tasks {
    compileKotlin {
        dependsOn("generateMainMysqlDBInterface")
        dependsOn("generateMainMysqlDBMigrations")
    }

    processResources {
        dependsOn("generateMainMysqlDBMigrations")
    }
}

sqldelight {
    databases {
        create("MysqlDB") {
            packageName.set("com.mtlarva.sandbox.mysql")
            dialect("app.cash.sqldelight:mysql-dialect:2.0.1")

            // useful if using flyway
            deriveSchemaFromMigrations = true
            migrationOutputDirectory = file("$projectDir/src/main/resources/generated/migrations/mysql")

            // generate suspending query methods
            // But it will use https://r2dbc.io/drivers/ instead of jdbc
            // generateAsync = true

            // set the source directory for the .sq & .sqm files
            // srcDirs.setFrom("src/main/resources/database")
        }
    }
}
