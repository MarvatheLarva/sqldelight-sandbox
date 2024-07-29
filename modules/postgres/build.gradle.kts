plugins {
    kotlin("jvm") version "2.0.0"
    id("app.cash.sqldelight") version "2.0.1"
}

dependencies {
    implementation("app.cash.sqldelight:jdbc-driver:2.0.1")
    implementation("org.postgresql:postgresql:42.7.3")

    implementation("org.flywaydb:flyway-core:10.16.0")
    implementation("org.flywaydb:flyway-database-postgresql:10.16.0")
}

tasks {
    compileKotlin {
        dependsOn("generateMainPostgresDBInterface")
        dependsOn("generateMainPostgresDBMigrations")
    }

    processResources {
        dependsOn("generateMainPostgresDBMigrations")
    }
}

sqldelight {
    databases {
        create("PostgresDB") {
            packageName.set("com.mtlarva.sandbox.postgres")
            dialect("app.cash.sqldelight:postgresql-dialect:2.0.1")

            // useful if using flyway
            deriveSchemaFromMigrations = true
            migrationOutputDirectory = file("$projectDir/src/main/resources/generated/migrations/postgres")

            // generate suspending query methods
            // But it will use https://r2dbc.io/drivers/ instead of jdbc
            // generateAsync = true

            // set the source directory for the .sq & .sqm files
            // srcDirs.setFrom("src/main/resources/database")
        }
    }
}
