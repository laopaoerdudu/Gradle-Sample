## implementation

可以显著缩短构建时间，如果 implementation 依赖项更改了其 API，Gradle 只会重新编译该依赖项以及直接依赖于它的模块。大多数应用和测试模块都应使用此配置。

## compileOnly (现已废弃）

如果您创建的 Android 模块时在编译期间需要相应依赖项，但它在运行时可有可无，此配置会很有用。

>也就是说， 只会将依赖项添加到编译类路径，不会将其添加到构建输出。

## runtimeOnly （现已弃用）

Gradle 只会将依赖项添加到 build 输出，以便在运行时使用。也就是说，不会将其添加到编译类路径。 

## annotationProcessor

添加 注解处理器的库 依赖，使用此配置可以将编译类路径与注释处理器类路径分开，从而提高构建性能。

>注意：Kotlin 项目应使用 `kapt` 声明注解处理器依赖项。

```

./gradlew lint

```


