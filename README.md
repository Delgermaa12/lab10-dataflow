# Лаб 10: Өгөгдлийн урсгалын тест (DU хосууд)

**Нэр:** О.Дэлгэрмаа
**Оюутны код:** B232270024

## Орчин

```
delgermaa@debian:~/starter/lab10-dataflow$ java -version 2>&1
openjdk version "17.0.19" 2026-04-21
OpenJDK Runtime Environment (build 17.0.19+10-1-deb12u2-Debian)
OpenJDK 64-Bit Server VM (build 17.0.19+10-1-deb12u2-Debian, mixedmode, sharing)


delgermaa@debian:~/starter/lab10-dataflow$ mvn -version
Apache Maven 3.9.9
Maven home: /usr/share/maven
Java version: 17.0.19, vendor: Debian, runtime: /usr/lib/jvm/java-17-openjdk-amd64
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "6.12.116+deb13-amd64", arch: "amd64", family: "unix"
```

## Хэсэг A: gcd

### DU хос (файлын мөрийн дугаараар)

Def/use: 13 (def x, y), 15 (p-use y), 16 (def tmp; c-use x, y),
17 (def x; c-use y), 18 (def y; c-use tmp), 20 (c-use x).

| Хувьсагч | Def | Use | Төрөл | Def-clear зам | Тестийн ID | Хамрагдсан |
|---|---|---|---|---|---|---|
| x | 13 | 16 | c | 13-15-16 | gcdTwoIterations | тийм |
| x | 13 | 20 | c | 13-15-20 | gcdZeroIterations | тийм |
| x | 17 | 16 | c | 17-18-15-16 | gcdTwoIterations | тийм |
| x | 17 | 20 | c | 17-18-15-20 | gcdTwoIterations | тийм |
| y | 13 | 15 | p T | 13-15-16 | gcdTwoIterations | тийм |
| y | 13 | 15 | p F | 13-15-20 | gcdZeroIterations | тийм |
| y | 13 | 16 | c | 13-15-16 | gcdTwoIterations | тийм |
| y | 13 | 17 | c | 13-15-16-17 | gcdTwoIterations | тийм |
| y | 18 | 15 | p T | 18-15-16 | gcdTwoIterations | тийм |
| y | 18 | 15 | p F | 18-15-20 | gcdTwoIterations | тийм |
| y | 18 | 16 | c | 18-15-16 | gcdTwoIterations | тийм |
| y | 18 | 17 | c | 18-15-16-17 | gcdTwoIterations | тийм |
| tmp | 16 | 18 | c | 16-17-18 | gcdTwoIterations | тийм |

