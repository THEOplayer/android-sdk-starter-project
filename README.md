# THEOplayer Android SDK - Starter project

## License

This projects falls under the license as defined in https://github.com/THEOplayer/license-and-disclaimer.

## Getting Started

This Android project is an example how to integrate [THEOplayer](https://www.theoplayer.com) into an Android app.

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

* Download and install Android Studio. 
* Obtain a THEOplayer [Android SDK](https://portal.theoplayer.com/) license. 

* If you don't have a license yet, contact your sales contact or email us at [support@theoplayer.com](mailto:support@theoplayer.com).

### Include THEOplayer Android SDK in the project

In the `settings.gradle` file, add `jitpack` as a Maven source:

```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven { url 'https://jitpack.io' }
    }
}
```

In the `app/build.gradle` file, use the proper name of your SDK:

```
dependencies {
    implementation "com.theoplayer.theoplayer-sdk-android:unified:$sdkVersion"
}
```

## Build the project

### In Android Studio

Just open the project, let Android Studio to install the dependencies and build the project.

### With Gradle

In a terminal navigate to the project folder and run:

```
./gradle assembleDebug
```

The generated APK file (what you need to install on your device) will be available in the ```app/build/outputs/apk/``` folder.
