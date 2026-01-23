# kt.types

Bring the Rust [Result Option] types to Kotlin

## Installation

Add the dependency to your `build.gradle.kts`:

```gradle
dependencies {
    implementation("io.github.initdc:types:0.1.0")
}
```

## Usage

```kotlin
import kt.types.*

val s = some(2)
val n = none<Int>()

val r = ok<Int, String>(2)
val e = err<Int, String>("error")
```

## Development

TODO: Write development instructions here

## Contributing

1. Fork it (<https://github.com/initdc/kt.types/fork>)
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create a new Pull Request

## Contributors

- [initdc](https://github.com/initdc) - creator and maintainer