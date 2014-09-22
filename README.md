# StorySphere

This is an experiment rendering Photospheres with OpenGL and no proprietary bullshit.

# Building

#### Android Studio

File -> Open Project -> point at this directory.

#### Command Line

```bash
./gradlew assembleDebug
adb install -r ./storysphere/build/outputs/apk/storysphere-debug
```


# Issues

+ Currently full-resolution Photospheres have too many bits. Tested successfully with 4096x2048 images on a Nexus5.
+ The sensing of the initial phone position can be off, we should have some way to "reset" the camera perspective to dead center etc.


# License

 GPLv3. See LICENSE.md

 StorySphere includes the [Rajawali](https://github.com/MasDennis/Rajawali) 3D engine available under the Apache 2 license.