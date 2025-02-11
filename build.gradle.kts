plugins {
    id("java")
}

group = "github.devparkge"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // ✅ Spring Boot Web
    implementation("org.springframework.boot:spring-boot-starter-web:3.4.2")

    // ✅ MyBatis
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.4")

    // ✅ JSON Web Token (JWT)
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")

    // ✅ Lombok (컴파일 시에만 필요)
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

    // ✅ H2 Database (테스트용)
    runtimeOnly("com.h2database:h2:2.3.232")

    // ✅ JUnit 테스트
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}