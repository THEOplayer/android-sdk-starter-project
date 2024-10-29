# [Archived] THEOplayer Android SDK - Starter project

> [!CAUTION]
> This sample project uses older versions of THEOplayer and is no longer maintained. Visit [THEOplayer/samples-android-sdk](https://github.com/THEOplayer/samples-android-sdk) for samples with the most recent THEOplayer SDK.

## License

This projects falls under the license as defined in https://github.com/THEOplayer/license-and-disclaimer.

## Getting Started

This Android project is an example how to integrate [THEOplayer](https://www.theoplayer.com) into an Android app.
There is a step-by-step [guide](https://support.theoplayer.com/hc/en-us/articles/360000779729-Android-Starter-Guide) for this project, we suggest you to follow it for better insights.

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

* Download and install Android Studio. 
* Obtain a THEOplayer [Android SDK](https://support.theoplayer.com/hc/en-us/categories/115000161065-SDK) license. 
If you don't have a license yet, contact your sales contact or email us at [support@theoplayer.com](mailto:support@theoplayer.com).

### Include THEOplayer Android SDK in the project

Once you obtained the license, you need copy it into the ``` app/libs ``` folder of the project.

### Link your SDK file in ```build.gradle```

In the module-level ```build.gradle``` file (```app/build.gradle```) use the proper name of your SDK file

```
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    implementation(name:'FILENAME_OF_YOUR_SDK_FILE', ext:'aar')

    implementation 'com.google.code.gson:gson:2.8.2'
    implementation 'com.android.support:appcompat-v7:27.1.1'
    implementation 'com.android.support.constraint:constraint-layout:1.1.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
}
```

For example if the SDK file is called ```theoplayer.aar```, then your ```build.gradle``` file should look like this :

```
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])

    implementation(name:'theoplayer', ext:'aar')

    implementation 'com.google.code.gson:gson:2.8.2'
    implementation 'com.android.support:appcompat-v7:27.1.1'
    implementation 'com.android.support.constraint:constraint-layout:1.1.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
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
