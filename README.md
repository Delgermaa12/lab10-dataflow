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

`gcd(7, 0)` нь давталтыг огт оруулахгүй тул анхны `x`-ийн 13->20,
анхны `y`-ийн 13->15(F) хосуудыг дангаараа хамарна. `gcd(12, 8)` нь
хоёр давталттай бөгөөд 17, 18-р мөрийн дахин тодорхойлолтуудаас эхэлсэн
хосуудыг хамруулна.

## Хэсэг B: bonus

### CFG ба def/use жагсаалт

CFG-г [cfg-bonus.png](./cfg-bonus.png) болон [cfg-bonus.dot](./cfg-bonus.dot)-д
хадгалсан. `attendance` ба `threshold` нь 31-р мөрийн параметрийн definition;
32-р мөрөнд хоёулаа predicate-use хийнэ. 35, 36, 37-р мөрүүд `present`,
`streak`, `best`-ийг тодорхойлно. 38-р мөрөнд `attendance` predicate-use,
`a`-ийн definition; 39-р мөрөнд `a` predicate-use байна.

40-р мөрөнд `present` c-use ба дахин definition, 41-р мөрөнд `streak` c-use
ба дахин definition, 42-р мөрөнд `streak` болон `best` predicate-use,
43-р мөрөнд `streak` c-use ба `best` definition байна. 46-р мөрийн
`streak = 0` нь өмнөх streak definition-үүдийг kill хийнэ. 49-р мөрөнд
`score` definition, 50-р мөрөнд `present` ба `threshold` predicate-use,
51-р мөрөнд `score` definition, 53-р мөрөнд `best` predicate-use,
54-р мөрөнд `score` c-use ба definition, 56-р мөрөнд `score` c-use байна.

### ALL-DEFS шат

ALL-DEFS-д 2 тест хэрэгтэй. B1 нь алдаатай өдөр болон тав ба түүнээс урт
дарааллыг хамруулж, `present`, `streak`, `best`, хоёр `score` definition-ийн
ашиглалтыг хүргэнэ. B2 нь хоосон массив ба threshold=0 нөхцлөөр 51-р мөрийн
score definition-ийг 56-р мөр хүртэл хүргэнэ. Нэг гүйцэтгэлд 51-р мөрийн
score definition-ээс гадна 49-р мөрийн definition-ийг use-д хүргэх боломжгүй
тул хоёр тестээс цөөнөөр бүх definition-ийг use-д хүргэж чадахгүй.

ALL-DEFS шатны `mvn verify` гаралт `results/mvn-verify-alldefs.txt`,
JaCoCo CSV нь `results/jacoco-alldefs.csv` файлд байна.
