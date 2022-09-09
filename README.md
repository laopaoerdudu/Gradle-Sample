### substitute 

Gradle 依赖替换,在项目开发过程中，不可避免的需要引入一些第三方库，而不同的第三方库之间，可能存在一些依赖关系。例如：你依赖了库 A 与 B，而同时 B 也依赖于 A。
这样就可能存在这种情况：你依赖的 A 的版本与 B 中依赖的 A 的版本不同。同时可以把依赖直接变更为 project 的形式。

```
dependencies {
    configurations.all {
        resolutionStrategy.dependencySubstitution {
            substitute module("org.utils:api") because "we work with the unreleased development version" with project(":api")
            substitute module("org.utils:util:2.5") with project(":util")
        }
    }
    ...
}
```