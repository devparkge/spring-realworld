plugins {
    id("java")
}

group = "github.devparkge"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    runtimeOnly("com.h2database:h2:2.3.232")
    implementation("org.springframework.boot:spring-boot-starter-web:3.4.2")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.4")
    compileOnly("org.projectlombok:lombok:1.18.36")
}

tasks.test {
    useJUnitPlatform()
}