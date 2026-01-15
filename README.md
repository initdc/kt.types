# kt.types

Bring the Rust [Result Option] types to Kotlin

## Installation

Add the dependency to your `build.gradle.kts`:

```gradle
dependencies {
    implementation("kt.types:lib:0.0.1")
}
```

## Usage

```kotlin
val s = Some(2)
val n = None<Int>()

val r = Ok<Int, String>(2)
val e = Err<Int, String>("error")
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