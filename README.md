# Device Manager

Application created to manage devices. 

This application is a test for the company atSistemas.

## Used libraries

Functional programing with VAVR 
```xml
<dependency>
    <groupId>io.vavr</groupId>
    <artifactId>vavr</artifactId>
</dependency>
```

Getter, setter, builder annotation with Lombok
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <scope>provided</scope>
 </dependency>
```

Suite test
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>${junit-jupiter.version}</version>
</dependency>
<dependency>
    <groupId>org.assertj</groupId>
    <artifactId>assertj-core</artifactId>
    <version>${assertj-core.version}</version>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <version>${mockito.version}</version>
    <scope>test</scope>
</dependency>
```


## Run test
```bash
mvn test
```

## Usage
```bash
.\run.sh
```


