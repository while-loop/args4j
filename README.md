args4j
=======

<p align="center">
  <img src="https://github.com/while-loop/args4j/blob/master/resources/gopherwalk.png">
  <br><br><br>
  <a href="https://travis-ci.org/while-loop/args4j"><img src="https://img.shields.io/travis/while-loop/args4j.svg?style=flat-square"></a>
  <a href="https://github.com/while-loop/args4j/releases"><img src="https://img.shields.io/github/release/while-loop/args4j.svg?style=flat-square"></a>
  <a href="https://coveralls.io/github/while-loop/args4j"><img src="https://img.shields.io/coveralls/while-loop/args4j.svg?style=flat-square"></a>
  <a href="LICENSE"><img src="https://img.shields.io/github/license/mashape/apistatus.svg?style=flat-square"></a>
</p>


Features
--------

- Argument order agnostic
- Java annotations

Download
--------

Grab via Maven:
```xml
<dependency>
  <groupId>com.github.whileloop</groupId>
  <artifactId>args4j</artifactId>
  <version>1.0.0</version>
</dependency>
```

or Gradle:
```groovy
compile 'com.github.whileloop:args4j:1.0.0'
```


Usage
-----

```java
@Program(
  name = "IFaceDump",
  usage = "[options...] -i interface  "
)
public class IFaceDump {
    @Option(shortOpt = 'i', longOpt = "iface", required = true)
    private static String iface = null;

    @Option(shortOpt = 'v', longOpt = "verbose")
    private static boolean verbose = false;


    public static void main(String[] args){

    }
}

```

Running:
```bash
java Example -v -i enp3s0 1.3.3.7
```

Support
-------

```bash
java Example -i enp3s0 1.3.3.7
java Example -vr6 --net=test_net -p 80
```

Future features/TODO
--------------------

- multi char short opt
- multi command support (i.e. -vvv for more verbose output)
- sub commands (Extend Converter<> to handle your own sub command?)
- custom short/long opt flags
- custom longOpt opt/value separator


Changelog
---------

The format is based on [Keep a Changelog](http://keepachangelog.com/)
and this project adheres to [Semantic Versioning](http://semver.org/).

[CHANGELOG.md](CHANGELOG.md)

License
-------
args4j is licensed under the MIT license. See [LICENSE](LICENSE) for details.

Author
------

Anthony Alves
